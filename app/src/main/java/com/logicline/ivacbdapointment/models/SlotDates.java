package com.logicline.ivacbdapointment.models;


import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class SlotDates {
    @SerializedName("slot_dates")
    public Map<String, String> slotDates;
    private List<String> mDate = new ArrayList<>();

    public void setSlotDates(Map<String, String> slotDates) {
        this.slotDates = slotDates;
    }


    public List<String> getSlotDates(){
        sortDates();
        return mDate;
    }

    private void sortDates(){
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
        for (Date date : dates) {
            mDate.clear();
            String formattedDate = dateFormat.format(date);
            mDate.add(formattedDate);
        }
    }
}
