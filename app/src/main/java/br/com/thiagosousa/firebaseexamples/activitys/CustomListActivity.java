package br.com.thiagosousa.firebaseexamples.activitys;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.thiagosousa.firebaseexamples.R;
import br.com.thiagosousa.firebaseexamples.adapter.ResiduoAdapter;
import br.com.thiagosousa.firebaseexamples.objects.Residuo;
import br.com.thiagosousa.firebaseexamples.useful.UtilActivity;

public class CustomListActivity extends UtilActivity implements AdapterView.OnItemClickListener {

    private static final String CUSTOMLISTACTIVITYTAG = "CustomList event";
    private ListView mainListView;
    List<Residuo> residuos;
    ResiduoAdapter adapter;
    String nomes[];

    //    [Start]: onCreate()
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list);
        initViews(true);
        nomes = new String[]{
                "Bateria", "Livro", "Caixa de papelão",
                "Roupa", "Geladeira", "Carne", "Garrafa de suco",
                "Lâmpadas incandescentes", "Paracetamol Vencido",
                "Mãos Francesas", "Brinquedos estragados",
                "Pneu de carro inútil"
        };

        residuos = new ArrayList<Residuo>();
//        Populando a lista de residuos
        residuos.add(new Residuo(nomes[0], 0, 0, false));
        residuos.add(new Residuo(nomes[1], 1, 5, true));
        residuos.add(new Residuo(nomes[2], 2, 5, true));
        residuos.add(new Residuo(nomes[3], 3, 4, true));
        residuos.add(new Residuo(nomes[4], 4, 1, true));
        residuos.add(new Residuo(nomes[5], 5, 3, false));
        residuos.add(new Residuo(nomes[6], 6, 7, true));
        residuos.add(new Residuo(nomes[7], 7, 4, true));
        residuos.add(new Residuo(nomes[8], 8, 4, false));
        residuos.add(new Residuo(nomes[9], 9, 2, true));
        residuos.add(new Residuo(nomes[10], 10, 6, true));
        residuos.add(new Residuo(nomes[11], 11, 4, true));

        adapter = new ResiduoAdapter(this, residuos);
        mainListView.setAdapter(adapter);
        mainListView.setOnItemClickListener(this);

    }
//        [End]: onCreate()

    //        [Start]: initViews()
    public void initViews(boolean init) {

        if (init) {
            mainListView = findViewById(R.id.customlistactivity_listview);
        } else {
            Log.w(CUSTOMLISTACTIVITYTAG, "The initViews() is desactivated");
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        switch (String.valueOf(nomes[position])) {

            default:

                //Objeto de apoio
                Residuo residuo = residuos.get(position);

//                Exibindo mensagem Toast com o nome do residuo
                showToastShort(residuo.getNome());

                Intent intent = new Intent(getBaseContext(), ResiduoDetailActivity.class);

//                Configurando a shared animation
//                Views
                View view1 = view.findViewById(R.id.residue_representation);
                View view2 = view.findViewById(R.id.residue_reciclable);
                View view3 = view.findViewById(R.id.residue_category);

                Pair<View, String> pair1 = Pair.create(view1, view1.getTransitionName());
                Pair<View, String> pair2 = Pair.create(view2, view2.getTransitionName());
                Pair<View, String> pair3 = Pair.create(view3, view3.getTransitionName());

                ActivityOptions activityOptions = ActivityOptions
                        .makeSceneTransitionAnimation(this, pair1, pair2, pair3);

//                Enviando por intent, o objeto com os dados que serao usados na outra tela
                intent.putExtra("residuo", residuo);

                startActivity(intent, activityOptions.toBundle());

                break;
        }
    }
//        [End]: initViews()
}
