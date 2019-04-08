package com.example.record.recordthing.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.example.record.recordthing.R;

/**
 * 项目名：RecordThing
 * 包名：com.example.record.recordthing.dialog
 * 文件名：Loading
 * 创建者：Gyk
 * 创建时间：2019/4/8 13:23
 * 描述：  TODO
 */

public abstract class Loading extends Dialog {

    public abstract void cancle();

    public Loading(@NonNull Context context) {
        super(context,R.style.Loading);
        setContentView(R.layout.loading);
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.CENTER;
        window.setAttributes(params);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        cancle();
        dismiss();
    }
}
