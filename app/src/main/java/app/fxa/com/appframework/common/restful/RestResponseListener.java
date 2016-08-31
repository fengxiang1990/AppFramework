package app.fxa.com.appframework.common.restful;

import android.util.Log;

import java.io.IOException;
import java.net.ConnectException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by fxa on 2016/6/21.
 */
public abstract class RestResponseListener<T> implements Callback<T> {

    String tag = "RestResponseListener";

    public abstract void onSuccess(Call<T> call, Response<T> response);

    public abstract void onError(Call<T> call, ErrorResponse response);


    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.code() == 200) {
            onSuccess(call, response);
        } else {
            Log.e(tag, "response code-->" + response.code());
            Log.e(tag, "response message-->" + response.message());
            String warnMsg = "";
            int code = 0;
            switch (response.code()) {
                case 400:
                    code = 10001;
                    warnMsg = "错误的请求";
                    break;
                case 403:
                    code = 10002;
                    warnMsg = "没有请求权限";
                    break;
                case 404:
                    code = 10003;
                    warnMsg = "没有找到路径";
                    break;
                case 500:
                    code = 10004;
                    warnMsg = "服务器内部错误";
                    break;
            }
            onError(call, new ErrorResponse(code, warnMsg));
        }

    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        if (t instanceof ConnectException) {
            onError(call, new ErrorResponse(10005, t.getMessage()));
            return;
        } else if (t instanceof SocketTimeoutException) {
            onError(call, new ErrorResponse(10006, t.getMessage()));
            return;
        } else if (t instanceof IOException) {
            onError(call, new ErrorResponse(10007, t.getMessage()));
            return;
        } else if (t instanceof ProtocolException) {
            onError(call, new ErrorResponse(10008, t.getMessage()));
            return;
        }

        onError(call, new ErrorResponse(11000, t.getMessage()));
    }


}
