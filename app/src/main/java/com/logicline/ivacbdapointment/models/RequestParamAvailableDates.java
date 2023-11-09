package com.logicline.ivacbdapointment.models;


import java.util.ArrayList;
import java.util.List;

public class RequestParamAvailableDates {
        public String action;
        public int ivac_id;
        public int visa_type;
        public List<Object> info;

    public RequestParamAvailableDates(String action, int ivac_id, int visa_type, List<Object> info) {
        this.action = action;
        this.ivac_id = ivac_id;
        this.visa_type = visa_type;
        this.info = info;
    }
}
