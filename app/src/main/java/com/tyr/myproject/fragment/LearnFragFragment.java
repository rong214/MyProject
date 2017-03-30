package com.tyr.myproject.fragment;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tyr.myproject.R;
import com.tyr.myproject.base.BaseFragment;
import com.tyr.myproject.bean.Person;
import com.tyr.myproject.learnactivity.NewsContentActivity;

import java.util.ArrayList;
import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

/**
 * A simple {@link Fragment} subclass.
 * 兼容pad的写法
 */
public class LearnFragFragment extends BaseFragment {
    private RecyclerView mRecycler;
    public List<Person> mNewsList = new ArrayList<>();//用Person类代替新闻类
    private boolean isTwoPage;
    @Override
    public View initView() {
        view = View.inflate(context,R.layout.base_recycleview,null);
        mRecycler = (RecyclerView) view.findViewById(R.id.recycler_view);
        return view;
    }

    @Override
    public void initData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        mRecycler.setLayoutManager(layoutManager);
        mRecycler.setAdapter(new LearnFragAdapter());
        initNewsList();
        if(getActivity().findViewById(R.id.frame_new_content)!=null){
            isTwoPage = true;//可以找到frame_new_content布局时，为双页模式
        }else{
            isTwoPage = false;//找不到frame_new_content布局时，为单页模式
        }
    }

    @Override
    public void initHeadViewData() {

    }
    public void initNewsList(){
        for (int i = 0;i<30;i++) {
            Person p = new Person();
            p.name = "新闻标题"+i;
            p.age = getRandomString("这里是新闻内容");
            mNewsList.add(p);
        }
    }
    class LearnFragAdapter extends RecyclerView.Adapter<LearnFragAdapter.ViewHolder>{

        @Override
        public LearnFragAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.news_item, parent, false);
            final ViewHolder holder = new ViewHolder(view);
            holder.tv_title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Person p = mNewsList.get(holder.getAdapterPosition());
                    if(isTwoPage){
                        LearnFragContentFragment contentFragment = (LearnFragContentFragment) getFragmentManager().findFragmentById(R.id.news_content_fragment);
                        contentFragment.refreshFrag(p.name,p.age);
                    }else{
                        intent = new Intent(context, NewsContentActivity.class);
                        intent.putExtra("title",p.name);
                        intent.putExtra("content",p.age);
                        context.startActivity(intent);
                    }
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(LearnFragAdapter.ViewHolder holder, int position) {
            Person person = mNewsList.get(holder.getAdapterPosition());
            holder.tv_title.setText(person.name);
        }

        @Override
        public int getItemCount() {
            return mNewsList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{
            TextView tv_title;
            public ViewHolder(View itemView) {
                super(itemView);
                tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            }
        }
    }
}
