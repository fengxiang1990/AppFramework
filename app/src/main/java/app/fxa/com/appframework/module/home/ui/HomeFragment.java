package app.fxa.com.appframework.module.home.ui;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.fxa.com.appframework.R;
import app.fxa.com.appframework.common.restful.RestResponse;
import app.fxa.com.appframework.common.restful.RestResponseListener;
import app.fxa.com.appframework.common.wifiupload.UploadRequest;
import app.fxa.com.appframework.common.wifiupload.UploadTask;
import app.fxa.com.appframework.common.wifiupload.UploadTaskQueue;
import app.fxa.com.appframework.module.home.model.Book;
import app.fxa.com.appframework.module.home.request.HomeRequest;
import app.fxa.com.appframework.util.ImageUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Response;


/**
 * Created by fengxiang on 2016/8/17.
 */
public class HomeFragment extends Fragment {
    String tag = "HomeFragment";
    Unbinder unbinder;

    @BindView(R.id.img)
    SimpleDraweeView simpleDraweeView;

    @BindView(R.id.img2)
    SimpleDraweeView simpleDraweeView2;

    @BindView(R.id.img3)
    SimpleDraweeView simpleDraweeView3;

    @BindView(R.id.img4)
    SimpleDraweeView simpleDraweeView4;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        String url = "http://www.samsunghdwallpaper.com/images/2013/11/27/Mermaid%20swimming%20in%20the%20sea%20274.jpg";
        ImageUtils.loadImgFullScreen(getActivity(), simpleDraweeView, url);

        String url2 = "http://www.shaimn.com/uploads/allimg/160301/1-160301110623.jpg";
        ImageUtils.loadImgFullScreen(getActivity(), simpleDraweeView2, url2);

        String url3 = "http://www.467541.com/uploads/allimg/140226/1-140226112T0.jpg";
        ImageUtils.loadImgFullScreen(getActivity(), simpleDraweeView3, url3);

        String url4 = "http://pic.4j4j.cn/upload/pic/20131202/c8c6f627d0.jpg";
        ImageUtils.loadImgFullScreen(getActivity(), simpleDraweeView4, url4);
        return rootView;
    }


    void getBooks(){
        Log.e(tag, "getBooks");
        new HomeRequest().books(new RestResponseListener<RestResponse<List<Book>>>() {
            @Override
            public void onSuccess(Call<RestResponse<List<Book>>> call, Response<RestResponse<List<Book>>> response) {
                RestResponse<List<Book>> restResponse =   response.body();
                Log.e(tag,restResponse.toString());
            }

            @Override
            public void onError(Call<RestResponse<List<Book>>> call, Throwable t) {
                Log.e(tag,t.getMessage());
            }
        });
    }
    boolean isWifi = false;

    @OnClick(R.id.img)
    void imgClick() {
        getBooks();
        Log.e(tag, "img clicked");
        if (!isWifi) {
            Map<String, Object> map = new HashMap<>();
            map.put("uid", "001");
            map.put("img", "img1");
            try {
                String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "test.JPG";
                String filePath2 = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "DSC_0257.JPG";
                UploadRequest request = new UploadRequest(map, new File(filePath), new File(filePath2));
                UploadTask task = new UploadTask(request);
                UploadTaskQueue.put(task);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @OnClick(R.id.img2)
    void img2Click() {
//        new HomeRequest().test(new RestResponseListener<RestResponse>() {
//                @Override
//                public void onSuccess(Call<RestResponse> call, Response<RestResponse> response) {
//                    Log.e(tag, response.body().toString());
//                }
//
//                @Override
//                public void onError(Call<RestResponse> call, Throwable t) {
//                    Log.e(tag, t.getMessage());
//                }
//        });
        if (!isWifi) {
            try {
                Map<String, Object> map = new HashMap<>();
                map.put("uid", "001");
                map.put("img", "img1");
                String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "test.JPG";
                UploadRequest request = new UploadRequest(map, new File(filePath));
                HomeRequest homeRequest = new HomeRequest();
                UploadTask task = new UploadTask(request, homeRequest);
                UploadTaskQueue.put(task);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
