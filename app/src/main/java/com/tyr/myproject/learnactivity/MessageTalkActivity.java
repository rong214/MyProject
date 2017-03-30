package com.tyr.myproject.learnactivity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tyr.myproject.R;
import com.tyr.myproject.adapter.MsgAdapter;
import com.tyr.myproject.base.BaseActivity;
import com.tyr.myproject.bean.MsgBean;

import java.util.ArrayList;
import java.util.List;

public class MessageTalkActivity extends BaseActivity {
    private RecyclerView mRecyclerMsg;
    private EditText mEtMsg;
    private Button mBtnSend;
    private List<MsgBean> msgBeanList = new ArrayList<>();
    private MsgAdapter msgAdapter;
    @Override
    public void initView() {
        setContentView(R.layout.activity_message_talk);
        mRecyclerMsg = (RecyclerView) findViewById(R.id.recycler_msg);
        mEtMsg = (EditText) findViewById(R.id.et_edit);
        mBtnSend = (Button) findViewById(R.id.btn_send);
    }

    @Override
    public void initHeadViewData() {
        mBaseTitle.setText("消息对话");
    }

    @Override
    public void initData() {
        initMsgList();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerMsg.setLayoutManager(layoutManager);
        msgAdapter = new MsgAdapter(msgBeanList);
        mRecyclerMsg.setLayoutManager(layoutManager);
        mRecyclerMsg.setAdapter(msgAdapter);
        mBtnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = mEtMsg.getText().toString();
                if (!TextUtils.isEmpty(input)) {
                    MsgBean msgBean = new MsgBean(input,MsgBean.SEND);
                    msgBeanList.add(msgBean);
                    msgAdapter.notifyItemInserted(msgBeanList.size()-1);
                    mRecyclerMsg.scrollToPosition(msgBeanList.size()-1);
                    mEtMsg.setText("");
                }
            }
        });
    }
    public void initMsgList(){
        MsgBean msgBean1 = new MsgBean("您好，欢迎你",MsgBean.RECEIVED);
        msgBeanList.add(msgBean1);
        MsgBean msgBean2 = new MsgBean("请问你是谁",MsgBean.SEND);
        msgBeanList.add(msgBean2);
        MsgBean msgBean3 = new MsgBean("我是我",MsgBean.RECEIVED);
        msgBeanList.add(msgBean3);
    }
}
