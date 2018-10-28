package br.com.thiagosousa.firebaseexamples.activitys.filepicker;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.thiagosousa.firebaseexamples.R;
import br.com.thiagosousa.firebaseexamples.adapter.FilePickerActivityAdapter;
import br.com.thiagosousa.firebaseexamples.useful.UtilActivity;

public class FilePickerActivity extends UtilActivity {
    private static final String TAG = "FilePickerActivity";

    private RecyclerView recyclerView;
    private FilePickerActivityAdapter adapter;
    private List<String> itens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_picker);

//        Lista de itens que sera preenchida por loadItens()
        itens = new ArrayList<>();
        itens.add("ImagePicker");

//        Configurando o RecyclerView
        recyclerView = findViewById(R.id.FilePickerActivityRecyclerView);

        adapter = new FilePickerActivityAdapter( this,itens);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getBaseContext(), DividerItemDecoration.HORIZONTAL));


    }

    @Override
    public void initViews(boolean init) {

    }

    @Override
    public void configureScreen(boolean configure) {

    }
}
