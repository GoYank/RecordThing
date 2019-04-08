package com.example.record.recordthing.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.backends.pipeline.Fresco;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author : Gyk
 * CreateBy : 2018/11/19/10:54
 * PackageName : com.example.gyk.chatim.base
 * Describe : TODO
 **/
public abstract class BaseFragment extends Fragment{

    private View view;
    Unbinder unbinder;
    public Context context;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = getActivity();
        Fresco.initialize(getActivity());
        view = initView();
        unbinder = ButterKnife.bind(this, view);
        initData();
        return view;


    }


    public abstract View initView();

    public abstract void initData();


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
