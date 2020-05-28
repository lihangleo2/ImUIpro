package com.leo.impro.utils.swip;

import android.graphics.Canvas;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;


/**
 * Created by WANG on 18/3/14.
 */

public class PlusItemSlideCallback extends WItemTouchHelperPlus.Callback {
    private SlidTranslate slidTranslate;

    public PlusItemSlideCallback(SlidTranslate slidTranslate) {
        this.slidTranslate = slidTranslate;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }


    @Override
    int getSlideViewWidth() {
        return 0;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(0, ItemTouchHelper.START);
    }

    @Override
    public String getItemSlideType() {
        return WItemTouchHelperPlus.SLIDE_ITEM_TYPE_ITEMVIEW;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        if (viewHolder instanceof SwipBaseHolder) {
            SwipBaseHolder swipBaseHolder = (SwipBaseHolder) viewHolder;
            float actionWidth = swipBaseHolder.getActionWidth();
            if (dX < -actionWidth) {
                dX = -actionWidth;
            }
            if (slidTranslate != null) {
                slidTranslate.translationX(swipBaseHolder, dX);
            }
        }
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
    }
}
