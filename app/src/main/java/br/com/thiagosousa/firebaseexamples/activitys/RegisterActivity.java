package br.com.thiagosousa.firebaseexamples.activitys;

import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.material.textfield.TextInputEditText;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

import br.com.thiagosousa.firebaseexamples.R;
import br.com.thiagosousa.firebaseexamples.objects.User;
import br.com.thiagosousa.firebaseexamples.useful.AuthActivity;

public class RegisterActivity extends AuthActivity implements View.OnClickListener {

    private static final String TAG = "RegisterActivity";

    private TextInputEditText nameField;
    private TextInputEditText lastNameField;
    private TextInputEditText emailField;
    private TextInputEditText passwordField;
    private TextInputEditText confirmPasswordField;
    private Button registerButton;
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

    //    [Start]: onStart()
    @Override
    protected void onStart() {
        super.onStart();
    }
//    [End]: onStart()

    //    [Start]: initViews method
    @Override
    public void initViews(boolean init) {
        if (init) {
//            if init is true, initialize views
            registerButton = findViewById(R.id.createNewUserButton);
            nameField = findViewById(R.id.name_field_for_register);
            lastNameField = findViewById(R.id.last_name_field_for_register);
            emailField = findViewById(R.id.email_field_for_register);
            passwordField = findViewById(R.id.password_field_for_register);
            confirmPasswordField = findViewById(R.id.confirm_password_field_for_register);
            mainRegisterConstainer = findViewById(R.id.mainContainerRegister);

        } else {
//            if init is false, do nothing
            init = false;
        }
    }
//    [End]: initViews method

    //    [Start]: configureScreen()
    @Override
    public void configureScreen(boolean setup) {
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
            Log.i(TAG, "configureScreen is desactivated");
        }
    }
    //    [End]: configureScreen()

    //    [Start]: initOnclickOfViews()
    public void initOnclickOfViews(boolean init) {
        if (init) {
            registerButton.setOnClickListener(this);

        } else {
            Log.w(TAG, "initOnclickOfViews: eventos de clique dos botoes desabilitados");
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
                Log.i(TAG, "O evento de clique em do botão de registro foi chamado");

                int cont = 0;

                TextInputEditText[] registerFields = new TextInputEditText[]{
                        nameField,
                        lastNameField,
                        emailField,
                        passwordField,
                        confirmPasswordField};

                if((passwordField.getText().toString()).equals(confirmPasswordField.getText().toString())) {
                    for (TextInputEditText registerField : registerFields) {
                        Log.w(TAG, "Verificando campo " + cont);
                        if (isEmptyField(registerFields[cont])) {
                            Log.w(TAG, "O campo " + registerField.getId() + " estava vazio!");
                            registerField.setError(getString(R.string.empty_field_msg));
                        } else {

//                        Criando um usuario com os dados obtidos pelos campos do formulario de cadastro
                            User user = new User(
                                    nameField.getText().toString(),
                                    lastNameField.getText().toString(),
                                    emailField.getText().toString(),
                                    passwordField.getText().toString(),
                                    false
                            );

                        /*Tratando algum erro que possa ocorrer em relacao a consistencia dos
                         dados recebidos dos campos*/
                            try {
                                newUser(user);
                            }catch (IllegalArgumentException e){
                                showToastShort(getString(R.string.error_register_fields_content_bad));
                                showProgressDialog(false);
                            }

                        /*Log.w(TAG, "Tudo certo com os campos de login!\nAutenticando para " +
                                (mAuth.getFirebaseAuthInstance()).getEmail());*/
                        }
                        cont++;
                    }
//                    Os campos de senha nao tem o mesmo conteudo
                } else {
                    showSnackBarLong(getString(R.string.error_confirm_password), mainRegisterConstainer);
                }
                break;
        }
    }
    //    [End]: onClick()


//    [Start]: onUserDataChangedInDatabase()
    @Override
    public void onUserDataChangedInDatabase() {
    }
//    [End]: onUserDataChangedInDatabase()
}
