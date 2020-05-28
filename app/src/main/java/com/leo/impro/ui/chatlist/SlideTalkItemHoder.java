package com.leo.impro.ui.chatlist;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.leo.impro.R;
import com.leo.impro.utils.swip.SwipBaseHolder;

import androidx.annotation.NonNull;


/**
 * Created by leo
 * on 2019/8/27.
 */
public class SlideTalkItemHoder extends SwipBaseHolder {
    public RelativeLayout slide_itemView;
    public TextView txt_collect;
    public TextView txt_delete;


    public SlideTalkItemHoder(@NonNull View itemView) {
        super(itemView);
        slide_itemView = itemView.findViewById(R.id.slide_itemView);
        txt_collect = itemView.findViewById(R.id.txt_collect);
        txt_delete = itemView.findViewById(R.id.txt_delete);
    }

    @Override
    public int getSlidItemWith() {
        return (int) itemView.getContext().getResources().getDimension(R.dimen.dp_140);
    }
}
