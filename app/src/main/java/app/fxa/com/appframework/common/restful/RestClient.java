package app.fxa.com.appframework.common.restful;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import app.fxa.com.appframework.util.FileUtils;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by fxa on 2016/6/21.
 */

public class RestClient {

    static String tag = "RestClient";
    static String cache_dir = "app.fxa.cache";
    static String scheme = "http";
    static String host = "192.168.254.1";
    static int port = 8080;
    private static Retrofit retrofit;

    private static Context context;

    public static void init(Context context) {
        RestClient.context = context;
        getRetrofitInstance();
    }


    /**
     * 创建Retrofit 客户端
     *
     * @return
     */
    public static Retrofit getRetrofitInstance() {
        File cacheFile = new File(FileUtils.SD_PATH + cache_dir, "[缓存目录]");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb
        if (retrofit == null) {
            OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
            OkHttpClient client = httpBuilder.
                    readTimeout(1, TimeUnit.MINUTES)
                    .connectTimeout(1, TimeUnit.MINUTES)
                    .writeTimeout(3, TimeUnit.MINUTES) //设置超时
                    .retryOnConnectionFailure(true)
                    .cache(cache)
                    .addNetworkInterceptor(interceptor)
                    .addInterceptor(interceptor)
                    .build();
            HttpUrl.Builder builder = new HttpUrl.Builder();
            builder.scheme(scheme);
            builder.host(host);
            builder.port(port);
            retrofit = new Retrofit.Builder()
                    .baseUrl(builder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }

    /**
     * 创建rest对象
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T createRest(Class<T> clazz) {
        return getRetrofitInstance().create(clazz);
    }


    static Interceptor interceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!isNetworkAvailable(context)) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
                Log.e(tag, "no network");
            }
            Response originalResponse = chain.proceed(request);
            if (isNetworkAvailable(context)) {
                Log.e(tag, "on network cache1");
                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                String cacheControl = request.cacheControl().toString();
                return originalResponse.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .removeHeader("Pragma")
                        .build();
            } else {
                Log.e(tag, "no network cache1");
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=2419200")
                        .removeHeader("Pragma")
                        .build();
            }
        }
    };


    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null) {
        } else {

            NetworkInfo[] info = cm.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
