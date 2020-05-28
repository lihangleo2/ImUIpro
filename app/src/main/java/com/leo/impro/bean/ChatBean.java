package com.leo.impro.bean;

import java.io.Serializable;

/**
 * Created by leo
 * on 2020/5/15.
 */
public class ChatBean implements Serializable {
    //不同的消息类型
    private int type;
    //1 显示时间，非1 隐藏时间
    private int isShowTime;
    //阅读状态 1是已读 0是未读 -1是发送失败
    private int readState;

    //消息文本
    private String message;


    //语音
    private int seconds;//语音时长
    private String audioPath;//语音路径

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getIsShowTime() {
        return isShowTime;
    }

    public void setIsShowTime(int isShowTime) {
        this.isShowTime = isShowTime;
    }

    public int getReadState() {
        return readState;
    }

    public void setReadState(int readState) {
        this.readState = readState;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public String getAudioPath() {
        return audioPath;
    }

    public void setAudioPath(String audioPath) {
        this.audioPath = audioPath;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
