package com.logicline.ivacbdapointment.ui;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import com.logicline.ivacbdapointment.data.ApiInterface;
import com.logicline.ivacbdapointment.models.AvailableDates;
import com.logicline.ivacbdapointment.models.AvailableTimeSlotSpecificDate;
import com.logicline.ivacbdapointment.models.RequestParamAvailableDates;
import com.logicline.ivacbdapointment.models.RequestParamSpecificTimeSlot;
import com.logicline.ivacbdapointment.models.ScreenState;
import com.logicline.ivacbdapointment.models.SlotTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewModel extends AndroidViewModel {
    private static final String TAG = "ViewModel";
    private final String API_ACTION = "generateSlotTime";
    private final List<Object> API_INFO = new ArrayList<>();
    private final ApiInterface myApi;

    private final MutableLiveData<List<String>> availableDates = new MutableLiveData<>();
    private final MutableLiveData<List<SlotTime>> availableSlotTimes = new MutableLiveData<>();

    public ViewModel(@NonNull Application application, ApiInterface myApi) {
        super(application);
        this.myApi = myApi;
    }

    public MutableLiveData<List<String>> getAvailableDates() {
        return availableDates;
    }

    public MutableLiveData<List<SlotTime>> getAvailableSlotTimes() {
        return availableSlotTimes;
    }

    public void fetchAvailableDates(int visaType, int ivacId) {
        RequestParamAvailableDates request = new RequestParamAvailableDates(API_ACTION,
                ivacId, visaType, API_INFO);
        myApi.getAvailableDates(request).enqueue(new Callback<AvailableDates>() {
            @Override
            public void onResponse(Call<AvailableDates> call, Response<AvailableDates> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null && Objects.equals(response.body().status, "OK")) {
                        //availableDates.postValue(response.body().slot_dates.keySet());
                        Log.d(TAG, "onResponse: size " + response.body().slot_dates.size());


                        List<String> list = sortDates(response.body().slot_dates);
                        Log.d(TAG, "onResponse: after sorting  " + list.size());
                        availableDates.postValue(list);
                    } else {
                        availableDates.postValue(null);
                    }
                } else {
                    availableDates.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<AvailableDates> call, Throwable t) {
                availableDates.postValue(null);
            }
        });
    }

    public void fetchAvailableTimeSlot(String date, int visaType, int ivacId) {
        RequestParamSpecificTimeSlot request = new RequestParamSpecificTimeSlot(API_ACTION,
                date, ivacId, visaType, API_INFO);

        myApi.getTimeSlotSpecificDate(request).enqueue(new Callback<AvailableTimeSlotSpecificDate>() {
            @Override
            public void onResponse(Call<AvailableTimeSlotSpecificDate> call, Response<AvailableTimeSlotSpecificDate> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null && Objects.equals(response.body().status, "OK")) {
                        availableSlotTimes.postValue(response.body().slot_times);
                    } else {
                        availableSlotTimes.postValue(null);
                    }
                } else {
                    availableSlotTimes.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<AvailableTimeSlotSpecificDate> call, Throwable t) {
                availableSlotTimes.postValue(null);
            }
        });
    }

    private List<String> sortDates(Map<String, String> slotDates){
        List<String> mDate = new ArrayList<>();
        for(String key : slotDates.keySet()) {
            mDate.add(slotDates.get(key));
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        List<Date> dates = new ArrayList<>();
        for (String dateString : mDate) {
            try {
                Date date = dateFormat.parse(dateString);
                dates.add(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        // Sort the dates in ascending order
        Collections.sort(dates, new Comparator<Date>() {
            @Override
            public int compare(Date date1, Date date2) {
                return date1.compareTo(date2);
            }
        });

        // Format and print the sorted dates
        mDate.clear();
        for (Date date : dates) {
            String formattedDate = dateFormat.format(date);
            mDate.add(formattedDate);
        }

        return mDate;
    }


    public static class MyViewModelFactory extends ViewModelProvider.NewInstanceFactory {
        private ApiInterface myApi;
        private Application application;

        public MyViewModelFactory(Application application, ApiInterface myApi) {
            this.myApi = myApi;
            this.application = application;
        }

        @NonNull
        @Override
        public <T extends androidx.lifecycle.ViewModel> T create(@NonNull Class<T> viewModelClass) {
            if (viewModelClass == ViewModel.class) {
                return (T) new ViewModel(application, myApi);
            }
            return null;
        }
    }
}
