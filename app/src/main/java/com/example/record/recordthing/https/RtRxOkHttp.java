package com.example.record.recordthing.https;

import android.content.Context;
import android.util.Log;

import com.example.record.recordthing.utils.Constans;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static android.content.ContentValues.TAG;

/**
 * Author : Gyk
 * CreateBy : 2018/11/15/17:18
 * PackageName : com.example.gyk.retrofit.https
 * Describe : TODO
 **/
public class RtRxOkHttp<T> {

    private static Retrofit retrofit;
    private static OkHttpClient okHttpClient;
    private static BaseApi api;
    private static RtRxOkHttp instance;
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();

    public static RtRxOkHttp getInstance() {
        if (instance == null) {
            instance = new RtRxOkHttp();
        }
        return instance;
    }

    public static void init() {
        generateOkhttpClient();
        retrofit = new Retrofit.Builder()
                .baseUrl(Constans.HTTP)
                .client(okHttpClient)
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .addConverterFactory(ResponseConverterFactory.create())
                .build();
        api = retrofit.create(BaseApi.class);

    }

    public static void generateOkhttpClient() {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient.Builder()
                    .retryOnConnectionFailure(true)
                    .readTimeout(6000 * 10, TimeUnit.SECONDS)
                    .connectTimeout(6000 * 10, TimeUnit.SECONDS)
                    .addInterceptor(new LoggingInterceptor())
                    .build();
        }
    }

    //获取apiService

    public static BaseApi getApiService() {
        if (api == null) {
            init();
        }
        return api;
    }

    public Subscription createRtRx(final Context activity, Observable<T> postHttp, final HttpRxListener listener, final int sort) {
        try {
            final Subscription subscription = postHttp.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<T>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            try {
                                e.printStackTrace();
                                Log.e(TAG, "-----OnError----" + e);
                                listener.httpResponse(null, false, sort);
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }
                            e.printStackTrace();
                        }

                        @Override
                        public void onNext(T t) {
                            try {
                                listener.httpResponse(t, true, sort);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });

            return subscription;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
