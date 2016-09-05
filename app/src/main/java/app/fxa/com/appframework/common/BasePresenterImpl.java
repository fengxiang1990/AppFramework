package app.fxa.com.appframework.common;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.Holder;
import com.orhanobut.dialogplus.ViewHolder;

import app.fxa.com.appframework.R;
import app.fxa.com.appframework.common.restful.ErrorResponse;

/**
 * Created by fengxiang on 2016/9/5.
 */
public class BasePresenterImpl implements BasePresenter {

    protected Context context;

    public BasePresenterImpl(Context context) {
        this.context = context;
    }

    @Override
    public void onNetWorkError(ErrorResponse response) {
        Holder holder = new ViewHolder(R.layout.dia_err_content);
        DialogPlus dialog = DialogPlus.newDialog(context)
                .setContentHolder(holder)
                .setHeader(R.layout.dia_err_header)
                .setCancelable(true)
                .setGravity(Gravity.CENTER)
                .create();
        View view = holder.getInflatedView();
        TextView text_message = (TextView) view.findViewById(R.id.text_content);
        text_message.setText(response.getMessage());
        dialog.show();
    }
}
