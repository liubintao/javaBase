package com.robust.concurrency.lock;

import javax.xml.crypto.Data;
import java.util.concurrent.locks.StampedLock;

/**
 * @Description:
 * @Author: robust
 * @CreateDate: 2019/4/9 16:03
 * @Version: 1.0
 */
public class StampedSample {
    private final StampedLock sl = new StampedLock();

    void mutate() {
        long stamp = sl.writeLock();
        try {
            write();
        } finally {
            sl.unlockWrite(stamp);
        }
    }

    Data access() {
        long stamp = sl.tryOptimisticRead();
        Data data = read();
        if (!sl.validate(stamp)) {
            stamp = sl.readLock();
            try {
                data = read();
            } finally {
                sl.unlockRead(stamp);
            }
        }
        return data;
    }
    // …

    private void write() {

    }

    private Data read() {
        return null;
    }
}


