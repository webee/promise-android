package com.github.webee.android.test.promise;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.github.webee.android.promise.AndroidExecutors;
import com.github.webee.promise.Promise;
import com.github.webee.promise.PromiseExecutors;
import com.github.webee.promise.Transition;
import com.github.webee.promise.functions.Action;
import com.github.webee.promise.functions.Fulfillment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.promiseStatus)
    TextView promiseStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.promiseBtn)
    public void promiseEvent() {
        Log.d("PROMISE", "clicked");
        new Promise<>("init", new Fulfillment<String>() {
            @Override
            public void run(final Transition<String> transition) {
                PromiseExecutors.defaultExecutor().execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                            transition.update("A");
                            Thread.sleep(1000);
                            transition.update("B");
                            Thread.sleep(1000);
                            transition.update("C");
                            Thread.sleep(1000);
                            transition.update("D");
                            Thread.sleep(1000);
                            transition.update("E");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        transition.fulfill("done");
                    }
                });
            }
        }).handleOn(
                AndroidExecutors.mainThread()
        ).status(new Action<String>() {
            @Override
            public void run(String v) {
                Log.d("PROMISE", v);
                promiseStatus.setText(v);
            }
        }).fulfilled(new Action<String>() {
            @Override
            public void run(String v) {
                Log.d("PROMISE", v);
                promiseStatus.setText(v);
            }
        });
    }
}
