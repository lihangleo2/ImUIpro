package com.leo.impro.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;


import com.leo.impro.ui.chat.adapter.EmotionGridViewAdapter;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.github.rockerhieu.emojicon.EmojiconHandler;


/**
 * Created by leo
 * Time：2019/1/22
 * Email shinezejian@163.com
 * Description:点击表情的全局监听管理类
 * <p>
 * 我对这部分代码进行了重写
 * 这是点击系统表情的管理类。 不但支持自定义表情，也支持系统表情。
 */
public class LeoOnItemClickManagerUtils {

    private static LeoOnItemClickManagerUtils instance;
    private EditText mEditText;//输入框
    private static Context mContext;

    //自定义表情集合
    private ArrayList<Integer> myFaceList = new ArrayList<>();

    public static LeoOnItemClickManagerUtils getInstance(Context context) {
        mContext = context;
        if (instance == null) {
            synchronized (LeoOnItemClickManagerUtils.class) {
                if (instance == null) {
                    instance = new LeoOnItemClickManagerUtils();
                }
            }
        }
        return instance;
    }

    public void attachToEditText(EditText editText) {
        mEditText = editText;
        for (int i = 0; i < Lemoji.MYFACE.length; i++) {
            myFaceList.add(Lemoji.MYFACE[i]);
        }
    }

    public AdapterView.OnItemClickListener getOnItemClickListener() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object itemAdapter = parent.getAdapter();

                if (itemAdapter instanceof EmotionGridViewAdapter) {
                    // 点击的是表情
                    EmotionGridViewAdapter emotionGvAdapter = (EmotionGridViewAdapter) itemAdapter;

                    if (position == emotionGvAdapter.getCount() - 1) {
                        // 如果点击了最后一个回退按钮,则调用删除键事件
                        mEditText.dispatchKeyEvent(new KeyEvent(
                                KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
                    } else {

                        /*
                         * 这里我们用了自定义表情，并且点击系统表情显示IOS表情
                         *
                         * 1、先注意 EmojiconHandler 将图片资源加入
                         * 2、其次在 Lemoji 类里，定义好表情 和 对应的 key value
                         * 当然这里的edittext用的是第三方的。但这里并不冲突。
                         * 3、如果想聊天界面一样显示表情，那么像EmojiconEditText修改EmojiconTextView
                         * 同时除了系统表情外，如果要自定义表情，要和ios端保持“一致的规则”
                         * */

                        Integer resourceId = emotionGvAdapter.getItem(position);
                        String emjStr = Lemoji.findString.get(resourceId);

                        SpannableStringBuilder builder = new SpannableStringBuilder(mEditText.getText().toString().trim() + emjStr);

                        String zhengze = "\\[[^\\]]+\\]";
                        Pattern patternEmotion = Pattern.compile(zhengze, Pattern.CASE_INSENSITIVE);
                        Matcher matcher = patternEmotion.matcher(mEditText.getText().toString().trim() + emjStr);
                        while (matcher.find()) {
                            String key = matcher.group();
                            Integer targetResourceId = EmojiconHandler.sEmojisMap.get(Lemoji.findInteger.get(key));
                            Drawable drawable = mContext.getResources().getDrawable(targetResourceId);

                            //这里是我修改的支持键盘系统表情 和 自定义表情
                            int mTop = (DensityUtils.dp2px( 16) - DensityUtils.dp2px( 18)) / 2;
                            int mWidth = DensityUtils.dp2px( 18) * drawable.getIntrinsicWidth() / drawable.getIntrinsicHeight();
                            int bottom = mTop + DensityUtils.dp2px( 18);
                            drawable.setBounds(0, mTop, mWidth, bottom);

                            builder.setSpan(
                                    //这里是为了保证和 自定义edittext 显示表情位置一致
                                    new CenterAlignImageSpan(drawable, mContext), matcher.start(), matcher
                                            .end(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        }

                        mEditText.setText(builder);
                        // 将光标设置到新增完表情的右侧
                        int endPosition = mEditText.getText().toString().length();
                        mEditText.setSelection(endPosition);
                    }

                }
            }
        };
    }

}
