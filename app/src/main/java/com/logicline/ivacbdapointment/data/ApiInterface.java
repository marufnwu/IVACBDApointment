package com.logicline.ivacbdapointment.data;

import com.logicline.ivacbdapointment.models.AvailableDates;
import com.logicline.ivacbdapointment.models.AvailableTimeSlotSpecificDate;
import com.logicline.ivacbdapointment.models.RequestParamAvailableDates;
import com.logicline.ivacbdapointment.models.RequestParamSpecificTimeSlot;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("get_payment_options_v2")
    Call<AvailableDates> getAvailableDates(@Body RequestParamAvailableDates body);

    @POST("get_payment_options_v2")
    Call<AvailableTimeSlotSpecificDate> getTimeSlotSpecificDate(@Body RequestParamSpecificTimeSlot body);
}
