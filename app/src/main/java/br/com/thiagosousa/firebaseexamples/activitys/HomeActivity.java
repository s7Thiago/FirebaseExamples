package br.com.thiagosousa.firebaseexamples.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;

import br.com.thiagosousa.firebaseexamples.R;
import br.com.thiagosousa.firebaseexamples.useful.AuthDataBaseActivity;

public class HomeActivity extends AuthDataBaseActivity implements View.OnClickListener {
    private static final String HOMEACTIVITYTAG = "Home activity event";

    private FloatingActionButton fab;
    private Toolbar toolbar;
    private ListView homeItens;
    private String[] itens = new String[]{"Interação com o Firebase Database"};
    private ArrayAdapter<String> mAdapter;

    //    [Start]: onCreate()
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initViews(true);
        configureScreen(true);

        homeItens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){

                    case 0:
                        startActivity(new Intent(getBaseContext(), FirebaseDatabaseActivity.class));
                        break;

                    default:
                        break;
                }
            }
        });

    }
    //    [End]: onCreate()

    //[Start]: onStart()
    @Override
    protected void onStart() {
        super.onStart();

    }
//[End]: onStart()

    //    [Start]: initViews()
    public void initViews(boolean init) {
        if (init) {
            Log.d(HOMEACTIVITYTAG, "initViews() is activated in HOMEACTIVITY");
//            Se ativado, as views do layout são linkadas ao código

            fab = findViewById(R.id.fab);
            toolbar = findViewById(R.id.toolbar);
            homeItens = findViewById(R.id.home_listView);

        } else {
            Log.w(HOMEACTIVITYTAG, "initViews() is desactivated in HOMEACTIVITY");
//            Se desativado, somente avisar no log
        }
    }
    //    [End]: initViews()

    //    [Start]: configureScreen()
    private void configureScreen(boolean config) {
        if (config) {
            Log.d(HOMEACTIVITYTAG, "configureScreen() is activated in HOMEACTIVITY");
//            Se ativado, alguns aspectos nesta tela serão modificados

            setSupportActionBar(toolbar);
//            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

//            Configurando onClicks
            fab.setOnClickListener(this);

            //            Initializing the firebase
            mAuth = FirebaseAuth.getInstance();

//           Setting up the ListView
            mAdapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_list_item_1, itens);
            homeItens.setAdapter(mAdapter);

        } else {
            Log.w(HOMEACTIVITYTAG, "configureScreen() is desactivated in HOMEACTIVITY");
//            Se desativado, somente avisar no log
        }
    }
//    [End]: configureScreen()

    //    [Start]: onClick()
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                break;
            default:
                Log.w(HOMEACTIVITYTAG, "View clicked is have not a registered action in HOMEACTIVITY");
                break;
        }
    }
    //    [End]: onClick()

    //    [Start]: onCreateOptionsMenu()
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_home, menu);

        return true;
    }
//    [End]: onCreateOptionsMenu()

//    [Start]: onOptionsItemSelected()
@Override
public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
        case R.id.action_disconnect:
            signOut(null);
            Log.w(HOMEACTIVITYTAG, "Menu Action_signOut: Current User is signed out");
            finish();
            Log.w(HOMEACTIVITYTAG, "Opening the LoginScreen...");
            openScreen(LoginActivity.class);
            break;

        case R.id.set_message_in_database:
            sendMessageToDatabase("Olá, mundo!!!");
            break;
    }
    return true;
}
//    [End]: onOptionsItemSelected()

}
