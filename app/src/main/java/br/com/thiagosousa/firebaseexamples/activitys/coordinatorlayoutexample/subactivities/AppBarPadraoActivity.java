package br.com.thiagosousa.firebaseexamples.activitys.coordinatorlayoutexample.subactivities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;

import br.com.thiagosousa.firebaseexamples.R;
import br.com.thiagosousa.firebaseexamples.adapter.CoordinatorRecyclerViewAdapter;

public class AppBarPadraoActivity extends AppCompatActivity{

    private static final String APPBARPADRAOACTIVITYTAG = "AppBarpadrao event";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private androidx.appcompat.widget.Toolbar mToolbar;
    private String itens[];
    private int numItens = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_bar_padrao);
        initViews(true);
        gerarItens(true);
        configureScreen(true);
    }

    public void initViews(boolean init) {
        if (init) {
            recyclerView = findViewById(R.id.activity_app_bar_padrao_recyclerview);
            mToolbar = findViewById(R.id.activity_app_bar_padrao_toolbar);
        } else {
            Log.w(APPBARPADRAOACTIVITYTAG, "O metodo initViews() em AppBarPadraoActivity" +
                    " foi desabilitade");
        }
    }

    public void configureScreen(boolean configure) {
        if (configure) {
            itens = gerarItens(true);
//            Configurando o recycclerview
            adapter = new CoordinatorRecyclerViewAdapter(itens);

//             use this setting to improve performance if you know that changes
//             in content do not change the layout size of the RecyclerView
            recyclerView.setHasFixedSize(true);

//            use a linear layout manager
            mLayoutManager = new LinearLayoutManager(getBaseContext());
            recyclerView.setLayoutManager(mLayoutManager);

            recyclerView.setAdapter(adapter);

//            Configurando a toolbar
            setSupportActionBar(mToolbar);

//            Configurando o menu na toolbar


        } else {
            Log.w(APPBARPADRAOACTIVITYTAG, "O metodo configureScreen() em AppBarPadraoActivity" +
                    " foi desabilitade");
        }
    }

    private String[] gerarItens(boolean generate) {
        String innerItens[] = new String[numItens];
        if (generate) {

            for (int i = 0; i < 1000; i++) {
                innerItens[i] = ("item " + (i + 1));
            }

            return innerItens;

        } else {
            Log.w(APPBARPADRAOACTIVITYTAG, "O metodo gerarItens() em AppBarPadraoActivity" +
                    " foi desabilitade");
        }
        return innerItens;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_home, menu);
        return true;
    }
}
