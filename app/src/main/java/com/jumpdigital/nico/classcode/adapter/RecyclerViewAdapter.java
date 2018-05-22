package com.jumpdigital.nico.classcode.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jumpdigital.nico.classcode.Item.Record;
import com.jumpdigital.nico.classcode.R;

import java.util.List;

/**
 * Created by Nico on 21/05/2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    Context mContext;
    List<Record> mData;

    public RecyclerViewAdapter(Context mContext, List<Record> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.item_record, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        holder.tvDateFormat.setText(mData.get(position).getDateFormat());
        holder.tvDayName.setText(mData.get(position).getDayName());
        holder.ivAttendance.setImageResource(mData.get(position).getAttendance());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivAttendance;
        private TextView tvDateFormat;
        private TextView tvDayName;

        public ViewHolder(View itemView) {
            super(itemView);
            tvDateFormat = itemView.findViewById(R.id.tv_date_format);
            tvDayName = itemView.findViewById(R.id.tv_day_name);
            ivAttendance = itemView.findViewById(R.id.attendance);
        }
    }
}
