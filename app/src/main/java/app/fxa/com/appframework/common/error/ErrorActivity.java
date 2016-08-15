package app.fxa.com.appframework.common.error;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import app.fxa.com.appframework.R;
import app.fxa.com.appframework.common.BaseActivityWithToolBar;

/**
 * Created by fengxiang on 2016/8/12.
 */
public class ErrorActivity extends BaseActivityWithToolBar {

    String tag = "ErrorActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acrivity_error);
        final ErrorTask task = getIntent().getParcelableExtra("task");
        Log.e(tag, task.tag);
        Log.e(tag, task.className);
        findViewById(R.id.btn_retry).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(ErrorActivity.this, Class.forName(task.className));
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
