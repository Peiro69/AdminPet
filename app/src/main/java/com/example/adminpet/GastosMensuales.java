package com.example.adminpet;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class GastosMensuales extends Fragment {

    FirebaseFirestore db = FirebaseFirestore.getInstance();



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
        String ide = "";
        Bundle args = getArguments();
        if (args!=null){
            ide = args.getString("id");
        }

        Toast.makeText(getActivity().getApplicationContext(), "ARGS: " +args, Toast.LENGTH_SHORT).show();


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
                Calendar calendar = Calendar.getInstance();
                Integer dia = calendar.get(Calendar.DAY_OF_MONTH);
                Integer mes = calendar.get(Calendar.MONTH);
                Integer anio = calendar.get(Calendar.YEAR);

                String fechaA = ""+dia+"/"+(mes+1)+"/"+anio;

                //DocumentReference usuario = db.collection("usuarios").document(""+ide);
                HashMap<String, Object> datos = new HashMap<>();
                datos.put("Mes",fechaA);
                datos.put("GastoMes",GMA);

                db.collection("usuarios/"+ide+"/gastos").add(datos).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getActivity().getApplicationContext(), "Gasto Mensual: "+ GMA, Toast.LENGTH_SHORT).show();
                        Toast.makeText(getActivity().getApplicationContext(), "Fecha: "+fechaA, Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity().getApplicationContext(), "NO FUNCO :(", Toast.LENGTH_SHORT).show();
                    }
                });


                        //.addOnSuccessListener(new OnSuccessListener<Void>() {
                        //            @Override
                        //            public void onSuccess(Void unused) {
                        //                Toast.makeText(getActivity().getApplicationContext(), "Registro realizado", Toast.LENGTH_SHORT).show();
                        //            }
                        //        }).addOnFailureListener(new OnFailureListener() {
                        //    @Override
                        //    public void onFailure(@NonNull Exception e) {
                        //        Toast.makeText(getActivity().getApplicationContext(), "ERROR", Toast.LENGTH_SHORT).show();
                        //    }
                        //});
                //Toast.makeText(getActivity().getApplicationContext(), "gasto mensual: "+GMA, Toast.LENGTH_SHORT).show();

            }
        });






        return a;
    }



}