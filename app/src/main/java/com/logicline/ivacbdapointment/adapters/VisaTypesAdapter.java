package com.logicline.ivacbdapointment.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.logicline.ivacbdapointment.R;
import com.logicline.ivacbdapointment.databinding.ItemVisaTypesBinding;
import com.logicline.ivacbdapointment.models.VisaType;
import com.logicline.ivacbdapointment.utils.Constants;

import java.util.List;

public class VisaTypesAdapter extends RecyclerView.Adapter<VisaTypesAdapter.MyViewHolder> {
    private final Context context;
    private ItemClickListener mItemClickListener;
    private List<VisaType> data;
    private int type;

    public VisaTypesAdapter(Context context, int type) {
        this.context = context;
        this.type = type;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (type == Constants.DATE_ACTIVITY_INTENT_TYPE_VISA){
            View view = LayoutInflater.from(context).inflate(R.layout.item_visa_types, parent, false);
            return new MyViewHolder(view);
        }else {
            View view = LayoutInflater.from(context).inflate(R.layout.item_ivacs, parent, false);
            return new MyViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bindData(data.get(position));
    }

    @Override
    public int getItemCount() {
        if (data == null)
            return 0;
        return data.size();
    }

    public void setData(List<VisaType> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void addData(List<VisaType> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(VisaType visaType);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final ItemVisaTypesBinding binding;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemVisaTypesBinding.bind(itemView);
        }

        @SuppressLint("UseCompatLoadingForDrawables")
        public void bindData(VisaType item) {
            binding.ivIcon.setImageDrawable(context.getDrawable(item.getImageDrawable()));
            binding.tvTitle.setText(item.getTitle());

            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mItemClickListener != null) {
                        mItemClickListener.onItemClick(item);
                    }
                }
            });
        }
    }
}
