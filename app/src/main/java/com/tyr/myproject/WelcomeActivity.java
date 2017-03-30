package com.tyr.myproject;


import android.content.Context;
import android.content.Intent;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import com.tyr.myproject.base.BaseActivity;

public class WelcomeActivity extends BaseActivity {
    private Animation mAlpaAnim;

    @Override
    public void initView() {
        setContentView(R.layout.activity_welcome);
    }

    @Override
    public void initHeadViewData() {

    }

    @Override
    public void initData() {
        mAlpaAnim = new AlphaAnimation(1f,0.5f);//没效果
        mAlpaAnim.setDuration(500);
        findViewById(R.id.iv_welcome).startAnimation(mAlpaAnim);
        mAlpaAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                intent = new Intent(context,MainActivity.class);
                context.startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
