package br.com.thiagosousa.firebaseexamples.useful;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

import br.com.thiagosousa.firebaseexamples.R;
import br.com.thiagosousa.firebaseexamples.activitys.HomeActivity;
import br.com.thiagosousa.firebaseexamples.objects.User;

@SuppressLint("Registered")
public class AuthActivity extends UtilActivity {
    private static final String AUTHACTICITYTAG = "Auth event";

    protected FirebaseAuth mAuth;
    private User objectUser; //is used to stores util datas to be used for other classes

    //    [Start]: updateUI()
    public void updateUI(FirebaseUser user) {
        if (user != null) {
//            Se houver alguém conectado
            showToastShort(user.getEmail() + " está logado agora");
            Log.w(AUTHACTICITYTAG, "o updateUI() detectou que " + user.getEmail() + " está logado.");
        } else {
//            Se não houver ninguém conectado
            showToastShort((getString(R.string.no_user_connected_msg)) + " :(");
            Log.i(AUTHACTICITYTAG, "o updateUI() não detectou nenhum usuário.");
        }
    }
//    [End]: updateUI()

    //    [Start]: newUser()
    public void newUser(final User user) {

        showProgressDialog(true);

        mAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(AUTHACTICITYTAG, "createUserWithEmail:success");
                            FirebaseUser firebaseUser = mAuth.getCurrentUser();

//                            Escrevendo os dados obtidos no banco de dados
                            AuthDataBaseActivity dataBaseOperation = new AuthDataBaseActivity();
                            dataBaseOperation.writeUserInDatabase(user);

                            showProgressDialog(false);
                            updateUI(firebaseUser);
                            openScreen(HomeActivity.class);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(AUTHACTICITYTAG, "createUserWithEmail:failure", task.getException());
                            showToastShort("Authentication failed.");
                            updateUI(null);
                            showProgressDialog(false);
                        }

                        // ...
                    }
                });

    }
    //    [End]: newUser()

    //[Start]: connectUser()
    public void connectUser(final String email, final String password) {

        showProgressDialog(true);

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(AUTHACTICITYTAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            //Instantiate a user object and fill the util attributes with data
                            setUser(new User(null, email, password));

                            Log.w(AUTHACTICITYTAG, "Created a new User instance: \n\n"
                            + "Email: " + getUser().getEmail()
                            +"\nPassword: " + password  +"\n");

                            finish();
                            openScreen(HomeActivity.class);
                            showProgressDialog(false);
                            updateUI(user);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(AUTHACTICITYTAG, "signInWithEmail:failure", task.getException());
                            showToastShort("Authentication failed.");
                            updateUI(null);
                            showProgressDialog(false);
                        }

                        // ...
                    }
                });
    }
    //[End]: connectUser()

    //    [Start]: signOut()
    public void signOut(View container_for_notification) {

        if (isAnyoneConnected()) {
            Log.w(AUTHACTICITYTAG, "The user " + (Objects.requireNonNull(mAuth.getCurrentUser()).getEmail() + " is signed out!"));
            mAuth.signOut();
            updateUI(null);
        } else {
            Log.w(AUTHACTICITYTAG, getString(R.string.no_user_connected_msg));
            showToastShort(getString(R.string.no_user_connected_msg));
        }
    }
//    [End]: signOut()

    //    [Start]: isAnyoneConnected()
    public boolean isAnyoneConnected() {

//        Retorna 'true' se houver alguém conectado
        return mAuth.getCurrentUser() != null;
    }
//    [End]: isAnyoneConnected()

    //    [Start]: verifyFields()
    /**
     * Used for validate a collection fields, not alow that some are void content
     **/
    protected boolean verifyFields(TextInputEditText[] textInputEditTexts) {
        boolean fieldsOK = false;

        Log.i(AUTHACTICITYTAG, "verifiyng login fields...");

        for (TextInputEditText textInputEditText : textInputEditTexts) {

            if (!isEmptyField(textInputEditText)) {

//                Está tudo correto. Nenhum campo vazio
                Log.i(AUTHACTICITYTAG, "There´s nothing wrong with the fields!");
                fieldsOK = true;
            } else {

//                Foi detectado algum campo vazio. mostrar mensagem de erro
                Log.i(AUTHACTICITYTAG, "There is something wrong with the fields!");
                fieldsOK = false;
                textInputEditText.setError(getString(R.string.empty_field_msg));
            }

        }

        Log.w(AUTHACTICITYTAG, "verifyLoginFields() returned " + fieldsOK + ".");
        return fieldsOK;
    }
//    [End]:verifyFields()

    //    [Start]: verifyFields()
    /**
     * Used for validate a collection fields, not alow that some are void content
     **/
    protected boolean verifyFields(EditText[] textInputEditTexts) {
        boolean fieldsOK = false;

        Log.i(AUTHACTICITYTAG, "verifiyng login fields...");

        for (EditText textInputEditText : textInputEditTexts) {

            if (!isEmptyField(textInputEditText)) {

//                Está tudo correto. Nenhum campo vazio
                Log.i(AUTHACTICITYTAG, "There´s nothing wrong with the fields!");
                fieldsOK = true;
            } else {

//                Foi detectado algum campo vazio. mostrar mensagem de erro
                Log.i(AUTHACTICITYTAG, "There is something wrong with the fields!");
                fieldsOK = false;
                textInputEditText.setError(getString(R.string.empty_field_msg));
            }

        }

        Log.w(AUTHACTICITYTAG, "verifyLoginFields() returned " + fieldsOK + ".");
        return fieldsOK;
    }
//    [End]:verifyFields()

    //    [Start]:getFirebaseAuthInstance()
    public FirebaseAuth getFirebaseAuthInstance() {
        return mAuth = FirebaseAuth.getInstance();
    }
    //    [End]:getFirebaseAuthInstance()

    //    [Start]:getters and setters for user object
    public User getUser() {
        return objectUser;
    }

    public void setUser(User user) {
        this.objectUser = user;
    }
//    [End]:getters and setters for user object
}
