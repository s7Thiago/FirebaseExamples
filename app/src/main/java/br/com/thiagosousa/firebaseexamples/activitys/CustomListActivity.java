package br.com.thiagosousa.firebaseexamples.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.thiagosousa.firebaseexamples.R;
import br.com.thiagosousa.firebaseexamples.adapter.ResiduoAdapter;
import br.com.thiagosousa.firebaseexamples.objects.Residuo;

public class CustomListActivity extends AppCompatActivity {

    private static final String CUSTOMLISTACTIVITYTAG = "CustomList event";
    private ListView mainListView;
    List<Residuo> residuos;
    ResiduoAdapter adapter;

    //    [Start]: onCreate()
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list);
        initViews(true);

        residuos = new ArrayList<Residuo>();
//        Populando a lista de residuos
        residuos.add(new Residuo("Bateria", 0, 0, false));
        residuos.add(new Residuo("Livro", 1, 5, true));
        residuos.add(new Residuo("Caixa de papelão", 2, 5, true));
        residuos.add(new Residuo("Roupa", 3, 4, true));
        residuos.add(new Residuo("Geladeira", 4, 1, true));
        residuos.add(new Residuo("Carne", 5, 3, false));
        residuos.add(new Residuo("Garrafa de suco", 6, 7, true));
        residuos.add(new Residuo("Lâmpadas incandescentes", 7, 4, true));
        residuos.add(new Residuo("Paracetamol Vencido", 8, 4, false));
        residuos.add(new Residuo("Mãos Francesas", 9, 2, true));
        residuos.add(new Residuo("Brinquedos estragados", 10, 6, true));
        residuos.add(new Residuo("Pneu de carro inútil", 11, 4, true));

        adapter = new ResiduoAdapter(this, residuos);
        mainListView.setAdapter(adapter);

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
//        [End]: initViews()
}
