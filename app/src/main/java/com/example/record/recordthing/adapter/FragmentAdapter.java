package com.example.record.recordthing.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.List;

/**
 * Author : Gyk
 * CreateBy : 2019/03/04/10:19
 * PackageName : com.example.gyk.chatim.adapter
 * Describe : TODO
 **/
public class FragmentAdapter extends FragmentPagerAdapter {

    List<Fragment> data;

    public FragmentAdapter(FragmentManager fm,List<Fragment> mData) {
        super(fm);
        this.data = mData;
    }

    @Override
    public Fragment getItem(int position) {
        Log.e("gyk","position333="+position);
        return data.get(position);
    }

    @Override
    public int getCount() {
        return data.size();
    }
}
