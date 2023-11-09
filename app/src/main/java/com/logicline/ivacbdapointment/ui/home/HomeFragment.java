package com.logicline.ivacbdapointment.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.logicline.ivacbdapointment.R;
import com.logicline.ivacbdapointment.adapters.VisaTypesAdapter;
import com.logicline.ivacbdapointment.databinding.FragmentHomeBinding;
import com.logicline.ivacbdapointment.models.VisaType;
import com.logicline.ivacbdapointment.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private VisaTypesAdapter adapter;
    //private HomeViewModel homeViewModel;
    private final ArrayList visaTypes = new ArrayList() {
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

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
       /* homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);*/

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (requireActivity().getActionBar() != null){
            requireActivity().getActionBar().setTitle("IVAC");
        }

        adapter = new VisaTypesAdapter(requireContext());
        adapter.setData(visaTypes);
        adapter.setItemClickListener(new VisaTypesAdapter.ItemClickListener() {
            @Override
            public void onItemClick(VisaType visaType) {
                Utils.showToast(requireContext(), "Clicked");
            }
        });

        GridLayoutManager layoutManager = new GridLayoutManager(requireContext(), 3);
        binding.rvVisaTypes.setLayoutManager(layoutManager);
        binding.rvVisaTypes.setAdapter(adapter);


        //initObservers();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    /*private void initObservers(){
        homeViewModel.getVisaTypeMutableLiveData().observe(getViewLifecycleOwner(), new Observer<List<VisaType>>() {
            @Override
            public void onChanged(List<VisaType> visaTypes) {
                if (adapter != null){
                    adapter.setData(visaTypes);
                }
            }
        });
    }*/
}