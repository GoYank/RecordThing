package com.example.record.recordthing.https;

import com.example.record.recordthing.bean.MessBean;
import com.example.record.recordthing.bean.RecordBean;
import com.example.record.recordthing.bean.RecordDataBean;
import com.example.record.recordthing.bean.RegisterBean;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Author : Gyk
 * CreateBy : 2018/11/15/17:19
 * PackageName : com.example.gyk.retrofit.https
 * Describe : TODO
 **/
public interface BaseApi {

    @POST("/v1/users")
    Observable<RegisterBean> regisNet(@QueryMap Map<String, String> map);
    /**
     * 资讯接口
     **/
    @GET("xboot/api/trs/channelList")
    Observable<MessBean> getMessageNet(@QueryMap Map<String, String> map);

    @GET("event/index")
    Observable<RecordBean> getRecordNet(@QueryMap Map<String, String> map);
    @GET("event/search")
    Observable<RecordDataBean> getRecordDataNet(@QueryMap Map<String, String> map);
}
