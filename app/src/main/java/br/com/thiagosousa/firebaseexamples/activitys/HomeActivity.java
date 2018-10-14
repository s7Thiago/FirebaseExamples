package br.com.thiagosousa.firebaseexamples.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

import br.com.thiagosousa.firebaseexamples.R;
import br.com.thiagosousa.firebaseexamples.RecyclerViewActivity;
import br.com.thiagosousa.firebaseexamples.activitys.coordinatorlayoutexample.CoordinatorLayoutExampleActivity;
import br.com.thiagosousa.firebaseexamples.objects.User;
import br.com.thiagosousa.firebaseexamples.useful.AuthActivity;
import br.com.thiagosousa.firebaseexamples.useful.Observer.Observer;

public class HomeActivity extends AuthActivity implements View.OnClickListener, AdapterView.OnItemClickListener, Observer {
    private static final String TAG = "HomeActivity";

    private FloatingActionButton fab;
    private Toolbar toolbar;
    private ListView homeItens;
    private String[] itens = new String[]{
            "Interação com o Firebase Database",
            "Exemplos de lista (com Spinner Selector)",
            "ListView Customizado", "Gradient animation",
            "SharedAnimation Example", "CoordinatorLayout Example",
            "MoveFun Logo SVG", "Bottom Sheet Example", "ListView com viewholder design pattern",
            "RecyclerView Example"};
    private ArrayAdapter<String> mAdapter;
    private CoordinatorLayout rootContainer;
    private User user;

    //    [Start]: onCreate()
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initViews(true);
        configureScreen(true);

        homeItens.setOnItemClickListener(this);

//        user = (User) getIntent().getParcelableExtra ("UserObject");
//        Log.w(TAG, "onCreate: Usuario capturado da tela de login:" + user.getEmail());

    }
    //    [End]: onCreate()

    //[Start]: onStart()
    @Override
    protected void onStart() {
        super.onStart();

        //Faz algo caso as informacoes do atual usuario sejam alteradas
        onUserDataChangedInDatabase();
    }
//[End]: onStart()

    //    [Start]: initViews()
    @Override
    public void initViews(boolean init) {
        if (init) {
            Log.d(TAG, "initViews() is activated in HOMEACTIVITY");
//            Se ativado, as views do layout são linkadas ao código

            fab = findViewById(R.id.fab);
            toolbar = findViewById(R.id.toolbar);
            homeItens = findViewById(R.id.home_listView);
            rootContainer = findViewById(R.id.home_activity_root_container);

        } else {
            Log.w(TAG, "initViews() is desactivated in HOMEACTIVITY");
//            Se desativado, somente avisar no log
        }
    }
    //    [End]: initViews()

    //    [Start]: configureScreen()
    @Override
    public void configureScreen(boolean config) {
        if (config) {
            Log.d(TAG, "configureScreen() is activated in HOMEACTIVITY");
//            Se ativado, alguns aspectos nesta tela serão modificados

            setSupportActionBar(toolbar);
//            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

//            Configurando onClicks
            fab.setOnClickListener(this);

            //            Initializing the firebase
            mAuth = getFirebaseAuthInstance();

//           Setting up the ListView
            mAdapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_list_item_1, itens);
            homeItens.setAdapter(mAdapter);

        } else {
            Log.w(TAG, "configureScreen() is desactivated in HOMEACTIVITY");
//            Se desativado, somente avisar no log
        }
    }
//    [End]: configureScreen()

    //    [Start]: onClick()
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:

                if (mAuth.getCurrentUser() != null) {
                    Snackbar
                            .make(view,
                                    "Bem-vindo(a), " + Objects
                                            .requireNonNull(mAuth
                                                    .getCurrentUser())
                                            .getEmail(),
                                    Snackbar
                                            .LENGTH_LONG)
                            .setAction("Action", null)
                            .show();
                } else {
                    Snackbar
                            .make(view, "No one logged in!",
                                    Snackbar
                                            .LENGTH_LONG)
                            .setAction("Action", null)
                            .show();
                }
                break;
            default:
                Log.w(TAG, "View clicked is have not a registered action in HOMEACTIVITY");
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
                signOut();
                Log.w(TAG, "Menu Action_signOut: Current User is signed out");
                finish();
                Log.w(TAG, "Opening the LoginScreen...");
                openScreen(LoginActivity.class);
                break;

            case R.id.set_message_in_database:
                sendMessageToDatabase("Olá, mundo!!!");
                break;

            case R.id.show_snackkbar_with_current_user_information:
//                showToastShort(getCurrentUserOfDatabase().toString());
                break;
        }
        return true;
    }
//    [End]: onOptionsItemSelected()

    //[Start]: onItemClick()
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {

            case 0:
                startActivity(new Intent(getBaseContext(), FirebaseDatabaseActivity.class));
                break;

            case 1:
                startActivity(new Intent(getBaseContext(), FragmentActivity.class));
                break;

            case 2:
                openScreen(CustomListActivity.class);
                break;

            case 3:
                openScreen(GradientBackgroundAnimActivity.class);
                break;

            case 4:
                openScreen(SharedAnimationExample.class);
                break;

            case 5:
                openScreen(CoordinatorLayoutExampleActivity.class);
                break;
            case 6:
                openScreen(MoveFunLogoSvgActivity.class);
                break;

            case 7:
                openScreen(BottomSheetActivity.class);
                break;

            case 8:
                openScreen(CustomListViewViewHolderActivity.class);
                break;

            case 9:
                openScreen(RecyclerViewActivity.class);
                break;

            default:
                Log.w(TAG, getResources().getString(R.string.no_action_attirbuted));
                showToastShort(getResources().getString(R.string.no_action_attirbuted));
                break;
        }
    }
    //[End]: onItemClick()

    //    [Start]: onUserDataChangedInDatabase()
    @Override
    public void onUserDataChangedInDatabase() {
        FirebaseDatabase.getInstance().getReference("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot user : dataSnapshot.getChildren()) {

                    User inneUser = user.getValue(User.class);

                    Log.d(TAG, "onDataChange: Capturing current user for extract data: " + inneUser);

                    assert inneUser != null;
                    if (inneUser.getEmail().toLowerCase().equals(Objects.requireNonNull(mAuth.getCurrentUser()).getEmail())) {

                        Log.i(TAG, "onDataChange: currentUser: \n" + mAuth.getCurrentUser().getEmail());
                        Log.w(TAG, "\n--------------------\nonDataChange: Done!" + "" + "\n\n " + inneUser + "\nisAdmin: " + inneUser.isAdmin() + "\n\n-------------------------");

                        //Abaixo vao os codigos de comportamento que definel o que fazer se algum dado for alterado

                        if (inneUser.isAdmin()) {
                            Objects.requireNonNull(getSupportActionBar()).setTitle(inneUser.getName() + " admin");
                        } else {
                            openScreen(FirebaseDatabaseActivity.class);
                            finish();
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
//    [End]: onUserDataChangedInDatabase()

    //    Caso haja alguma alteracao em algum dado do objeto usuario, faz o tratamente adequado
    @Override
    public void update(Object object) {
        if (object instanceof User) {

        }
    }

}
