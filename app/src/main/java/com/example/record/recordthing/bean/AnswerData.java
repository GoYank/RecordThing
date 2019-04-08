package com.example.record.recordthing.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Author : Gyk
 * CreateBy : 2019/03/07/10:19
 * PackageName : com.example.gyk.chatim.bean
 * Describe : TODO
 **/
@Entity
public class AnswerData {
    @Id(autoincrement = true)
    Long id;
    @Unique
    int topicNum;
    String answer;
    @Generated(hash = 1100256174)
    public AnswerData(Long id, int topicNum, String answer) {
        this.id = id;
        this.topicNum = topicNum;
        this.answer = answer;
    }
    @Generated(hash = 1298808599)
    public AnswerData() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getTopicNum() {
        return this.topicNum;
    }
    public void setTopicNum(int topicNum) {
        this.topicNum = topicNum;
    }
    public String getAnswer() {
        return this.answer;
    }
    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
