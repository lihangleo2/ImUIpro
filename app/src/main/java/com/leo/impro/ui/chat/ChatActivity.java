package com.leo.impro.ui.chat;


import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.leo.impro.R;
import com.leo.impro.bean.ChatBean;
import com.leo.impro.bean.ImageModel;
import com.leo.impro.customview.audiorecord.AudioRecorderButton;
import com.leo.impro.customview.emj.EmotionKeyboard;
import com.leo.impro.customview.popup.CommonPopupWindow;
import com.leo.impro.databinding.ActivityChatBinding;
import com.leo.impro.ui.chat.adapter.HorizontalRecyclerviewAdapter;
import com.leo.impro.ui.chat.adapter.NoHorizontalScrollerVPAdapter;
import com.leo.impro.ui.chat.fragment.EmojiFragment;
import com.leo.impro.ui.chat.fragment.TestFragment;
import com.leo.impro.utils.Lemoji;
import com.leo.impro.utils.LeoOnItemClickManagerUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.viewpager.widget.ViewPager;

/**
 * Created by leo
 * on 2020/5/28.
 */
public class ChatActivity extends AppCompatActivity implements View.OnClickListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener, CommonPopupWindow.PopItemListener {
    ActivityChatBinding binding;

    private ChatAdapter adapter;
    ArrayList<ChatBean> chatList = new ArrayList<>();

    /**
     * 表情 软键盘
     */

    //软键盘布局
    private EditText edit_content;
    private ImageView emotion_button;//表情按钮
    private TextView text_send; // 发送按钮
    private ImageView img_voice; //切换语音按钮
    private AudioRecorderButton audioRecordButton;//语音按钮

    //表情面板
    private EmotionKeyboard mEmotionKeyboard;

    //申请权限
    private RxPermissions rxPermissions;

    //语音播放的
    private MediaPlayer mMediaPlayer;

    //    private int voicePlayPosition = -1;
    private CommonPopupWindow popEvaluate;

    /**
     * 底部表情的
     * */
    private HorizontalRecyclerviewAdapter horizontalRecyclerviewAdapter;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    ArrayList<ImageModel> sourceList = new ArrayList<>();
    private int oldPosition = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_chat);

        rxPermissions = new RxPermissions(this);
        edit_content = findViewById(R.id.bar_edit_text);
        emotion_button = findViewById(R.id.emotion_button);
        img_voice = findViewById(R.id.img_voice);
        audioRecordButton = findViewById(R.id.audioRecordButton);
        text_send = findViewById(R.id.text_send);
        adapter = new ChatAdapter(this);


        ChatBean chatBean1 = new ChatBean();
        chatBean1.setType(1);
        chatList.add(chatBean1);

        ChatBean chatBean2 = new ChatBean();
        chatBean2.setType(2);
        chatList.add(chatBean2);

        ChatBean chatBean3 = new ChatBean();
        chatBean3.setType(3);
        chatList.add(chatBean3);

        ChatBean chatBean4 = new ChatBean();
        chatBean4.setType(4);
