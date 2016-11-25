package com.github.webee.android.promise;

import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by webee on 16/11/19.
 */

public class AndroidExecutors {
    private static final AtomicReference<AndroidExecutors> INSTANCE = new AtomicReference<>();

    private final Executor mainThreadExecutor;

    private static AndroidExecutors getInstance() {
        for (;;) {
            AndroidExecutors current = INSTANCE.get();
            if (current != null) {
                return current;
            }
            current = new AndroidExecutors();
            if (INSTANCE.compareAndSet(null, current)) {
                return current;
            }
        }
    }

    private AndroidExecutors() {
        mainThreadExecutor = new LooperExecutor(Looper.getMainLooper());
    }

    public static Executor mainThread() {
        return getInstance().mainThreadExecutor;
    }

    public static Executor from(Looper looper) {
        return new LooperExecutor(looper);
    }
}
