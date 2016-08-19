package app.fxa.com.appframework.common.wifiupload;

import android.util.Log;

import java.io.Serializable;
import java.util.Map;

import app.fxa.com.appframework.common.restful.RestResponseListener;

/**
 * 上传任务请求类
 * Created by fengxiang on 2016/8/19.
 */
public class UploadRequest<T> implements BaseRequest<T>,Serializable {

    String tag = "UploadRequest";
    Map<String, Object> params;

    public UploadRequest(Map<String, Object> params) {
        this.params = params;
    }

    @Override
    public void execute(RestResponseListener<T> listener) {
        Log.e(tag, UploadRequest.this.toString() + "execute");
        //  UploadService baseService = RestClient.createRest(UploadService.class);
        //  Call<T> call = baseService.upload(params);
        //  call.enqueue(listener);
    }
}
