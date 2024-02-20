package com.example.whatsapp1;

public class messageModel {
    String uid,message,msgid;
    long time;

    public messageModel(String uid, String message, long time) {
        this.uid = uid;
        this.message = message;
        this.time = time;
    }

    public messageModel(String uid, String message) {
        this.uid = uid;
        this.message = message;
    }
    public messageModel(){

    }


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMsgid() {
        return msgid;
    }

    public void setMsgid(String msgid) {
        this.msgid = msgid;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
