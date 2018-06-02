package br.com.thiagosousa.firebaseexamples.activitys;

import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.TextInputEditText;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.nio.charset.Charset;

import br.com.thiagosousa.firebaseexamples.R;
import br.com.thiagosousa.firebaseexamples.useful.AuthDataBaseActivity;

public class FirebaseDatabaseActivity extends AuthDataBaseActivity implements View.OnClickListener {
    private static final String TAGFIREBASEDATABASEACTIVITY = "DatabaseActivity event";

    private Button sendToDatabaseButton;
    private TextInputEditText contentData;
    private TextView message_textview;

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

    //    [Start]: onRestoreInstanceState()
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.w(TAGFIREBASEDATABASEACTIVITY, "onRestoreInstanceState() foi chamado");

//        Obtendo a referência das views no arquivo de layout XML
        message_textview = findViewById(R.id.database_messag_textview);
        contentData = findViewById(R.id.database_message_field);

        Log.w(TAGFIREBASEDATABASEACTIVITY, "Racuperando os dados salvos no Bundle:");
        Log.w(TAGFIREBASEDATABASEACTIVITY, "Dado 1:   " + String.valueOf(savedInstanceState.getString("textViewContent")));
        Log.w(TAGFIREBASEDATABASEACTIVITY, "Dado 2:   " + String.valueOf(savedInstanceState.getString("editTextContent")));
//        Pegando os dados salvos no Bundle
        String contentDataInner =  String.valueOf(savedInstanceState.getString("textViewContent"));
        String messageViewContent = String.valueOf(savedInstanceState.getString("editTextContent"));

        Log.i(TAGFIREBASEDATABASEACTIVITY, "Realocando os dados nas suas respectivas views");
//        Realocando os dados nas suas respectivas views
        message_textview.setText(contentDataInner);
        contentData.setText(messageViewContent);

        super.onRestoreInstanceState(savedInstanceState);
    }
//    [End]: onRestoreInstanceState()


    //    [Start]: onSaveInstanceState()
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.w(TAGFIREBASEDATABASEACTIVITY, "onSaveInstanceState() foi chamado");

//        Capturando a referência direta para a view no arquivo de layout
        message_textview = findViewById(R.id.database_messag_textview);
        contentData = findViewById(R.id.database_message_field);

        Log.i(TAGFIREBASEDATABASEACTIVITY, "Armazenando o conteúdo das Views");
//        Armazenando o conteúdo das Views
        String contentDataInner = contentData.getText().toString();
        String messageViewContent = message_textview.getText().toString();

        Log.i(TAGFIREBASEDATABASEACTIVITY, "Salvando as informações para serem restauradas posteriormente");
//        Salvando as informações para serem restauradas posteriormente
        outState.putString("textViewContent", messageViewContent);
        outState.putString("editTextContent", contentDataInner);

        super.onSaveInstanceState(outState);
    }
//    [End]: onSaveInstanceState()

    //    [Start]: initViews()
    public void initViews(boolean init) {
        if (init) {
            Log.d(TAGFIREBASEDATABASEACTIVITY, "initViews() is activated in FIREBASEDATABASEACTIVITY");
//            Se ativado, as views do layout são linkadas ao código
            sendToDatabaseButton = findViewById(R.id.send_to_database_button);
            contentData = findViewById(R.id.database_message_field);
            message_textview = findViewById(R.id.database_messag_textview);

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
                String mMessage = (Build.BRAND + "\n" + contentData.getText().toString());
                Log.i(TAGFIREBASEDATABASEACTIVITY, "Sending message_textview: " + mMessage);
                sendMessageToDatabase(mMessage);

                //Atualizando a tela com o estado atual do Banco de dados
                updateUI(message_textview);
                break;
        }
    }

   /* public void sendToDatabase(View view) {
        Log.w(TAGFIREBASEDATABASEACTIVITY, "send_to_database_button is clicked");
        String mMessage = contentData.getText().toString();
        Log.i(TAGFIREBASEDATABASEACTIVITY, "Sending message_textview: " + message_textview);
        sendMessageToDatabase(mMessage);
    }*/
}
