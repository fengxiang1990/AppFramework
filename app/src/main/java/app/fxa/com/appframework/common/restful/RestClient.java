package app.fxa.com.appframework.common.restful;

import okhttp3.HttpUrl;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by fxa on 2016/6/21.
 */

public class RestClient {

    static String scheme = "http";
    static String host = "";
    static int port = 80;
    private static Retrofit retrofit;

    /**
     * 创建Retrofit 客户端
     *
     * @return
     */
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            HttpUrl.Builder builder = new HttpUrl.Builder();
            builder.scheme(scheme);
            builder.host(host);
            builder.port(port);
            retrofit = new Retrofit.Builder()
                    .baseUrl(builder.build())
                    .addConverterFactory(GsonConverterFactory.create())
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
