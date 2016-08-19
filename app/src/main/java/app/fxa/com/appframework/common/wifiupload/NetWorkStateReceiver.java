package app.fxa.com.appframework.common.wifiupload;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 监控网络状态广播
 * Created by fengxiang on 2016/8/19.
 */
public class NetWorkStateReceiver extends BroadcastReceiver {

    String tag = "NetWorkStateReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
// TODO Auto-generated method stub
        if (intent.getAction().equals(WifiManager.RSSI_CHANGED_ACTION)) {
            //signal strength changed
        } else if (intent.getAction().equals(WifiManager.NETWORK_STATE_CHANGED_ACTION)) {//wifi连接上与否
            Log.i(tag, "网络状态改变");
            NetworkInfo info = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
            if (info.getState().equals(NetworkInfo.State.DISCONNECTED)) {
                Log.i(tag, "wifi网络连接断开");
            } else if (info.getState().equals(NetworkInfo.State.CONNECTED)) {

                WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();

                //获取当前wifi名称
                Log.i(tag, "连接到网络 " + wifiInfo.getSSID());
                onConnectWifi();
            }

        } else if (intent.getAction().equals(WifiManager.WIFI_STATE_CHANGED_ACTION)) {//wifi打开与否
            int wifistate = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_DISABLED);

            if (wifistate == WifiManager.WIFI_STATE_DISABLED) {
                Log.i(tag, "系统关闭wifi");
            } else if (wifistate == WifiManager.WIFI_STATE_ENABLED) {
                Log.i(tag, "系统开启wifi");
            }
        }
    }

    public void onConnectWifi() {
        Observable.just(null).repeat().observeOn(Schedulers.newThread())
                .flatMap(new Func1<Object, Observable<UploadTask>>() {
                    @Override
                    public Observable<UploadTask> call(Object object) {
                        try {
                            UploadTask task =  UploadTaskQueue.take();
                            return Observable.just(task);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                })
                .subscribe(new Subscriber<UploadTask>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(UploadTask task) {
                        task.execute();
                    }
                });
    }
}
