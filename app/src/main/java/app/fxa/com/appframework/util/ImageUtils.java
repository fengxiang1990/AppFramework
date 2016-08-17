package app.fxa.com.appframework.util;

import android.app.Activity;
import android.net.Uri;
import android.widget.LinearLayout;

import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by fengxiang on 2016/8/17.
 */
public class ImageUtils {

    public static void loadImgFullScreen(Activity context, SimpleDraweeView simpleDraweeView, String url) {
        simpleDraweeView.setImageURI(url);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(DisplayUtil.getScreenWidth(context), DisplayUtil.getScreenHeight(context));
        simpleDraweeView.setLayoutParams(params);
    }

    public static void loadImgWH169(Activity context, SimpleDraweeView simpleDraweeView, String url) {
        simpleDraweeView.setImageURI(url);
        int width = DisplayUtil.getScreenWidth(context);
        int height = width / 16 * 9;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
        simpleDraweeView.setLayoutParams(params);
    }

    public static void loadImgWH43(Activity context, SimpleDraweeView simpleDraweeView, String url) {
        simpleDraweeView.setImageURI(url);
        int width = DisplayUtil.getScreenWidth(context);
        int height = width / 4 * 3;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
        simpleDraweeView.setLayoutParams(params);
    }


    public static void loadImgFullScreen(Activity context, SimpleDraweeView simpleDraweeView, Uri uri) {
        simpleDraweeView.setImageURI(uri);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(DisplayUtil.getScreenWidth(context), DisplayUtil.getScreenHeight(context));
        simpleDraweeView.setLayoutParams(params);
    }

    public static void loadImgWH169(Activity context, SimpleDraweeView simpleDraweeView, Uri uri) {
        simpleDraweeView.setImageURI(uri);
        int width = DisplayUtil.getScreenWidth(context);
        int height = width / 16 * 9;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
        simpleDraweeView.setLayoutParams(params);
    }

    public static void loadImgWH43(Activity context, SimpleDraweeView simpleDraweeView, Uri uri) {
        simpleDraweeView.setImageURI(uri);
        int width = DisplayUtil.getScreenWidth(context);
        int height = width / 4 * 3;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
        simpleDraweeView.setLayoutParams(params);
    }
}
