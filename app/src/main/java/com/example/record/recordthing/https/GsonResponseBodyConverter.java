package com.example.record.recordthing.https;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Author : Gyk
 * CreateBy : 2018/11/15/16:51
 * PackageName : com.example.gyk.retrofit.https
 * Describe : Gson解析器
 **/
public class GsonResponseBodyConverter<T> implements Converter<ResponseBody,T> {

    private final Gson gson;
    private final Type type;


    public GsonResponseBodyConverter(Gson gson,Type type){
        this.gson = gson;
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
        return gson.fromJson(response,type);
    }
}
