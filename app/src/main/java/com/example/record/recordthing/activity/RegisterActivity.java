package com.example.record.recordthing.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.record.recordthing.MyApplication;
import com.example.record.recordthing.R;
import com.example.record.recordthing.base.BaseActivity;
import com.example.record.recordthing.base.BaseParameter;
import com.example.record.recordthing.bean.RegisterBean;
import com.example.record.recordthing.https.HttpRxListener;
import com.example.record.recordthing.https.RtRxOkHttp;
import com.example.record.recordthing.utils.StringUtils;

import butterknife.BindView;
import cn.jpush.im.android.api.options.RegisterOptionalUserInfo;
import rx.Observable;

public class RegisterActivity extends BaseActivity implements HttpRxListener {

    @BindView(R.id.tv_regis)
    TextView tv_regis;
    @BindView(R.id.et_phone)
    EditText et_phone;
    @BindView(R.id.et_password)
    EditText et_password;
    @BindView(R.id.et_password_true)
    EditText et_password_true;
    private String userName;
    private String password;
    private RegisterOptionalUserInfo registerOptionalUserInfo;

    @Override
    public void initView() {
        setContentView(R.layout.activity_register);
    }

    @Override
    public void initData() {
        tv_regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StringUtils.getText(et_phone) != "" && StringUtils.getText(et_password) != ""
                        && StringUtils.getText(et_password_true) != "") {
                    if (StringUtils.getText(et_password).equals(StringUtils.getText(et_password_true))){
                        getRegisNet();
                    }else {
                        Toast.makeText(RegisterActivity.this,"您两次输入的密码不一样，请重新输入!",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(RegisterActivity.this,"用户名或密码不能为空，请重新输入!",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void getRegisNet() {
        BaseParameter hashMap = new BaseParameter();
        Observable<RegisterBean> regisNet = RtRxOkHttp.getApiService().regisNet(hashMap);
        hashMap.put("username", StringUtils.getText(et_phone));
        hashMap.put("password", StringUtils.getText(et_password));
        RtRxOkHttp.getInstance().createRtRx(this, regisNet, this, 1);
    }

    @Override
    public void addActivity() {
        MyApplication.getInstance().addActivity(this);
    }


    @Override
    public void httpResponse(Object info, boolean isSusccess, int sort) {
        if (isSusccess) {
            switch (sort) {
                case 1:

                    break;

            }
        }
    }
}
