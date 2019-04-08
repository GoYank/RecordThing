package com.example.record.recordthing.https;

import android.app.Activity;
import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.content.ContentValues.TAG;

/**
 * Author : Gyk
 * CreateBy : 2018/11/15/16:22
 * PackageName : com.example.gyk.retrofit.https
 * Describe : OkHttp
 **/
public class OkHttpUtil {
    private static OkHttpClient mOkHttpClient;
    private static String str;

    public static void getAsynHttp(String url, Callback callback){
        final OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();

        if (mOkHttpClient == null){
            mOkHttpClient = httpBuilder
                    //设置超时时间
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(15,TimeUnit.SECONDS)
                    .build();
        }
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(callback);
    }

    public static void postAsynHttp(final Activity activity, String url, final FormBody.Builder builder,final int i,final HttpInterface httpInterface){
        final OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();

        if (mOkHttpClient == null){
            mOkHttpClient = httpBuilder
                    .connectTimeout(10,TimeUnit.SECONDS)
                    .writeTimeout(15,TimeUnit.SECONDS)
                    .build();
        }
        FormBody formBody = builder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        if (((FormBody)request.body()).size()>0){
           String method = request.method();
           if ("POST".equals(method)){
               StringBuffer stringBuffer = new StringBuffer();
               if (request.body() instanceof FormBody){
                   FormBody body = (FormBody) request.body();
                   for (int j=0;j<body.size();j++){
                       stringBuffer.append(body.encodedName(j)+"="+body.encodedValue(j)+",");
                   }
                   stringBuffer.delete(stringBuffer.length() - 1,stringBuffer.length());
                   Log.e(TAG, "参数---"+stringBuffer.toString());
               }
           }
        }

        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG,"网络错误(OnFailure)"+e);
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (httpInterface != null){
                                httpInterface.succeed(i,str,false);
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try{
                    str = response.body().string();
                    Log.e(TAG,"---访问结果---"+str);
                }catch (IOException e){
                    e.printStackTrace();
                }
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            if (httpInterface != null){
                                httpInterface.succeed(i,str,true);
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
            }
        });

    }


    public static void postHttp(String url,final FormBody.Builder builder,Callback callback){
        final OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();

        if (mOkHttpClient == null){
            mOkHttpClient = httpBuilder
                    .connectTimeout(10,TimeUnit.SECONDS)
                    .writeTimeout(15,TimeUnit.SECONDS)
                    .build();
        }

        FormBody formBody = builder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        if (((FormBody)request.body()).size()>0){
            String method = request.method();
            if ("POST".equals(method)){
                StringBuffer stringBuffer = new StringBuffer();
                if (request.body() instanceof  FormBody){
                    FormBody body = (FormBody) request.body();
                    for (int j=0;j<body.size();j++){
                        stringBuffer.append(body.encodedName(j)+"="+body.encodedValue(j)+"&");
                    }
                    stringBuffer.delete(stringBuffer.length() - 1,stringBuffer.length());
                    Log.e(TAG,"参数---"+stringBuffer.toString());
                }
            }
        }
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(callback);
    }


    public interface HttpInterface{
        void succeed(int i,String result,boolean isSuccess);
    }
}




