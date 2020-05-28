package com.leo.impro.ui.chat.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.leo.impro.R;
import com.leo.impro.customview.ScaleCircleNavigator;
import com.leo.impro.ui.chat.adapter.EmotionGridViewAdapter;
import com.leo.impro.ui.chat.adapter.EmotionPagerAdapter;
import com.leo.impro.utils.DensityUtils;
import com.leo.impro.utils.LeoOnItemClickManagerUtils;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

/**
 * Created by leo
 * Time：2019/1/22
 *
 * 表情fragment
 */
public class EmojiFragment extends Fragment {
    private EmotionPagerAdapter emotionPagerGvAdapter;
    private ViewPager vp_complate_emotion_layout;
    private MagicIndicator magicIndicator;
    private List<GridView> emotionViews = new ArrayList<>();
    private Integer[] emoArry;// = Lemoji.DATA;

    public static EmojiFragment newInstance(Integer[] emoArry) {

        EmojiFragment fragment = new EmojiFragment();
        fragment.emoArry = emoArry;
        return fragment;
    }


    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_emoji, null);
            vp_complate_emotion_layout = view.findViewById(R.id.vp_complate_emotion_layout);
            magicIndicator = view.findViewById(R.id.magic_indicator3);
            initEmotion();
            initMagicIndicator3();
        }
        return view;

    }

    /**
     * 初始化表情面板
     * 思路：获取表情的总数，按每行存放7个表情，动态计算出每个表情所占的宽度大小（包含间距），
     * 而每个表情的高与宽应该是相等的，这里我们约定只存放3行
     * 每个面板最多存放7*3=21个表情，再减去一个删除键，即每个面板包含20个表情
     * 根据表情总数，循环创建多个容量为20的List，存放表情，对于大小不满20进行特殊
     * 处理即可。
     */
    private void initEmotion() {
        // 获取屏幕宽度
        int screenWidth = DensityUtils.getScreenWidth();
        // item的间距
        int spacing = DensityUtils.dp2px(12);
        // 动态计算item的宽度和高度
        int itemWidth = (screenWidth - spacing * 8) / 7;
        //动态计算gridview的总高度
        int gvHeight = itemWidth * 3 + spacing * 6;

        List<Integer> emotionNames = new ArrayList<>();
        // 遍历所有的表情的key

        for (int i = 0; i < emoArry.length; i++) {
            emotionNames.add(emoArry[i]);
            // 每20个表情作为一组,同时添加到ViewPager对应的view集合中
            if (emotionNames.size() == 20) {
                GridView gv = createEmotionGridView(emotionNames, screenWidth, spacing, itemWidth, gvHeight);
                emotionViews.add(gv);
                // 添加完一组表情,重新创建一个表情名字集合
                emotionNames = new ArrayList<>();
            }
        }

        // 判断最后是否有不足20个表情的剩余情况
        if (emotionNames.size() > 0) {
            GridView gv = createEmotionGridView(emotionNames, screenWidth, spacing, itemWidth, gvHeight);
            emotionViews.add(gv);
        }

        // 将多个GridView添加显示到ViewPager中
        emotionPagerGvAdapter = new EmotionPagerAdapter(emotionViews);
        vp_complate_emotion_layout.setAdapter(emotionPagerGvAdapter);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(screenWidth, gvHeight);
        vp_complate_emotion_layout.setLayoutParams(params);
    }

    /**
     * 创建显示表情的GridView
     */
    private GridView createEmotionGridView(List<Integer> emotionNames, int gvWidth, int padding, int itemWidth, int gvHeight) {
        // 创建GridView
        GridView gv = new GridView(getActivity());
        //设置点击背景透明
        gv.setSelector(android.R.color.transparent);
        //设置7列
        gv.setNumColumns(7);
        gv.setPadding(padding, padding, padding, padding);
        gv.setHorizontalSpacing(padding);
        gv.setVerticalSpacing(padding * 2);
        //设置GridView的宽高
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(gvWidth, gvHeight);
        gv.setLayoutParams(params);
        // 给GridView设置表情图片
        EmotionGridViewAdapter adapter = new EmotionGridViewAdapter(getActivity(), emotionNames, itemWidth);
        gv.setAdapter(adapter);

        /*
         * 注意这里如果只用到系统表情可用GlobalOnItemClickManagerUtils
         *
         * 既支持系统表情  又支持 自定义表情 用LeoOnItemClickManagerUtils 具体我会讲解。
         * */
        //设置全局点击事件
        gv.setOnItemClickListener(LeoOnItemClickManagerUtils.getInstance(getActivity()).getOnItemClickListener());
        return gv;
    }


    private void initMagicIndicator3() {

        ScaleCircleNavigator scaleCircleNavigator = new ScaleCircleNavigator(getActivity());
        scaleCircleNavigator.setCircleCount(emotionViews.size());
        scaleCircleNavigator.setNormalCircleColor(Color.LTGRAY);
        scaleCircleNavigator.setSelectedCircleColor(Color.DKGRAY);
        scaleCircleNavigator.setMinRadius(9);
        scaleCircleNavigator.setMaxRadius(12);
        //这是点击小圆点 可滑动的操作
//        scaleCircleNavigator.setCircleClickListener(new ScaleCircleNavigator.OnCircleClickListener() {
//            @Override
//            public void onClick(int index) {
//                mViewPager.setCurrentItem(index);
//            }
//        });
        magicIndicator.setNavigator(scaleCircleNavigator);
        ViewPagerHelper.bind(magicIndicator, vp_complate_emotion_layout);
        if (emotionViews.size() <= 1) {
            magicIndicator.setVisibility(View.INVISIBLE);
        } else {
            magicIndicator.setVisibility(View.VISIBLE);
        }
    }
}
