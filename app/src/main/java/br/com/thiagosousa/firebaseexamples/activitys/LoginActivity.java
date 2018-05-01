package br.com.thiagosousa.firebaseexamples.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import br.com.thiagosousa.firebaseexamples.R;
import br.com.thiagosousa.firebaseexamples.useful.AuthActivity;

public class LoginActivity extends AuthActivity implements View.OnClickListener {
    private static final String LOGINAVTIVITYTAG = "LoginAcivity event";

    private Button registerScreenButton;
    private Button loginButton;
    private TextInputEditText emailField;
    private TextInputEditText passwordField;

    //    [Start]: onCreate method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews(true);
        configureScreen(true);

    }
//    [End]: onCreate method

    //    [Start]: initViews method
    private void initViews(boolean init) {
        if (init) {
//            if init is true, initialize views
            init = true;
            registerScreenButton = findViewById(R.id.registerScreenButton);
            emailField = findViewById(R.id.email_field_for_login);
            passwordField = findViewById(R.id.password_field_for_login);
            loginButton = findViewById(R.id.signInButton);

        } else {
//            if init is false, do nothing
            init = false;
        }
    }
//    [End]: initViews method

    //    [Start]: configureScreen()
    private void configureScreen(boolean setup) {
        if (setup) {
//            Configure screen, if setup variable is true
            initOnclickOfViews(true);

//            Initializing the firebase
            mAuth = FirebaseAuth.getInstance();
        } else {
//            Do nothing
            Log.i(LOGINAVTIVITYTAG, "configureScreen is desactivated");
        }
    }
    //    [End]: configureScreen()

    //    [Start]: initOnclickOfViews()
    public void initOnclickOfViews(boolean init) {
        if (init) {
//            if init is true, initialize onClick of views
            init = true;

            registerScreenButton.setOnClickListener(this);
            loginButton.setOnClickListener(this);

        } else {
//            if init is false, do nothing
            init = false;
        }
    }
    //    [End]: initOnclickOfViews()

    //    [Start]: onStart method
    @Override
    protected void onStart() {
        super.onStart();

        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);

        //        Verifica se há alguém conectado, e abre a HomeActivity se positivo
        if (isAnyoneConnected()) {
            openScreen(HomeActivity.class);
            finish();
        } else {
            Log.i(LOGINAVTIVITYTAG,"Não há ninguém conectado. Permanecendo nesta tela." );
        }

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
                Log.i(LOGINAVTIVITYTAG, "onClick(): O botão de login foi pressionado");

                String email = (emailField.getText().toString());
                String password = (passwordField.getText().toString());

                TextInputEditText[] loginFields = new TextInputEditText[]{emailField, passwordField};

                if (verifyFields(loginFields)) {
//                    Se não houver nada de errado com os campos
                    connectUser(email, password);

                } else {
//                    Se houver algo de errado com algum dos campos
                    Log.w(LOGINAVTIVITYTAG,"Há algo de errado com algum dos campos" );
                }
                break;
        }
    }
    //    [End]: onClick method
}
