package br.com.thiagosousa.firebaseexamples.fragments;

import android.app.ActivityOptions;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.thiagosousa.firebaseexamples.R;
import br.com.thiagosousa.firebaseexamples.activitys.ResiduoDetailActivity;
import br.com.thiagosousa.firebaseexamples.adapter.ResiduoAdapter;
import br.com.thiagosousa.firebaseexamples.objects.Residuo;

public class ResiduosListFragment extends Fragment implements AdapterView.OnItemClickListener {
    private static final String TAG = "ResiduosListFragment";

    //    Componentes visuais
    private ListView residuosList;

    //
    ArrayList<Residuo> list;
    String nomes[] = new String[]{
            "Bateria", "Livro", "Caixa de papelão",
            "Roupa", "Geladeira", "Carne", "Garrafa de suco",
            "Lâmpadas incandescentes", "Paracetamol Vencido",
            "Mãos Francesas", "Brinquedos estragados",
            "Pneu de carro inútil"
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_custom_list, container, false);

//        Inicializando views
        residuosList = view.findViewById(R.id.customlistactivity_listview);

        list = new ArrayList<>();

//        Preenchendo a lista de itens para usar no listview
        list.add(new Residuo(nomes[0], 0, 0, false));
        list.add(new Residuo(nomes[1], 1, 5, true));
        list.add(new Residuo(nomes[2], 2, 5, true));
        list.add(new Residuo(nomes[3], 3, 4, true));
        list.add(new Residuo(nomes[4], 4, 1, true));
        list.add(new Residuo(nomes[5], 5, 3, false));
        list.add(new Residuo(nomes[6], 6, 7, true));
        list.add(new Residuo(nomes[7], 7, 4, true));
        list.add(new Residuo(nomes[8], 8, 4, false));
        list.add(new Residuo(nomes[9], 9, 2, true));
        list.add(new Residuo(nomes[10], 10, 6, true));
        list.add(new Residuo(nomes[11], 11, 4, true));

//        Configurando a lista
        ResiduoAdapter adapter = new ResiduoAdapter(getActivity().getApplicationContext(), list);
        residuosList.setAdapter(adapter);
        residuosList.setOnItemClickListener(this);


        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (String.valueOf(nomes[position])) {

            default:

                //Objeto de apoio
                Residuo residuo = list.get(position);


                Intent intent = new Intent(getActivity().getApplicationContext(), ResiduoDetailActivity.class);

//                Configurando a shared animation
//                Views
                View view1 = view.findViewById(R.id.residue_representation);
                View view2 = view.findViewById(R.id.residue_reciclable);
                View view3 = view.findViewById(R.id.residue_category);

                Pair<View, String> pair1 = Pair.create(view1, view1.getTransitionName());
                Pair<View, String> pair2 = Pair.create(view2, view2.getTransitionName());
                Pair<View, String> pair3 = Pair.create(view3, view3.getTransitionName());

                ActivityOptions activityOptions = ActivityOptions
                        .makeSceneTransitionAnimation(getActivity(), pair1, pair2, pair3);

//                Enviando por intent, o objeto com os dados que serao usados na outra tela
                intent.putExtra("residuo", residuo);

                startActivity(intent, activityOptions.toBundle());

                break;
        }
    }
}
