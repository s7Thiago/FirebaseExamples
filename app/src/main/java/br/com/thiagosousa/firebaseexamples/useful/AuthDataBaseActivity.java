package br.com.thiagosousa.firebaseexamples.useful;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.FirebaseException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import br.com.thiagosousa.firebaseexamples.objects.Message;
import br.com.thiagosousa.firebaseexamples.objects.User;

@SuppressLint("Registered")
public class AuthDataBaseActivity extends AuthActivity {

    private static final String TAGAUTHDATABASEACTIVITY = "DatabaseActivity event";

    //    Retrieving the instance of FirebaseDatabase
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    //    reference location for written
    protected DatabaseReference messagesReference = database.getReference("Messages");
    protected DatabaseReference usersReference = database.getReference("Users");
    private String messageID;
    private String userID;
    protected  User currentUserOfDatabase;

    //[Start]: sendMessageToDatabase()
    public void sendMessageToDatabase(String message) {

        //Criando um novo nó para a nova mensagem
        //O novo nó ficará em /message/messageID
        messageID = messagesReference.push().getKey();

        //Criando um objeto Message
        Message mMessage = new Message(message, new SimpleDateFormat("dd/MM/yyyy").format(new Date()), mAuth.getCurrentUser().getEmail());

        //Enviando para o nó 'messages' usando o respectivo ID
        messagesReference.child(messageID).setValue(mMessage);

        //Mostrando status ao usuário
        showToastShort("Posting in database: " + message);
    }
//[End]: sendMessageToDatabase()

    // [Start]: sendMessageToDatabase() with custom reference
    public void sendMessageToDatabase(String reference, String message) {
        messagesReference = database.getReference(reference);
        messagesReference.setValue(message);
        showToastShort("Posting in database: " + message);
    }
//[End]: sendMessageToDatabase() with custom reference

    //[Start]:updateUI()
    public void updateUI(final TextView messageView) {

        Log.w(TAGAUTHDATABASEACTIVITY, "AuthDatabaseActivity updateUI() foi invocado");

        //Decide o que fazer quando é detectada uma mudança na estrutura dos dados
        messagesReference.child(messageID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Recupera o novo dado em um objeto Message e inere num textview
                Message mMessage = dataSnapshot.getValue(Message.class);

                //Recuperando a mensagem
                String message_received = mMessage != null ? mMessage.getMessageContent() : "null: nada recebido";

                messageView.setText(message_received);

                Log.w(TAGAUTHDATABASEACTIVITY, "Mensagem recuperada: \n\n" + message_received);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Failed to read value
                Log.w(TAGAUTHDATABASEACTIVITY, "Falha ao ler valor (AuthDatabaseActivity updateUI()).", databaseError.toException());
            }
        });
    }
//[End]:updateUI()

    //[Start]:getters and setters
    public User getCurrentUserOfDatabase() {
        return currentUserOfDatabase;
    }

    public void setCurrentUserOfDatabase(User currentUserOfDatabase) {
        this.currentUserOfDatabase = currentUserOfDatabase;
    }
//[End]:getters and setters

    // [Start]:writeUserInDatabase()
    public void writeUserInDatabase(User user) {

//        Pegando um id para o user que será salvo
        userID = usersReference.push().getKey();

//        Salvando as informacoes de um user no banco de dados
        usersReference.child(userID).setValue(user);
    }
// [End]:writeUserInDatabase()

    //[Start]: showUserNameInActionBar()
    public void showUserNameInActionBar(boolean show) {

        if(show) {
            usersReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    for(DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                        User user = userSnapshot.getValue(User.class);
                        Log.i(TAGAUTHDATABASEACTIVITY, "showUserNameInActionBar(): Usuario recebido: " + user.toString());

                        if((user.getEmail()).equals(Objects.requireNonNull(mAuth.getCurrentUser()).getEmail())) {
                            setCurrentUserOfDatabase(user);
                            getSupportActionBar().setTitle(user.isAdmin() ? "Admin " + user.getName(): user.getName());

                            Log.i(TAGAUTHDATABASEACTIVITY, "\n\nshowUserNameInActionBar(): Usuario ativo:" +
                                    getCurrentUserOfDatabase().toString());
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        } else {
            Log.w(TAGAUTHDATABASEACTIVITY, "O nome de usuario não está sendo exibido na actionbar");
        }
    }
//[End]: showUserNameInActionBar()

}
