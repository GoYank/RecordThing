package com.example.record.recordthing.fragment;


import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.record.recordthing.MyApplication;
import com.example.record.recordthing.R;
import com.example.record.recordthing.activity.ScoreActivity;
import com.example.record.recordthing.base.BaseFragment;
import com.example.record.recordthing.bean.AnswerData;
import com.example.record.recordthing.bean.QuestionBank;
import com.example.record.recordthing.greendao.AnswerDataDao;
import com.example.record.recordthing.greendao.DaoSession;
import com.example.record.recordthing.utils.CallBack;


import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author : Gyk
 * CreateBy : 2019/03/04/10:21
 * PackageName : com.example.gyk.chatim.fragment
 * Describe : TODO
 **/
public class AnswerFragment extends BaseFragment {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_contentA)
    TextView tv_contentA;
    @BindView(R.id.tv_contentB)
    TextView tv_contentB;
    @BindView(R.id.tv_contentC)
    TextView tv_contentC;
    @BindView(R.id.tv_contentD)
    TextView tv_contentD;
    @BindView(R.id.ll_A)
    LinearLayout ll_A;
    @BindView(R.id.ll_B)
    LinearLayout ll_B;
    @BindView(R.id.ll_C)
    LinearLayout ll_C;
    @BindView(R.id.ll_D)
    LinearLayout ll_D;
    @BindView(R.id.bt_answer)
    Button bt_answer;
    @BindView(R.id.tv_selectA)
    TextView tv_selectA;
    @BindView(R.id.tv_selectB)
    TextView tv_selectB;
    @BindView(R.id.tv_selectC)
    TextView tv_selectC;
    @BindView(R.id.tv_selectD)
    TextView tv_selectD;

    int id;
    CallBack callBack;
    List<QuestionBank> data = new ArrayList<>();
    HashMap<String, String> hashMap = new HashMap<>();
    List<AnswerData> answerData = new ArrayList<>();
    AnswerData answer = new AnswerData();

    @Override
    public View initView() {
        return View.inflate(getContext(), R.layout.fragment_answer, null);
    }


    public AnswerFragment bind(int id, List<QuestionBank> mData) {
        this.id = id;
        this.data = mData;
        Log.e("id", "id=" + id);
        return this;

    }

    @Override
    public void initData() {
        tv_title.setText("\t" + "\t" + data.get(id).getTopic());
        tv_selectA.setText("A");
        tv_selectB.setText("B");
        tv_selectC.setText("C");
        tv_selectD.setText("D");
        tv_contentA.setText(data.get(id).getOptionA());
        tv_contentB.setText(data.get(id).getOptionB());
        tv_contentC.setText(data.get(id).getOptionC());
        tv_contentD.setText(data.get(id).getOptionD());
        if (id == data.size() - 1) {
            bt_answer.setVisibility(View.VISIBLE);
        } else {
            bt_answer.setVisibility(View.GONE);
        }
        select("OK");
    }

    @OnClick({R.id.ll_A, R.id.ll_B, R.id.ll_C, R.id.ll_D, R.id.bt_answer})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.ll_A:
                judge("A");
                hashMap.put("" + id, "A");
                select("A");
                break;
            case R.id.ll_B:
                judge("B");
                hashMap.put("" + id, "B");
                select("B");
                break;
            case R.id.ll_C:
                judge("C");
                hashMap.put("" + id, "C");
                select("C");
                break;
            case R.id.ll_D:
                judge("D");
                hashMap.put("" + id, "D");
                select("D");
                break;
            case R.id.bt_answer:
                startActivity(new Intent(getActivity(), ScoreActivity.class));
                break;
        }
    }
    public void select(String name) {
        String select;
        if (name.equals("OK")) {
            select = hashMap.get("" + id);
        } else {
            select = name;
        }

        if (select != null) {
            switch (select) {
                case "A":
                    tv_selectA.setText("");
                    tv_selectA.setBackgroundResource(R.mipmap.ok);
                    tv_contentA.setTextColor(Color.rgb(18, 150, 219));
                    tv_selectB.setBackgroundResource(R.drawable.bg_text);
                    tv_selectB.setText("B");
                    tv_contentB.setTextColor(Color.rgb(32, 32, 32));
                    tv_selectC.setBackgroundResource(R.drawable.bg_text);
                    tv_selectC.setText("C");
                    tv_contentC.setTextColor(Color.rgb(32, 32, 32));
                    tv_selectD.setBackgroundResource(R.drawable.bg_text);
                    tv_selectD.setText("D");
                    tv_contentD.setTextColor(Color.rgb(32, 32, 32));
                    break;
                case "B":
                    tv_selectB.setText("");
                    tv_selectB.setBackgroundResource(R.mipmap.ok);
                    tv_contentB.setTextColor(Color.rgb(18, 150, 219));
                    tv_selectA.setBackgroundResource(R.drawable.bg_text);
                    tv_selectA.setText("A");
                    tv_contentA.setTextColor(Color.rgb(32, 32, 32));
                    tv_selectC.setBackgroundResource(R.drawable.bg_text);
                    tv_selectC.setText("C");
                    tv_contentC.setTextColor(Color.rgb(32, 32, 32));
                    tv_selectD.setBackgroundResource(R.drawable.bg_text);
                    tv_selectD.setText("D");
                    tv_contentD.setTextColor(Color.rgb(32, 32, 32));
                    break;
                case "C":
                    tv_selectC.setText("");
                    tv_selectC.setBackgroundResource(R.mipmap.ok);
                    tv_contentC.setTextColor(Color.rgb(18, 150, 219));
                    tv_selectB.setBackgroundResource(R.drawable.bg_text);
                    tv_selectB.setText("B");
                    tv_contentB.setTextColor(Color.rgb(32, 32, 32));
                    tv_selectA.setBackgroundResource(R.drawable.bg_text);
                    tv_selectA.setText("A");
                    tv_contentA.setTextColor(Color.rgb(32, 32, 32));
                    tv_selectD.setBackgroundResource(R.drawable.bg_text);
                    tv_selectD.setText("D");
                    tv_contentD.setTextColor(Color.rgb(32, 32, 32));

                    break;
                case "D":
                    tv_selectD.setText("");
                    tv_selectD.setBackgroundResource(R.mipmap.ok);
                    tv_contentD.setTextColor(Color.rgb(18, 150, 219));
                    tv_selectB.setBackgroundResource(R.drawable.bg_text);
                    tv_selectB.setBackgroundResource(R.drawable.bg_text);
                    tv_selectB.setText("B");
                    tv_contentB.setTextColor(Color.rgb(32, 32, 32));
                    tv_selectC.setBackgroundResource(R.drawable.bg_text);
                    tv_selectC.setText("C");
                    tv_contentC.setTextColor(Color.rgb(32, 32, 32));
                    tv_selectA.setBackgroundResource(R.drawable.bg_text);
                    tv_selectA.setText("A");
                    tv_contentA.setTextColor(Color.rgb(32, 32, 32));
                    break;
            }
        }

    }

    public void insert(int top, String an) {
        DaoSession daoSession = ((MyApplication) getActivity().getApplication()).getDaoSession();
        AnswerData answer = new AnswerData();
        answer.setTopicNum(top);
        answer.setAnswer(an);
        daoSession.insert(answer);
    }

    public List queryListByMessage(int name) {
        DaoSession daoSession = ((MyApplication) getActivity().getApplication()).getDaoSession();
        QueryBuilder<AnswerData> qb = daoSession.queryBuilder(AnswerData.class);
        QueryBuilder<AnswerData> studentQueryBuilder = qb.where(AnswerDataDao.Properties.TopicNum.eq(name)).orderAsc(AnswerDataDao.Properties.TopicNum);
        List<AnswerData> answerList = studentQueryBuilder.list(); //查出当前对应的数据
        return answerList;
    }

    public void updataData(AnswerData s) {
        DaoSession daoSession = ((MyApplication) getActivity().getApplication()).getDaoSession();
        daoSession.update(s);
    }

    public void judge(String s) {
        Log.e("gyk", "topicNum=" + data.get(id).getId().intValue());
        answerData = queryListByMessage((data.get(id).getId()).intValue());
        Log.e("gyk", "anserdata.size=" + answerData.size());
        if (answerData.size() == 0) {
            Log.e("gyk", "----insert");
            insert(data.get(id).getId().intValue(), s);
        } else {
            Log.e("gyk", "----update");
            answer.setId(answerData.get(0).getId());
            answer.setTopicNum(data.get(id).getId().intValue());
            answer.setAnswer(s);
            updataData(answer);
        }
    }
}











