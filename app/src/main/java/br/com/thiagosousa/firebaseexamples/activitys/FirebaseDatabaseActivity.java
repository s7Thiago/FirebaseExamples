package br.com.thiagosousa.firebaseexamples.activitys;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.com.thiagosousa.firebaseexamples.R;
import br.com.thiagosousa.firebaseexamples.adapter.MessageAdapter;
import br.com.thiagosousa.firebaseexamples.objects.Message;
import br.com.thiagosousa.firebaseexamples.useful.AuthDataBaseActivity;

public class FirebaseDatabaseActivity extends AuthDataBaseActivity implements View.OnClickListener {
    private static final String TAGFIREBASEDATABASEACTIVITY = "DatabaseActivity event";

    private Button sendToDatabaseButton;
    private TextInputEditText contentData;
    private TextView message_textview;
    private ListView messages_listView;
    private List<Message> messagesList = new ArrayList<Message>();

    //    [Start]:onCreate()
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_database);
        initViews(true);

//        Setup of views clicks
        sendToDatabaseButton.setOnClickListener(this);

        //initializing FirebaseAuth
        mAuth = FirebaseAuth.getInstance();
    }
//    [End]:onCreate()

    //    [Start]:onStart()
    @Override
    protected void onStart() {
        super.onStart();
        loadMessagesFromFirebaseDatabase();

//        Ocultando a actionbar
        getSupportActionBar().hide();

    }
    //    [End]:onStart()


    //    [Start]:loadMessagesFromFirebaseDatabase()
    public void loadMessagesFromFirebaseDatabase(){

        //Capturando as mensagens no banco de dados e adicionando a uma lista para serem exibidas
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

//                Limpando a lista antes de popular
                messagesList.clear();

//                Populando a lista com as mensagens do banco de dados
                for(DataSnapshot messageSnapshot: dataSnapshot.getChildren()){
                    Message message = messageSnapshot.getValue(Message.class);
                    messagesList.add(message);
                }

//                Configurando a lista para exibir as ultimas mensagens
                MessageAdapter adapter = new MessageAdapter(getBaseContext(), messagesList);
                messages_listView.setAdapter(adapter);

//                Colocando o scroll do listview na ultima posicao para exibir a mensagem mais recente
                messages_listView.setSelection(messagesList.size());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                showToastShort("Operação Cancelada");
            }
        });
    }
    //    [End]:loadMessagesFromFirebaseDatabase()

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
            messages_listView = findViewById(R.id.database_activity_listview);

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
                if(!isEmptyField(contentData)) {
                    Log.w(TAGFIREBASEDATABASEACTIVITY, "send_to_database_button is clicked");
                    String mMessage = Objects.requireNonNull(contentData.getText().toString());
                    Log.i(TAGFIREBASEDATABASEACTIVITY, "Sending message_textview: " + mMessage);
                    sendMessageToDatabase(mMessage);

//                    Limpando o campo
                    contentData.setText("");

                    //Atualizando a tela com o estado atual do Banco de dados
                    updateUI(message_textview);
                }else{
                    showToastShort("Please, type something");
                }
                break;
        }
    }
}
