package com.example.testsqlite.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.testsqlite.R;
import com.example.testsqlite.entity.HouseBean;

import java.util.List;

/**
 * Created by qweenhool on 2017/8/25.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<HouseBean> list;
    private Context context;
    private LayoutInflater inflater;

    public MyAdapter(Context context, List<HouseBean> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_house_property, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //设置房间名称
        holder.houseName.setText(list.get(position).getHouseName());
        //设置房间信息

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView houseName;
        private TextView houseInfo;

        public MyViewHolder(View view) {
            super(view);
            houseName = (TextView) view.findViewById(R.id.tv_house_name);
            houseInfo = (TextView) view.findViewById(R.id.tv_house_info);
        }
    }
}
