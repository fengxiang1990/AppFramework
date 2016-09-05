package app.fxa.com.appframework.module.login.layer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import app.fxa.com.appframework.R;
import app.fxa.com.appframework.common.BaseActivityWithToolBar;

/**
 * Created by fengxiang on 2016/8/16.
 */
public class LoginActivity extends BaseActivityWithToolBar{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public static void start(Context context) {
        Intent intent = new Intent(context,LoginActivity.class);
        context.startActivity(intent);
    }
}
