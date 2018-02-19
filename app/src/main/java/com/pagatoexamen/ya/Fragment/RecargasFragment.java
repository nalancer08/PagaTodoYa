package com.pagatoexamen.ya.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pagatoexamen.ya.Adapter.MarcaAdapter;
import com.pagatoexamen.ya.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecargasFragment extends Fragment {

    private View view;
    private RecyclerView claro_list;
    private RecyclerView tuenti_list;
    private RecyclerView entel_list;

    public RecargasFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        this.view = inflater.inflate(R.layout.fragment_recargas, container, false);

        // Binding
        this.claro_list = this.view.findViewById(R.id.claro_list);
        this.tuenti_list = this.view.findViewById(R.id.tuenti_list);
        this.entel_list = this.view.findViewById(R.id.entel_list);

        // Init list
        this.createClaroItems();
        this.createTuentiItems();
        this.createEntelItems();

        return view;
    }

    private void createClaroItems() {

        this.claro_list.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        JSONArray items = new JSONArray();
        for(int i = 1; i <= 3; i++) {

            JSONObject m = new JSONObject();
            try {

                m.put("tipo", "claro");
                m.put("titulo", "Megas");
                items.put(m);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        MarcaAdapter adapter = new MarcaAdapter(getContext(), items);
        claro_list.setAdapter(adapter);
    }

    private void createTuentiItems() {

        this.tuenti_list.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        JSONArray items = new JSONArray();
        for(int i = 1; i <= 3; i++) {

            JSONObject m = new JSONObject();
            try {

                m.put("tipo", "tuenti");
                m.put("titulo", "Tiempo aire");
                items.put(m);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        MarcaAdapter adapter = new MarcaAdapter(getContext(), items);
        tuenti_list.setAdapter(adapter);
    }

    private void createEntelItems() {

        this.entel_list.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        JSONArray items = new JSONArray();
        for(int i = 1; i <= 3; i++) {

            JSONObject m = new JSONObject();
            try {

                m.put("tipo", "entel");
                m.put("titulo", "Tiempo aire");
                items.put(m);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        MarcaAdapter adapter = new MarcaAdapter(getContext(), items);
        entel_list.setAdapter(adapter);
    }
}