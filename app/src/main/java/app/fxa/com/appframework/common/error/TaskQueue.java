package app.fxa.com.appframework.common.error;

import android.content.Context;

import java.util.concurrent.ArrayBlockingQueue;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by fengxiang on 2016/8/12.
 */
public class TaskQueue {

    int SIZE = 5;
    static ArrayBlockingQueue<ErrorTask> queue;
    private static TaskQueue taskQueue = null;

    static Context context;

    public static void init(Context context) {
        taskQueue = new TaskQueue();
        TaskQueue.context = context;
    }

    private TaskQueue() {
        queue = new ArrayBlockingQueue<ErrorTask>(SIZE);
        Observable.just(null).repeat().observeOn(Schedulers.newThread())
                .flatMap(new Func1<Object, Observable<ErrorTask>>() {
                    @Override
                    public Observable<ErrorTask> call(Object object) {
                        try {
                            ErrorTask task = queue.take();
                            return Observable.just(task);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ErrorTask>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ErrorTask task) {
                        task.openErrorPage();
                    }
                });


/**
 new Thread() {
 public void run() {
 for (; ; ) {
 try {
 ErrorTask task = queue.take();
 if (task != null) {
 task.openErrorPage();
 }
 } catch (Exception e) {
 e.printStackTrace();
 }
 }
 }
 }.start();

 **/
    }

    public static void put(ErrorTask task) {
        try {
            queue.put(task);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ErrorTask take() {
        try {
            return queue.take();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
