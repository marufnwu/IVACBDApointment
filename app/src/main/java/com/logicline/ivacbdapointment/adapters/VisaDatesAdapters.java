package com.logicline.ivacbdapointment.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.logicline.ivacbdapointment.R;
import com.logicline.ivacbdapointment.databinding.ItemDateBinding;
import com.logicline.ivacbdapointment.ui.ViewModel;
import com.logicline.ivacbdapointment.utils.TimeAndDateUtils;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class VisaDatesAdapters extends RecyclerView.Adapter<VisaDatesAdapters.MyViewHolder>{
    private ItemClickListener mItemClickListener;
    private Context context;
    private List<String> data;

    public VisaDatesAdapters(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public VisaDatesAdapters.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_date, parent, false);
        return new VisaDatesAdapters.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VisaDatesAdapters.MyViewHolder holder, int position) {
        holder.bindData(data.get(position));
    }

    @Override
    public int getItemCount() {
        if (data == null)
            return 0;
        return data.size();
    }

    public void setData(List<String> data){
        this.data = data;
        notifyDataSetChanged();
    }

    public void setItemClickListener(ItemClickListener listener){
        this.mItemClickListener = listener;
    }

    public interface ItemClickListener{
        void onItemClick(String date);
    }
    class MyViewHolder extends RecyclerView.ViewHolder {

        private ItemDateBinding binding;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemDateBinding.bind(itemView);
        }

        public void bindData(String date){
            binding.tvDate.setText(TimeAndDateUtils.getDateWithMonthName(date));

            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mItemClickListener != null){
                        mItemClickListener.onItemClick(date);
                    }
                }
            });
        }
    }
}
