package app.fxa.com.appframework.common.restful;

import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by fxa on 2016/6/21.
 */

public class RestClient {

    static String scheme = "http";
    static String host = "192.168.254.1";
    static int port = 8080;
    private static Retrofit retrofit;

    /**
     * 创建Retrofit 客户端
     *
     * @return
     */
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
            OkHttpClient client = httpBuilder.
                    readTimeout(1, TimeUnit.MINUTES)
                    .connectTimeout(2, TimeUnit.MINUTES)
                    .writeTimeout(3, TimeUnit.MINUTES) //设置超时
                    .retryOnConnectionFailure(true)
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


}
