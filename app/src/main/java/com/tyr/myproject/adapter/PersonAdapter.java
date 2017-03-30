package com.tyr.myproject.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.tyr.myproject.R;
import com.tyr.myproject.bean.Person;
import com.tyr.myproject.learnactivity.MessageTalkActivity;

import java.util.List;

/**
 * Created by Administrator on 2017.3.22.
 */

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {
    private List<Person> mPersonList;
    private OnRecyclerItemClickListener onRecyclerItemClickListener;
    static class ViewHolder extends RecyclerView.ViewHolder {
        View allPerson;
        TextView personName, personAge;

        public ViewHolder(View itemView) {
            super(itemView);
            allPerson = itemView;
            personName = (TextView) itemView.findViewById(R.id.tv_name);
            personAge = (TextView) itemView.findViewById(R.id.tv_age);
        }
    }

    public PersonAdapter(List<Person> personList,OnRecyclerItemClickListener onRecyclerItemClickListener) {
        this.mPersonList = personList;
        this.onRecyclerItemClickListener = onRecyclerItemClickListener;
    }

    @Override
    public PersonAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.personAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Person person = mPersonList.get(position);
                Toast.makeText(v.getContext(), person.age, Toast.LENGTH_SHORT).show();
            }
        });
        holder.allPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(v.getContext(), MessageTalkActivity.class);
//                v.getContext().startActivity(intent);
                if(onRecyclerItemClickListener!=null){
                    onRecyclerItemClickListener.onItemClick(holder.allPerson, (int)holder.allPerson.getTag());
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(PersonAdapter.ViewHolder holder, int position) {
        Person person = mPersonList.get(position);
        holder.personName.setText(person.name);
        holder.personAge.setText(person.age);
        holder.allPerson.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mPersonList.size();
    }
    /**
     * 自定义RecyclerView 中item view点击回调方法
     */
    public interface OnRecyclerItemClickListener{
        /**
         * item view 回调方法
         * @param view  被点击的view
         * @param position 点击索引
         */
        void onItemClick(View view, int position);
    }
}
