package app.fxa.com.appframework.common.wifiupload;

import app.fxa.com.appframework.common.restful.RestResponseListener;

/**
 * Created by fengxiang on 2016/8/19.
 */
public interface BaseRequest<T> {

    void execute(RestResponseListener<T> listener);

}
