package br.com.thiagosousa.firebaseexamples.useful;

import android.annotation.SuppressLint;
import android.util.Log;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

@SuppressLint("Registered")
public class AuthDataBaseActivity extends AuthActivity {

    private static final String TAGAUTHDATABASEACTIVITY = "DatabaseActivity event";

    //    Retrieving the instance of FirebaseDatabase
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    //    reference location for written
    DatabaseReference mRef = database.getReference("message");

    //[Start]: sendMessageToDatabase()
    public void sendMessageToDatabase(String message) {
        mRef.setValue(message);
        showToastShort("Posting in database: " + message);
    }
//[End]: sendMessageToDatabase()

// [Start]: sendMessageToDatabase() with custom reference
    public void sendMessageToDatabase(String reference, String message) {
        mRef = database.getReference(reference);
        mRef.setValue(message);
        showToastShort("Posting in database: " + message);
    }
//[End]: sendMessageToDatabase() with custom reference

//[Start]:updateUI()
    public void updateUI() {

    }
//[End]:updateUI()

}
