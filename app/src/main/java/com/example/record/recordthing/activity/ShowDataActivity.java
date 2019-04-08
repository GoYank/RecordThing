package com.example.record.recordthing.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.record.recordthing.MyApplication;
import com.example.record.recordthing.R;
import com.example.record.recordthing.adapter.LoadMoreAdapter;
import com.example.record.recordthing.adapter.LoadMoreDataAdapter;
import com.example.record.recordthing.adapter.LoadYearAdapter;
import com.example.record.recordthing.base.BaseActivity;
import com.example.record.recordthing.base.BaseParameter;
import com.example.record.recordthing.bean.RecordDataBean;
import com.example.record.recordthing.dialog.Loading;
import com.example.record.recordthing.https.HttpRxListener;
import com.example.record.recordthing.https.RtRxOkHttp;
import com.example.record.recordthing.utils.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observable;

/**
 * 项目名：RecordThing
 * 包名：com.example.record.recordthing.activity
 * 文件名：ShowDataActivity
 * 创建者：Gyk
 * 创建时间：2019/4/4 10:58
 * 描述：  TODO
 */

public class ShowDataActivity extends BaseActivity implements HttpRxListener {

    @BindView(R.id.recy_year)
    RecyclerView recy_year;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tv_total)
    TextView tv_total;
    @BindView(R.id.ll_total)
    LinearLayout ll_total;
    private String region;
    private String region_code;
    private String data_start;
    private String data_end;
    private String key_word;
    private LoadYearAdapter loadYearAdapter;
    private Loading loading;
    private List<RecordDataBean.DataBean.EventBean> list = new ArrayList<>();
    public static final String POSITION = "ShowDataActivity";

    public static void transMission(Context context, String region, String region_code, String data_start, String data_end, String key_word) {
        Intent intent = new Intent(context, ShowDataActivity.class);
        intent.putExtra(POSITION, region);
        intent.putExtra(POSITION + 1, region_code);
        intent.putExtra(POSITION + 2, data_start);
        intent.putExtra(POSITION + 3, data_end);
        intent.putExtra(POSITION + 4, key_word);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_showdata);
    }

    @Override
    public void initData() {
        tvTitle.setText("大事记库");
        region = getIntent().getStringExtra(POSITION);
        region_code = getIntent().getStringExtra(POSITION + 1);
        data_start = getIntent().getStringExtra(POSITION + 2);
        data_end = getIntent().getStringExtra(POSITION + 3);
        key_word = getIntent().getStringExtra(POSITION + 4);
        getRecordDataNet();
    }


    private void getRecordDataNet() {
        BaseParameter hashMap = new BaseParameter();
        hashMap.put("region", region);
        hashMap.put("region_code", region_code);
        hashMap.put("date_start", data_start);
        hashMap.put("date_end", data_end);
        hashMap.put("key_word", key_word);
        Observable<RecordDataBean> recordDataNet = RtRxOkHttp.getApiService().getRecordDataNet(hashMap);
        RtRxOkHttp.getInstance().createRtRx(this, recordDataNet, this, 1);
    }

    @Override
    public void addActivity() {
        MyApplication.getInstance().addActivity(this);
    }

    @OnClick({R.id.ivBack})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivBack:
                finish();
                break;
        }
    }

    @Override
    public void httpResponse(Object info, boolean isSusccess, int sort) {
        if (isSusccess) {
            switch (sort) {
                case 1:
                    RecordDataBean recordDataBean = (RecordDataBean) info;
                    list.clear();
                    if (recordDataBean.getCode() == 200) {
                        ll_total.setVisibility(View.VISIBLE);
                        tv_total.setText("共检索到" + recordDataBean.getData().get(0).getCount() + "条数据");
                        for (int i = 0; i < recordDataBean.getData().get(0).getEvent().size(); i++) {
                            list.add(recordDataBean.getData().get(0).getEvent().get(i));
                        }
                        loadYearAdapter = new LoadYearAdapter(getApplication(), list);
                        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
                        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        recy_year.setLayoutManager(layoutManager);
                        recy_year.setAdapter(loadYearAdapter);
                    }
                    break;
            }
        }
    }
}
