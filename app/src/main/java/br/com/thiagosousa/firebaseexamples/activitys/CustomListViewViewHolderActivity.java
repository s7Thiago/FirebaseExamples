package br.com.thiagosousa.firebaseexamples.activitys;

import android.app.ActivityOptions;
import android.content.Intent;
import androidx.core.app.ActivityOptionsCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.thiagosousa.firebaseexamples.DetailViewHolderActivity;
import br.com.thiagosousa.firebaseexamples.R;
import br.com.thiagosousa.firebaseexamples.adapter.AdapterWithViewHolder;
import br.com.thiagosousa.firebaseexamples.objects.ItemForActivityWithListViewHolder;

public class CustomListViewViewHolderActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private static final String ACTIVITYVIEWHOLDERTAG = "ActivityViewHolderList";

    private ListView listView;
    private AdapterWithViewHolder adapter;
    private List<ItemForActivityWithListViewHolder> itens;
    private int numberOfItens = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list_view_view_holder);
        initViews(true);
        configureScreen(true);

    }

    private void configureScreen(boolean configure) {
        if (configure) {

            itens = gerarItens();

            adapter = new AdapterWithViewHolder(this, itens);

            listView.setAdapter(adapter);
            listView.setOnItemClickListener(this);

        } else {
            Log.w(ACTIVITYVIEWHOLDERTAG, "O método configureScreen() está desativade");
        }
    }

    private void initViews(boolean init) {
        if (init) {
            listView = findViewById(R.id.customlistviewholderactivity_mainlistview);

            Log.w(ACTIVITYVIEWHOLDERTAG, "O método initViews() está desativade");

        } else {

        }

    }

    public List<ItemForActivityWithListViewHolder> gerarItens() {
        List<ItemForActivityWithListViewHolder> itens = new ArrayList<>();

        for(int i = 0; i < numberOfItens; i++) {
            itens.add(new ItemForActivityWithListViewHolder(
                    "Title of item " + (i + 1),
                    String.valueOf(Calendar.getInstance().get(Calendar.HOUR)),
                    "Address of item " + (i + 1) + " of list"));
        }

        return itens;
    }

//    Eventos de clique do listView
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            default:
//                Configurando os pares para a transição entre as telas

                View view1 = view.findViewById(R.id.item_customlistviewholderactivity_imageview);
                View view2 = view.findViewById(R.id.item_customlistviewholderactivity_title_textview);
                View view3 = view.findViewById(R.id.item_customlistviewholderactivity_time_textview);
                View view4 = view.findViewById(R.id.item_customlistviewholderactivity_address_textview);

                Pair<View, String> pair1 = Pair.create(view1, view1.getTransitionName());
                Pair<View, String> pair2 = Pair.create(view2, view2.getTransitionName());
                Pair<View, String> pair3 = Pair.create(view3, view3.getTransitionName());
                Pair<View, String> pair4 = Pair.create(view4, view4.getTransitionName());

//                Configurando o intent com os pares
                Intent intent = new Intent(getBaseContext(), DetailViewHolderActivity.class);
                ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(this, pair1, pair2, pair3, pair4);

//                Colocando os dados do objeto na posição clicada no intent
                ItemForActivityWithListViewHolder item = itens.get(position);
                intent.putExtra(DetailViewHolderActivity.EXTRA_ITEM_OBJECT, item);

                startActivity(intent, activityOptions.toBundle());
                break;
        }
    }
}
