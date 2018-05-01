package br.com.thiagosousa.firebaseexamples.activitys;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

import br.com.thiagosousa.firebaseexamples.R;
import br.com.thiagosousa.firebaseexamples.useful.AuthActivity;

public class HomeActivity extends AuthActivity implements View.OnClickListener {
    private static final String HOMEACTIVITYTAG = "Home activity event";

    private FloatingActionButton fab;
    private Toolbar toolbar;

    //    [Start]: onCreate()
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initViews(true);
        configureScreen(true);

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
    }
    return true;
}
//    [End]: onOptionsItemSelected()
}
