package com.example.adminpet;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


public class regCitasMedicas extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public regCitasMedicas() {
        // Required empty public constructor
    }


    public static regCitasMedicas newInstance(String param1, String param2) {
        regCitasMedicas fragment = new regCitasMedicas();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View a = inflater.inflate(R.layout.fragment_reg_citas_medicas, container, false);


        Bundle args = getArguments();
        Toast.makeText(getActivity().getApplicationContext(), "ARGS: "+args, Toast.LENGTH_SHORT).show();

        return a;
    }
}