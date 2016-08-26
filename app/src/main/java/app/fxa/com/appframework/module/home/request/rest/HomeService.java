package app.fxa.com.appframework.module.home.request.rest;

import java.util.List;

import app.fxa.com.appframework.common.restful.RestResponse;
import app.fxa.com.appframework.module.home.model.Book;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by fengxiang on 2016/8/23.
 */
public interface HomeService {

    @FormUrlEncoded
    @POST("/file/test")
    Call<RestResponse> test(@Field("url") String url);


    @Headers("Cache-Control:public, max-age=30")
    @GET("/cache/books/{id}")
    Call<RestResponse<List<Book>>> books(@Path("id") int id);
}
