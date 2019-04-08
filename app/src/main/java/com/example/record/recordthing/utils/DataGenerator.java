package com.example.record.recordthing.utils;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.record.recordthing.R;
import com.example.record.recordthing.fragment.ChatFragment;
import com.example.record.recordthing.fragment.LinkManFragment;
import com.example.record.recordthing.fragment.MyFragment;

/**
 * Author : Gyk
 * CreateBy : 2018/12/03/10:25
 * PackageName : com.example.gyk.chatim.utils
 * Describe : Fragment切换所需数据
 **/
public class DataGenerator {
    /**
     * mTabRes 未选中时的icon
     **/
    public static final int[] mTabRes = new int[]{R.mipmap.menu_home_normal, R.mipmap.menu_public_class_normal, R.mipmap.menu_user_normal};
    /**
     * mTabResPressed 选中时的icon
     **/
    public static final int[] mTabResPressed = new int[]{R.mipmap.menu_home_selected, R.mipmap.menu_public_class_selected, R.mipmap.menu_user_selected};
    /**
     * mTabTitle  tablayout的title
     **/
    public static final String[] mTabTitle = new String[]{"首页", "卡片", "我的"};

    /**
     * @param from 用来获取fragemt[]中的fragment
     **/
    public static Fragment[] getFragment(String from) {
        Fragment fragments[] = new Fragment[3];
        fragments[0] = ChatFragment.newInstance(from);//newInstance方法为Fragment父类中的方法，规定了Fragment传值
        fragments[1] = LinkManFragment.newInstance(from);
        fragments[2] = MyFragment.newInstance(from);
        return fragments;
    }

    /**
     * @param context  上下文
     * @param position position参数为tablayout切换时当前所在的位置
     *                 此方法为tablayout的setCustomView方法自定义tablayout布局所用
     **/
    public static View getTabView(Context context, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_tab, null);
        ImageView tabIcon = (ImageView) view.findViewById(R.id.tab_content_image);
        tabIcon.setImageResource(DataGenerator.mTabRes[position]);
        TextView tabText = (TextView) view.findViewById(R.id.tab_content_text);
        tabText.setText(mTabTitle[position]);
        return view;

    }
}
