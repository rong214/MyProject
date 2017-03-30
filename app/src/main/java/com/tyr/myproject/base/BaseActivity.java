package com.tyr.myproject.base;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tyr.myproject.R;

public abstract class BaseActivity extends AppCompatActivity {
    private static final String TAG = "BaseActivity";
    public TextView mBaseTitle;
    public ImageView mIvBack;
    public Intent intent;
    public Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        initView();
        initHeadView();
        initHeadViewData();
        initData();
        Log.d(TAG, getClass().getSimpleName());
    }
    public void initHeadView(){
        mBaseTitle = (TextView) findViewById(R.id.tv_base_title);
        mIvBack = (ImageView) findViewById(R.id.iv_back);
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public abstract void initView();
    public abstract void initHeadViewData();
    public abstract void initData();
    private Toast mToast;
    public void showToast(Context context, String text) {
        if(mToast == null) {
            mToast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(text);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    public void cancelToast() {
        if (mToast != null) {
            mToast.cancel();
        }
    }
    @Override
    public void onBackPressed() {
        cancelToast();
        super.onBackPressed();
    }
}
