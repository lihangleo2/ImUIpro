package com.leo.impro.utils.swip;


import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by leo
 * on 2019/8/27.
 */
public class SwipItemHelper {
    private WItemTouchHelperPlus extension;
    public SwipItemHelper(SlidTranslate slidTranslate) {
        PlusItemSlideCallback callback = new PlusItemSlideCallback(slidTranslate);
        extension = new WItemTouchHelperPlus(callback);
    }

    public void attachRecyclerView(RecyclerView recyclerView) {
        extension.attachToRecyclerView(recyclerView);
    }
}
