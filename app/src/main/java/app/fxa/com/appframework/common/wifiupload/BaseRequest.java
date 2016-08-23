package app.fxa.com.appframework.common.wifiupload;

import java.util.Map;

import app.fxa.com.appframework.common.restful.RestResponse;
import app.fxa.com.appframework.common.restful.RestResponseListener;

/**
 * Created by fengxiang on 2016/8/19.
 */
public interface BaseRequest {

    void execute(Map<String,Object> map, RestResponseListener<RestResponse> listener);

}
