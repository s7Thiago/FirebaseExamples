package br.com.thiagosousa.firebaseexamples.activitys;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputEditText;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

import br.com.thiagosousa.firebaseexamples.R;
import br.com.thiagosousa.firebaseexamples.useful.AuthActivity;

public class RegisterActivity extends AuthActivity implements View.OnClickListener {

    private static final String REGISTERACTIVITYTAG = "RegisterActivity";

    private Button registerButton;
    private TextInputEditText emailField;
    private TextInputEditText passwordField;
    private ConstraintLayout mainRegisterConstainer;

    //    [Start]: onCreate method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initViews(true);
        configureScreen(true);
    }
//    [End]: onCreate method

    //    [Start]: initViews method
    private void initViews(boolean init) {
        if (init) {
//            if init is true, initialize views
            registerButton = findViewById(R.id.createNewUserButton);
            emailField = findViewById(R.id.email_field_for_register);
            passwordField = findViewById(R.id.password_field_for_register);
            mainRegisterConstainer = findViewById(R.id.mainContainerRegister);

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
            Objects.requireNonNull(getSupportActionBar()).setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            initViews(true);
            initOnclickOfViews(true);

//            Initializing the firebase
            mAuth = FirebaseAuth.getInstance();
        } else {
//            Do nothing
            Log.i(REGISTERACTIVITYTAG, "configureScreen is desactivated");
        }
    }
    //    [End]: configureScreen()

    //    [Start]: initOnclickOfViews()
    public void initOnclickOfViews(boolean init) {
        if (init) {
//            if init is true, initialize onClick of views
            init = true;

            registerButton.setOnClickListener(this);

        } else {
//            if init is false, do nothing
            init = false;
        }
    }
    //    [End]: initOnclickOfViews()

    //    [Start]: onSupportNavigateUp method
    @Override
    public boolean onSupportNavigateUp() {
//        finish();
        onBackPressed();
        return true;
    }
    //    [End]: onSupportNavigateUp method

    //        [Start]: onBackPressed method
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        showToastShort("back is pressed");
    }
//    [End]: onBackPressed method

    //    [Start]: onClick()
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.createNewUserButton:
                Log.i(REGISTERACTIVITYTAG, "O evento de clique em onClick() do botão de login foi clicado");

                String email = (emailField.getText().toString());
                String password = (passwordField.getText().toString());

                TextInputEditText[] registerFields = new TextInputEditText[]{emailField, passwordField};

                for (TextInputEditText registerField : registerFields) {
                    if (isEmptyField(registerField)) {
                        Log.w(REGISTERACTIVITYTAG, "Algum campo está vazio. ID: " + registerField.getId());
                        registerField.setError(getString(R.string.empty_field_msg));
                    } else {
                        Log.w(REGISTERACTIVITYTAG, "Tudo certo com os campos de login!\nAutenticando para " +
                                Objects.requireNonNull(mAuth.getCurrentUser()).getEmail());
                        newUser(email, password);
                    }
                }
                break;

            case R.id.mainContainerRegister:
                updateUI(mAuth.getCurrentUser());
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
    // [End]: onCreateOptionsMenu()

    //[Start]: onOptionsItemSelected()
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_disconnect:
                Log.w(REGISTERACTIVITYTAG, "Desconectando usuário através de uma opção do menu");
                signOut(mainRegisterConstainer);
                break;

            default:
                showToastShort("Um item de menu não definido foi clicado");
        }
        return true;
    }
    //[End]: onOptionsItemSelected()
}
