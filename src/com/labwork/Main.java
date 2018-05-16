package com.labwork;

import com.coursework.MonitorSynchronization;
import com.coursework.Utils;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Lab Work _ 6
 * Author : Uliana Stefanishyna
 * Group : IO-52
 * Task : A = (B * C) * E + d * T * (MO * MT)
 * 1 - d, MO
 * 2 - E
 * 3 - C
 * 4 - A, B, MT
 */

public class Main {

    static final int P = 4;
    static final int N = 1000;
    private static final int H = N / P;

    private int[][] MO;
    private int[] T, B, E;
    private AtomicInteger d;
    private AtomicInteger count;

    private MonitorResouceC monitorResouceC = new MonitorResouceC();
    private MonitorResourceMT monitorResourceMT = new MonitorResourceMT();
    private MonitorResourceMR monitorResourceMR = new MonitorResourceMR();
    private MonitorSynchronization monitorSynchronization = new MonitorSynchronization();

    private Main() {
        this.MO = new int[N][N];
        this.B = new int[N];
        this.E = new int[N];
        this.T = new int[N];
        this.d = new AtomicInteger(0);
        this.count = new AtomicInteger(1);

        for (int i = 0; i < P; i++) {
            ThreadFunction threadFunction = new ThreadFunction();
            threadFunction.start();
        }
    }

    class ThreadFunction extends Thread {

        int tid;
        Utils utils = new Utils(N);

        public ThreadFunction() {
            this.tid = count.getAndIncrement();
        }

        @Override
        public void run() {
            System.out.println("thread #" + tid + " has started");

            final int Hip1 = tid * H;
            final int Hi = (tid - 1) * H;

            switch (tid) {
                case 1:
                    d.getAndSet(1);
                    MO = utils.fillMatrixBy(1);
                    break;
                case 2:
                    E = utils.fillVectorBy(1);
                    break;
                case 3:
                    int[] C = utils.fillVectorBy(1);
                    monitorResouceC.setC(C);
                    monitorSynchronization.signalByInput();
                    break;
                case 4:
                    B = utils.fillVectorBy(1);
                    int[][] MT = utils.fillMatrixBy(1);
                    monitorResourceMT.setMX(MT);
                    monitorSynchronization.signalByInput();
                    break;
            }

            monitorSynchronization.waitByInput();

            //copy
            int[][] MTi = monitorResourceMT.getMX();

            int[][] MRi = utils.multMatrixMatrixValue(MO, MTi, d.get(), Hi, Hip1);

            monitorResourceMR.setMR(MRi, Hi, Hip1);

            int[] Ci = monitorResouceC.getC();

            int[] E = utils.multVectorVector(B, Ci, Hi, Hip1);



        }
    }

    public static void main(String[] args) {
        new Main();

    }
}

