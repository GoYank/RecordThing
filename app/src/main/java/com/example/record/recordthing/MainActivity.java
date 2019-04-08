package com.example.record.recordthing;


import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.record.recordthing.base.BaseActivity;
import com.example.record.recordthing.bean.QuestionBank;
import com.example.record.recordthing.greendao.DaoSession;
import com.example.record.recordthing.utils.DataGenerator;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.flAll)
    FrameLayout flAll;
    @BindView(R.id.main_tab)
    TabLayout mTabLayout;
    private long time = 0;
    private Fragment[] mFragments;
    private String id;


    public static void transMission(Context context, String id) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("id", id);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
        mFragments = DataGenerator.getFragment("Tablayout");
    }

    /**
     * initData()  tablayout的选择监听方法
     **/
    @Override
    public void initData() {
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.e("gyk", "tab.getPosition=" + tab.getPosition());
                onTabSelectIcon(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        //addTab增加标签页  newTab()创建新的标签页
        //setCustomView设置自定义布局的tablayout
        for (int i = 0; i < 3; i++) {
            mTabLayout.addTab(mTabLayout.newTab().setCustomView(DataGenerator.getTabView(this, i)));
        }
    }


    private void onTabSelectIcon(int position) {
        onTabItemSelected(position);//选择当前页所要显示的Fragment
        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            View view = mTabLayout.getTabAt(i).getCustomView();//这里用到了getCustomView,即获取自定义布局的tablayout
            ImageView icon = view.findViewById(R.id.tab_content_image);//自定义布局中的控件
            TextView text = view.findViewById(R.id.tab_content_text);//同上
            //如果i==tab.position则为选中状态
            if (i == position) {
                icon.setImageResource(DataGenerator.mTabResPressed[i]);
                text.setTextColor(getResources().getColor(R.color.black));
            } else {
                icon.setImageResource(DataGenerator.mTabRes[i]);
                text.setTextColor(getResources().getColor(android.R.color.darker_gray));
            }
        }
    }

    private void onTabItemSelected(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = mFragments[0];
                break;
            case 1:
                fragment = mFragments[1];
                break;
            case 2:
//                startActivity(new Intent(this, RegisterActivity.class));
                fragment = mFragments[2];
                break;
        }
        if (fragment != null) {
            //getSupportFragmentManager为V4包中Frament的方法，getFragmentManager为app包中Fragment的方法
            getSupportFragmentManager().beginTransaction().replace(R.id.flAll, fragment).commit();
        }
    }

    @Override
    public void addActivity() {
        MyApplication.getInstance().addActivity(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK) {
            exit();
        }
        return true;
    }


    private void exit() {
        if (System.currentTimeMillis() - time > 2000) {
            time = System.currentTimeMillis();
            Toast.makeText(this, "再点一次退出程序", Toast.LENGTH_SHORT).show();
        } else {
            moveTaskToBack(false);
        }

    }

}
