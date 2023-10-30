package com.example.adminpet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {

    private Context contexto;
    private ArrayList<Mascota> listItem;


    public Adaptador(Context contexto, ArrayList<Mascota> listItem) {
        this.contexto = contexto;
        this.listItem = listItem;
    }





    @Override
    public int getCount() {
        return listItem.size();
    }

    @Override
    public Object getItem(int i) {
        return listItem.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(contexto).inflate(R.layout.items_mascotas_lv, null);
        ImageView ivFoto = (ImageView) view.findViewById(R.id.ivFoto);
        TextView tvNombre = (TextView) view.findViewById(R.id.tvNombre);
        TextView tvEdad = (TextView) view.findViewById(R.id.tvEdad);

        Mascota p = (Mascota) getItem(i);
        Glide.with(contexto)
                .load(p.getRuta())
                .centerCrop()
                .fitCenter()
                .into(ivFoto);
        ivFoto.getLayoutParams().height = 250;
        ivFoto.getLayoutParams().width = 250;
        tvNombre.setText(p.getNombre());
        tvEdad.setText(""+p.getEdad());

        return view;

    }
}
