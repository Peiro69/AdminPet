package com.example.adminpet;

import static android.content.ContentValues.TAG;

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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends AppCompatActivity {


    FirebaseFirestore db = FirebaseFirestore.getInstance();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void login(View v){

        EditText campo1 = this.findViewById(R.id.correo);
        String correo = campo1.getText().toString();
        EditText campo2 = this.findViewById(R.id.contrasenia);
        String psw = campo2.getText().toString();


        if(correo.isEmpty() && psw.isEmpty()){
            Toast.makeText(this, "Debes rellenar los campos", Toast.LENGTH_SHORT).show();
        }else{
            IngresarApp(correo,psw);
        }

    }

    private void IngresarApp(String correo, String psw) {
        
        db.collection("usuarios")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        boolean a = false;
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String correoDB = (String)document.getData().get("correo");
                                String claveDB = (String)document.getData().get("contrasenia");
                                String nombreDB = (String)document.getData().get("nombre");
                                //System.out.println(correoDB+" "+correo);
                                //System.out.println(claveDB+" "+psw);
                                //System.out.println(document.getData());
                                //System.out.println(document.getId());
                                if(correo.equals(correoDB) && psw.equals(claveDB)){
                                    a = true;
                                    String id = (String)document.getId();
                                    System.out.println(id);
                                    Toast.makeText(getApplicationContext(), "Bienvenido: " + nombreDB , Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(MainActivity.this,Principal1.class);
                                    i.putExtra("id",id);
                                    startActivity(i);
                                    break;
                                }


                                //System.out.println("data: "+document.getData());

                            }
                            if (a == false){
                                Toast.makeText(getApplicationContext(), "ERROR EN LAS CREDENCIALES" , Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    public void registro(View v){
        Toast.makeText(this, "Vas a crear una cuenta ", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this,Registro.class);
        startActivity(i);

    }
}