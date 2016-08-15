package app.fxa.com.appframework;

import android.app.Application;
import android.util.Log;

import com.facebook.drawee.backends.pipeline.Fresco;

import app.fxa.com.appframework.common.error.TaskQueue;


public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("applicayion", "onCreate");
        Fresco.initialize(this);
        TaskQueue.init(this);

    }

}
