package com.example.record.recordthing.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.record.recordthing.R;
import com.example.record.recordthing.activity.RecordDataActivity;
import com.example.record.recordthing.base.BaseFragment;
import com.example.record.recordthing.base.BaseParameter;
import com.example.record.recordthing.bean.RecordBean;
import com.example.record.recordthing.https.HttpRxListener;
import com.example.record.recordthing.https.RtRxOkHttp;


import butterknife.BindView;
import butterknife.OnClick;
import rx.Observable;

/**
 * Author : Gyk
 * CreateBy : 2018/11/19/10:54
 * PackageName : com.example.gyk.chatim.fragment
 * Describe : TODO
 **/
public class ChatFragment extends BaseFragment implements HttpRxListener {

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tv_start)
    TextView tv_start;
    @BindView(R.id.tv_content)
    TextView tv_content;
    @BindView(R.id.tv_record)
    TextView tv_record;
    @BindView(R.id.tv_recordData)
    TextView tv_recordData;
    @Override
    public View initView() {
        return View.inflate(getContext(), R.layout.fragment_chat, null);
    }

    @Override
    public void initData() {
        tvTitle.setText("大事记库");
        tv_content.setText("\t"+"\t"+"\t"+"\t"+"中国大事记库(现当代版)是利用数字化技术收集各类历史典籍" +
                "，志书年鉴、公开出版物等文献资料中1912年1月1日(民国元年)起至今数百万条大事记、年表等数据、" +
                "使用大数据技术和AI技术进行数据分析，建立同时具备资料性" +
                "、阅读性、研究性的大型数据库系统，是研究、学习、了解中国国当代史的必备工具。"+"\n"+
                "\t"+"\t"+"\t"+"\t"+"本APP是《中国大事记库》的体验版，随机收录数据库中的部分数据，免费提供检索阅读服务" +
                "，对此有感兴趣的机构和读者可以联系后买全部数据和功能，或前往已购图书馆、档案馆等机构查询、下载数据。");
        getRecordNet();
    }


    private void getRecordNet(){
        BaseParameter hashMap = new BaseParameter();
        Observable<RecordBean> recordNet = RtRxOkHttp.getApiService().getRecordNet(hashMap);
        RtRxOkHttp.getInstance().createRtRx(getActivity(),recordNet,this,1);
    }

    @Override
    public void httpResponse(Object info, boolean isSusccess, int sort) {
        if (isSusccess){
            switch (sort){
                case 1:
                    RecordBean recordBean = (RecordBean) info;
                    if (recordBean.getCode()==200){
                        tv_record.setText(recordBean.getData().get(0).getEvent_count()+"条大事记数据");
                        tv_recordData.setText("来源自"+recordBean.getData().get(0).getBook_count()+"本文献资料");
                    }
                    break;
            }
        }
    }

    @OnClick({R.id.tv_start})
    public void onViewClicked(View v){
        switch (v.getId()){
            case R.id.tv_start:
                startActivity(new Intent(getActivity(), RecordDataActivity.class));
                break;
        }
    }

    public static ChatFragment newInstance(String arg) {
        ChatFragment fragment = new ChatFragment();
        Bundle args = new Bundle();
        args.putString("Tablayout", arg);
        fragment.setArguments(args);
        return fragment;
    }

}
