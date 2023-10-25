package com.example.adminpet;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;


public class GastosMensuales extends Fragment {

    FirebaseFirestore db = FirebaseFirestore.getInstance();



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GastosMensuales() {
        // Required empty public constructor
    }


    public static GastosMensuales newInstance(String param1, String param2) {
        GastosMensuales fragment = new GastosMensuales();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String ide = "";
        Bundle args = getArguments();
        if (args!=null){
            ide = args.getString("id");

        }
    }

    private TextView c1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View a = inflater.inflate(R.layout.fragment_gastos_mensuales, container, false);
        // Inflate the layout for this fragment
        String ide = "";
        Bundle args = getArguments();
        if (args!=null){
            ide = args.getString("id");
            Toast.makeText(getActivity().getApplicationContext(), "ideeee: "+ide, Toast.LENGTH_SHORT).show();
        }

        EditText c1 = a.findViewById(R.id.gastoMensual);
        Button btnGM = a.findViewById(R.id.btnGastoMes);

        btnGM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String GMA = c1.getText().toString();
                Toast.makeText(getActivity().getApplicationContext(), "gasto mensual: "+GMA, Toast.LENGTH_SHORT).show();

            }
        });






        return a;
    }



}