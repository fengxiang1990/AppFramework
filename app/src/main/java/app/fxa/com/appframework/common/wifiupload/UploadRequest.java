package app.fxa.com.appframework.common.wifiupload;

import android.support.annotation.Nullable;
import android.util.Log;

import java.io.File;
import java.util.Map;

import app.fxa.com.appframework.common.restful.RestClient;
import app.fxa.com.appframework.common.restful.RestResponse;
import app.fxa.com.appframework.common.restful.RestResponseListener;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;

/**
 * 上传任务请求类
 * Created by fengxiang on 2016/8/19.
 */
public class UploadRequest extends BaseRequest {

    private static final long serialVersionUID = -751337600096084365L;

    String tag = "UploadRequest";
    public Map<String, Object> params;
    File file;

    public UploadRequest(Map<String, Object> params, File file) {
        this.params = params;
        this.file = file;
    }

    @Override
    public void execute(@Nullable Map<String, Object> map, RestResponseListener<RestResponse> listener) {
        try {
            for (String key : map.keySet()) {
                Log.e(tag, key + " " + map.get(key).toString());
            }
            Log.e(tag, UploadRequest.this.toString() + "execute");
            UploadService baseService = RestClient.createRest(UploadService.class);
            // 创建 RequestBody，用于封装 请求RequestBody
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
            Call<RestResponse> call = baseService.upload(body);
            call.enqueue(listener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
