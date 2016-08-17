package app.fxa.com.appframework.module.home.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

import app.fxa.com.appframework.R;
import app.fxa.com.appframework.common.BaseActivityWithToolBar;
import app.fxa.com.appframework.common.TabEntity;
import app.fxa.com.appframework.module.login.ui.LoginActivity;
import app.fxa.com.appframework.util.ViewFindUtils;

/**
 * Created by fengxiang on 2016/8/11.
 */
public class MainActivity extends BaseActivityWithToolBar {

    private ArrayList<Fragment> mFragments = new ArrayList<>();

    private String[] mTitles = {"首页", "消息", "联系人", "更多"};
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_tab);
        frgmentManager = getSupportFragmentManager();
        mFragments.add(new HomeFragment());
        for (int i=1;i<mTitles.length;i++) {
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
        switchFragment(frgmentManager.beginTransaction(), mFragments.get(0));
        mTabLayout_1.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
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
}
