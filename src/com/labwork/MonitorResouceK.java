package com.labwork;

import static com.labwork.Main.P;

public class MonitorResouceK {

    private int[] K;
    private int a;

    public MonitorResouceK() {
        this.K = new int[P];
        this.a = 0;
    }

    public synchronized int getK() {
        if (a == 0) {
            for (int i = 0; i < P; i++) {
                a += K[i];
            }
        }
        return a;
    }

    public synchronized void setK(int a, int tid) {
        this.K[tid - 1] = a;
    }
}