package com.leo.impro.utils.swip;

import android.graphics.Color;

import com.lihang.nbadapter.BaseAdapter;

import java.util.Collections;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by leo
 * on 2019/8/27.
 */
public class DragSwipHelper {
    private ItemTouchHelper helper;
    private int mDragFlags;//拖拽
    private int mSwipeFlags;//侧滑删除

    //这里不封装到adapter里，是因为有些拖拽，有些人的item不是满条目的。
    //所以放这里，比较好自由扩展，包括这里的viewHolder,可以强转成你的viewHolder，即可对立面的元素操作了
    public  void attachDragRecyclerView(RecyclerView recyclerView, final BaseAdapter adapter) {
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;//拖拽
        attachRecyclerView(recyclerView, adapter, dragFlags, 0);
    }

    public  void attachSwipRecyclerView(RecyclerView recyclerView, final BaseAdapter adapter) {
        int swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;//侧滑删除
        attachRecyclerView(recyclerView, adapter, 0, swipeFlags);
    }

    public  void attachSwipAndDragRecyclerView(RecyclerView recyclerView, final BaseAdapter adapter) {
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;//拖拽
        int swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;//侧滑删除
        attachRecyclerView(recyclerView, adapter, dragFlags, swipeFlags);
    }


    public  void attachRecyclerView(RecyclerView recyclerView, final BaseAdapter adapter, final int dragFlags, final int swipeFlags) {
        mDragFlags = dragFlags;
        mSwipeFlags = swipeFlags;
        //为RecycleView绑定触摸事件
        if (helper != null) {
            return;
        }
        helper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                //首先回调的方法 返回int表示是否监听该方向
//                int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;//拖拽
//                int swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;//侧滑删除
                return makeMovementFlags(mDragFlags, mSwipeFlags);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                //滑动事件
                Collections.swap(adapter.getDataList(), viewHolder.getAdapterPosition(), target.getAdapterPosition());
                int fromPosition = viewHolder.getAdapterPosition();
                int toPosition = target.getAdapterPosition();
                adapter.notifyItemMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());
                if (toPosition > fromPosition) {
                    adapter.notifyItemRangeChanged(fromPosition, Math.abs(toPosition - fromPosition) + 1);
                } else {
                    adapter.notifyItemRangeChanged(toPosition, Math.abs(toPosition - fromPosition) + 1);
                }
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                //侧滑事件
                int removePosition = viewHolder.getAdapterPosition();
                adapter.getDataList().remove(removePosition);
                adapter.notifyItemRemoved(removePosition);
                adapter.notifyItemRangeChanged(removePosition, adapter.getItemCount());
            }


            @Override
            public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
                //item长按被选中了，选中item颜色变深
                if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
                    viewHolder.itemView.setBackgroundColor(Color.LTGRAY);
                }
                super.onSelectedChanged(viewHolder, actionState);
            }

            @Override
            public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                super.clearView(recyclerView, viewHolder);
                //松手了，将item恢复成白色
                viewHolder.itemView.setBackgroundColor(Color.WHITE);
            }

            @Override
            public boolean isLongPressDragEnabled() {
                //是否可拖拽
                return true;
            }
        });
        helper.attachToRecyclerView(recyclerView);
    }
}
