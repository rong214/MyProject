package com.tyr.myproject.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tyr.myproject.R;
import com.tyr.myproject.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class DayFragment extends BaseFragment {


    @Override
    public View initView() {
        return View.inflate(context,R.layout.fragment_day,null);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initHeadViewData() {
        mBaseTitle.setText("天天");
    }
}
