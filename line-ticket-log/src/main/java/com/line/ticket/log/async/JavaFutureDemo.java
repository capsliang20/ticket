package com.line.ticket.log.async;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class JavaFutureDemo {
    public static final int SLEEP_GAP = 500;

    public static String getCurThreadName() {
        return Thread.currentThread().getName();
    }

    static class HotWaterJob implements Callable<Boolean> {

        @Override
        public Boolean call() throws Exception {
            try {
                System.out.println("洗水壶");
                System.out.println("灌冷水");
                System.out.println("放在火上");
                Thread.sleep(SLEEP_GAP);
                System.out.println("水开了");
            } catch (InterruptedException e) {
                System.out.println("异常中断");
                return Boolean.FALSE;
            }
            return Boolean.TRUE;
        }
    }

    static class WashJob implements Callable<Boolean> {

        @Override
        public Boolean call() throws Exception {
            try {
                System.out.println("洗茶壶");
                System.out.println("洗茶杯");
                System.out.println("拿茶叶");
                Thread.sleep(SLEEP_GAP);
                System.out.println("洗完了");
            } catch (InterruptedException e) {
                System.out.println("异常中断");
                return Boolean.FALSE;
            }
            System.out.println("清洗结束");
            return Boolean.TRUE;
        }
    }

    public static void drinkTea(boolean waterOk, boolean cupOk) {
        if (waterOk && cupOk) {
            System.out.println("泡茶喝");
        } else if (!waterOk) {
            System.out.println("烧水失败,没茶喝");
        } else {
            System.out.println("杯子洗不了,没茶喝");
        }
    }

    public static void main(String[] args) {
        Callable<Boolean> hJob = new HotWaterJob();
        FutureTask<Boolean> hTask = new FutureTask<>(hJob);
        Thread hThread = new Thread(hTask, "** 烧水-Thread");

        Callable<Boolean> wJob = new WashJob();
        FutureTask<Boolean> wTask = new FutureTask<>(wJob);
        Thread wThread = new Thread(wTask, "** 清洗-Thread");

        hThread.start();
        wThread.start();

        Thread.currentThread().setName("主线程");
        try {
            boolean waterOk = hTask.get();
            boolean cupOk = wTask.get();
            drinkTea(waterOk, cupOk);
        } catch (InterruptedException e) {
            System.out.println(getCurThreadName() + "发生异常中断");
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("运行结束");
    }
}
