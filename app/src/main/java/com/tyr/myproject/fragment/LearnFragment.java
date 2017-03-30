package com.tyr.myproject.fragment;


import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.tyr.myproject.R;
import com.tyr.myproject.adapter.PersonAdapter;
import com.tyr.myproject.base.BaseFragment;
import com.tyr.myproject.bean.Person;
import com.tyr.myproject.learnactivity.MessageTalkActivity;
import com.tyr.myproject.learnactivity.NewsActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static android.R.attr.name;

/**
 * A simple {@link Fragment} subclass.
 */
public class LearnFragment extends BaseFragment {
    private RecyclerView mRecyclerView;
    private PersonAdapter mMyAdapter;
    private List<Person> mPersons;
    @Override
    public View initView() {
        view = View.inflate(context, R.layout.fragment_learn, null);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_fragment_two);
        return view;
    }

    @Override
    public void initData() {
        mPersons = new ArrayList<>();
        initPersonData();
//        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        GridLayoutManager layoutManager = new GridLayoutManager(context,3);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mMyAdapter = new PersonAdapter(mPersons,new PersonAdapter.OnRecyclerItemClickListener(){

            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(context, "回调点击"+view.getTag(), Toast.LENGTH_SHORT).show();
                if(position%2==0){
                    intent = new Intent(context, MessageTalkActivity.class);
                    context.startActivity(intent);
                }else{
                    intent = new Intent(context, NewsActivity.class);
                    context.startActivity(intent);
                }
            }
        });
        mRecyclerView.setAdapter(mMyAdapter);
    }
    public void initPersonData(){
        for (int i = 0; i < 50; i++) {
            Person p = new Person();
            p.name = getRandomLengthName("name"+i);
            p.age = i+"";
            mPersons.add(p);
        }
    }

    @Override
    public void initHeadViewData() {
        mBaseTitle.setText("学习");
    }
    private String getRandomLengthName(String name) {
        Random random = new Random();
        int length = random.nextInt(20) + 1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(name);
        }
        return builder.toString();
    }
}
