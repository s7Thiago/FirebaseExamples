package br.com.thiagosousa.firebaseexamples.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.thiagosousa.firebaseexamples.R;
import br.com.thiagosousa.firebaseexamples.adapter.AdapterWithViewHolder;
import br.com.thiagosousa.firebaseexamples.objects.ItemForActivityWithListViewHolder;

public class CustomListViewViewHolderActivity extends AppCompatActivity {
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
}
