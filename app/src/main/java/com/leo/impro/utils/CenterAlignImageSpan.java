package com.leo.impro.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.style.ImageSpan;
import android.util.Log;

import androidx.annotation.NonNull;

/**
 * Created by leo
 * on 2019/1/18.
 */
public class CenterAlignImageSpan extends ImageSpan {

    private int mTop;

    public CenterAlignImageSpan(@NonNull Drawable drawable, Context context) {
        super(drawable);
        initData(context);
    }

    public CenterAlignImageSpan(@NonNull Context context, int resourceId) {
        super(context, resourceId);
        /*
         * 这个高度是 字体 size -  图片size / 2
         * */
//        mTop = 0;
    }

    public void initData(Context context) {
        mTop = (DensityUtils.dp2px( 16) - DensityUtils.dp2px( 18)) / 2;
    }


    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {

        Drawable b = getDrawable();
        canvas.save();

        //这是底部的
//        int transY = bottom - b.getBounds().bottom;
        //这是中间的
//        if (mVerticalAlignment == ALIGN_BASELINE) {
        int transY = top + ((bottom - top) / 2) - ((b.getBounds().bottom - b.getBounds().top) / 2) - mTop;
//        }
//        Log.e("最后一项的检查", transY + "=====");
        Log.e("最后一项的检查", "我的啊 mTop === " + mTop);
        Log.e("最后一项的检查", "我的啊 其他 === " + (top + ((bottom - top) / 2) - ((b.getBounds().bottom - b.getBounds().top) / 2)));

        canvas.translate(x, transY);
        b.draw(canvas);
        canvas.restore();
//        Drawable b = getDrawable();
//        Paint.FontMetricsInt fm = paint.getFontMetricsInt();
//        int transY = (y + fm.descent + y + fm.ascent) / 2 - b.getBounds().bottom / 2 ;//计算y方向的位移
//        canvas.save();
//        canvas.translate(x, transY);//绘制图片位移一段距离
//        Log.e("居然卡在这里了", transY + "============" + x);
//        b.draw(canvas);
//        canvas.restore();
    }
}

