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

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class Principal1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal1);
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);

        Bundle paquete = getIntent().getExtras();
        if(paquete != null){
            String ide = paquete.getString("id");
            System.out.println("La clave es: "+ide);
            Toast.makeText(this, "El id es: "+ide, Toast.LENGTH_SHORT).show();
        }




        PantallaInicio pi = new PantallaInicio();
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,pi).commit();


        FloatingActionButton fab = findViewById(R.id.btnFlotante);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fab.setVisibility(view.GONE);
                RegistrarMascota rm = new RegistrarMascota();
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
                    PantallaInicio pi = new PantallaInicio();
                    getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,pi).commit();
                    Toast.makeText(getApplicationContext(),"Mascotas Registradas",Toast.LENGTH_SHORT).show();
                } else if (id==R.id.op5) {
                    fab.setVisibility(View.GONE);
                    RegistrarMascota rm = new RegistrarMascota();
                    getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,rm).commit();
                    Toast.makeText(Principal1.this, "Registrar Mascota", Toast.LENGTH_SHORT).show();
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