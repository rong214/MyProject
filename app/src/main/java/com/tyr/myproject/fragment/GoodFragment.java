package com.tyr.myproject.fragment;


import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.tyr.myproject.R;
import com.tyr.myproject.base.BaseFragment;
import com.tyr.myproject.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class GoodFragment extends BaseFragment implements ViewPager.OnPageChangeListener{
    private ViewPager mVpager;
    private MyVpAdapter mVpAdapter;
    private LinearLayout mLayoutGroup;
    private int[] mListImage = {R.drawable.vp_one,R.drawable.vp_two,R.drawable.vp_three};
    private List<ImageView> ivs = new ArrayList<ImageView>();
    private Handler mHandler = new Handler(){
        private int currentItem;
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            // 检查消息队列并移除未发送的消息，这主要是避免在复杂环境下消息出现重复等问题。
            if (hasMessages(Constants.Viewpage_Next)) {
                removeMessages(Constants.Viewpage_Next);
            }
            switch (msg.what) {
                case Constants.Viewpage_Next:
                    currentItem = (mVpager.getCurrentItem());
                    mVpager.setCurrentItem(currentItem + 1);
                    mHandler.sendEmptyMessageDelayed(Constants.Viewpage_Next, 3000);
                    break;
                case Constants.MSG_BREAK_SILENT:
                    mHandler.sendEmptyMessageDelayed(Constants.Viewpage_Next, 3000);
                    break;
                case Constants.MSG_PAGE_CHANGED:
                    currentItem = msg.obtain().arg1;
                case Constants.MSG_KEEP_SILENT://手动拖拽什么都不做
                    break;
            }
        }
    };
    @Override
    public View initView() {
        view =  View.inflate(context,R.layout.fragment_good,null);
        mVpager = (ViewPager) view.findViewById(R.id.vp_fragment_one);
        mLayoutGroup = (LinearLayout) view.findViewById(R.id.ll_ivgroup);
        return view;
    }

    @Override
    public void initData() {
        initViewPager();
    }

    @Override
    public void initHeadViewData() {
        mBaseTitle.setText("好好");
    }
    public void initViewPager() {
        ivs.removeAll(ivs);
        mLayoutGroup.removeAllViews();
            for (int i = 0; i < mListImage.length; i++) {
                ImageView iv = new ImageView(context);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(13, 13);
                params.setMargins(5, 0, 5, 0);
                iv.setLayoutParams(params);
                mLayoutGroup.addView(iv);
                ivs.add(iv);
                if (i == 0) {
                    iv.setImageResource(R.drawable.quan);
                } else {
                    iv.setImageResource(R.drawable.quana);
                }
            }
            mVpAdapter = new MyVpAdapter();
            mVpager.setAdapter(mVpAdapter);
            mVpager.setOnPageChangeListener(this);
            mVpager.setCurrentItem(mListImage.length*100);
            mVpager.setOffscreenPageLimit(2);
            mHandler.sendEmptyMessageDelayed(Constants.MSG_BREAK_SILENT, 2000);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mHandler.sendMessage(Message.obtain(mHandler,
                Constants.MSG_PAGE_CHANGED, position, 0));
        for (int i = 0; i < mListImage.length; i++) {
            ivs.get(i).setImageResource(R.drawable.quana);
        }
        ivs.get(position % mListImage.length).setImageResource(R.drawable.quan);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        switch (state) {
            case ViewPager.SCROLL_STATE_DRAGGING://手动拖拽的
                mHandler.sendEmptyMessage(Constants.MSG_KEEP_SILENT);
                break;
            case ViewPager.SCROLL_STATE_IDLE://闲置的
                mHandler.sendEmptyMessageDelayed(Constants.Viewpage_Next, 3000);
                break;
            default:
                break;
        }
    }

    private class MyVpAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView iv = new ImageView(context);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            iv.setImageResource(mListImage[position % mListImage.length]);
            container.addView(iv);
            return iv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }


    }
}
