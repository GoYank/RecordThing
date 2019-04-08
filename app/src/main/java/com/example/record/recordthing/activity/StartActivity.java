package com.example.record.recordthing.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.example.record.recordthing.MainActivity;
import com.example.record.recordthing.MyApplication;
import com.example.record.recordthing.R;
import com.example.record.recordthing.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class StartActivity extends BaseActivity {

    @BindView(R.id.welcome)
    TextView welcome;

    @Override
    public void initView() {
        setContentView(R.layout.activity_start);
    }

    @Override
    public void initData() {
    }

    @Override
    public void addActivity() {
        MyApplication.getInstance().addActivity(this);
    }

    @OnClick({R.id.welcome})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.welcome:
                startActivity(new Intent(this,MainActivity.class));
                break;

        }
    }
}
