package com.logicline.ivacbdapointment.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.logicline.ivacbdapointment.R;
import com.logicline.ivacbdapointment.databinding.ItemAvailableSlotBinding;
import com.logicline.ivacbdapointment.models.SlotTime;
import com.logicline.ivacbdapointment.utils.TimeAndDateUtils;

import java.util.List;

public class AvailableSlotAdapter extends RecyclerView.Adapter<AvailableSlotAdapter.MyViewHolder> {
    private final Context context;
    private List<SlotTime> data;

    public AvailableSlotAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_available_slot, parent, false);
        return new MyViewHolder(view);
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

    public void setData(List<SlotTime> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final ItemAvailableSlotBinding binding;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            binding = ItemAvailableSlotBinding.bind(itemView);
        }

        public void bindData(SlotTime item) {
            String date = TimeAndDateUtils.getDateWithMonthName(item.date);
            binding.tvDate.setText(date);
            binding.tvSlotTime.setText(item.time_display);
            binding.tvAvailableSits.setText(item.availableSlot + "");
            binding.tvWarning.setVisibility(View.GONE);

            if (item.availableSlot < 10) {
                binding.cv.setCardBackgroundColor(context.getColor(R.color.card_background_red));
                binding.tvWarning.setVisibility(View.VISIBLE);
            }
        }
    }
}
