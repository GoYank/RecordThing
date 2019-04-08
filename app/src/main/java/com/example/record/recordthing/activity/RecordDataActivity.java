package com.example.record.recordthing.activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.record.recordthing.MyApplication;
import com.example.record.recordthing.R;
import com.example.record.recordthing.base.BaseActivity;
import com.example.record.recordthing.bean.JsBean;
import com.example.record.recordthing.utils.GetJsonDataUtil;
import com.example.record.recordthing.utils.Logger;
import com.example.record.recordthing.utils.StringUtils;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 项目名：RecordThing
 * 包名：com.example.record.recordthing.activity
 * 文件名：RecordDataActivity
 * 创建者：Gyk
 * 创建时间：2019/4/2 15:00
 * 描述：  TODO
 */

public class RecordDataActivity extends BaseActivity {

    @BindView(R.id.ll_area)
    LinearLayout ll_area;
    @BindView(R.id.tv_province)
    TextView tv_province;
    @BindView(R.id.tv_city)
    TextView tv_city;
    @BindView(R.id.tv_area)
    TextView tv_area;
    @BindView(R.id.tv_starttime)
    TextView tv_starttime;
    @BindView(R.id.tv_endtime)
    TextView tv_endtime;
    @BindView(R.id.et_keyword)
    EditText et_keyword;
    private List<JsBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private Thread thread;
    TimePickerView pvTime, pvTime1;
    private static final int MSG_LOAD_DATA = 0x0001;
    private static final int MSG_LOAD_SUCCESS = 0x0002;
    private static final int MSG_LOAD_FAILED = 0x0003;
    private static boolean isLoaded = false;
    private String region;
    private String region_code;
    private String data_start;
    private String data_end;
    private String key_word;

    @Override
    public void initView() {
        setContentView(R.layout.activity_recorddata);
    }

    @Override
    public void initData() {
        mHandler.sendEmptyMessage(MSG_LOAD_DATA);
        initTimePicker1();
        key_word = StringUtils.getText(et_keyword);
    }

    @Override
    public void addActivity() {
        MyApplication.getInstance().addActivity(this);
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_LOAD_DATA:
                    if (thread == null) {//如果已创建就不再重新创建子线程了

//                        Toast.makeText(RecordDataActivity.this, "Begin Parse Data", Toast.LENGTH_SHORT).show();
                        thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                // 子线程中解析省市区数据
                                initJsonData();
                            }
                        });
                        thread.start();
                    }
                    break;

                case MSG_LOAD_SUCCESS:
//                    Toast.makeText(RecordDataActivity.this, "Parse Succeed", Toast.LENGTH_SHORT).show();
                    isLoaded = true;
                    break;

                case MSG_LOAD_FAILED:
