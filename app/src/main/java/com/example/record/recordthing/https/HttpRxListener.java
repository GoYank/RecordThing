package com.example.record.recordthing.https;

/**
 * Author : Gyk
 * CreateBy : 2018/11/15/17:17
 * PackageName : com.example.gyk.retrofit.https
 * Describe : TODO
 **/
public interface HttpRxListener<T> {
    void httpResponse(T info,boolean isSusccess,int sort);
}
