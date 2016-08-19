package app.fxa.com.appframework.util;

import android.app.Activity;
import android.net.Uri;
import android.widget.LinearLayout;

import com.facebook.drawee.view.SimpleDraweeView;

/**
 * 这是一个用来显示图片的工具类
 * 注意:这个工具类只能用于在LinearLayout 中显示图片。否则LayoutParams类型将会不正确。
 * Created by fengxiang on 2016/8/17.
 */
public class ImageUtils {

    /**
     * 全屏加载图片
     * @param context
     * @param simpleDraweeView
     * @param url  图片的网络地址
     */
    public static void loadImgFullScreen(Activity context, SimpleDraweeView simpleDraweeView, String url) {
        simpleDraweeView.setImageURI(url);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(DisplayUtil.getScreenWidth(context), DisplayUtil.getScreenHeight(context));
        simpleDraweeView.setLayoutParams(params);
    }

    /**
     * 以宽高比16:9的方式加载图片
     * @param context
     * @param simpleDraweeView
     * @param url 图片的网络地址
     */
    public static void loadImgWH169(Activity context, SimpleDraweeView simpleDraweeView, String url) {
        simpleDraweeView.setImageURI(url);
        int width = DisplayUtil.getScreenWidth(context);
        int height = width / 16 * 9;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
        simpleDraweeView.setLayoutParams(params);
    }

    /**
     * 以宽高比4:3的方式加载图片
     * @param context
     * @param simpleDraweeView
     * @param url 图片的网络地址
     */
    public static void loadImgWH43(Activity context, SimpleDraweeView simpleDraweeView, String url) {
        simpleDraweeView.setImageURI(url);
        int width = DisplayUtil.getScreenWidth(context);
        int height = width / 4 * 3;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
        simpleDraweeView.setLayoutParams(params);
    }


    /**
     * 全屏加载图片
     * @param context
     * @param simpleDraweeView
     * @param uri  图片的uri  可以是本地图片也可以是网络图片
     */
    public static void loadImgFullScreen(Activity context, SimpleDraweeView simpleDraweeView, Uri uri) {
        simpleDraweeView.setImageURI(uri);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(DisplayUtil.getScreenWidth(context), DisplayUtil.getScreenHeight(context));
        simpleDraweeView.setLayoutParams(params);
    }

    /**
     *宽高比16:9 加载图片
     * @param context
     * @param simpleDraweeView
     * @param uri 图片的uri地址
     */
    public static void loadImgWH169(Activity context, SimpleDraweeView simpleDraweeView, Uri uri) {
        simpleDraweeView.setImageURI(uri);
        int width = DisplayUtil.getScreenWidth(context);
        int height = width / 16 * 9;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
        simpleDraweeView.setLayoutParams(params);
    }

    /**
     * 以宽高比4:3 加载图片
     * @param context
     * @param simpleDraweeView
     * @param uri 图片uri 地址
     */
    public static void loadImgWH43(Activity context, SimpleDraweeView simpleDraweeView, Uri uri) {
        simpleDraweeView.setImageURI(uri);
        int width = DisplayUtil.getScreenWidth(context);
        int height = width / 4 * 3;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
        simpleDraweeView.setLayoutParams(params);
    }
}
