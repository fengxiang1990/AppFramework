package app.fxa.com.appframework.common.error;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by fengxiang on 2016/8/12.
 */
public class ErrorTask implements Parcelable {

    protected String tag;
    protected Activity source;
    protected String className;


    public ErrorTask(Activity source) {
        this.source = source;
        this.className =  source.getClass().getName();
        tag = source.getClass().getName() + System.currentTimeMillis();
    }


    protected ErrorTask(Parcel in) {
        tag = in.readString();
        className = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(tag);
        dest.writeString(className);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ErrorTask> CREATOR = new Creator<ErrorTask>() {
        @Override
        public ErrorTask createFromParcel(Parcel in) {
            return new ErrorTask(in);
        }

        @Override
        public ErrorTask[] newArray(int size) {
            return new ErrorTask[size];
        }
    };

    public void openErrorPage() {
        Intent intent = new Intent(source, ErrorActivity.class);
        intent.putExtra("task", this);
        source.startActivity(intent);
    }

}
