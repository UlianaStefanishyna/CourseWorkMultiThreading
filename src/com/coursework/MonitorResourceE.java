package com.coursework;

import static com.coursework.Main.N;

public class MonitorResourceE {

    private int[] E;

    public MonitorResourceE(){
        this.E = new int[N];
    }

    public synchronized int[] getE() {
        return E;
    }

    public synchronized void setE(int[] e) {
        E = e;
    }
}
