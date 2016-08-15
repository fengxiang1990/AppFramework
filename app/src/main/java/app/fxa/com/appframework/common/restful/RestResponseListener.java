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

    public abstract void onError(Call<T> call, Throwable t);


    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.code() == 200) {
            onSuccess(call, response);
        } else {
            Log.e(tag, "response code-->" + response.code());
            Log.e(tag, "response message-->" + response.message());
            String warnMsg = "";
            switch (response.code()) {
                case 400:
                    warnMsg = "错误的请求";
                    break;
                case 403:
                    warnMsg = "没有请求权限";
                    break;
                case 404:
                    warnMsg = "没有找到路径";
                    break;
                case 500:
                    warnMsg = "服务器内部错误";
                    break;
            }
            onError(call, new ErrorResponse(response.code(), warnMsg));
        }

    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        if (t instanceof ConnectException) {
            onError(call, new ErrorResponse(-1, "不能连接到服务器,请检查网络连接"));
            return;
        }else if(t instanceof SocketTimeoutException){
            onError(call, new ErrorResponse(-2, "请求超时"));
            return;
        }else if(t instanceof IOException){
            onError(call, new ErrorResponse(-3, "数据传输失败"));
            return;
        }else if(t instanceof ProtocolException){
            onError(call, new ErrorResponse(-4, "尝试过多的连接"));
            return;
        }

        onError(call, t);
    }


}
