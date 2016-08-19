package app.fxa.com.appframework.common.wifiupload;

import android.util.Log;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by fengxiang on 2016/8/19.
 */
public class UploadTaskQueue {

    static String tag = "UploadTaskQueue";
    private static BlockingQueue<UploadTask> queue = new ArrayBlockingQueue<UploadTask>(20);

    public static void put(UploadTask task) {
        try {
            Log.e(tag, "put a task");
            queue.put(task);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static UploadTask take() {
        try {
            if (queue.size() > 0) {
                Log.e(tag, "take a task");
                return queue.take();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
