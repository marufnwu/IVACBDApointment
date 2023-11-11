package com.logicline.ivacbdapointment.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Constants {

    public static final String KEY_INTENT_DATES_ACTIVITY =  "intentDatesActivity";
    public static final String KEY_INTENT_AVAILABLE_SLOT_DATE = "availableSlotDate";
    public static final String KEY_INTENT_AVAILABLE_SLOT_VISA_TYPE = "availableSlotVisaType";
    public static final String KEY_INTENT_AVAILABLE_SLOT_IVAC_CODE = "availableSlotIvacCode";

    public static Map<String, Integer> visaTypeMap = new HashMap<String, Integer>() {{
        put("Tourist", 3);
        put("Medical/Attendee", 13);
        put("Business", 1);
        put("Entry", 6);
        put("Student", 2);
        put("Conference", 4);
        put("Employment", 8);
        put("Transit", 5);
        put("Research/Training", 12);
        put("Other", 18);
    }};

    public static Map<String, Integer> ivacMap = new HashMap<String, Integer>() {{
        put("Barisal", 9);
        put("Bogura", 19);
        put("Brahmanbaria", 23);
        put("Chittagong", 5);
        put("Comilla", 21);
        put("Dhaka", 17);
        put("Jessore", 12);
        put("Khulna", 3);
        put("Kushtia", 24);
        put("Mymensingh", 8);
        put("Noakhali", 22);
        put("Rajshahi", 2);
        put("Rangpur", 7);
        put("Satkhira", 20);
        put("Sylhet", 4);
        put("Thakurgoan", 18);
    }};


    /*public static ArrayList visaType = new ArrayList() {
        {
            add("Tourist");
            add("Medical/Attendee");
            add("Business");
            add("Entry");
            add("Student");
            add("Conference");
            add("Employment");
            add("Transit");
            add("Research/Training");
            add("Other");
        }
    };
     */

    public static ArrayList ivacList = new ArrayList() {
        {
            add("Select A IVAC");
            add("Barisal");
            add("Bogura");
            add("Brahmanbaria");
            add("Chittagong");
            add("Comilla");
            add("Dhaka");
            add("Jessore");
            add("Khulna");
            add("Kushtia");
            add("Mymensingh");
            add("Noakhali");
            add("Rajshahi");
            add("Rangpur");
            add("Satkhira");
            add("Sylhet");
            add("Thakurgoan");
        }
    };
}
