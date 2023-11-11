package com.logicline.ivacbdapointment.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.google.gson.Gson;
import com.logicline.ivacbdapointment.R;
import com.logicline.ivacbdapointment.adapters.AvailableSlotAdapter;
import com.logicline.ivacbdapointment.adapters.VisaDatesAdapters;
import com.logicline.ivacbdapointment.adapters.VisaTypesAdapter;
import com.logicline.ivacbdapointment.data.ApiInterface;
import com.logicline.ivacbdapointment.data.MyApi;
import com.logicline.ivacbdapointment.databinding.ActivityDatesBinding;
import com.logicline.ivacbdapointment.models.AvailableDates;
import com.logicline.ivacbdapointment.models.RequestParamAvailableDates;
import com.logicline.ivacbdapointment.models.VisaType;
import com.logicline.ivacbdapointment.utils.Constants;
import com.logicline.ivacbdapointment.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DatesActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "DatesActivity";
    private ActivityDatesBinding binding;
    private ViewModel viewModel;
    private VisaType visaType;
    private int ivacCode = -1;
    private int visaCode = -1;
    private VisaDatesAdapters datesAdapters;

    public static Intent getDAtesActivityIntent(Context context, VisaType visaType){
        Intent intent = new Intent(context, DatesActivity.class);
        Gson gson = new Gson();
        intent.putExtra(Constants.KEY_INTENT_DATES_ACTIVITY, gson.toJson(visaType));

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDatesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        ApiInterface myApi = MyApi.getInstance(this);
        ViewModel.MyViewModelFactory factory = new ViewModel.MyViewModelFactory(getApplication(), myApi);


        viewModel = new ViewModelProvider(this, factory).get(ViewModel.class);

        if (getIntent().hasExtra(Constants.KEY_INTENT_DATES_ACTIVITY)){
            Gson gson = new Gson();

            String intentString = getIntent().getStringExtra(Constants.KEY_INTENT_DATES_ACTIVITY);
            visaType = gson.fromJson(intentString, VisaType.class);
            visaCode = Constants.visaTypeMap.getOrDefault(visaType.getTitle(), -1);
        }else
            return;

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
        if (visaType != null && getSupportActionBar() != null){
            getSupportActionBar().setTitle(visaType.getTitle());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,Constants.ivacList);
        binding.spnrSelectIvac.setAdapter(adapter);

        binding.spnrSelectIvac.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0){
                    String ivac = Constants.ivacList.get(position).toString();
                    ivacCode = Constants.ivacMap.getOrDefault(ivac, -1);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        datesAdapters = new VisaDatesAdapters(this);
        datesAdapters.setItemClickListener(new VisaDatesAdapters.ItemClickListener() {
            @Override
            public void onItemClick(String date) {
                Intent intent =
                        AvailableSlotsActivity.getAvailableSlotActivityIntent(
                                DatesActivity.this, date, visaCode, ivacCode);
                startActivity(intent);
            }
        });

        binding.rvDates.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.rvDates.setAdapter(datesAdapters);

        binding.btnFindDates.setOnClickListener(this);

    }

    private void initObservers(){
        viewModel.getAvailableDates().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                binding.pbLoading.setVisibility(View.GONE);
                if (strings != null){
                    datesAdapters.setData(strings);
                }else {
                    Utils.showToast(getApplicationContext(), "No Dates Available");
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        if (v == binding.btnFindDates){
            if (ivacCode != -1 && visaCode != -1 && viewModel != null){
                viewModel.fetchAvailableDates(visaCode, ivacCode);
                binding.pbLoading.setVisibility(View.VISIBLE);
            }
        }
    }
}