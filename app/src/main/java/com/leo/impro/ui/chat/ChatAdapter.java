package com.leo.impro.ui.chat;

import android.graphics.drawable.AnimationDrawable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.leo.impro.R;
import com.leo.impro.base.BaseViewHolder;
import com.leo.impro.bean.ChatBean;
import com.leo.impro.databinding.ItemChatBinding;
import com.leo.impro.databinding.ItemChatCancleBinding;
import com.leo.impro.databinding.ItemChatMymedicalBinding;
import com.leo.impro.databinding.ItemChatOverBinding;
import com.leo.impro.databinding.ItemChatStartBinding;
import com.leo.impro.databinding.ItemChatTwoBinding;
import com.leo.impro.databinding.ItemDoctorMedicalBinding;
import com.leo.impro.databinding.ItemDoctorMessageBinding;
import com.leo.impro.databinding.ItemDoctorPicBinding;
import com.leo.impro.databinding.ItemDoctorRecipeBinding;
import com.leo.impro.databinding.ItemDoctorVoiceBinding;
import com.leo.impro.databinding.ItemMychatMessageBinding;
import com.leo.impro.databinding.ItemMychatPicBinding;
import com.leo.impro.databinding.ItemMychatVoiceBinding;
import com.leo.impro.utils.DensityUtils;
import com.lihang.nbadapter.BaseAdapter;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by leo
 * on 2020/1/13.
 */
public class ChatAdapter extends BaseAdapter<ChatBean> {
    private View.OnClickListener onClickListener;

    public static final int TYPE_MEDICAL = 1;//病历
    public static final int TYPE_HELPER = 2;//医助

    public static final int TYPE_START = 3;//问诊开始

    public static final int TYPE_CANCLE = 13;//问诊取消

    public static final int TYPE_OVER = 14;// 问诊结束 出现待评价ui

    public static final int TYPE_DOCTOR_MESSAGE = 4;//医生的 发信息
    public static final int TYPE_MINE_MESSAGE = 7;//我的 发的信息

    public static final int TYPE_DOCTOR_MEDICAL = 6;//医生的 发的病历
    public static final int TYPE_MINE_MEDICAL = 8;//我的 我的病历本

    public static final int TYPE_DOCTOR_RECIPE = 5;//医生的 诊断和处方信息

    public static final int TYPE_MINE_VOICE = 9;//我的 语音
    public static final int TYPE_DOCTOR_VOICE = 10;//医生的 语音

    public static final int TYPE_MINE_PIC = 11;//我的 发送的图片
    public static final int TYPE_DOCTOR_PIC = 12;//医生的 发送的图片


    private int currentVoicePosition = -1;
    private AnimationDrawable animationDrawable;

    public ChatAdapter(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    public int getItemViewType(int position) {
        if (dataList.get(position).getType() == TYPE_MEDICAL) {
            return TYPE_MEDICAL;
        } else if (dataList.get(position).getType() == TYPE_START) {
            return TYPE_START;
        } else if (dataList.get(position).getType() == TYPE_DOCTOR_MESSAGE) {
            return TYPE_DOCTOR_MESSAGE;
        } else if (dataList.get(position).getType() == TYPE_DOCTOR_RECIPE) {
            return TYPE_DOCTOR_RECIPE;
        } else if (dataList.get(position).getType() == TYPE_DOCTOR_MEDICAL) {
            return TYPE_DOCTOR_MEDICAL;
        } else if (dataList.get(position).getType() == TYPE_MINE_MESSAGE) {
            return TYPE_MINE_MESSAGE;
        } else if (dataList.get(position).getType() == TYPE_MINE_MEDICAL) {
            return TYPE_MINE_MEDICAL;
        } else if (dataList.get(position).getType() == TYPE_MINE_VOICE) {
            return TYPE_MINE_VOICE;
        } else if (dataList.get(position).getType() == TYPE_DOCTOR_VOICE) {
            return TYPE_DOCTOR_VOICE;
        } else if (dataList.get(position).getType() == TYPE_MINE_PIC) {
            return TYPE_MINE_PIC;
        } else if (dataList.get(position).getType() == TYPE_DOCTOR_PIC) {
            return TYPE_DOCTOR_PIC;
        } else if (dataList.get(position).getType() == TYPE_CANCLE) {
            return TYPE_CANCLE;
        } else if (dataList.get(position).getType() == TYPE_OVER) {
            return TYPE_OVER;
        } else {
            return TYPE_HELPER;
        }
    }

    @Override
    public RecyclerView.ViewHolder getViewHolder(ViewGroup viewGroup, int viewType) {

        switch (viewType) {
            case TYPE_MEDICAL:
                //病历
                ItemChatBinding binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.item_chat, viewGroup, false);
                return new BaseViewHolder(binding);

            case TYPE_START:
                //问诊开始
                ItemChatStartBinding bindingStart = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.item_chat_start, viewGroup, false);
                return new BaseViewHolder(bindingStart);

            case TYPE_OVER:
                //问诊结束
                ItemChatOverBinding bindingOver = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.item_chat_over, viewGroup, false);
                return new BaseViewHolder(bindingOver);


