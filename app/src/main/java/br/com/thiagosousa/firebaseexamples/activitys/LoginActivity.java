package br.com.thiagosousa.firebaseexamples.activitys;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

import br.com.thiagosousa.firebaseexamples.R;
import br.com.thiagosousa.firebaseexamples.objects.User;
import br.com.thiagosousa.firebaseexamples.useful.AuthActivity;

public class LoginActivity extends AuthActivity implements View.OnClickListener {
    private static final String TAG = "LoginActivity";

    private Button registerScreenButton;
    private Button loginButton;
    private EditText emailField;
    private EditText passwordField;
    private ConstraintLayout mainLoginConstraintLayout;
    private AnimationDrawable animationDrawable;

    //
    User user;

    //    [Start]: onCreate method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews(true);
        configureScreen(true);
        playScreenAnimation(false);
        redirect();

    }
//    [End]: onCreate method

    //    [Start]: initViews method
    @Override
    public void initViews(boolean init) {
        if (init) {
//            if init is true, initialize views
            registerScreenButton = findViewById(R.id.registerScreenButton);
            emailField = findViewById(R.id.email_field_for_login);
            passwordField = findViewById(R.id.password_field_for_login);
            loginButton = findViewById(R.id.signInButton);
            mainLoginConstraintLayout = findViewById(R.id.main_login_constraint_layout);

        } else {
//            if init is false, do nothing
            Log.i(TAG, "initViews() is desactivated");
        }
    }
//    [End]: initViews method

    //    [Start]: configureScreen()
    @Override
    public void configureScreen(boolean setup) {
        if (setup) {
//            Configure screen, if setup variable is true
            initOnclickOfViews(true);

//            Initializing the firebase
            mAuth = FirebaseAuth.getInstance();
        } else {
//            Do nothing
            Log.i(TAG, "configureScreen is desactivated");
        }
    }
    //    [End]: configureScreen()

    //    [Start]: initOnclickOfViews()
    public void initOnclickOfViews(boolean init) {
        if (init) {
//            if init is true, initialize onClick of views

            registerScreenButton.setOnClickListener(this);
            loginButton.setOnClickListener(this);

        } else {
//            if init is false, do nothing
            Log.w(TAG, "The inclick of all views has been desactivated!");
        }
    }
    //    [End]: initOnclickOfViews()

    //    [Start]: onStart method
    @Override
    protected void onStart() {
        super.onStart();

//        redirect();
    }
//    [End]: onStart method

    //    [Start]: onPause method
    @Override
    protected void onPause() {
        super.onPause();
//        isShowingKeyboard();
    }
//    [End]: onPause method

    //    [Start]: onClick method
    @Override
    public void onClick(View view) {
        int viewID = view.getId();
        Context context = getApplicationContext();

        switch (viewID) {
            case R.id.registerScreenButton:
                showToastShort(getString(R.string.button_register_screen_opening));
//                finish();
                startActivity(new Intent(context, RegisterActivity.class));
                break;

            case R.id.signInButton:
                Log.i(TAG, "onClick(): O botão de login foi pressionado");

                String email = (emailField.getText().toString());
                String password = (passwordField.getText().toString());
                final String userData[] = new String[]{email, password};

                EditText[] loginFields = new EditText[]{emailField, passwordField};

                if (verifyFields(loginFields)) {
//                    Se não houver nada de errado com os campos

                    if ((email.toLowerCase()).equals("ts open")) {
                        openScreen(HomeActivity.class);
                    } else {

                        //Preparando login. O ultimo parametro eh a tela que sera aberta, se o login for efetuado com sucesso
                        connectUser(email, password, HomeActivity.class);
                    }
                } else {
//                    Se houver algo de errado com algum dos campos
                    Log.w(TAG, "Há algo de errado com algum dos campos");
                }
                break;
        }
    }
    //    [End]: onClick method

    //[Start]: onSaveInstanceState()
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        emailField = findViewById(R.id.email_field_for_login);
        passwordField = findViewById(R.id.password_field_for_login);

        String email = String.valueOf(emailField.getText().toString()),
                password = String.valueOf(passwordField.getText().toString());

        /*Capturando os dados dos campos mais importantes desta tela para serem recuperados
        caso a tela seja rotacionada*/
        Log.w(TAG, "onSaveInstanceState() em LoginActivity foi chamado");
        outState.putString("loginEmailSaveData", email);
        outState.putString("loginPasswordSaveData", password);
        super.onSaveInstanceState(outState);
    }
    //[End]: onSaveInstanceState()

    //[Start]: onRestoreInstanceState()
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        emailField = findViewById(R.id.email_field_for_login);
        passwordField = findViewById(R.id.password_field_for_login);

        Log.w(TAG, "onRestoreInstanceState() em LoginActivity foi chamado");
        Log.w(TAG, "Restaurando as seguntes informações para as respectivas views:" +
                "\n\n" + String.valueOf(savedInstanceState.getString("loginEmailSaveData")) +
                "\n" + String.valueOf(savedInstanceState.getString("loginPasswordSaveData")));

        //Recuperando informações gravadas pelo método onSaveInstanceState()
        String email = String.valueOf(savedInstanceState.getString("loginEmailSaveData")),
                password = String.valueOf(savedInstanceState.getString("loginPasswordSaveData"));

        //Realocando as informações nas suas respectivas views
        emailField.setText(email);
        passwordField.setText(password);

        super.onRestoreInstanceState(savedInstanceState);
    }
    //[End]: onRestoreInstanceState()

    //    [Start]: playScreenAnimation()
    public void playScreenAnimation(boolean load) {
        if (load) {
            //inicia a animacao da tela
            mainLoginConstraintLayout.setBackgroundResource(R.drawable.bg_login_screen_animation);
            animationDrawable = (AnimationDrawable) mainLoginConstraintLayout.getBackground();
            animationDrawable.setEnterFadeDuration(4000);
            animationDrawable.setExitFadeDuration(4000);
            animationDrawable.start();
        } else {
            Log.w(TAG, "playScreenAnimation() were called, but is desactivated");
        }
    }
//    [End]: playScreenAnimation()

    //    [Start]: getUserDataFromDatabase()
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
                            openScreen(HomeActivity.class);
                            finish();
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
//    [End]: getUserDataFromDatabase()


//    [Start]: redirect()
    private void redirect() {
        //        Verifica se há alguém conectado. Se sim, abre a tela adequada
        if (isAnyoneConnected()) {
            onUserDataChangedInDatabase();
        } else {
            Log.i(TAG, "Não há ninguém conectado. Permanecendo nesta tela.");
        }
    }
//    [End]: redirect()

}
