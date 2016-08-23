package app.fxa.com.appframework.module.home.request.rest;

import app.fxa.com.appframework.common.restful.RestResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by fengxiang on 2016/8/23.
 */
public interface HomeService {

    @FormUrlEncoded
    @POST("/file/test")
    Call<RestResponse> test(@Field("url") String url);
}
