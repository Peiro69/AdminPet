package com.example.adminpet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Principal1 extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal1);
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);

        String ide = "";

        Bundle paquete = getIntent().getExtras();
        if(paquete != null){
            ide = paquete.getString("id");
        }


        String finalIde = ide;

        //Toast.makeText(this, "ide: "+finalIde, Toast.LENGTH_SHORT).show();

        db.collection("usuarios/"+ide+"/mascotas")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            //System.out.println("holi: "+task.getResult());
                            //System.out.println("myInteger es de tipo " +  ((Object)task.getResult()).getClass().getSimpleName());

                            QuerySnapshot qs = task.getResult();

                            if (qs.isEmpty()){
                                PantallaInicio pi = new PantallaInicio();
                                getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,pi).commit();
                                Toast.makeText(Principal1.this, "Mascotas Registradas", Toast.LENGTH_SHORT).show();

                            }else {
                                pantalla_inicio_con_mascotas picm = new pantalla_inicio_con_mascotas(finalIde);
                                getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,picm).commit();
                                Toast.makeText(Principal1.this, "Mascotas Registradas", Toast.LENGTH_SHORT).show();

                            }


                        } else {
                            Toast.makeText(Principal1.this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });




        FloatingActionButton fab = findViewById(R.id.btnFlotante);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ide = "";

                Bundle paquete = getIntent().getExtras();
                if(paquete != null){
                    ide = paquete.getString("id");

                }



                fab.setVisibility(view.GONE);
                RegistrarMascota rm = new RegistrarMascota(ide);
                getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,rm).commit();
                Toast.makeText(Principal1.this, "Registrar Mascota", Toast.LENGTH_SHORT).show();
            }
        });


        //FloatingActionButton fab = findViewById(R.id.fab);
        //fab.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View view) {
        //        Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
        //                .setAction("Action", null).show();
        //    }
        //});


        NavigationView nav = (NavigationView) findViewById(R.id.nav);
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                int id = item.getItemId();
                if (id == R.id.op1){
                    fab.setVisibility(View.VISIBLE);
                    GastosMensuales gm = new GastosMensuales();

                    gm.setArguments(paquete);

                    getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,gm).commit();
                    Toast.makeText(getApplicationContext(), "Gastos mensuales", Toast.LENGTH_SHORT).show();

                    //getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,p).commit();
                    //Toast.makeText(getApplicationContext(), "Vas al perfil", Toast.LENGTH_SHORT).show();

                }else if(id==R.id.op2){
                    fab.setVisibility(View.VISIBLE);
                    CitasMedicas cm = new CitasMedicas();
                    getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,cm).commit();
                    Toast.makeText(getApplicationContext(), "Citas Médicas", Toast.LENGTH_SHORT).show();

                }else if(id==R.id.op3){
                    fab.setVisibility(View.GONE);
                    regCitasMedicas rcm = new regCitasMedicas();
                    getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,rcm).commit();
                    Toast.makeText(getApplicationContext(), "Añadir Citas Médicas", Toast.LENGTH_SHORT).show();


                } else if (id==R.id.op4) {
                    fab.setVisibility(View.VISIBLE);
                    db.collection("usuarios/"+finalIde+"/mascotas")
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        //System.out.println("holi: "+task.getResult());
                                        //System.out.println("myInteger es de tipo " +  ((Object)task.getResult()).getClass().getSimpleName());

                                        QuerySnapshot qs = task.getResult();

                                        if (qs.isEmpty()){
                                            PantallaInicio pi = new PantallaInicio();
                                            getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,pi).commit();
                                            Toast.makeText(Principal1.this, "Mascotas Registradas", Toast.LENGTH_SHORT).show();
                                        }else {
                                            pantalla_inicio_con_mascotas picm = new pantalla_inicio_con_mascotas(finalIde);
                                            getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,picm).commit();
                                            Toast.makeText(Principal1.this, "Mascotas Registradas", Toast.LENGTH_SHORT).show();
                                        }


                                    } else {
                                        Toast.makeText(Principal1.this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                    //PantallaInicio pi = new PantallaInicio();
                    //getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,pi).commit();
                    //Toast.makeText(getApplicationContext(),"Mascotas Registradas",Toast.LENGTH_SHORT).show();
                } else if (id==R.id.op5) {
                    fab.setVisibility(View.GONE);
                    RegistrarMascota rm = new RegistrarMascota(finalIde);
                    getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,rm).commit();
                    Toast.makeText(Principal1.this, "Registrar Mascota" , Toast.LENGTH_SHORT).show();
                } else if (id==R.id.op6) {

                    fab.setVisibility(View.VISIBLE);
                    Perfil p = new Perfil();
                    getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,p).commit();



                }

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.principal);
                drawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });
        DrawerLayout dl = (DrawerLayout) findViewById(R.id.principal);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                dl,
                R.string.a,
                R.string.c

        );
        dl.addDrawerListener(toggle);
        toggle.syncState();

        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dl.isDrawerOpen(GravityCompat.START)){
                    dl.closeDrawer(GravityCompat.START);
                }else{
                    dl.openDrawer((int) Gravity.START);
                }
            }
        });

    }




}