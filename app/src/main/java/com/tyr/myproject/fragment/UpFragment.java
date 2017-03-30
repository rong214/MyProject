package com.tyr.myproject.fragment;


import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.tyr.myproject.R;
import com.tyr.myproject.base.BaseFragment;
import com.tyr.myproject.broadcast.MyLocalBroadCast;

import static android.content.ContentValues.TAG;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpFragment extends BaseFragment {
    private Button mBtnExit;
    private LocalBroadcastManager mBroadcastManager;
    private IntentFilter intentFilter;
    private MyLocalBroadCast myLocalBroadCast;
    @Override
    public View initView() {
        view = View.inflate(context, R.layout.fragment_up, null);
        mBtnExit = (Button) view.findViewById(R.id.btn_exit);
        return view;
    }

    @Override
    public void initData() {
        intentFilter = new IntentFilter();
        intentFilter.addAction("com.tyr.myproject.LOCAL_BROADCAST");
        myLocalBroadCast = new MyLocalBroadCast();
        mBroadcastManager = LocalBroadcastManager.getInstance(context);
        mBroadcastManager.registerReceiver(myLocalBroadCast,intentFilter);
        mBtnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent("com.tyr.myproject.LOCAL_BROADCAST");
                mBroadcastManager.sendBroadcast(intent);
                System.exit(0);
                Log.d(TAG, "onClick: ");
            }
        });
    }

    @Override
    public void initHeadViewData() {
        mBaseTitle.setText("向上");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBroadcastManager.unregisterReceiver(myLocalBroadCast);
    }
}
