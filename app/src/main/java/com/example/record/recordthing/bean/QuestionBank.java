package com.example.record.recordthing.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Author : Gyk
 * CreateBy : 2019/03/05/15:40
 * PackageName : com.example.gyk.chatim.bean
 * Describe : TODO
 **/
@Entity
public class QuestionBank {
    @Id(autoincrement = true)
    Long id;
    @Unique
    int num;//题号
    String topic;//题目
    String optionA;
    String optionB;
    String optionC;
    String optionD;
    String answer;
    @Generated(hash = 1712448497)
    public QuestionBank(Long id, int num, String topic, String optionA,
            String optionB, String optionC, String optionD, String answer) {
        this.id = id;
        this.num = num;
        this.topic = topic;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.answer = answer;
    }
    @Generated(hash = 1279952239)
    public QuestionBank() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getNum() {
        return this.num;
    }
    public void setNum(int num) {
        this.num = num;
    }
    public String getTopic() {
        return this.topic;
    }
    public void setTopic(String topic) {
        this.topic = topic;
    }
    public String getOptionA() {
        return this.optionA;
    }
    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }
    public String getOptionB() {
        return this.optionB;
    }
    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }
    public String getOptionC() {
        return this.optionC;
    }
    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }
    public String getOptionD() {
        return this.optionD;
    }
    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }
    public String getAnswer() {
        return this.answer;
    }
    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
