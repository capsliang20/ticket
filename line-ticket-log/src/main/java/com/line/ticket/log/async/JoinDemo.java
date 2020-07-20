package com.line.ticket.log.async;

public class JoinDemo {
    public static final int SLEEP_GAP = 500;

    public static void main(String[] args) {
        Thread hThread = new HotWaterThread();
        Thread wThread = new WashThread();
        hThread.start();
        wThread.start();
        try {
            hThread.join();
            wThread.join();
            Thread.currentThread().setName("主线程");
            System.out.println("泡茶喝");
        } catch (InterruptedException e) {
            System.out.println(getCurThreadName() + "异常中断");
        }
        System.out.println("运行结束");
    }

    public static String getCurThreadName() {
        return Thread.currentThread().getName();
    }

    static class HotWaterThread extends Thread {
        public HotWaterThread() {
            super("** 烧水-Thread");
        }

        @Override
        public void run() {
            try {
                System.out.println("洗好水壶");
                System.out.println("灌上冷水");
                System.out.println("放在火上");
                Thread.sleep(SLEEP_GAP);
                System.out.println("烧完了");
            } catch (InterruptedException e) {
                System.out.println("异常中断");
            }
            System.out.println("运行结束");
        }
    }

    static class WashThread extends Thread {
        public WashThread() {
            super("** 清洗-Thread");
        }

        @Override
        public void run() {
            try {
                System.out.println("洗茶壶");
                System.out.println("洗茶杯");
                System.out.println("拿茶叶");
                Thread.sleep(SLEEP_GAP);
                System.out.println("洗完了");
            } catch (InterruptedException e) {
                System.out.println("异常中断");
            }
            System.out.println("运行结束");
        }
    }
}
