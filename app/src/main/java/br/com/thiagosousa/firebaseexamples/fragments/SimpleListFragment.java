package br.com.thiagosousa.firebaseexamples.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.thiagosousa.firebaseexamples.R;

public class SimpleListFragment extends Fragment {
    final static String SIMPLELISTFRAGMENTTAG = "listFragment event";
    private ArrayList<String> itens;
    private ArrayAdapter<String> adapter;

    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.simple_list_fragment, container, false);
        initViews(true, container, view);

//        Configurando os itens para a lista do fragment
        itens = addItens(itens, 300);

//        Configurando a lista do fragment
        adapter = new ArrayAdapter<String>(getActivity().getApplicationContext() ,android.R.layout.simple_list_item_1, itens);
        listView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void initViews(boolean init, ViewGroup root, View view){
        if(init) {
            listView = view.findViewById(R.id.simpe_list_fragment_listview);
        }else{
            Log.w(SIMPLELISTFRAGMENTTAG, "initViews() em SimpleListFragment está desativado");
        }
    }

    public ArrayList<String> addItens(ArrayList<String> arrayList, int numberItens) {

//        Inicializando a lista
        arrayList = new ArrayList<>();

//        Preenchendo a lista
        for(int i = 0; i < numberItens; i++){
            arrayList.add("Item " + i);
        }

//        Retornando a lista preenchida
        return arrayList;
    }
}
