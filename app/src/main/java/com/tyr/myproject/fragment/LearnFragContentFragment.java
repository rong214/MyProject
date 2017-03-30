package com.tyr.myproject.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tyr.myproject.R;
import com.tyr.myproject.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class LearnFragContentFragment extends BaseFragment {
    private TextView mTvTitle,mTvContent;

    @Override
    public View initView() {
        view = View.inflate(context, R.layout.fragment_learn_frag_content, null);
        mTvTitle = (TextView) view.findViewById(R.id.tv_title);
        mTvContent = (TextView) view.findViewById(R.id.tv_content);
        return view;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initHeadViewData() {

    }
    public void refreshFrag(String title,String content){
        mTvTitle.setText(title);
        mTvContent.setText(content);
    }
}
