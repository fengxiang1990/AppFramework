package app.fxa.com.appframework.common.wifiupload;

import android.util.Log;

import java.io.Serializable;

import app.fxa.com.appframework.common.restful.RestResponseListener;
import retrofit2.Call;
import retrofit2.Response;

/**
 * wifi下上传任务模型类
 * Created by fengxiang on 2016/8/19.
 */
public class UploadTask<T> implements Serializable{

    String tag = "UploadTask";

    UploadRequest<T> uploadRequest;
    UploadRequest<T> afterUploadRequest;

    /**
     * 上传后不做任何后续操作
     *
     * @param request
     */
    public UploadTask(UploadRequest<T> request) {
        uploadRequest = request;
    }

    /**
     * 上传成功后有后续操作 执行request2
     *
     * @param request
     * @param request2
     */
    public UploadTask(UploadRequest<T> request, UploadRequest<T> request2) {
        uploadRequest = request;
        afterUploadRequest = request2;
    }


    /**
     * 执行任务
     */
    public void execute() {
        uploadRequest.execute(new RestResponseListener<T>() {
            @Override
            public void onSuccess(Call<T> call, Response<T> response) {
                Log.e(tag, "upload exucete success");
                if (afterUploadRequest != null) {
                    afterUploadRequest.execute(new RestResponseListener<T>() {
                        @Override
                        public void onSuccess(Call<T> call, Response<T> response) {
                            Log.e(tag, "after upload exucete success");
                        }

                        @Override
                        public void onError(Call<T> call, Throwable t) {
                            Log.e(tag, "after upload exucete error");
                        }
                    });
                }

            }

            @Override
            public void onError(Call<T> call, Throwable t) {
                Log.e(tag, "upload exucete error");
            }
        });
    }
}
