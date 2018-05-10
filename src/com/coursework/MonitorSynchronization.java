package com.coursework;

import static com.coursework.Main.P;

public class MonitorSynchronization {

    private int F1;
    private int F2;
    private int F3;

    public MonitorSynchronization() {
        this.F1 = 0;
        this.F2 = 0;
        this.F3 = 0;
    }

    public synchronized void signalByInput() {
        F1++;

        if (F1 == 2) {
            notifyAll();
        }
    }

    public synchronized void signalByMin() {
        F2++;

        if (F2 == P) {
            notifyAll();
        } else {
            waitThread();
        }
    }

    public synchronized void signalByEndCalc() {
        F3++;

        if (F3 == P) {
            notifyAll();
        } else {
            waitThread();
        }
    }

    public synchronized void waitByInput() {
        while (F1 < 2) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void waitThread(){
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
