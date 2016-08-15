package app.fxa.com.appframework.common.restful;

/**
 * Created by fengxiang on 2016/1/21.
 */
public class RestResponse<T> {

    private String Status;
    private int Code;

    private String Message;

    private T Data;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public T getResult() {
        return Data;
    }

    public void setResult(T result) {
        this.Data = result;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        Code = code;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
