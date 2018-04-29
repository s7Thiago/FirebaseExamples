package br.com.thiagosousa.firebaseexamples.useful;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

import br.com.thiagosousa.firebaseexamples.R;
import br.com.thiagosousa.firebaseexamples.activitys.HomeActivity;

@SuppressLint("Registered")
public class AuthActivity extends UtilActivity {
    private static final String AUTHACTICITYTAG = "Auth event";

    protected FirebaseAuth mAuth;


    //    [Start]: onStart()
    @Override
    protected void onStart() {
        super.onStart();

        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);

    }
    //    [End]: onStart()

    //    [Start]: updateUI()
    public void updateUI(FirebaseUser user) {
        if (user != null) {
//            Se houver alguém conectado
            showToastShort(user.getEmail() + " está logado agora");
            Log.i(AUTHACTICITYTAG, "o updateUI() detectou que " + user.getEmail() + " está logado.");
        } else {
//            Se não houver ninguém conectado
            showToastShort((getString(R.string.no_user_connected_msg)) + " :(");
            Log.i(AUTHACTICITYTAG, "o updateUI() não detectou nenhum usuário.");
        }
    }
//    [End]: updateUI()

    //    [Start]: newUser()
    public void newUser(String email, String password) {

        showProgressDialog(true);

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(AUTHACTICITYTAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            showProgressDialog(false);
                            updateUI(user);
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
    public void connectUser(String email, String password) {

        showProgressDialog(true);

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(AUTHACTICITYTAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            showProgressDialog(false);
                            updateUI(user);
                            openScreen(HomeActivity.class);
                            finish();
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

}
