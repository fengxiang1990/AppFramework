package app.fxa.com.appframework.common.error;

import android.os.Bundle;
import android.util.Log;

import app.fxa.com.appframework.R;
import app.fxa.com.appframework.common.BaseRequestActivity;

/**
 * Created by fengxiang on 2016/8/12.
 */
public class TestActivity extends BaseRequestActivity {

    String tag = "TestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        loadData();
    }

    @Override
    public void loadData() {
        new Thread() {
            public void run() {
                for (int i = 0; i < 3; i++) {
                    try {
                        Thread.sleep(1000);
                        Log.e(tag, "load times-->" + i);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                Log.e(tag, "net work error");
                ErrorTask task = new ErrorTask(TestActivity.this);
                ErrorPageTaskQueue.put(task);

                finish();
            }
        }.start();


    }
}
