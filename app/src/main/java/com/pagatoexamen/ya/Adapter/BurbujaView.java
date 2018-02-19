package com.pagatoexamen.ya.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pagatoexamen.ya.R;

/**
 * Created by Erick Sanchez - App Builders CTO
 * Revision 1 - 30/10/17
 */

public class BurbujaView extends RecyclerView.ViewHolder {


    public ImageView marca;
    public TextView titulo;

    public BurbujaView(View itemView) {

        super(itemView);
        this.marca = itemView.findViewById(R.id.image_bubble);
        this.titulo = itemView.findViewById(R.id.bubble_title);
    }

    public ImageView getCover() {
        return marca;
    }

    public TextView getName() {
        return titulo;
    }
}