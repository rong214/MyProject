package com.tyr.myproject.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tyr.myproject.R;
import com.tyr.myproject.bean.MsgBean;

import java.util.List;

/**
 * Created by Administrator on 2017.3.23.
 */

public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.ViewHolder> {
    private List<MsgBean> msgBeanList;
    public MsgAdapter(List<MsgBean> msgBeanList){
        this.msgBeanList = msgBeanList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.msg_item,parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MsgBean msgBean = msgBeanList.get(position);
        if(msgBean.type==MsgBean.RECEIVED){
            holder.llLeft.setVisibility(View.VISIBLE);
            holder.llRight.setVisibility(View.GONE);
            holder.tvLeft.setText(msgBean.msg);
        }else{
            holder.llRight.setVisibility(View.VISIBLE);
            holder.llLeft.setVisibility(View.GONE);
            holder.tvRight.setText(msgBean.msg);
        }
    }

    @Override
    public int getItemCount() {
        return msgBeanList.size();
    }

   static class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout llLeft,llRight;
       TextView tvLeft,tvRight;
        public ViewHolder(View itemView) {
            super(itemView);
            llLeft = (LinearLayout) itemView.findViewById(R.id.ll_left);
            llRight = (LinearLayout) itemView.findViewById(R.id.ll_right);
            tvLeft = (TextView) itemView.findViewById(R.id.tv_left);
            tvRight = (TextView) itemView.findViewById(R.id.tv_right);
        }
    }
}
