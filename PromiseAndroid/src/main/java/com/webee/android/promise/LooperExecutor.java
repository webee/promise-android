package com.webee.android.promise;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;

/**
 * Created by webee on 16/11/19.
 */

public class LooperExecutor implements Executor {
    private final Handler handler;

    LooperExecutor(Looper looper) {
        handler = new Handler(looper);
    }

    LooperExecutor(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void execute(@NonNull Runnable r) {
        handler.post(r);
    }
}
