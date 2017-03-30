package com.tyr.myproject.bean;

/**
 * Created by Administrator on 2017.3.23.
 */

public class MsgBean {
    public String msg;
    public int type;
    public static final int SEND = 0;
    public static final int RECEIVED = 1;
    public MsgBean(String msg,int type){
        this.msg = msg;
        this.type = type;
    }
}
