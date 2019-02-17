package br.com.thiagosousa.firebaseexamples.activitys;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import br.com.thiagosousa.firebaseexamples.R;
import br.com.thiagosousa.firebaseexamples.adapter.ConstraintAnimationsExamplesListAdapter;

public class ConstraintAnimationActivity extends AppCompatActivity {

    private static final String TAG = "ConstraintAnimationActi";

    //    Lista
    RecyclerView listExamples;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint_animation);

//        Linkando lista do xml ao java
        listExamples = findViewById(R.id.contraintLayoutanimationsExamplesList);

//        Configurando lista

        ConstraintAnimationsExamplesListAdapter adapter = new ConstraintAnimationsExamplesListAdapter(this, getItens());
        listExamples.setAdapter(adapter);
        LinearLayoutManager llM = new LinearLayoutManager(this);
        listExamples.setLayoutManager(llM);
        listExamples.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

    }

    private List<String> getItens() {
        List<String> itens = new ArrayList<>();
        itens.add("Exemplo 1");

        for (String item : itens)
            Log.d(TAG, "getItens: itemTitle -> " + item);

        return itens;
    }
}