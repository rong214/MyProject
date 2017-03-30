package com.tyr.myproject.learnactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tyr.myproject.R;
import com.tyr.myproject.base.BaseActivity;

public class NewsActivity extends BaseActivity {

    @Override
    public void initView() {
        setContentView(R.layout.activity_news);
    }

    @Override
    public void initHeadViewData() {
        mBaseTitle.setText("仿新闻");
    }

    @Override
    public void initData() {

    }
}
