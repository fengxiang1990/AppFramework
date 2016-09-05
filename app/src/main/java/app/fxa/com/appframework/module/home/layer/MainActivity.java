package app.fxa.com.appframework.module.home.layer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

import app.fxa.com.appframework.R;
import app.fxa.com.appframework.common.BaseActivityWithToolBar;
import app.fxa.com.appframework.common.TabEntity;
import app.fxa.com.appframework.common.wifiupload.UploadTaskQueue;
import app.fxa.com.appframework.module.login.layer.LoginActivity;
import app.fxa.com.appframework.util.FileUtils;
import app.fxa.com.appframework.util.ViewFindUtils;

/**
 * Created by fengxiang on 2016/8/11.
 */
public class MainActivity extends BaseActivityWithToolBar {

    String tag = "MainActivity";
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    private String[] mTitles = {"首页", "日报", "联系人", "更多"};
    private int[] mIconUnselectIds = {
            R.mipmap.tab_home_unselect, R.mipmap.tab_speech_unselect,
            R.mipmap.tab_contact_unselect, R.mipmap.tab_more_unselect};
    private int[] mIconSelectIds = {
            R.mipmap.tab_home_select, R.mipmap.tab_speech_select,
            R.mipmap.tab_contact_select, R.mipmap.tab_more_select};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private View mDecorView;
    private CommonTabLayout mTabLayout_1;
    FragmentManager frgmentManager;

    //tab 点击顺序队列
    List<Integer> tabSelectedQueue = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_tab);
        frgmentManager = getSupportFragmentManager();
        mFragments.add(new HomeFragment());
        mFragments.add(new DayReportFragment());
        for (int i = 2; i < mTitles.length; i++) {
            mFragments.add(SimpleCardFragment.getInstance("Switch ViewPager " + mTitles[i]));
        }

        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }

        mDecorView = getWindow().getDecorView();
        /** with nothing */
        mTabLayout_1 = ViewFindUtils.find(mDecorView, R.id.tl_1);
        mTabLayout_1.setTabData(mTabEntities);

        mTabLayout_1.setCurrentTab(0);
        tabSelectedQueue.add(0);
        switchFragment(frgmentManager.beginTransaction(), mFragments.get(0));
        mTabLayout_1.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                tabSelectedQueue.add(position);
                if (position == 3) {
                    LoginActivity.start(MainActivity.this);
                } else {
                    switchFragment(frgmentManager.beginTransaction(), mFragments.get(position));
                }
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (mTabLayout_1.getCurrentTab() == 3) {
            int lastSelectedTab = tabSelectedQueue.get(tabSelectedQueue.size() - 2);
            mTabLayout_1.setCurrentTab(lastSelectedTab);
            tabSelectedQueue.add(lastSelectedTab);
        }
    }

    public void switchFragment(FragmentTransaction fragmentTransaction,
                               Fragment fragment) {
        for (Fragment objFragment : mFragments) {
            if (objFragment.isAdded()) {
                fragmentTransaction.hide(objFragment);
            }
        }
        if (!fragment.isAdded()) {
            fragmentTransaction.add(R.id.fragments, fragment);
        } else {
            fragmentTransaction.show(fragment);
        }
        fragmentTransaction.commit();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Log.e(tag, "key back");
            FileUtils.createUploadTaskFile(this);
            FileUtils.wiriteUploadTaskQueueIntoFile(UploadTaskQueue.queue);
        }
        return super.onKeyDown(keyCode, event);
    }
}
