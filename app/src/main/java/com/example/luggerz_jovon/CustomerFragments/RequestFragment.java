package com.example.luggerz_jovon.CustomerFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.luggerz_jovon.R;

public class RequestFragment extends Fragment {

    private static final String TAG = "RequestFragment";


    private ImageView itemImage;
    private EditText etLugDate, etLugTime, etLugPickup, etLugDestination;
    private RadioGroup mRadioGroup;
    private Button btnSubmit;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_request, container, false);

        itemImage = view.findViewById(R.id.itemImage);

        etLugDate = view.findViewById(R.id.etLugDate);
        etLugTime = view.findViewById(R.id.etLugTime);
        etLugPickup = view.findViewById(R.id.etLugPickup);
        etLugDestination = view.findViewById(R.id.etLugDestination);
        btnSubmit = view.findViewById(R.id.btnSubmit);




        mRadioGroup = (RadioGroup) view.findViewById(R.id.radioGroup);
        mRadioGroup.check(R.id.LuggerX);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        
        return view;
    }


}
