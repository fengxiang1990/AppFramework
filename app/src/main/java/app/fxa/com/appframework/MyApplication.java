package app.fxa.com.appframework;

import android.app.Application;
import android.util.Log;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

import java.util.concurrent.BlockingQueue;

import app.fxa.com.appframework.common.error.ErrorPageTaskQueue;
import app.fxa.com.appframework.common.wifiupload.UploadTask;
import app.fxa.com.appframework.common.wifiupload.UploadTaskQueue;
import app.fxa.com.appframework.util.FileUtils;
import okhttp3.OkHttpClient;


public class MyApplication extends Application {

    String tag  = "MyApplication";
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("applicayion", "onCreate");
        OkHttpClient okHttpClient = new OkHttpClient();
        ImagePipelineConfig config = OkHttpImagePipelineConfigFactory
                .newBuilder(this, okHttpClient)
                .build();
        Fresco.initialize(this, config);
        ErrorPageTaskQueue.init(this);
        BlockingQueue<UploadTask> queue = FileUtils.readUploadTaskQueueFromFile();
        if (queue != null) {
            UploadTaskQueue.queue = queue;
            Log.e(tag,"UploadTaskQueue.queue size-->"+UploadTaskQueue.queue.size());
        }
    }

}
