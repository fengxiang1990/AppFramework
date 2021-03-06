package app.fxa.com.appframework.util;


import android.content.SharedPreferences;
import android.net.Uri;


public class AppConfig {

    public static final int TAKE_PHOTO = 1;
    public static final int CROP_PHOTO = 2;
    public static Uri imageUri; // 图片路径
    public static String filename; // 图片名称

    public static final String PREFERENCE_NAME = "APP";
    public static String USER_NAME = "username";
    public static String PASS_WORD = "password";
    public static String USER_ID = "user_id";
    public static String MAIL = "mail";
    public static String TEL_PHONE = "tel_phone";
    public static String IS_LOGIN = "isLogin";
    public static String IS_REMEMBER_PASSWORD = "is_remember_password";

    public static String JSESSIONID = null;

    public static String DATE = "date";

    public static String IS_SHOW_IMG = "isNoImg";

    //检查是否无图模式
    public static boolean isShowImg(SharedPreferences preference) {
        return preference.getBoolean(AppConfig.IS_SHOW_IMG, true);
    }


    //检查是否登陆
    public static boolean isLogin(SharedPreferences preference) {
        return preference.getBoolean(AppConfig.IS_LOGIN, false);
        //return true;
    }


}
