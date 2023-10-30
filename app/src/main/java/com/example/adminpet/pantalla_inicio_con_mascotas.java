package com.example.adminpet;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class pantalla_inicio_con_mascotas extends Fragment {

    private String ID;


    private ArrayList<Mascota> listado;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public pantalla_inicio_con_mascotas(String datoID){
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
        return inflater.inflate(R.layout.fragment_pantalla_inicio_con_mascotas, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView l = (ListView) view.findViewById(R.id.lvMascotas);
        Adaptador adaptador = new Adaptador(getActivity().getApplicationContext(),cargar_datos());
        l.setAdapter(adaptador);
    }

    public ArrayList<Mascota> cargar_datos(){
        listado = new ArrayList<>();
        //System.out.println(listado);
        listado.add(new Mascota("https://firebasestorage.googleapis.com/v0/b/appapet-67086.appspot.com/o/fotos%2F1000012127?alt=media&token=5697794b-aa46-4f6c-b10c-b642c527a877&_gl=1*d4mcfj*_ga*MTE5MDE5NzMxNC4xNjk3NzIzOTYw*_ga_CW55HF8NVT*MTY5ODYyNzQ1Ni4yNi4xLjE2OTg2Mjc0NzIuNDQuMC4w","juan","6 meses"));
        System.out.println("listado inicial: "+ listado);
        db.collection("usuarios/"+getID()+"/mascotas")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            listado = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String rutaDB = (String)document.getData().get("foto");
                                String nombreDB = (String)document.getData().get("nombre");
                                String edadDB = (String) document.getData().get("edad");

                                Mascota m = new Mascota(rutaDB,nombreDB,edadDB);

                                listado.add(m);

                                System.out.println("aaaaaa "+listado);
                                //String correoDB = (String)document.getData().get("correo");
                            }
                            System.out.println("holiiiiiiiiiiaaaaaaaaaa: "+listado);
                        } else {
                            //Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });

        System.out.println("listado final cargado: "+ listado);



        //listado.add(new Mascota());
        return listado;
    }



}