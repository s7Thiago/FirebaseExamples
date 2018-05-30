package br.com.thiagosousa.firebaseexamples.activitys;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.thiagosousa.firebaseexamples.R;
import br.com.thiagosousa.firebaseexamples.useful.AuthDataBaseActivity;

public class FirebaseDatabaseActivity extends AuthDataBaseActivity implements View.OnClickListener {
    private static final String TAGFIREBASEDATABASEACTIVITY = "DatabaseActivity event";

    private Button sendToDatabaseButton;
    private TextInputEditText contentData;
    private TextView message;

    //    [Start]:onCreate()
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_database);
        initViews(true);

//        Setup of views clicks
        sendToDatabaseButton.setOnClickListener(this);
    }
//    [End]:onCreate()

    //    [Start]: initViews()
    public void initViews(boolean init) {
        if (init) {
            Log.d(TAGFIREBASEDATABASEACTIVITY, "initViews() is activated in FIREBASEDATABASEACTIVITY");
//            Se ativado, as views do layout são linkadas ao código
            sendToDatabaseButton = findViewById(R.id.send_to_database_button);
            contentData = findViewById(R.id.database_message_field);
            message = findViewById(R.id.database_messag_textview);

        } else {
            Log.w(TAGFIREBASEDATABASEACTIVITY, "initViews() is desactivated in FIREBASEDATABASEACTIVITY");
//            Se desativado, somente avisar no log
        }
    }
    //    [End]: initViews()

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.send_to_database_button:
                Log.w(TAGFIREBASEDATABASEACTIVITY, "send_to_database_button is clicked");
                String mMessage = (Build.DEVICE + "\n" + contentData.getText().toString());
                Log.i(TAGFIREBASEDATABASEACTIVITY,"Sending message: " + message );
                sendMessageToDatabase(mMessage);
                break;
        }
    }

   /* public void sendToDatabase(View view) {
        Log.w(TAGFIREBASEDATABASEACTIVITY, "send_to_database_button is clicked");
        String mMessage = contentData.getText().toString();
        Log.i(TAGFIREBASEDATABASEACTIVITY, "Sending message: " + message);
        sendMessageToDatabase(mMessage);
    }*/
}
