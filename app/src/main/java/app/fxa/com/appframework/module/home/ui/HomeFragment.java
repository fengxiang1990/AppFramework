package app.fxa.com.appframework.module.home.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;

import app.fxa.com.appframework.R;
import app.fxa.com.appframework.util.ImageUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by fengxiang on 2016/8/17.
 */
public class HomeFragment extends Fragment {
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}