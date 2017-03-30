package com.tyr.myproject;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.RadioGroup;

import com.tyr.myproject.base.BaseActivity;
import com.tyr.myproject.base.BaseFragment;
import com.tyr.myproject.factory.FragmentFactory;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener{
    private static final String TAG = "MainActivity";
    private RadioGroup mRgBottom;
    private FragmentFactory mFactory;
    private FragmentTransaction mTransaction;
    private FragmentManager mFManager;
    private BaseFragment mCurrentFragment;
    private boolean mIsExit;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mIsExit = false;
        }
    };
    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
        mRgBottom = (RadioGroup) findViewById(R.id.rg_bottom);
        mRgBottom.setOnCheckedChangeListener(this);

    }
    @Override
    public void initHeadViewData() {

    }

    @Override
    public void initData() {
        mFManager = getSupportFragmentManager();
        mTransaction = mFManager.beginTransaction();
        mFactory = new FragmentFactory();
        mCurrentFragment = mFactory.getFragment(R.id.rb_bottom_one);
        mTransaction.replace(R.id.frame_top_content,mCurrentFragment).commit();
        mRgBottom.check(R.id.rb_bottom_one);
    }
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        mFManager.beginTransaction().replace(R.id.frame_top_content,mFactory.getFragment(checkedId)).commit();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }else{
            return super.onKeyDown(keyCode, event);
        }
    }

    private void exit() {
        if(!mIsExit){
            mIsExit = true;
            showToast(this,"再按一次退出！");
            mHandler.sendEmptyMessageDelayed(0,2000);
        }else{
            System.exit(0);
        }
    }
}
