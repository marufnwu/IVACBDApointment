package com.logicline.ivacbdapointment.ui.home;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.logicline.ivacbdapointment.R;
import com.logicline.ivacbdapointment.models.VisaType;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<List<VisaType>> visaTypeMutableLiveData;

    public HomeViewModel() {
        visaTypeMutableLiveDataList();
    }

    public MutableLiveData<List<VisaType>> getVisaTypeMutableLiveData() {
        return visaTypeMutableLiveData;
    }

    private void visaTypeMutableLiveDataList() {
        ArrayList visaTypes = new ArrayList() {
            {
                add(new VisaType("Tourist", R.drawable.ic_tourist));
                add(new VisaType("Medical/Attendee", R.drawable.ic_medical));
                add(new VisaType("Business", R.drawable.ic_business));
                add(new VisaType("Entry", R.drawable.ic_entry));
                add(new VisaType("Student", R.drawable.ic_student));
                add(new VisaType("Conference", R.drawable.ic_conference));
                add(new VisaType("Research/Training", R.drawable.ic_research));
                add(new VisaType("Employment", R.drawable.ic_employee));
                add(new VisaType("Transit", R.drawable.ic_transit));
                add(new VisaType("Other", R.drawable.ic_others));

            }
        };
        visaTypeMutableLiveData.postValue(visaTypes);
    }

}