package app.fxa.com.appframework.util;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by fengxiang on 2016/8/23.
 */
public class FileUtils {
    /**
     * @param conetxt
     * @param sourceFileName assets 根目录下的文件名
     * @return file path
     * @throws IOException
     */
    public static String copyFileFromAssetsToSDCard(Context conetxt, String sourceFileName) throws IOException {
        InputStream inStream = conetxt.getAssets().open(sourceFileName);
        String filePath = android.os.Environment.getExternalStorageDirectory()
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
