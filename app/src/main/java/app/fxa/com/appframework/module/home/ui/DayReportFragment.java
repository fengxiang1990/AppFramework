package app.fxa.com.appframework.module.home.ui;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import app.fxa.com.appframework.R;
import app.fxa.com.appframework.common.BaseFragment;
import app.fxa.com.appframework.util.DateUtil;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 我的日报界面
 * Created by fengxiang on 2016/9/1.
 */
public class DayReportFragment extends BaseFragment {

    String tag = "DayReportFragment";
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dayreport, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        init();
        return rootView;
    }


    private boolean undo = false;
    private CaldroidFragment caldroidFragment;

    private void setCustomResourceForDates() {
        Calendar cal = Calendar.getInstance();
        List<String> dates =new ArrayList<>();
        dates.add(new String("2016-08-15"));
        dates.add(new String("2016-08-16"));
        dates.add(new String("2016-08-17"));
        dates.add(new String("2016-08-18"));
        dates.add(new String("2016-08-19"));

        dates.add(new String("2016-08-22"));
        dates.add(new String("2016-08-23"));
        dates.add(new String("2016-08-24"));
        dates.add(new String("2016-08-25"));
        dates.add(new String("2016-08-26"));

        dates.add(new String("2016-08-29"));
        dates.add(new String("2016-08-30"));
        dates.add(new String("2016-08-31"));
        dates.add(new String("2016-09-01"));



        // Max date is next 7 days
        // cal = Calendar.getInstance();
        // cal.add(Calendar.DATE, 7);
        //Date greenDate = cal.getTime();


        if (caldroidFragment != null) {
           Drawable drawable = getResources().getDrawable(R.mipmap.ic_launcher);
            for(String dateStr : dates){
                Date date = DateUtil.StringToDate(dateStr, DateUtil.DateStyle.YYYY_MM_DD);
                caldroidFragment.setBackgroundDrawableForDate(drawable, date);
                caldroidFragment.setTextColorForDate(R.color.white, date);
            }
           // ColorDrawabl green = new ColorDrawable(Color.GREEN);
            //caldroidFragment.setBackgroundDrawableForDate(drawable, blueDate);
            // caldroidFragment.setBackgroundDrawableForDate(green, greenDate);
           // caldroidFragment.setTextColorForDate(R.color.white, blueDate);
            //caldroidFragment.setTextColorForDate(R.color.white, greenDate);
        }
    }


    public void init() {
        caldroidFragment = new CaldroidFragment();
        Bundle args = new Bundle();
        Calendar cal = Calendar.getInstance();
        args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
        args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
        args.putBoolean(CaldroidFragment.ENABLE_SWIPE, true);
        args.putBoolean(CaldroidFragment.SIX_WEEKS_IN_CALENDAR, true);
        caldroidFragment.setArguments(args);
        setCustomResourceForDates();
        FragmentTransaction t = getChildFragmentManager().beginTransaction();
        t.replace(R.id.calendar1, caldroidFragment);
        t.commit();
        final CaldroidListener listener = new CaldroidListener() {

            @Override
            public void onSelectDate(Date date, View view) {

            }

            @Override
            public void onChangeMonth(int month, int year) {

            }

            @Override
            public void onLongClickDate(Date date, View view) {

            }

            @Override
            public void onCaldroidViewCreated() {

            }

        };
        // Setup Caldroid
        caldroidFragment.setCaldroidListener(listener);

    }


}
