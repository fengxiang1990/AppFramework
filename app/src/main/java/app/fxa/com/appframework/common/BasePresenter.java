package app.fxa.com.appframework.common;

import app.fxa.com.appframework.common.restful.ErrorResponse;

/**
 * Created by fengxiang on 2016/9/5.
 */
public interface BasePresenter {
    /**
     * 网络连接错误
     */
    public void onNetWorkError(ErrorResponse response);
}
