package br.com.thiagosousa.firebaseexamples.activitys.coordinatorlayoutexample;

/*Esta classe apresenta uma lista de telas com implementações de exemplos de técnicas de rolagem
* com o auxílio do CoordinatorLayout, baseados em artigo que foi encontrado no endereço abaixo:
* https://medium.com/android-dev-br/design-support-library-c2c7aebd8cb*/

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import br.com.thiagosousa.firebaseexamples.R;
import br.com.thiagosousa.firebaseexamples.activitys.coordinatorlayoutexample.subactivities.AbasActivity;
import br.com.thiagosousa.firebaseexamples.activitys.coordinatorlayoutexample.subactivities.AppBarPadraoActivity;
import br.com.thiagosousa.firebaseexamples.activitys.coordinatorlayoutexample.subactivities.EspacoFlexivelActivity;
import br.com.thiagosousa.firebaseexamples.activitys.coordinatorlayoutexample.subactivities.EspacoFlexivelComImagemActivity;
import br.com.thiagosousa.firebaseexamples.useful.UtilActivity;

public class CoordinatorLayoutExampleActivity extends UtilActivity implements ListView.OnItemClickListener {
    private static final String COORDINATORLAYOUTEXAMPLEACTIVITYTAG = "Coordinator event";

    private String[] itens = new String[]{"Coordinator com app bar padrão",
            "Abas", "Espaço flexível", "Espaço flexível com imagem"};

    private ListView listView;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_layout_example);
        initViews(true);

        adapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_list_item_1, itens);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    public void initViews(boolean init) {
        if (init) {
            listView = findViewById(R.id.CoordinatorLayoutExampleActivityListView);
        } else {
            Log.w(COORDINATORLAYOUTEXAMPLEACTIVITYTAG, "O método initViews() em " +
                    "CoordinatorLayoutExampleActivity foi definido como false");
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                openScreen(AppBarPadraoActivity.class);
                break;

            case 1:
                openScreen(AbasActivity.class);
                break;

            case 2:
                openScreen(EspacoFlexivelActivity.class);
                break;

            case 3:
                openScreen(EspacoFlexivelComImagemActivity.class);
                break;
            default:
                Log.w(COORDINATORLAYOUTEXAMPLEACTIVITYTAG, "Um item que não possui comportamento" +
                        "definido foi clicado");
                showToastShort("Um item que não possui comportamento\" +\n" +
                        "                        \"definido foi clicado");
                break;
        }
    }
}
