package com.example.record.recordthing.https;


import android.util.Log;


import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class LoggingInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        //这个chain里面包含了request和response，所以你要什么都可以从这里拿
        Request request = chain.request();

        long t1 = System.nanoTime();//请求发起的时间

//        Logger.e("jrq", String.format("发送请求 %s on %s%n%s",
//                request.url(), chain.connection(), request.headers()));

//        Response response = chain.proceed(request);
        long t2 = System.nanoTime();//收到响应的时间

//        if(SPUtils.getToken() == null){
//            return response;
//        }
        Request authorised = request.newBuilder()

                .build();

        //这里不能直接使用response.body().string()的方式输出日志
        //因为response.body().string()之后，response中的流会被关闭，程序会报错，我们需要创建出一
        //个新的response给应用层处理
        Response response1 = chain.proceed(authorised);
//        ResponseBody responseBody = response.peekBody(1024 * 1024);
        ResponseBody responseBody1 = response1.peekBody(1024 * 1024);
//        Logger.e("jrq",String.format("接收响应: [%s] %n返回json:【%s】 %.1fms%n%s",
//                response.request().url(),
//                responseBody.string(),
//                (t2 - t1) / 1e6d,
//                response.headers()));

        Log.e("gyk", "==http:" + response1.request().url() + "\n方式:" + response1.request().method() + "\n时间:" + (t2 - t1) / 1e6d + "\n" + responseBody1.string());
        return response1;


    }
}
