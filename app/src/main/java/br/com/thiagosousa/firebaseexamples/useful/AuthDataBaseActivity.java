package br.com.thiagosousa.firebaseexamples.useful;

import android.annotation.SuppressLint;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

import br.com.thiagosousa.firebaseexamples.objects.Message;

@SuppressLint("Registered")
public class AuthDataBaseActivity extends AuthActivity {

    private static final String TAGAUTHDATABASEACTIVITY = "DatabaseActivity event";

    //    Retrieving the instance of FirebaseDatabase
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    //    reference location for written
    DatabaseReference mRef = database.getReference("messages");
    private String messageID;

    //[Start]: sendMessageToDatabase()
    public void sendMessageToDatabase(String message) {

        //Criando um novo nó para a nova mensagem
        //O novo nó ficará em /message/messageID
        messageID = mRef.push().getKey();

        //Criando um objeto Message
        Message mMessage = new Message(message);

        //Enviando para o nó 'messages' usando o respectivo ID
        mRef.child(messageID).setValue(mMessage);

        //Mostrando status ao usuário
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
    public void updateUI(final TextView messageView) {

        Log.w(TAGAUTHDATABASEACTIVITY, "AuthDatabaseActivity updateUI() foi invocado");

        //Decide o que fazer quando é detectada uma mudança na estrutura dos dados
        mRef.child(messageID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Recupera o novo dado em um objeto Message e inere num textview
                Message mMessage = dataSnapshot.getValue(Message.class);

                //Recuperando a mensagem
                String message_received = mMessage != null ? mMessage.getMessageContent() : "null: nada recebido";

                messageView.setText(message_received);

                Log.w(TAGAUTHDATABASEACTIVITY,"Mensagem recuperada: \n\n" + message_received );
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Failed to read value
                Log.w(TAGAUTHDATABASEACTIVITY, "Falha ao ler valor (AuthDatabaseActivity updateUI()).", databaseError.toException());
            }
        });
    }
//[End]:updateUI()

}