//                    Toast.makeText(RecordDataActivity.this, "Parse Failed", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };


    @OnClick({R.id.ll_area, R.id.ll_starttime, R.id.ll_endtime, R.id.tv_start})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.ll_area:
                if (isLoaded) {
                    showPickerView();
                } else {
//                    Toast.makeText(RecordDataActivity.this, "Please waiting until the data is parsed", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.ll_starttime:
                pvTime.show();
                break;
            case R.id.ll_endtime:
                pvTime1.show();
                break;
            case R.id.tv_start:
                if (region == null) {
                    Toast.makeText(this, "请选择地域", Toast.LENGTH_SHORT).show();
                } else {
                    if (data_start == null || data_end == null) {
                        Toast.makeText(this, "请选择开始时间或者结束时间", Toast.LENGTH_SHORT).show();
                    } else {
                        ShowDataActivity.transMission(this, region, region_code, data_start, data_end, key_word);
                    }
                }
                break;
        }
    }

    private void showPickerView() {// 弹出选择器

        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                String JsonData = new GetJsonDataUtil().getJson(getApplicationContext(), "test.json");
                ArrayList<JsBean> jsonBean = parseData(JsonData);//用Gson 转成实体
                //返回的分别是三个级别的选中位置
                String opt1tx = options1Items.size() > 0 ?
                        options1Items.get(options1).getPickerViewText() : "";
                String code = jsonBean.get(options1).getCode();
                Logger.e("tag", "code = " + code);

                String opt2tx = options2Items.size() > 0
                        && options2Items.get(options1).size() > 0 ?
                        options2Items.get(options1).get(options2) : "";
                String code1 = jsonBean.get(options1).getCityList().get(options2).getCode();
                Logger.e("tag", "code1 = " + code1);
                String opt3tx = options2Items.size() > 0
                        && options3Items.get(options1).size() > 0
                        && options3Items.get(options1).get(options2).size() > 0 ?
                        options3Items.get(options1).get(options2).get(options3) : "";
                String code2 = jsonBean.get(options1).getCityList().get(options2).getAreaList().get(options3).getCode();
                Logger.e("tag", "code2 = " + code2);
                region = opt1tx + "," + opt2tx + "," + opt3tx;
                region_code = code + "," + code1 + "," + code2;
                tv_province.setText(opt1tx);
                tv_city.setText(opt2tx);
                tv_area.setText(opt3tx);
//                String tx = opt1tx + opt2tx + opt3tx;
//                Toast.makeText(RecordDataActivity.this, tx, Toast.LENGTH_SHORT).show();
            }
        })

                .setTitleText("城市选择")
                .setDividerColor(Color.rgb(102, 187, 105))
                .setTextColorCenter(Color.BLACK)//设置选中项的颜色
                .setTextColorOut(Color.rgb(181, 181, 181))//设置没有被选中项的颜色
                .setCancelColor(Color.BLACK)
                .setSubmitColor(Color.rgb(69, 194, 130))
                .setContentTextSize(20)
                .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }

    private void initJsonData() {//解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String JsonData = new GetJsonDataUtil().getJson(this, "test.json");//获取assets目录下的json文件数据

        ArrayList<JsBean> jsonBean = parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> cityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String cityName = jsonBean.get(i).getCityList().get(c).getName();
                cityList.add(cityName);//添加城市
                ArrayList<String> city_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃

                for (int d = 0; d < jsonBean.get(i).getCityList().get(c).getAreaList().size(); d++) {
                    if (jsonBean.get(i).getCityList().get(c).getAreaList().get(d).getName() == null
                            || jsonBean.get(i).getCityList().get(c).getAreaList().size() == 0) {
                        city_AreaList.add("");
                    } else {
                        String areaName = jsonBean.get(i).getCityList().get(c).getAreaList().get(d).getName();
                        city_AreaList.add(areaName);
                    }

                }
//                city_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getAreaList());
                province_AreaList.add(city_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(cityList);

            /**
             * 添加地区数据
             */
            options3Items.add(province_AreaList);
        }

        mHandler.sendEmptyMessage(MSG_LOAD_SUCCESS);

    }


    public ArrayList<JsBean> parseData(String result) {//Gson 解析
        ArrayList<JsBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            mHandler.sendEmptyMessage(MSG_LOAD_FAILED);
        }
        return detail;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
    }

    private void initTimePicker1() {//选择出生年月日
        //控制时间范围(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
        //因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        SimpleDateFormat formatter_year = new SimpleDateFormat("yyyy ");
        String year_str = formatter_year.format(curDate);
        int year_int = (int) Double.parseDouble(year_str);


        SimpleDateFormat formatter_mouth = new SimpleDateFormat("MM ");
        String mouth_str = formatter_mouth.format(curDate);
        int mouth_int = (int) Double.parseDouble(mouth_str);

        SimpleDateFormat formatter_day = new SimpleDateFormat("dd ");
        String day_str = formatter_day.format(curDate);
        int day_int = (int) Double.parseDouble(day_str);


        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(1900, 0, 1);
        Calendar endDate = Calendar.getInstance();
        endDate.set(year_int, mouth_int - 1, day_int);

        //时间选择器
        pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                // 这里回调过来的v,就是show()方法里面所添加的 View 参数，如果show的时候没有添加参数，v则为null
                /*btn_Time.setText(getTime(date));*/
                data_start = getTime(date);
                tv_starttime.setText(getTime(date));
            }
        })
                .setType(new boolean[]{true, true, true, false, false, false}) //年月日时分秒 的显示与否，不设置则默认全部显示
                .setLabel("年", "月", "日", "", "", "")//默认设置为年月日时分秒
                .isCenterLabel(false)
                .setDividerColor(Color.rgb(102, 187, 105))
                .setTextColorCenter(Color.BLACK)//设置选中项的颜色
                .setTextColorOut(Color.rgb(181, 181, 181))//设置没有被选中项的颜色
                .setDate(selectedDate)
                .setLineSpacingMultiplier(1.2f)
                .setTextXOffset(-10, 0, 10, 0, 0, 0)//设置X轴倾斜角度[ -90 , 90°]
                .setRangDate(startDate, endDate)
//                .setBackgroundId(0x00FFFFFF) //设置外部遮罩颜色
                .setDecorView(null)
                .setCancelColor(Color.BLACK)
                .setSubmitColor(Color.rgb(69, 194, 130))
                .build();
        //时间选择器
        pvTime1 = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                // 这里回调过来的v,就是show()方法里面所添加的 View 参数，如果show的时候没有添加参数，v则为null
                /*btn_Time.setText(getTime(date));*/
                data_end = getTime(date);
                tv_endtime.setText(getTime(date));
            }
        })
                .setType(new boolean[]{true, true, true, false, false, false}) //年月日时分秒 的显示与否，不设置则默认全部显示
                .setLabel("年", "月", "日", "", "", "")//默认设置为年月日时分秒
                .isCenterLabel(false)
                .setDividerColor(Color.rgb(102, 187, 105))
                .setTextColorCenter(Color.BLACK)//设置选中项的颜色
                .setTextColorOut(Color.rgb(181, 181, 181)) //设置没有被选中项的颜色
                .setDate(selectedDate)
                .setLineSpacingMultiplier(1.2f)
                .setTextXOffset(-10, 0, 10, 0, 0, 0)//设置X轴倾斜角度[ -90 , 90°]
                .setRangDate(startDate, endDate)
//                .setBackgroundId(0x00FFFFFF) //设置外部遮罩颜色
                .setDecorView(null)
                .setCancelColor(Color.BLACK)
                .setSubmitColor(Color.rgb(69, 194, 130))
                .build();
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }
}
