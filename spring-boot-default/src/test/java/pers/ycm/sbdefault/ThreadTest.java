package pers.ycm.sbdefault;

import org.junit.Test;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yuanchengman
 * @date 2021-02-20
 */
public class ThreadTest {

    private static int STATE = 0;
    private static Lock LOCK = new ReentrantLock();

    /**
     * 三个线程a,b,c按顺序打印10次A,B,C </br>
     * 利用线程池
     */
    @Test
    public void test1() {
        //强制使用ThreadPoolExecutor创建线程池
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 3, 10L, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        int times = 10;
        for (int i = 0; i < times; i++) {
            int tempIndex = i;
            threadPoolExecutor.execute(() -> {
                System.out.println("A Thread, print A.");
            });
            threadPoolExecutor.execute(() -> {
                System.out.println("B Thread, print B.");
            });
            threadPoolExecutor.execute(() -> {
                System.out.println("C Thread, print C.");
            });
        }
    }

    /**
     * 三个线程a,b,c按顺序打印10次A,B,C </br>
     * 利用锁
     */
    @Test
    public void test2() {
        new Thread(() -> {
            for (int i = 0; i < 10;) {
                try {
                    LOCK.lock();
                    if (STATE % 3 == 0) {
                        System.out.println("A Thread, print A.");
                        STATE++;
                        i++;
                    }
                } finally {
                    LOCK.unlock();
                }
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 10;) {
                try {
                    LOCK.lock();
                    if (STATE % 3 == 1) {
                        System.out.println("B Thread, print B.");
                        STATE++;
                        i++;
                    }
                } finally {
                    LOCK.unlock();
                }
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 10;) {
                try {
                    LOCK.lock();
                    if (STATE % 3 == 2) {
                        System.out.println("C Thread, print C.");
                        STATE++;
                        i++;
                    }
                } finally {
                    LOCK.unlock();
                }
            }
        }).start();
    }
}
