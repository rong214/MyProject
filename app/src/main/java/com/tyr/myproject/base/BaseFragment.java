package com.tyr.myproject.base;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tyr.myproject.R;

import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment extends Fragment {
    public Context context;
    public TextView mBaseTitle;
    public ImageView mIvBack;
    public Intent intent;
    public View view;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = initView();
        initHeadView();
        initHeadViewData();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }
    public void initHeadView(){
        mBaseTitle = (TextView) view.findViewById(R.id.tv_base_title);
        mIvBack = (ImageView) view.findViewById(R.id.iv_back);
        mIvBack.setVisibility(View.GONE);
    }
    public abstract View initView();
    public abstract void initData();
    public abstract void initHeadViewData();
    public String getRandomString(String str){
        Random random = new Random();
        int length = random.nextInt(20)+1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(str);
        }
        return builder.toString();
    }
}
