package br.com.thiagosousa.firebaseexamples.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import br.com.thiagosousa.firebaseexamples.R;
import br.com.thiagosousa.firebaseexamples.adapter.AnimatedVectorDrawableActivity_Adapter;
import br.com.thiagosousa.firebaseexamples.useful.UtilActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class AnimatedVectorDrawableActivity extends UtilActivity {
    private static final String TAG = "AnimatedVectorDrawableA";

    //
    RecyclerView recyclerView;
    AnimatedVectorDrawableActivity_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animated_vector_drawable);
        initViews(true);
    }

    @Override
    public void initViews(boolean init) {
        if (init) {
            recyclerView = findViewById(R.id.animatedVectorDrawableActivity_ReciclerView);

            configureScreen(true);
        } else {
            Log.d(TAG, "initViews: initViews is desactivated");
        }
    }

    @Override
    public void configureScreen(boolean configure) {
        if (configure) {
            configureRecyclerView(true);
        } else {
            Log.d(TAG, "configureScreen: configureScreen is desactivated");
        }
    }

    private void configureRecyclerView(boolean configure) {
        if (configure) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(linearLayoutManager);

            adapter = new AnimatedVectorDrawableActivity_Adapter(getApplicationContext(), gerarItens());
            recyclerView.setAdapter(adapter);

//            Adicionando divider para decorar o recyclerView e separar cada item
            recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));

        } else {
            Log.w(TAG, "configureRecyclerView: configureRecyclerView is desactivated");
        }
    }

    private List<String> gerarItens() {
        List<String> itens = new ArrayList<>();
        itens.add("Exemplo 1");
        return itens;
    }


}
