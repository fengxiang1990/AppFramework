package app.fxa.com.appframework.util;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.concurrent.BlockingQueue;

import app.fxa.com.appframework.common.wifiupload.UploadTask;

/**
 * Created by fengxiang on 2016/8/23.
 */
public class FileUtils {

    static String tag = "FileUtils";
    public static String SD_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
    public static String UPLOAD_TASK_FILE = "hmf_upload_task_file.obj";

    public static BlockingQueue<UploadTask> readUploadTaskQueueFromFile() {
        try {
            File file = new File(SD_PATH + UPLOAD_TASK_FILE);
            if (file.exists()) {
                ObjectInputStream in = new ObjectInputStream(new FileInputStream(SD_PATH + UPLOAD_TASK_FILE));
                BlockingQueue<UploadTask> queue = (BlockingQueue<UploadTask>) in.readObject();
                Log.e(tag, "queue size-->" + queue.size());
                in.close();
                return queue;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void wiriteUploadTaskQueueIntoFile(BlockingQueue<UploadTask> queue) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(SD_PATH + UPLOAD_TASK_FILE));
            out.writeObject(queue);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createUploadTaskFile(Context context) {
        File file = new File(SD_PATH + UPLOAD_TASK_FILE);
        if (file.exists()) {
            file.delete();
        }
        try {
            if (file.createNewFile()) {
                Log.e(tag, "create file-->" + file.getPath());
            } else {
                Log.e(tag, "create file error");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param conetxt
     * @param sourceFileName assets 根目录下的文件名
     * @return file path
     * @throws IOException
     */
    public static String copyFileFromAssetsToSDCard(Context conetxt, String sourceFileName) throws IOException {
        InputStream inStream = conetxt.getAssets().open(sourceFileName);
        String filePath = Environment.getExternalStorageDirectory()
                .getAbsolutePath() + File.separator + sourceFileName;
        OutputStream outStream = new FileOutputStream(filePath);
        byte[] buffer = new byte[1024];
        int length = inStream.read(buffer);
        outStream.write(buffer, 0, length);
        outStream.flush();
        inStream.close();
        outStream.close();
        return filePath;
    }
}
