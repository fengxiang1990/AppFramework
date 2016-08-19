package app.fxa.com.appframework.common.wifiupload;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.POST;

/**
 * Created by fengxiang on 2016/8/19.
 */
public interface UploadService<T> {

    /**
     * 上传图片方法
     *
     * @param params
     * @return
     */
    @POST("img/upload")
    Call<T> upload(Map<String, Object> params);
}
