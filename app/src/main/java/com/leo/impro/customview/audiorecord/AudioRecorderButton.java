package com.leo.impro.customview.audiorecord;

import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


import com.leo.impro.R;

import androidx.appcompat.widget.AppCompatButton;

/**
 * 控制录音Button
 * 1、重写onTouchEvent；（changeState方法、wantToCancel方法、reset方法）；
 * 2、编写AudioDialogManage、并与该类AudioRecorderButton进行整合；
 * 3、编写AudioManage、并与该类AudioRecorderButton进行整合；
 */

public class AudioRecorderButton extends AppCompatButton implements AudioManage.AudioStateListener {

    /**
     * AudioRecorderButton的三个状态
     */
    private static final int STATE_NORMAL = 1;           //默认状态
    private static final int STATE_RECORDERING = 2;      //录音状态
    private static final int STATE_WANT_TO_CALCEL = 3;   //取消状态

    private int mCurState = STATE_NORMAL;    // 当前录音状态
    private boolean isRecordering = false;   // 是否已经开始录音
    private boolean mReady;    // 是否触发onLongClick

    private static final int DISTANCE_Y_CANCEL = 50;

    private AudioDialogManage audioDialogManage;

    private AudioManage mAudioManage;

    private int[] arrayImageId = new int[] {R.mipmap.icon_voice_1, R.mipmap.icon_voice_2, R.mipmap.icon_voice_3
            , R.mipmap.icon_voice_4, R.mipmap.icon_voice_5, R.mipmap.icon_voice_6};

    /**
     * 正常录音完成后的回调接口
     */
    public interface AudioFinishRecorderListener{
        void onFinish(int seconds, String FilePath);
    }

    private AudioFinishRecorderListener mListener;

    /**
     * 添加监听完成后回调接口的方法
     * @param listener
     */
    public void setAudioFinishRecorderListener(AudioFinishRecorderListener listener){
        this.mListener = listener;
    }

