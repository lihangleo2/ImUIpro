package com.leo.impro.utils.swip;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by leo
 * on 2019/8/27.
 */
public abstract class SwipBaseHolder extends RecyclerView.ViewHolder implements Extension {

    public abstract int getSlidItemWith();

    public SwipBaseHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public float getActionWidth() {
        return getSlidItemWith();
    }
}
