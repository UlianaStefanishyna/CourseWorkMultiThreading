package com.coursework;


/**
 * Course Work _ 2
 * Author : Uliana Stefanishyna
 * Group : IO-52
 * Task : MR = MB * min(E * MM) + MO * (MS * MR) * d
 */

public class Utils {

    private static int N;
    private int COUNT = 10;

    public Utils(int N) {
        this.N = N;
    }

    public synchronized void printVector(int[] A) {
        if (N < COUNT)
            for (int i = 0; i < N; i++) {
                System.out.print(A[i] + " ");
            }
        System.out.println();
    }

    public int[][] fillMatrixBy(int value) {
        int[][] MA = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                MA[i][j] = value;
            }
        }
        return MA;
    }

    public synchronized void printMatrix(int[][] MA) {
        if (N > 9)
            return;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(MA[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public int[] fillVectorBy(int value) {
        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = value;
        }
        return A;
    }

    public int multVectorVector(int[] A, int[] B, int Hi, int Hip1) {
        int d = 0;
        for (int i = Hi; i < Hip1; i++) {
            d += A[i] * B[i];
        }
        return d;
    }

    public int[][] multMatrixMatrix(int[][] MA, int[][] MB, int Hi, int Hip1) {
        int[][] MR = new int[N][N];
        int t = 0;
        for (int i = Hi; i < Hip1; i++) {
            for (int j = 0; j < N; j++) {
                t = 0;
                for (int k = 0; k < N; k++) {
                    t += MA[i][k] * MB[k][j];
                }
                MR[i][j] = t;
            }
        }
        return MR;
    }

    public int[] multVectorMatrix(int[] A, int[][] MA, int Hi, int Hip1) {

        int[] D = new int[N];
        for (int i = Hi; i < Hip1; i++) {
            D[i] = 0;
            for (int j = 0; j < N; j++) {
                D[i] += A[i] * MA[j][i];
            }
        }
        return D;
    }

    public int[][] multValueMatrix(int a, int[][] MA, int Hi, int Hip1) {
        for (int i = Hi; i < Hip1; i++) {
            for (int j = 0; j < N; j++) {
                MA[i][j] *= a;
            }
        }
        return MA;
    }

    public int[][] addMatrixMatrix(int[][] MA, int[][] MB, int Hi, int Hip1) {
        for (int i = Hi; i < Hip1; i++) {
            for (int j = 0; j < N; j++) {
                MA[i][j] += MB[i][j];
            }
        }
        return MA;
    }

    public int[][] multMatrixMatrixValue(int[][] MS, int[][] MXi, int di, int Hi, int Hip1) {
        int[][] MRi = new int[N][N];
        int t = 0;
        for (int i = Hi; i < Hip1; i++) {
            for (int j = 0; j < N; j++) {
                t = 0;
                for (int k = 0; k < N; k++) {
                    t += MS[i][k] * MXi[k][j];
                }
                MRi[i][j] = t * di;
            }
        }
        return MRi;
    }

    public int multVectorMatrixAndMin(int[] Ei, int[][] MM, int Hi, int Hip1, int mi) {
        int[] Ki = new int[N];
        for (int i = Hi; i < Hip1; i++) {
            Ki[i] = 0;
            for (int j = 0; j < N; j++) {
                Ki[i] += Ei[i] * MM[j][i];
            }
            if (Ki[i] < mi) {
                mi = Ki[i];
            }
        }
        return mi;
    }

    public int[] addVectorVector(int[] A, int[] B, int Hi, int Hip1) {
        for (int i = Hi; i < Hip1; i++) {
            A[i] += B[i];
        }
        return A;
    }

    public int[] multValueVector(int a, int[] A, int Hi, int Hip1) {
        for (int i = Hi; i < Hip1; i++) {
            A[i] *= a;
        }
        return A;
    }
}
