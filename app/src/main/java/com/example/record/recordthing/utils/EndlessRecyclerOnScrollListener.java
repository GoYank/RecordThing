package com.example.record.recordthing.utils;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Author : Gyk
 * CreateBy : 2019/02/25/16:28
 * PackageName : com.example.gyk.chatim.utils
 * Describe : TODO
 **/
public abstract class EndlessRecyclerOnScrollListener extends RecyclerView.OnScrollListener {

    //用来标记是否正在向上滑动
    private boolean isSlidingUpward = false;

    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
        //当不滑动时
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            //获取最后一个完全显示的itemPosition
            int lastItemPosition = manager.findLastCompletelyVisibleItemPosition();
            int itemCount = manager.getItemCount();

            //判断是否滑动到了最后一个item,并且是向上滑动
            if (lastItemPosition == (itemCount - 1) && isSlidingUpward) {
                //加载更多
                onLoadMore();

            }
        }
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        isSlidingUpward = dy > 0;
    }
    /**
     * 加载更多回调
     **/
    public  abstract void onLoadMore();
}