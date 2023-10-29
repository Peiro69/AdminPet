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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class RegistrarMascota extends Fragment {

    private String ID;



    FirebaseFirestore db = FirebaseFirestore.getInstance();




    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";






    public RegistrarMascota(String datoID) {
        // Required empty public constructor
        this.ID = datoID;


    }

    public String getID() {
        return ID;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View a = inflater.inflate(R.layout.fragment_registrar_mascota, container, false);


        EditText campo1 = a.findViewById(R.id.nombreMascota);
        Button btnRegMascota = a.findViewById(R.id.btnRegistrarMascota);

        btnRegMascota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nomMascota = campo1.getText().toString();

                HashMap<String, Object> mascotaa = new HashMap<>();
                mascotaa.put("nombre",nomMascota);
                db.collection("usuarios/"+getID()+"/mascotas").add(mascotaa).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getActivity().getApplicationContext(), "Mascota registrada", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity().getApplicationContext(), "ERROR", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });



        return a;
    }


}