    //构造方法
    public AudioRecorderButton(Context context) {
        super(context, null);
        // TODO Auto-generated constructor stub
    }
    public AudioRecorderButton(final Context context, AttributeSet attrs) {
        super(context, attrs);
        //实例化对话框管理器
        audioDialogManage = new AudioDialogManage(getContext());

        //音频文件保存路径
        String dir = Environment.getExternalStorageDirectory()
                + "/deepreality/VoiceCache";
        //获取音频管理器
        mAudioManage = AudioManage.getInstance(dir);
        //监听准备完成接口
        mAudioManage.setOnAudioStateListener(this);

        setOnLongClickListener(new OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                mReady = true;
                // 真正显示应该在audio end prepared以后
                //开始录音
                mAudioManage.prepareAudio();
                return false;
            }
        });

        mAudioManage.setOnAudioStatusUpdateListener(new AudioManage.OnAudioStatusUpdateListener() {

            //录音中....db为声音分贝，time为录音时长
            @Override
            public void onUpdate(double db, long time) {
                //根据分贝值来设置录音时音量图标的上下波动
                //audioDialogManage.mIcon.getDrawable().setLevel((int) (3000 + 6000 * db / 100));
                int imageIndex = 0;
                if (db > 50) {
                    imageIndex = ((int)db - 50) / 10;
                }
                if (imageIndex >= 5) {
                    imageIndex = 5;
                }
                audioDialogManage.ivVoice.setImageResource(arrayImageId[imageIndex]);
            }

        });


        // TODO Auto-generated constructor stub
    }

    /*
     * 复写onTouchEvent
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getAction();   //获取当前Action
        int x = (int) event.getX();       //获取当前的坐标
        int y = (int) event.getY();

        switch (action) {

            case MotionEvent.ACTION_DOWN:
                changeState(STATE_RECORDERING);
                break;

            case MotionEvent.ACTION_MOVE:

                // 已经开始录音状态时，根据X、Y的坐标，判断是否想要取消
                if (isRecordering) {
                    if (wantToCancel(x, y)) {
                        changeState(STATE_WANT_TO_CALCEL);
                    } else {
                        changeState(STATE_RECORDERING);
                    }
                }
                break;

            case MotionEvent.ACTION_UP:
                if (!mReady) {   //没有触发onLongClick
                    reset();
                    return super.onTouchEvent(event);
                }

                if (!isRecordering || mTime < 900) {  //录音时间过短
                    audioDialogManage.tooShort();
                    mAudioManage.cancel();
                    mHandler.sendEmptyMessageDelayed(MSG_DIALOG_DISMISS, 1300);// 延迟，1.3秒以后关闭“时间过短对话框”
                }

                else if (mCurState == STATE_RECORDERING) { //正常录制结束
                    audioDialogManage.dismissDialog();
                    // release
                    mAudioManage.release();
                    // callbackToAct
                    // 正常录制结束，回调录音时间和录音文件完整路径——在播放的时候需要使用
                    if(mListener!=null){
                        mListener.onFinish(mTime /1000, mAudioManage.getCurrentFilePath());
                    }

                } else if (mCurState == STATE_WANT_TO_CALCEL) {
                    // cancel
                    audioDialogManage.dismissDialog();
                    mAudioManage.cancel();
                }
                reset();
                break;
        }
        return super.onTouchEvent(event);
    }

    /**
     * 恢复状态以及一些标志位
     */
    private void reset() {
        isRecordering = false;
        mReady = false;                 //是否触发onLongClick
        mTime = 0;
        changeState(STATE_NORMAL);
    }

    private boolean wantToCancel(int x, int y) {
        // 判断手指的滑动是否超出范围
        if (x < 0 || x > getWidth()) {
            return true;
        }
        if (y < -DISTANCE_Y_CANCEL || y > getHeight() + DISTANCE_Y_CANCEL) {
            return true;
        }
        return false;
    }

    /**
     * 改变Button的背景和文本、展示不同状态的录音提示对话框
     * @param state
     */
    private void changeState(int state) {
        if (mCurState != state) {
            mCurState = state;
            switch (state) {
                case STATE_NORMAL:
                    setBackgroundResource(R.drawable.send_speech_btn_normal_style);
                    setText("按住说话");
                    break;

                case STATE_RECORDERING:
                    setBackgroundResource(R.drawable.send_speech_btn_pres_style);
                    setText("松开发送");
                    if (isRecordering) {
                        // 更新Dialog.recording()
                        audioDialogManage.recording();
                    }
                    break;

                case STATE_WANT_TO_CALCEL:
                    setBackgroundResource(R.drawable.send_speech_btn_pres_style);
                    setText("取消发送");
                    // 更新Dialog.wantCancel()
                    audioDialogManage.wantToCancel();
                    break;
            }
        }
    }

    /*
     * 实现“准备完毕”接口
     */
    @Override
    public void wellPrepared() {
        // TODO Auto-generated method stub
        mHandler.sendEmptyMessage(MSG_AUDIO_PREPARED);
    }

    private static final int MSG_AUDIO_PREPARED = 0x110;   //准备完全
    private static final int MSG_CURRENT_TIME = 0x111;     //当前语音时长
    private static final int MSG_DIALOG_DISMISS = 0x112;    //销毁对话框
    private static final int MSG_COUNT_DOWN_DONE = 0x113;    //录音倒计时结束

    /**
     * 接收子线程数据，并用此数据配合主线程更新UI
     * Handler运行在主线程（UI线程）中，它与子线程通过Message对象传递数据。
     * Handler接受子线程传过来的(子线程用sedMessage()方法传弟)Message对象，把这些消息放入主线程队列中，配合主线程进行更新UI。
     */
    private Handler mHandler = new Handler() {

        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case MSG_AUDIO_PREPARED:        //216:mHandler.sendEmptyMessage(MSG_AUDIO_PREPARED);
                    audioDialogManage.showRecorderingDialog();
                    isRecordering = true;
                    //已经在录制，同时开启一个获取音量、并且计时的线程
                    new Thread(mUpdateCurTimeRunnable).start();
                    break;

                case MSG_CURRENT_TIME:          //265:mHandler.sendEmptyMessage(MSG_VOICE_CHANGE);
                    audioDialogManage.updateCurTime(String.valueOf(mTime / 1000));
                    break;

                //这里在Handler里面处理DIALOG_DIMISS，是因为想让该对话框显示一段时间，延迟关闭，——详见125行
                case MSG_DIALOG_DISMISS:         //125:mHandler.sendEmptyMessageDelayed(MSG_DIALOG_DISMISS, 1300);
                    audioDialogManage.dismissDialog();
                    break;
                //处理录音时间结束
                case MSG_COUNT_DOWN_DONE:
                    mAudioManage.release();
                    // callbackToAct
                    // 正常录制结束，回调录音时间和录音文件完整路径——在播放的时候需要使用
                    if(mListener!=null){
                        mListener.onFinish(mTime /1000, mAudioManage.getCurrentFilePath());
                    }
                    audioDialogManage.dismissDialog();
                    reset();
                    break;
            }
        }
    };

    private int mTime;  //开始录音计时，计时；（在reset()中置空） 单位为毫秒
    /**
     * 更新当前录音时长的runnable
     */
    private Runnable mUpdateCurTimeRunnable = new Runnable() {

        @Override
        public void run() {

            while (isRecordering) {
                try {
                    Thread.sleep(100);
                    mTime += 100;
                    mHandler.sendEmptyMessage(MSG_CURRENT_TIME);

                    if(mTime == 60 * 1000){
                        mHandler.sendEmptyMessage(MSG_COUNT_DOWN_DONE);
                    }
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        }
    };
}
