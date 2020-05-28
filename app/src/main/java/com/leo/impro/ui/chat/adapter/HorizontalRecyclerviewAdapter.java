package com.leo.impro.ui.chat.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.leo.impro.R;
import com.leo.impro.bean.ImageModel;
import com.leo.impro.utils.DensityUtils;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by leo
 * Time：2019/1/22
 * Description:底部横向滚动tab适配器
 */
public class HorizontalRecyclerviewAdapter extends RecyclerView.Adapter<HorizontalRecyclerviewAdapter.ViewHolder> {

    private List<ImageModel> datas;

    private LayoutInflater mInflater;

    private Context context;

    private OnClickItemListener onClickItemListener;

    public HorizontalRecyclerviewAdapter(Context context, List<ImageModel> datas) {
        this.datas = datas;
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_horizontal_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        ImageModel model = datas.get(position);
        /**
         * 点击事件和长按事件
         */
        if (onClickItemListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //使用该方法获取position，防止点击事件时pos未刷新问题
                    int pos = holder.getLayoutPosition();
                    onClickItemListener.onItemClick(holder.itemView, pos, datas);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    //使用该方法获取position，防止点击事件时pos未刷新问题
                    int pos = holder.getLayoutPosition();
                    onClickItemListener.onItemLongClick(holder.itemView, pos, datas);
                    return false;
                }
            });

        }
        /**
         * 动态计算底部tab的宽度。
         */
        int width = DensityUtils.getScreenWidth();
        float itemW = width / 6;
        ViewGroup.LayoutParams lp = holder.imageBtn.getLayoutParams();
        lp.width = (int) itemW;

        //设置icon
        holder.image.setImageDrawable(model.icon);
        if (model.isSelected) {
            holder.imageBtn.setBackgroundColor(context.getResources().getColor(R.color.bg_horizontal_btn_selected));
            Log.e("为什么不走", "这是选中的====" + position);
        } else {
            holder.imageBtn.setBackgroundColor(context.getResources().getColor(R.color.white));
            Log.e("为什么不走", "这未选中的====" + position);

        }

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public RelativeLayout imageBtn;
        public ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            imageBtn = (RelativeLayout) itemView.findViewById(R.id.image_btn);
            image = (ImageView) itemView.findViewById(R.id.image);
        }
    }


    /**
     * 设置监听器
     *
     * @param listener
     */
    public void setOnClickItemListener(OnClickItemListener listener) {
        this.onClickItemListener = listener;
    }


    /**
     * Created by zejian
     * Time   16/1/6 下午3:15
     * Email shinezejian@163.com
     * Description:RecyclerView点击事件接口
     */
    public interface OnClickItemListener {

        void onItemClick(View view, int position, List<ImageModel> datas);

        void onItemLongClick(View view, int position, List<ImageModel> datas);

    }

}
