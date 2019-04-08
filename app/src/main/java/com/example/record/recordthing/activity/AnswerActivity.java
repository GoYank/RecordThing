package com.example.record.recordthing.activity;

import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.record.recordthing.MyApplication;
import com.example.record.recordthing.R;
import com.example.record.recordthing.adapter.FragmentAdapter;
import com.example.record.recordthing.base.BaseActivity;
import com.example.record.recordthing.bean.QuestionBank;
import com.example.record.recordthing.fragment.AnswerFragment;
import com.example.record.recordthing.greendao.DaoSession;
import com.example.record.recordthing.view.CustomViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author : Gyk
 * CreateBy : 2019/03/04/10:07
 * PackageName : com.example.gyk.chatim.activity
 * Describe : TODO
 **/
public class AnswerActivity extends BaseActivity {


    @BindView(R.id.viewpager)
    CustomViewPager viewPager;
    @BindView(R.id.tv_page)
    TextView tv_page;
    @BindView(R.id.tv_total)
    TextView tv_total;
    @BindView(R.id.shadow_right)
    ImageView shadow_right;
    @BindView(R.id.IvBack)
    ImageView IvBack;
    @BindView(R.id.tv_countDown)
    TextView tv_countDown;
    List<QuestionBank> data = new ArrayList<>();
    CountDownTimer countDownTimer;
    int select;
    @Override
    public void initView() {
        setContentView(R.layout.activity_answer);
    }


    @Override
    public void initData() {
        data = queryData();
        CountDown(10000,1000);
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            fragments.add(new AnswerFragment().bind(i, data));
        }
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(fragmentAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                shadow_right.setTranslationX(viewPager.getWidth() - i1);
            }

            @Override
            public void onPageSelected(int position) {
                tv_page.setText((position + 1) + "");
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        tv_total.setText("/" + data.size() + "");
    }


    @OnClick({R.id.IvBack})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.IvBack:
                finish();
                break;
        }
    }

    @Override
    public void addActivity() {
        MyApplication.getInstance().addActivity(this);
    }

    public List<QuestionBank> queryData() {
        List<QuestionBank> questionBanks = new ArrayList<>();
        DaoSession daoSession = ((MyApplication) getApplication()).getDaoSession();
        questionBanks = daoSession.loadAll(QuestionBank.class);
        return questionBanks;
    }


    public void CountDown(long millisInFuture, long countDownInterval) {
        countDownTimer = new CountDownTimer(millisInFuture, countDownInterval) {
            @Override
            public void onTick(long millisUntilFinished) {
                String value = String.valueOf((int)(millisUntilFinished/1000));
                tv_countDown.setText("倒计时:"+value);
            }

            @Override
            public void onFinish() {
            }
        };
        countDownTimer.start();
    }




}
