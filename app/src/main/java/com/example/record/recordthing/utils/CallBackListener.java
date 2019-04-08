package com.example.record.recordthing.utils;

/**
 * Author : Gyk
 * CreateBy : 2019/03/04/11:16
 * PackageName : com.example.gyk.chatim.utils
 * Describe : TODO
 **/
public class CallBackListener {

    public CallBack callBack;

    public void setCallBack(CallBack mCallBack){
        this.callBack = mCallBack;
    }

    public void onClickListener(int position){
        callBack.onClick(position);
    }
}
