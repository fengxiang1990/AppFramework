package app.fxa.com.appframework.common.wifiupload;

import app.fxa.com.appframework.common.restful.RestResponse;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by fengxiang on 2016/8/19.
 */
public interface UploadService {

    /**
     * 上传图片方法
     *
     * @param params
     * @return
     */
    @Multipart
    @POST("/file/uploads")
    Call<RestResponse> upload(
            @Part("fileDesc") RequestBody fileDesc,
            @Part MultipartBody.Part... file);
}
