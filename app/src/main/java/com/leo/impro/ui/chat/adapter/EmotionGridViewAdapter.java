package com.leo.impro.ui.chat.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;


import com.leo.impro.R;

import java.util.List;

import io.github.rockerhieu.emojicon.EmojiconHandler;

/**
 * Created by leo
 * Time：2019/1/22
 * Description：gridView表情布局
 */
public class EmotionGridViewAdapter extends BaseAdapter {

    private Context context;
    private List<Integer> emotionNames;
    private int itemWidth;

    public EmotionGridViewAdapter(Context context, List<Integer> emotionNames, int itemWidth) {
        this.context = context;
        this.emotionNames = emotionNames;
        this.itemWidth = itemWidth;
    }

    @Override
    public int getCount() {
        // +1 最后一个为删除按钮
        return emotionNames.size() + 1;
    }

    @Override
    public Integer getItem(int position) {
        return emotionNames.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView iv_emotion = new ImageView(context);
        // 设置内边距
        iv_emotion.setPadding(itemWidth / 8, itemWidth / 8, itemWidth / 8, itemWidth / 8);
        LayoutParams params = new LayoutParams(itemWidth, itemWidth);
        iv_emotion.setLayoutParams(params);

        //判断是否为最后一个item
        if (position == getCount() - 1) {
            iv_emotion.setImageResource(R.mipmap.compose_emotion_delete);
        } else {
            Integer item = emotionNames.get(position);
            iv_emotion.setImageResource(EmojiconHandler.getEmojiResource(context, item));
        }

        return iv_emotion;
    }

}
