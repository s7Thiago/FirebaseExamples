package br.com.thiagosousa.firebaseexamples.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.com.thiagosousa.firebaseexamples.R;
import br.com.thiagosousa.firebaseexamples.adapter.MyRecyclerViewAdapter;
import br.com.thiagosousa.firebaseexamples.objects.ItemRecyclerView;

public class RecyclerViewActivityFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter myReAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_recycler_view, container, false);


//        Configurando o recycler view
        recyclerView = view.findViewById(R.id.recyclerviewactivity_recyclerview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Objects.requireNonNull(getActivity()).getBaseContext());
        myReAdapter = new MyRecyclerViewAdapter(generateItens());

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myReAdapter);

        //            Adicionando divisor para melhorar a visualizacao
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity().getBaseContext(), DividerItemDecoration.VERTICAL));



        return view;
    }

    public List<ItemRecyclerView> generateItens() {
        List<ItemRecyclerView> itens = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            itens.add(new ItemRecyclerView("Title of item" + (i + 1),
                    getString(R.string.lorem_ipsum_description),
                    (i + 1)+ "/" + ( i + 1)+ "/" + (i * 2000 + i),
                    R.drawable.movefunlogo_svg));
        }
        return itens;
    }
}
