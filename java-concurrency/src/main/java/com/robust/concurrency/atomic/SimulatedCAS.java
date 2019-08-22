package com.robust.concurrency.atomic;

import java.nio.file.SimpleFileVisitor;

/**
 * @Description: 模拟CAS操作
 * @Author: robust
 * @CreateDate: 2019/6/27 10:23
 * @Version: 1.0
 */
public class SimulatedCAS {

    private int count;

    private void addOne(int newVal) {
        do{
            newVal = count + 1;
        }while (count != cas(count, newVal));
    }

    /**
     * 只有当目前 count 的值和期望值 expect 相等时，才会将 count 更新为 update。
     * @param expect 期望值
     * @param update 更新值
     * @return 旧值
     */
    private synchronized int cas(int expect, int update) {
        int curVal = count;
        if(curVal == expect) {
            count = update;
        }
        return curVal;
    }


    public static void main(String[] args) {
        SimulatedCAS simulatedCAS = new SimulatedCAS();
        for(int i = 0; i < 1000; i++) {
            new Thread(() -> {
                simulatedCAS.addOne(0);
            }).start();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(simulatedCAS.count);
    }
}
