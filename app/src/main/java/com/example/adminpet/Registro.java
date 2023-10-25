package com.example.adminpet;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class Registro extends AppCompatActivity {


    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
    }

    public void ingreso(View v){

        EditText c1 = findViewById(R.id.Nombre);
        String nombreU = c1.getText().toString();
        EditText c2 = findViewById(R.id.CorreoRegistro);
        String correoU = c2.getText().toString();
        EditText c3 = findViewById(R.id.nTelefono);
        String nTel = c3.getText().toString();
        EditText c4 = findViewById(R.id.Edad);
        String edad = c4.getText().toString();
        EditText c5 = findViewById(R.id.Contra1);
        String psw1 = c5.getText().toString();
        EditText c6 = findViewById(R.id.Contra2);
        String psw2 = c6.getText().toString();


        if (nombreU.isEmpty() && correoU.isEmpty() && nTel.isEmpty() && edad.isEmpty() && psw1.isEmpty()&& psw2.isEmpty()){
            Toast.makeText(this, "Debes agregar datos", Toast.LENGTH_SHORT).show();
        } else{
            registrarUsuario(nombreU,correoU,nTel,edad,psw1);

        }



    }

    private void registrarUsuario(String nombre, String correo, String nTel, String edad, String psw) {

        // Create a new user with a first and last name
        Map<String, Object> user = new HashMap<>();
        user.put("nombre", nombre);
        user.put("correo", correo);
        user.put("telefono", nTel);
        user.put("edad", edad);
        user.put("contrasenia", psw);



        // Add a new document with a generated ID
        db.collection("usuarios")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(Registro.this,"Bienvenido a AdminPet: " + nombre , Toast.LENGTH_SHORT).show();
                             Intent i = new Intent(getApplicationContext(),MainActivity.class);
                             startActivity(i);
                             //Recuperar el id del documento
                        String id = "";
                        //db.collection("usuarios/"+id+"/mascotas")

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Registro.this, "ERROR al registrar a: " + nombre, Toast.LENGTH_SHORT).show();
                        //System.out.println("ERROR "+e.toString());
                        //Toast.makeText(Registro.this, "ERROR: "+e.toString(), Toast.LENGTH_SHORT).show();
                    }
                });

    }
}