package com.labwork;

import static com.labwork.Main.N;

public class MonitorResourceMT {

    int[][] MX;

    public MonitorResourceMT() {
        this.MX = new int[N][N];
    }

    public synchronized int[][] getMX() {
        return MX;
    }

    public synchronized void setMX(int[][] MX) {
        this.MX = MX;
    }
}
