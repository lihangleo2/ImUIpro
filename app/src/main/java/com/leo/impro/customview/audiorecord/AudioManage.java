package com.leo.impro.customview.audiorecord;

import android.media.MediaRecorder;
import android.os.Handler;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Audio管理类
 */

public class AudioManage {
    private MediaRecorder mMediaRecorder;  //MediaRecorder可以实现录音和录像。需要严格遵守API说明中的函数调用先后顺序.
    private String mDir;             // 文件夹的名称
    private String mCurrentFilePath;

    private static AudioManage mInstance;

    private boolean isPrepared; // 标识MediaRecorder准备完毕

    private AudioManage(String dir) {
        mDir = dir;
    }

    private OnAudioStatusUpdateListener audioStatusUpdateListener;

    private long startTime;

    /**
     * 回调“准备完毕”
     *
     */
    public interface AudioStateListener {
        void wellPrepared();    // prepared完毕
    }

    public AudioStateListener mListener;

    public void setOnAudioStateListener(AudioStateListener audioStateListener) {
        mListener = audioStateListener;
    }


    /**
     * 使用单例实现 AudioManage
     * @param dir
     * @return
     */
    //DialogManage主要管理Dialog，Dialog主要依赖Context，而且此Context必须是Activity的Context，
    //如果DialogManage写成单例实现，将是Application级别的，将无法释放，容易造成内存泄露，甚至导致错误
    public static AudioManage getInstance(String dir) {
        if (mInstance == null) {
            synchronized (AudioManage.class) {   // 同步
                if (mInstance == null) {
                    mInstance = new AudioManage(dir);
                }
            }
        }

        return mInstance;
    }

    /**
     * 准备录音
     */
    public void prepareAudio() {

        try {
            isPrepared = false;

            File dir = new File(mDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            String fileName = GenerateFileName(); // 文件名字
            File file = new File(dir, fileName);  // 路径+文件名字

            //MediaRecorder可以实现录音和录像。需要严格遵守API说明中的函数调用先后顺序.
            mMediaRecorder = new MediaRecorder();
            mCurrentFilePath = file.getAbsolutePath();
            mMediaRecorder.setOutputFile(file.getAbsolutePath());    // 设置输出文件
            mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);    // 设置MediaRecorder的音频源为麦克风
            mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.AAC_ADTS);    // 设置音频的格式
            mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);    // 设置音频的编码为AMR_NB

            mMediaRecorder.prepare();

            mMediaRecorder.start();
            startTime = System.currentTimeMillis();
            updateMicStatus();

            isPrepared = true; // 准备结束

            if (mListener != null) {
                mListener.wellPrepared();
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 生成文件名称
     * @return
     */
    private String GenerateFileName() {
        // TODO Auto-generated method stub
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// HH:mm:ss
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date) + ".aac";     // 生成带有时间的名字

    }

    /**
     * 释放资源
     */
    public void release() {
        mMediaRecorder.stop();
        mMediaRecorder.release();
        mMediaRecorder = null;
    }

    /**
     * 取消（释放资源+删除文件）
     */
    public void cancel() {

        release();

        if (mCurrentFilePath != null) {
            File file = new File(mCurrentFilePath);
            file.delete();    //删除录音文件
            mCurrentFilePath = null;
        }
    }

    public String getCurrentFilePath() {
        // TODO Auto-generated method stub
        return mCurrentFilePath;
    }

    private int BASE = 1;
    private int SPACE = 100;// 间隔取样时间

    public void setOnAudioStatusUpdateListener(OnAudioStatusUpdateListener audioStatusUpdateListener) {
        this.audioStatusUpdateListener = audioStatusUpdateListener;
    }

    private final Handler mHandler = new Handler();
    private Runnable mUpdateMicStatusTimer = new Runnable() {
        public void run() {
            updateMicStatus();
        }
    };

    /**
     * 更新麦克状态
     */
    private void updateMicStatus() {

        if (mMediaRecorder != null) {
            double ratio = (double)mMediaRecorder.getMaxAmplitude() / BASE;
            double db;// 分贝
            if (ratio > 1) {
                db = 20 * Math.log10(ratio);
                if(null != audioStatusUpdateListener) {
                    audioStatusUpdateListener.onUpdate(db, System.currentTimeMillis() - startTime);
                }
            }
            mHandler.postDelayed(mUpdateMicStatusTimer, SPACE);
        }
    }

    public interface OnAudioStatusUpdateListener {
        /**
         * 录音中...
         * @param db 当前声音分贝
         * @param time 录音时长
         */
        public void onUpdate(double db, long time);

    }
}
