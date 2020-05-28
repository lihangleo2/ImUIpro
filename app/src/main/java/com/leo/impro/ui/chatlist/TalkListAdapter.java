package com.leo.impro.ui.chatlist;

import android.view.View;
import android.view.ViewGroup;

import com.leo.impro.R;
import com.leo.impro.utils.swip.SlidTranslate;
import com.leo.impro.utils.swip.SwipBaseHolder;
import com.lihang.nbadapter.BaseAdapter;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by leo
 * on 2019/8/27.
 */
public class TalkListAdapter extends BaseAdapter<String> implements SlidTranslate {

    public int type;

    public void setType(int type) {
        this.type = type;
    }

    View.OnClickListener onClickListener;

    public TalkListAdapter(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    public RecyclerView.ViewHolder getViewHolder(ViewGroup viewGroup, int viewType) {

        return new SlideTalkItemHoder(getResId(viewGroup, R.layout.item_talklist));

    }

    @Override
    public void onBindMyViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
        SlideTalkItemHoder slideItemHoder = (SlideTalkItemHoder) viewHolder;
        String itemBean = dataList.get(i);

        slideItemHoder.txt_collect.setTag(i);
        slideItemHoder.txt_collect.setOnClickListener(onClickListener);
        slideItemHoder.txt_delete.setTag(i);
        slideItemHoder.txt_delete.setOnClickListener(onClickListener);
    }


    @Override
    public void translationX(SwipBaseHolder swipBaseHolder, float dX) {
        SlideTalkItemHoder slideItemHoder = (SlideTalkItemHoder) swipBaseHolder;
        slideItemHoder.slide_itemView.setTranslationX(dX);
    }
}
