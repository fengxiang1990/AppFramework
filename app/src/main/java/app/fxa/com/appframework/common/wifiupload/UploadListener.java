package app.fxa.com.appframework.common.wifiupload;

/**
 * 用于上传后的结果回调。
 * Created by fengxiang on 2016/8/24.
 */
public interface UploadListener {

    /**
     * 执行成功
     */
    void onSuccess();

    /**
     *执行失败
     */
    void onError();
}


