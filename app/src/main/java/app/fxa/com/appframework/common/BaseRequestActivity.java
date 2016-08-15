package app.fxa.com.appframework.common;

import android.content.Intent;
import android.util.Log;

/**
 * Created by fengxiang on 2016/8/12.
 */
public abstract class BaseRequestActivity extends BaseActivityWithToolBar {

    String tag = "BaseRequestActivity";


    protected int NET_REQUEST_CODE = 1000;

    /**
     * 加载数据
     * 子类需要自己实现该方法
     * 可以在其他需要的时候手动调用
     */
    public abstract void loadData();


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NET_REQUEST_CODE) {
            Log.e(tag, "loadData");
            loadData();
        }
    }
}
