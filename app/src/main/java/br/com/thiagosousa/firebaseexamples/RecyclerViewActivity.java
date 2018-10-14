package br.com.thiagosousa.firebaseexamples;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.thiagosousa.firebaseexamples.adapter.MyRecyclerViewAdapter;
import br.com.thiagosousa.firebaseexamples.objects.ItemRecyclerView;
import br.com.thiagosousa.firebaseexamples.useful.UtilActivity;

public class RecyclerViewActivity extends UtilActivity {
    private static final String TAG = "RecyclerViewActivity";

//    Recyclerview utils
    private RecyclerView mRecyclerView;
    private MyRecyclerViewAdapter adapter;
    private List<ItemRecyclerView> itens;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        initViews(true);
        configureScreen(true);

    }

    @Override
    public void initViews(boolean init) {
        if (init) {

//            initializing the recyclerview
            mRecyclerView = findViewById(R.id.recyclerviewactivity_recyclerview);
            configureRecyclerView(true);

        }else {
            Log.w(TAG, "initViews: initViews() desactivated");
        }

    }

    @Override
    public void configureScreen(boolean configure) {
        if (configure) {

//            Setting up recyclerview
            configureRecyclerView(true);

        }else {
            Log.w(TAG, "initViews: configureScreen() desactivated");
        }
    }

    public void configureRecyclerView(boolean configure) {
        if (configure) {

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(linearLayoutManager);

            adapter = new MyRecyclerViewAdapter(generateItens());
            mRecyclerView.setAdapter(adapter);

//            Adicionando divisor para melhorar a visualizacao
            mRecyclerView.addItemDecoration(new
                    DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        } else {
            Log.w(TAG, "configureRecyclerView: is desactivated");
        }
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
