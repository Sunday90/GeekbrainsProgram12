package ru.geekbrains;

public class Program {
    public static void main(String[] args) {
        method1();
        method2();

    }

    public static void method1 () {
        final int size = 10000000;
        final int h = size / 2;
        float[] arr = new float[size];

        for (int i=0;i<size;i++) {
            arr[i] = 1;
        }

        long a = System.currentTimeMillis();

        for (int i=0;i<size;i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }

        System.out.println(System.currentTimeMillis() - a);

    }


    public static void method2 () {
        final int size = 10000000;
        final int h = size / 2;
        float[] arr = new float[size];
        float[] arr2 = new float[h];
        float[] arr3 = new float[h];


        for (int i=0;i<size;i++) {
            arr[i] = 1;
        }

        long a = System.currentTimeMillis();

        System.arraycopy(arr,0,arr2,0,h);
        System.arraycopy(arr,h,arr3,0,h);

        Thread t2 = new Thread() {
            public void run() {
                for (int i = 0; i < h; i++) {
                    arr2[i] = (float) (arr2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        };

        Thread t3 = new Thread() {
            public void run() {
                for (int i = 0; i < h; i++) {
                    arr3[i] = (float) (arr3[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        };

        try {
            t2.start();
            t2.join();
            t3.start();
            t3.join();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.arraycopy(arr2,0,arr,0,h);
        System.arraycopy(arr3,0,arr,h,h);

        System.out.println(System.currentTimeMillis() - a);

    }

}
