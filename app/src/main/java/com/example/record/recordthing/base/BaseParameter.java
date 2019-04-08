package com.example.record.recordthing.base;

import java.util.HashMap;

/**
 * Author : Gyk
 * PackageName : org.chinakongzi.android.confucius.activity
 * Describe : 继承自HashMap的Parameter基类
 **/
public class BaseParameter<T, Q> extends HashMap<Object, Object> {

    public static BaseParameter getHashMap() {
        BaseParameter hashMap = new BaseParameter();

        return hashMap;
    }
}
