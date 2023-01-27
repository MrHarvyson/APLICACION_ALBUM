package com.example.proyecto.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.proyecto.R;
import com.example.proyecto.adaptadores.RecycleViewAdapter;
import com.example.proyecto.recycleView.DataModel;
import java.util.ArrayList;
import java.util.List;

public class AcercaFragment extends Fragment {
    private RecyclerView recycleLista;
    private List<DataModel> mList;
    private RecycleViewAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Cargas la vista
        View view = inflater.inflate(R.layout.fragment_acerca_de, container, false);
        recycleLista = view.findViewById(R.id.lista_caracteristicas);
        recycleLista.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        mList = new ArrayList<>();

        List<String> nestedList1 = new ArrayList<>();
        nestedList1.add(getString(R.string.texto_recycle_lista));

        List<String> nestedList2 = new ArrayList<>();
        nestedList2.add(getString(R.string.texto_recycle_agregar));

        List<String> nestedList3 = new ArrayList<>();
        nestedList3.add(getString(R.string.texto_recycle_borrar));

        List<String> nestedList4 = new ArrayList<>();
        nestedList4.add(getString(R.string.texto_recycle_version));

        List<String> nestedList5 = new ArrayList<>();
        nestedList5.add(getString(R.string.texto_recycle_info));

        mList.add(new DataModel(nestedList1, getString(R.string.titulo_recycle_lista)));
        mList.add(new DataModel(nestedList2, getString(R.string.titulo_recycle_agregar)));
        mList.add(new DataModel(nestedList3, getString(R.string.titulo_recycle_borrar)));
        mList.add(new DataModel(nestedList4, getString(R.string.titulo_recycle_version)));
        mList.add(new DataModel(nestedList5, getString(R.string.titulo_recycle_info)));

        adapter = new RecycleViewAdapter(mList);
        recycleLista.setAdapter(adapter);
        return view;
    }
}