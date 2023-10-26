package com.example.adminpet;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


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
        //String ide = "";
        //Bundle args = getArguments();
        //if (args!=null){
        //    ide = args.getString("id");
        //}
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View a = inflater.inflate(R.layout.fragment_gastos_mensuales, container, false);
        // Inflate the layout for this fragment
        //String ide = "";
        //Bundle args = getArguments();
        //if (args!=null){
        //    ide = args.getString("id");
        //}

        EditText c1 = a.findViewById(R.id.gastoMensual);
        Button btnGM = a.findViewById(R.id.btnGastoMes);

        btnGM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ide = "";
                Bundle args = getArguments();
                if (args!=null){
                    ide = args.getString("id");
                }
                String GMA = c1.getText().toString();
                DocumentReference usuario = db.collection("usuarios").document(""+ide);
                HashMap<String, Object> datos = new HashMap<>();
                datos.put("GastoMesActual",GMA);
                datos.put("GastoMesAnterior","");
                usuario.update(datos).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(getActivity().getApplicationContext(), "Registro realizado", Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getActivity().getApplicationContext(), "ERROR", Toast.LENGTH_SHORT).show();
                            }
                        });
                Toast.makeText(getActivity().getApplicationContext(), "gasto mensual: "+GMA, Toast.LENGTH_SHORT).show();

            }
        });






        return a;
    }



}