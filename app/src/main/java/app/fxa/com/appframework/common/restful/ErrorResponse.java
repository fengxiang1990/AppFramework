package app.fxa.com.appframework.common.restful;

/**
 * Created by Administrator on 2016/6/27.
 */
public class ErrorResponse extends Throwable{

    public int code;
    public String message;


    public ErrorResponse(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

}
