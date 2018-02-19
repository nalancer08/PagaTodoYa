package com.pagatoexamen.ya.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.pagatoexamen.ya.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Erick Sanchez - App Builders CTO
 * Revision 1 - 30/10/17
 */

public class MarcaAdapter extends RecyclerView.Adapter<BurbujaView> {

    private Context context;
    private JSONArray tipos;

    public MarcaAdapter(final Context context, JSONArray tipos) {

        this.context = context;
        this.tipos = tipos;
    }

    @Override
    public BurbujaView onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.burbuja, parent, false);
        BurbujaView animeView = new BurbujaView(layoutView);
        return animeView;
    }


    @Override
    public void onBindViewHolder(final BurbujaView holder, int position) {

        holder.setIsRecyclable(false);
        try {

            final JSONObject marca = this.tipos.getJSONObject(position);
            String tipo = marca.getString("tipo");
            String titulo = marca.getString("titulo");
            final ImageView img = holder.getCover();
            String name = "ic_" + tipo;

            int id = this.context.getResources().getIdentifier(name,"drawable", this.context.getPackageName());
            Bitmap logo = BitmapFactory.decodeStream(this.context.getResources().openRawResource(id));
            img.setImageBitmap(logo);

            // Setting name
            holder.getName().setText(titulo);

            // Setting the click
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    /**Intent intent = new Intent(context, SingleAnimeController.class);
                    intent.putExtra("anime", anime.toString());
                    context.startActivity(intent);*/

                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }
        holder.setIsRecyclable(false);
    }

    @Override
    public int getItemCount() {

        return this.tipos == null ? 0 : tipos.length();
    }

    public void addNews(JSONArray animes) {

        this.tipos = animes;
        this.notifyItemRangeInserted(0, this.tipos.length() - 1);
    }

    public void clear() {

        int size = this.tipos.length();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                this.tipos.remove(0);
            }
            this.notifyItemRangeRemoved(0, size);
        }
    }
}