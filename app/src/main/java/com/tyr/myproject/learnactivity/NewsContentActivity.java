package com.tyr.myproject.learnactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.tyr.myproject.R;
import com.tyr.myproject.base.BaseActivity;
import com.tyr.myproject.fragment.LearnFragContentFragment;

public class NewsContentActivity extends BaseActivity {

    @Override
    public void initView() {
        setContentView(R.layout.activity_news_content);

    }

    @Override
    public void initHeadViewData() {

    }

    @Override
    public void initData() {
        String title = getIntent().getStringExtra("title");
        String content = getIntent().getStringExtra("content");
        LearnFragContentFragment contentFragment = (LearnFragContentFragment) getSupportFragmentManager().findFragmentById(R.id.learn_frag_content);
        contentFragment.refreshFrag(title,content);
    }

}
