package com.example.record.recordthing.bean;

import android.os.Parcelable;

import java.util.List;

/**
 * 项目名：RecordThing
 * 包名：com.example.record.recordthing.bean
 * 文件名：RecordBean
 * 创建者：Gyk
 * 创建时间：2019/4/2 14:51
 * 描述：  TODO
 */

public class RecordBean {

    /**
     * status : True
     * message : success
     * code : 200
     * timestamp : 2019-04-02 06:50:43
     * data : [{"event_count":0,"book_count":0}]
     */
/**/
    private String status;
    private String message;
    private int code;
    private String timestamp;
    private List<DataBean> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * event_count : 0
         * book_count : 0
         */

        private int event_count;
        private int book_count;

        public int getEvent_count() {
            return event_count;
        }

        public void setEvent_count(int event_count) {
            this.event_count = event_count;
        }

        public int getBook_count() {
            return book_count;
        }

        public void setBook_count(int book_count) {
            this.book_count = book_count;
        }
    }
}
