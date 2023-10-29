package com.example.adminpet;

import static android.app.Activity.RESULT_OK;
import static android.graphics.Bitmap.CompressFormat.JPEG;


import static com.google.common.io.Files.getFileExtension;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

public class RegistrarMascota extends Fragment {

    private String ID;

    static final int GALLERY_INTENT = 1;

    private StorageReference mStorage;

    private ImageView foto;

    private Uri uri;



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
        EditText campo2 = a.findViewById(R.id.edadMascota);
        EditText campo3 = a.findViewById(R.id.especieMascota);
        EditText campo4 = a.findViewById(R.id.razaMascota);
        Button btnRegMascota = a.findViewById(R.id.btnRegistrarMascota);

        foto = a.findViewById(R.id.imgBtnIngresarFoto);

        a.findViewById(R.id.imgBtnIngresarFoto).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirGaleria();
            }
        });








        btnRegMascota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nomMascota = campo1.getText().toString();
                String edadMascota = campo2.getText().toString();
                String especieMascota = campo3.getText().toString();
                String razaMascota = campo4.getText().toString();

                HashMap<String, Object> mascotaa = new HashMap<>();
                mascotaa.put("nombre",nomMascota);
                mascotaa.put("edad",edadMascota);
                mascotaa.put("especie",especieMascota);
                mascotaa.put("raza",razaMascota);
                db.collection("usuarios/"+getID()+"/mascotas").add(mascotaa).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        String idMascota = documentReference.getId().toString();
                        HashMap<String, Object> inter = new HashMap<>();
                        inter.put("claveUsuario", getID());
                        inter.put("claveMascota", idMascota);
                        db.collection("mascotas").add(inter).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(getActivity().getApplicationContext(), "Bienvenid@ a AdminPet: "+nomMascota, Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getActivity().getApplicationContext(), "no funco :(", Toast.LENGTH_SHORT).show();
                            }
                        });
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


    private void abrirGaleria() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, GALLERY_INTENT);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_INTENT && resultCode == RESULT_OK && data != null && data.getData() != null) {
            uri = data.getData();
            foto.setImageURI(uri);
        }
    }



}