//        chatBean4.setIsShowTime(1);
        chatList.add(chatBean4);
        chatList.add(chatBean4);
        chatList.add(chatBean4);

        ChatBean chatBean5 = new ChatBean();
        chatBean5.setType(5);
        chatList.add(chatBean5);

        ChatBean chatBean6 = new ChatBean();
        chatBean6.setType(6);
        chatList.add(chatBean6);

        ChatBean chatBean7 = new ChatBean();
        chatBean7.setType(7);
        chatBean7.setReadState(1);
        chatBean7.setMessage("你好~小逗比医生！~");
        chatList.add(chatBean7);

        ChatBean chatBean8 = new ChatBean();
        chatBean8.setType(8);
        chatList.add(chatBean8);


        ChatBean chatBean11 = new ChatBean();
        chatBean11.setType(11);
        chatList.add(chatBean11);

        ChatBean chatBean12 = new ChatBean();
        chatBean12.setType(12);
        chatList.add(chatBean12);


        ChatBean chatBean10 = new ChatBean();
        chatBean10.setType(10);
        chatList.add(chatBean10);

        ChatBean chatBean13 = new ChatBean();
        chatBean13.setType(13);
        chatList.add(chatBean13);

        ChatBean chatBean14 = new ChatBean();
        chatBean14.setType(14);
        chatList.add(chatBean14);


        adapter.setDataList(chatList);
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.scrollToPosition(adapter.getItemCount() - 1);
        initEmj();
        initVoiceRecord();
        initMedia();
        setListener();
        initEmjData();

    }



    private void initEmjData() {
        /*
         * 注意这里如果只用到系统表情可用GlobalOnItemClickManagerUtils
         *
         * 既支持系统表情  又支持 自定义表情 用LeoOnItemClickManagerUtils 具体我会讲解。
         * */
        LeoOnItemClickManagerUtils.getInstance(ChatActivity.this).attachToEditText(edit_content);


        ImageModel model1 = new ImageModel();
        model1.icon = getResources().getDrawable(R.mipmap.emj_xiao);
        model1.flag = "经典笑脸";
        model1.isSelected = true;
        sourceList.add(model1);

        for (int i = 0; i < 4; i++) {
            if (i == 0) {
                ImageModel model2 = new ImageModel();
                model2.icon = getResources().getDrawable(R.mipmap.gole);
                model2.flag = "其他";
                model2.isSelected = false;
                sourceList.add(model2);
            } else if (i == 1) {
                ImageModel model2 = new ImageModel();
                model2.icon = getResources().getDrawable(R.drawable.dding1);
                model2.flag = "逗比";
                model2.isSelected = false;
                sourceList.add(model2);
            } else {
                ImageModel model2 = new ImageModel();
                model2.icon = getResources().getDrawable(R.mipmap.emj_add);
                model2.flag = "其他";
                model2.isSelected = false;
                sourceList.add(model2);
            }

        }

        //底部tab
        horizontalRecyclerviewAdapter = new HorizontalRecyclerviewAdapter(ChatActivity.this, sourceList);
        binding.recyclerviewHorizontal.setHasFixedSize(true);//使RecyclerView保持固定的大小,这样会提高RecyclerView的性能
        binding.recyclerviewHorizontal.setAdapter(horizontalRecyclerviewAdapter);
        binding.recyclerviewHorizontal.setLayoutManager(new GridLayoutManager(ChatActivity.this, 1, GridLayoutManager.HORIZONTAL, false));
        //初始化recyclerview_horizontal监听器
        horizontalRecyclerviewAdapter.setOnClickItemListener(new HorizontalRecyclerviewAdapter.OnClickItemListener() {
            @Override
            public void onItemClick(View view, int position, List<ImageModel> datas) {
                //修改背景颜色的标记
                datas.get(oldPosition).isSelected = false;
                //记录当前被选中tab下标
                datas.get(position).isSelected = true;
                //通知更新，这里我们选择性更新就行了
                horizontalRecyclerviewAdapter.notifyItemChanged(oldPosition);
                horizontalRecyclerviewAdapter.notifyItemChanged(position);

                //viewpager界面切换
                binding.viewPager.setCurrentItem(position, false);
                oldPosition = position;
            }

            @Override
            public void onItemLongClick(View view, int position, List<ImageModel> datas) {
            }
        });

        fragments.add(EmojiFragment.newInstance(Lemoji.DATA));
        fragments.add(EmojiFragment.newInstance(Lemoji.SUNDATA));
        fragments.add(EmojiFragment.newInstance(Lemoji.MYFACE));
        fragments.add(TestFragment.newInstance(4));
        fragments.add(TestFragment.newInstance(5));

        NoHorizontalScrollerVPAdapter adapter = new NoHorizontalScrollerVPAdapter(getSupportFragmentManager(), fragments);
        binding.viewPager.setAdapter(adapter);


        binding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {

                //修改背景颜色的标记
                sourceList.get(oldPosition).isSelected = false;
                //记录当前被选中tab下标
                sourceList.get(position).isSelected = true;
                //通知更新，这里我们选择性更新就行了
                horizontalRecyclerviewAdapter.notifyItemChanged(oldPosition);
                horizontalRecyclerviewAdapter.notifyItemChanged(position);

                //viewpager界面切换
                binding.viewPager.setCurrentItem(position, false);
                oldPosition = position;

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });


    }


    private void initMedia() {
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
    }


    private void initVoiceRecord() {
        audioRecordButton.setAudioFinishRecorderListener(new AudioRecorderButton.AudioFinishRecorderListener() {
            @Override
            public void onFinish(int seconds, String FilePath) {
                ChatBean chatBean9 = new ChatBean();
                chatBean9.setType(9);
                chatBean9.setSeconds(seconds);
                chatBean9.setAudioPath(FilePath);
                chatList.add(chatBean9);
                adapter.notifyDataSetChanged();
                binding.recyclerView.scrollToPosition(adapter.getItemCount() - 1);
            }
        });
    }


    private void initEmj() {
        mEmotionKeyboard = EmotionKeyboard.with(ChatActivity.this)
                .setEmotionView(findViewById(R.id.ll_emotion_layout))//绑定表情面板
                .bindToRxPerimission(rxPermissions)
                .bindToContent(binding.smartRefreshLayout)//绑定内容view
                .bindToEditText(edit_content)//判断绑定那种EditView
                .bindToEmotionButton(emotion_button)//绑定表情按钮
                .bindToVoiceButton(img_voice)
                .bindToVoiceStart(audioRecordButton)
                .bindToSend(text_send)
                .build();

        /*
         * 注意这里如果只用到系统表情可用GlobalOnItemClickManagerUtils
         *
         * 既支持系统表情  又支持 自定义表情 用LeoOnItemClickManagerUtils 具体我会讲解。
         * */
//        GlobalOnItemClickManagerUtils.getInstance(MainActivity.this).attachToEditText(edit_content);

        text_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChatBean chatBean = new ChatBean();
                chatBean.setType(7);
                chatBean.setMessage(edit_content.getText().toString());
                edit_content.setText("");
                chatList.add(chatBean);
                adapter.notifyDataSetChanged();
                binding.recyclerView.scrollToPosition(adapter.getItemCount() - 1);
            }
        });
    }

    private void setListener() {
        mMediaPlayer.setOnPreparedListener(this);
        mMediaPlayer.setOnCompletionListener(this);
        binding.setOnClickListener(this);
        binding.leoTitleBar.image_left.setOnClickListener(this);
        initPopup();
    }


    private void initPopup() {
        popEvaluate = new CommonPopupWindow.Builder(this)
                .setView(R.layout.item_evaluate_pop)
                .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .setOutsideTouchable(true)//在外不可用手指取消
                .setViewOnclickListener(this)
                .setAnimationStyle(R.style.pop_animation)//设置popWindow的出场动画
                .create();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bar_image_left:
                finish();
                break;

            case R.id.linear_mychat_voice: // 点击我的语音 进行播放
                ChatBean chatBeanMyVoice = (ChatBean) v.getTag();
                int position = (int) v.getTag(R.id.linear_mychat_voice);

                if (adapter.getIsStaring() != -1 && adapter.getIsStaring() == position) {
                    // 如果是正在播放，而且点击的是 上次点击的，那么就进行暂停功能。
                    mMediaPlayer.pause();
                    adapter.endVoice();
                    adapter.notifyDataSetChanged();
                    return;
                }

//                voicePlayPosition = position;
                adapter.startVoice(position);
                //如果要优化，就只针对于播放那里进行优化
//                adapter.notifyItemChanged(voicePlayPosition);
                adapter.notifyDataSetChanged();
                //播放录音
                mMediaPlayer.reset();
                try {
                    //String path = "/storage/emulated/0/kairui/VoiceCache/2018-09-30 10:20:26.amr";
                    File file = new File(chatBeanMyVoice.getAudioPath());
                    FileInputStream fis = new FileInputStream(file);
                    mMediaPlayer.setDataSource(fis.getFD());
                    mMediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case R.id.image_happy:
                popEvaluate.showBottom(binding.getRoot(), 0.5f);
                break;
            case R.id.image_low:
                popEvaluate.showBottom(binding.getRoot(), 0.5f);
                break;
        }
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev != null && ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {
                mEmotionKeyboard.closeAndEmj();
            }
        }
        return super.dispatchTouchEvent(ev);
    }


    /**
     * 判断当前点击屏幕的地方是否是软键盘：
     * 根据点击Y坐标和当前edittext的Y轴坐标进行比对，高于的话 那么就是点击在软键盘外
     * 收起软键盘
     */
    public static boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            v.getLocationInWindow(leftTop);
            int left = leftTop[0], top = leftTop[1], bottom = top + v.getHeight(), right = left
                    + v.getWidth();
            /*
             * 重点 : 这里坐了个判断 只要是点击键盘上方。收起软件
             * */
            if (event.getY() > top) {
                // 保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        adapter.endVoice();
        adapter.notifyDataSetChanged();
//        adapter.notifyItemChanged(voicePlayPosition);
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mMediaPlayer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMediaPlayer.pause();
        adapter.endVoice();
        adapter.notifyDataSetChanged();
//        adapter.notifyItemChanged(voicePlayPosition);
    }

    @Override
    public void getChildView(View view) {
        view.findViewById(R.id.image_pop_delete).setOnClickListener((View v) -> {
            popEvaluate.dismiss();
        });
    }
}
