package com.tyr.myproject.factory;

import android.util.SparseArray;

import com.tyr.myproject.R;
import com.tyr.myproject.base.BaseFragment;
import com.tyr.myproject.fragment.DayFragment;
import com.tyr.myproject.fragment.GoodFragment;
import com.tyr.myproject.fragment.LearnFragment;
import com.tyr.myproject.fragment.UpFragment;

/**
 * Created by Administrator on 2017.3.21.
 */

public class FragmentFactory {
    private SparseArray<BaseFragment> mFragments = new SparseArray<>();
    private BaseFragment mBaseFragment;
    public BaseFragment getFragment(int id){
        if(mFragments.get(id)!=null){
            return mFragments.get(id);
        }
        return creatFragment(id);
    }
    private BaseFragment creatFragment(int id){
        switch (id){
            case R.id.rb_bottom_one:
                mBaseFragment = new GoodFragment();
                break;
            case R.id.rb_bottom_two:
                mBaseFragment = new LearnFragment();
                break;
            case R.id.rb_bottom_three:
                mBaseFragment = new DayFragment();
                break;
            case R.id.rb_bottom_four:
                mBaseFragment = new UpFragment();
                break;
        }
        mFragments.put(id,mBaseFragment);
        return mFragments.get(id);
    }
}
