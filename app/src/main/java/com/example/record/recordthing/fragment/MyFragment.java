package com.example.record.recordthing.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.record.recordthing.R;
import com.example.record.recordthing.activity.AnswerActivity;
import com.example.record.recordthing.base.BaseFragment;

import butterknife.BindView;

/**
 * Author : Gyk
 * CreateBy : 2018/11/20/9:21
 * PackageName : com.example.gyk.chatim.fragment
 * Describe : TODO
 **/
public class MyFragment extends BaseFragment {
    @BindView(R.id.bt)
    Button bt;
    @Override
    public View initView() {
        return View.inflate(getContext(), R.layout.fragment_my,null);
    }

    @Override
    public void initData() {

    }

    public static MyFragment newInstance(String arg) {
        MyFragment fragment = new MyFragment();
        Bundle args = new Bundle();
        args.putString("Tablayout",arg);
        fragment.setArguments(args);
        return fragment;
    }
}
