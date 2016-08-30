package app.fxa.com.appframework.common.wifiupload;

import android.util.Log;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import app.fxa.com.appframework.common.restful.RestResponse;
import app.fxa.com.appframework.common.restful.RestResponseListener;
import retrofit2.Call;
import retrofit2.Response;


/**
 * wifi下上传任务模型类
 * Created by fengxiang on 2016/8/19.
 */
public class UploadTask implements Serializable {

    String tag = "UploadTask";

    UploadRequest uploadRequest;
    BaseRequest afterUploadRequest;

    /**
     * 上传后不做任何后续操作
     *
     * @param request
     */
    public UploadTask(UploadRequest request) {
        uploadRequest = request;
    }

    /**
     * 上传成功后有后续操作 执行request2
     *
     * @param request
     * @param request2
     */
    public UploadTask(UploadRequest request, BaseRequest request2) {
        uploadRequest = request;
        afterUploadRequest = request2;
    }


    /**
     * 执行任务
     */
    public void execute(final UploadListener listener) {
        Log.e(tag, "execute...");
        uploadRequest.execute(uploadRequest.params, new RestResponseListener<RestResponse>() {
            @Override
            public void onSuccess(Call<RestResponse> call, Response<RestResponse> response) {
                Log.e(tag, "upload exucete success");
                listener.onSuccess();
                if (afterUploadRequest != null) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("url", response.body().getMessage());
                    afterUploadRequest.execute(map, new RestResponseListener<RestResponse>() {
                        @Override
                        public void onSuccess(Call<RestResponse> call, Response<RestResponse> response) {
                            Log.e(tag, response.body().toString());
                        }

                        @Override
                        public void onError(Call<RestResponse> call, Throwable t) {
                            Log.e(tag, "after upload exucete error");
                        }
                    });
                }

            }

            @Override
            public void onError(Call<RestResponse> call, Throwable t) {
                Log.e(tag, t.getMessage());
                t.printStackTrace();
                listener.onError();
            }
        });
    }
}