            case TYPE_DOCTOR_MESSAGE:
                //医生的 发信息
                ItemDoctorMessageBinding bindingDoctorMessage = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.item_doctor_message, viewGroup, false);
                return new BaseViewHolder(bindingDoctorMessage);

            case TYPE_DOCTOR_RECIPE:
                //医生的 诊断和处方信息
                ItemDoctorRecipeBinding bindingDoctorRecipe = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.item_doctor_recipe, viewGroup, false);
                return new BaseViewHolder(bindingDoctorRecipe);

            case TYPE_DOCTOR_MEDICAL:
                //医生的 发的病历
                ItemDoctorMedicalBinding bindingDoctorMedical = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.item_doctor_medical, viewGroup, false);
                return new BaseViewHolder(bindingDoctorMedical);

            case TYPE_MINE_MESSAGE:
                //我的 发的信息
                ItemMychatMessageBinding bindingMyChat = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.item_mychat_message, viewGroup, false);
                return new BaseViewHolder(bindingMyChat);

            case TYPE_MINE_MEDICAL:
                //我的 我的病历本
                ItemChatMymedicalBinding bindingMyMedical = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.item_chat_mymedical, viewGroup, false);
                return new BaseViewHolder(bindingMyMedical);

            case TYPE_MINE_VOICE:
                //我的 语音
                ItemMychatVoiceBinding bindingMyVoice = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.item_mychat_voice, viewGroup, false);
                return new BaseViewHolder(bindingMyVoice);

            case TYPE_DOCTOR_VOICE:
                //医生的 语音
                ItemDoctorVoiceBinding bindingDoctorVoice = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.item_doctor_voice, viewGroup, false);
                return new BaseViewHolder(bindingDoctorVoice);

            case TYPE_MINE_PIC:
                //我的 发送的图片
                ItemMychatPicBinding bindingMychatPic = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.item_mychat_pic, viewGroup, false);
                return new BaseViewHolder(bindingMychatPic);

            case TYPE_DOCTOR_PIC:
                //医生的 发送的图片
                ItemDoctorPicBinding bindingDoctorPic = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.item_doctor_pic, viewGroup, false);
                return new BaseViewHolder(bindingDoctorPic);

            case TYPE_CANCLE:
                //问诊取消
                ItemChatCancleBinding bindingCancle = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.item_chat_cancle, viewGroup, false);
                return new BaseViewHolder(bindingCancle);


            default:
                //医助
                ItemChatTwoBinding bingHelp = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.item_chat_two, viewGroup, false);
                return new BaseViewHolder(bingHelp);

        }


    }

    @Override
    public void onBindMyViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        BaseViewHolder baseViewHolder = (BaseViewHolder) viewHolder;
        ChatBean item = dataList.get(i);

        //病历
        if (baseViewHolder.binding instanceof ItemChatBinding) {
            ItemChatBinding binding = (ItemChatBinding) baseViewHolder.binding;
            String txtStr = "病情描述 :  咳嗽、浑身无力、有点发低烧，咳嗽、浑身发。。";
            SpannableString spannableString = new SpannableString(txtStr);
            ForegroundColorSpan colorSpan = new ForegroundColorSpan(viewHolder.itemView.getContext().getResources().getColor(R.color.black1b));
            spannableString.setSpan(colorSpan, 0, 5, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            ForegroundColorSpan colorSpanOther = new ForegroundColorSpan(viewHolder.itemView.getContext().getResources().getColor(R.color.grey89));
            spannableString.setSpan(colorSpanOther, 5, txtStr.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            binding.txtMedicalDes.setText(spannableString);

            //医生的 发信息
        } else if (baseViewHolder.binding instanceof ItemDoctorMessageBinding) {
            ItemDoctorMessageBinding binding = (ItemDoctorMessageBinding) baseViewHolder.binding;
            if (item.getIsShowTime() == 1) {
                binding.txtTime.setVisibility(View.VISIBLE);
            } else {
                binding.txtTime.setVisibility(View.GONE);
            }

            //医生的 诊断和处方信息
        } else if (baseViewHolder.binding instanceof ItemDoctorRecipeBinding) {
            ItemDoctorRecipeBinding binding = (ItemDoctorRecipeBinding) baseViewHolder.binding;
            if (item.getIsShowTime() == 1) {
                binding.txtTime.setVisibility(View.VISIBLE);
            } else {
                binding.txtTime.setVisibility(View.GONE);
            }

            //医生的 发的病历
        } else if (baseViewHolder.binding instanceof ItemDoctorMedicalBinding) {
            ItemDoctorMedicalBinding binding = (ItemDoctorMedicalBinding) baseViewHolder.binding;
            if (item.getIsShowTime() == 1) {
                binding.txtTime.setVisibility(View.VISIBLE);
            } else {
                binding.txtTime.setVisibility(View.GONE);
            }

            //我的 发的信息
        } else if (baseViewHolder.binding instanceof ItemMychatMessageBinding) {
            ItemMychatMessageBinding binding = (ItemMychatMessageBinding) baseViewHolder.binding;
            if (item.getIsShowTime() == 1) {
                binding.txtTime.setVisibility(View.VISIBLE);
            } else {
                binding.txtTime.setVisibility(View.GONE);
            }

            if (item.getReadState() == 1) {
                binding.txtHaveRead.setVisibility(View.VISIBLE);
                binding.txtNoRead.setVisibility(View.GONE);
                binding.linearFaile.setVisibility(View.GONE);
            } else if (item.getReadState() == -1) {
                binding.txtHaveRead.setVisibility(View.GONE);
                binding.txtNoRead.setVisibility(View.GONE);
                binding.linearFaile.setVisibility(View.VISIBLE);
            } else {
                binding.txtHaveRead.setVisibility(View.GONE);
                binding.txtNoRead.setVisibility(View.VISIBLE);
                binding.linearFaile.setVisibility(View.GONE);
            }
            binding.txtChat.setText(item.getMessage());


            //我的 我的病历本
        } else if (baseViewHolder.binding instanceof ItemChatMymedicalBinding) {
            ItemChatMymedicalBinding binding = (ItemChatMymedicalBinding) baseViewHolder.binding;
            if (item.getIsShowTime() == 1) {
                binding.txtTime.setVisibility(View.VISIBLE);
            } else {
                binding.txtTime.setVisibility(View.GONE);
            }

            //我的 发送的图片
        } else if (baseViewHolder.binding instanceof ItemMychatPicBinding) {
            ItemMychatPicBinding binding = (ItemMychatPicBinding) baseViewHolder.binding;
            Glide.with(binding.imageChat).
                    load(R.mipmap.aboutest).
                    transform(new FitCenter(), new RoundedCorners(DensityUtils.dp2px(10))).
                    into(binding.imageChat);

            //医生的 发送的图片
        } else if (baseViewHolder.binding instanceof ItemDoctorPicBinding) {
            ItemDoctorPicBinding binding = (ItemDoctorPicBinding) baseViewHolder.binding;
            Glide.with(binding.imageChat).
                    load(R.mipmap.aboutest).
                    transform(new FitCenter(), new RoundedCorners(DensityUtils.dp2px(10))).
                    into(binding.imageChat);

            //我的 语音
        } else if (baseViewHolder.binding instanceof ItemMychatVoiceBinding) {
            ItemMychatVoiceBinding binding = (ItemMychatVoiceBinding) baseViewHolder.binding;
            binding.txtVoice.setWidth((int) ((int) (viewHolder.itemView.getContext().getResources().getDimension(R.dimen.dp_180) * getPecent(item.getSeconds())) + viewHolder.itemView.getContext().getResources().getDimension(R.dimen.dp_20)));
            binding.txtVoice.setText(item.getSeconds() + "''");
            binding.linearMychatVoice.setTag(item);
            binding.linearMychatVoice.setTag(R.id.linear_mychat_voice, i);
            binding.linearMychatVoice.setOnClickListener(onClickListener);

            animationDrawable = (AnimationDrawable) binding.imageVoice.getBackground();
            if (currentVoicePosition == i) {
                animationDrawable.start();
            } else {
                //恢复到第一帧
                animationDrawable.selectDrawable(0);
                animationDrawable.stop();
            }

            //问诊结束，评价
        } else if (baseViewHolder.binding instanceof ItemChatOverBinding) {
            ItemChatOverBinding binding = (ItemChatOverBinding) baseViewHolder.binding;
            binding.imageLow.setOnClickListener(onClickListener);
            binding.imageHappy.setOnClickListener(onClickListener);
        }
    }


    public void startVoice(int position) {
        this.currentVoicePosition = position;
    }

    public void endVoice() {
        this.currentVoicePosition = -1;
    }

    public int getIsStaring() {
        return currentVoicePosition;
    }

    private float getPecent(int audioLength) {
        float pecent = 1.0f;
        if (audioLength <= 100) {
            pecent = audioLength / 100f;
        }
        return pecent;
    }
}
