package com.logicline.ivacbdapointment.ui;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.logicline.ivacbdapointment.adapters.AvailableSlotAdapter;
import com.logicline.ivacbdapointment.data.ApiInterface;
import com.logicline.ivacbdapointment.data.MyApi;
import com.logicline.ivacbdapointment.databinding.ActivityAvaliableSlotsBinding;
import com.logicline.ivacbdapointment.models.SlotTime;
import com.logicline.ivacbdapointment.utils.Constants;
import com.logicline.ivacbdapointment.utils.TimeAndDateUtils;
import com.logicline.ivacbdapointment.utils.Utils;

import java.util.List;

public class AvailableSlotsActivity extends AppCompatActivity {
    private ActivityAvaliableSlotsBinding binding;
    private ViewModel viewModel;
    private AvailableSlotAdapter adapter;

    public static Intent getAvailableSlotActivityIntent(Context context,
                                                        String date, int visaType, int ivacCode) {
        Intent intent = new Intent(context, AvailableSlotsActivity.class);
        intent.putExtra(Constants.KEY_INTENT_AVAILABLE_SLOT_DATE, date);
        intent.putExtra(Constants.KEY_INTENT_AVAILABLE_SLOT_VISA_TYPE, visaType);
        intent.putExtra(Constants.KEY_INTENT_AVAILABLE_SLOT_IVAC_CODE, ivacCode);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAvaliableSlotsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        ApiInterface myApi = MyApi.getInstance(this);
        ViewModel.MyViewModelFactory factory = new ViewModel.MyViewModelFactory(getApplication(), myApi);
        viewModel = new ViewModelProvider(this, factory).get(ViewModel.class);

        if (getIntent().hasExtra(Constants.KEY_INTENT_AVAILABLE_SLOT_DATE)){
            String date = getIntent().getStringExtra(Constants.KEY_INTENT_AVAILABLE_SLOT_DATE);
            int visaType = getIntent().getIntExtra(Constants.KEY_INTENT_AVAILABLE_SLOT_VISA_TYPE, -1);
            int ivacCode = getIntent().getIntExtra(Constants.KEY_INTENT_AVAILABLE_SLOT_IVAC_CODE, -1);

            if (visaType != -1 && ivacCode != -1){
                viewModel.fetchAvailableTimeSlot(date, visaType, ivacCode);
            }

            if (getSupportActionBar() != null){
                getSupportActionBar().setTitle(TimeAndDateUtils.getDateWithMonthName(date));
            }
        }

        initViews();
        initObservers();

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initViews(){
        binding.pbLoading.setVisibility(View.VISIBLE);
        adapter = new AvailableSlotAdapter(this);

        binding.rvAvailableTimeSlot.setLayoutManager(new LinearLayoutManager(this));
        binding.rvAvailableTimeSlot.setAdapter(adapter);
    }

    private void initObservers(){
        viewModel.getAvailableSlotTimes().observe(this, new Observer<List<SlotTime>>() {
            @Override
            public void onChanged(List<SlotTime> slotTimes) {
                binding.pbLoading.setVisibility(View.GONE);
                if (slotTimes != null && slotTimes.size() != 0){
                    binding.llNoItemFound.setVisibility(View.GONE);
                    adapter.setData(slotTimes);
                }else {
                    binding.llNoItemFound.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}