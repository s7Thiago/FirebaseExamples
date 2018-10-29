package br.com.thiagosousa.firebaseexamples.useful;

import android.annotation.SuppressLint;
import android.content.Intent;
import androidx.annotation.NonNull;
import com.google.android.material.textfield.TextInputEditText;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import br.com.thiagosousa.firebaseexamples.R;
import br.com.thiagosousa.firebaseexamples.activitys.HomeActivity;
import br.com.thiagosousa.firebaseexamples.objects.Message;
import br.com.thiagosousa.firebaseexamples.objects.User;
import br.com.thiagosousa.firebaseexamples.useful.Observer.Observer;
import br.com.thiagosousa.firebaseexamples.useful.Observer.Subject;

@SuppressLint("Registered")
public abstract class AuthActivity extends UtilActivity implements Subject {
    private static final String TAG = "AuthActivity";

    protected static FirebaseAuth mAuth;
    private List<Observer> observers = new ArrayList<>(); //

    //
    public boolean connected;

    //    Database variables
//    Retrieving the instance of FirebaseDatabase
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    //    reference location for written
    protected DatabaseReference messagesReference = database.getReference("Messages");
    protected DatabaseReference usersReference = database.getReference("Users");
    private String messageID;

    //    [Start]: updateUI()
    public void updateUI(FirebaseUser user) {
        if (user != null) {
//            Se houver alguém conectado
            showToastShort(user.getEmail() + " está logado agora");
            Log.w(TAG, "o updateUI() detectou que " + user.getEmail() + " está logado.");
        } else {
//            Se não houver ninguém conectado
            showToastShort((getString(R.string.no_user_connected_msg)) + " :(");
            Log.i(TAG, "o updateUI() não detectou nenhum usuário.");
        }
    }
//    [End]: updateUI()

    //    Cadastra um  novo usuario
    //    [Start]: newUser()
    public void newUser(final User user) {

        showProgressDialog(true);
        User user1 = user;

        mAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser firebaseUser = mAuth.getCurrentUser();

//                            Inserindo o nome de usuario no perfil do mesmo
                            UserProfileChangeRequest profileUpdate = new UserProfileChangeRequest
                                    .Builder()
                                    .setDisplayName(user.getName())
                                    .build();

                            firebaseUser.updateProfile(profileUpdate)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                        }
                                    });
//                            Inserindo o nome de usuario no perfil do mesmo

//                            Escrevendo os dados obtidos no banco de dados
                            writeUserInDatabase(user);

                            showProgressDialog(false);
                            updateUI(firebaseUser);
                            openScreen(HomeActivity.class, user);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            showToastShort("Authentication failed.");
                            updateUI((TextView) null);
                            showProgressDialog(false);
                        }
                    }
                });
    }
    //    [End]: newUser()

    //    Usado para fazer login utilizando email e senha de um usuario existente
    //[Start]: connectUser()
    public boolean connectUser(final String email, final String password, final Class activity) {
        showProgressDialog(true);

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail: success");
                            final FirebaseUser user = getFirebaseAuthInstance().getCurrentUser();
                            showProgressDialog(false);
                            setConnected(true);

                            //Abrindo tela e enviando dados recebidos
                            Intent intent = new Intent(getBaseContext(), activity);
                            startActivity(intent);
                        } else {
                            setConnected(false);
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            showToastShort("Authentication failed.");
                            //updateUI((TextView) null);
                            showProgressDialog(false);
                        }
                    }
                });

        Log.w(TAG, "connectUser: checando conexao interna: " + (isConnected()? "Sim" : "Nao"));

        return isConnected();
    }
    //[End]: connectUser()

    //    [Start]: signOut()
    public void signOut() {

        if (isAnyoneConnected()) {
            Log.w(TAG, "The user " + (Objects.requireNonNull(mAuth.getCurrentUser()).getEmail() + " is signed out!"));
            mAuth.signOut();
//            updateUI((TextView) null);
        } else {
            Log.w(TAG, "signOut: " + getString(R.string.no_user_connected_msg));
            showToastShort(getString(R.string.no_user_connected_msg));
        }
    }
//    [End]: signOut()

    //    [Start]: isAnyoneConnected()
    public boolean isAnyoneConnected() {

//        Retorna 'true' se houver alguém conectado
        return getFirebaseAuthInstance().getCurrentUser() != null;
    }
//    [End]: isAnyoneConnected()

    //    [Start]: verifyFields()

    /**
     * Used for validate a collection fields, not alow that some are void content
     **/
    protected boolean verifyFields(TextInputEditText[] textInputEditTexts) {
        boolean fieldsOK = false;

        Log.i(TAG, "verifiyng login fields...");

        for (TextInputEditText textInputEditText : textInputEditTexts) {

            if (!isEmptyField(textInputEditText)) {

//                Está tudo correto. Nenhum campo vazio
                Log.i(TAG, "There´s nothing wrong with the fields!");
                fieldsOK = true;
            } else {

//                Foi detectado algum campo vazio. mostrar mensagem de erro
                Log.i(TAG, "There is something wrong with the fields!");
                fieldsOK = false;
                textInputEditText.setError(getString(R.string.empty_field_msg));
            }

        }

        Log.w(TAG, "verifyLoginFields() returned " + fieldsOK + ".");
        return fieldsOK;
    }
//    [End]:verifyFields()

    //    [Start]: verifyFields()

    /**
     * Used for validate a collection fields, not alow that some are void content
     **/
    protected boolean verifyFields(EditText[] textInputEditTexts) {
        boolean fieldsOK = false;

        Log.i(TAG, "verifiyng login fields...");

        for (EditText textInputEditText : textInputEditTexts) {

            if (!isEmptyField(textInputEditText)) {

//                Está tudo correto. Nenhum campo vazio
                Log.i(TAG, "There´s nothing wrong with the fields!");
                fieldsOK = true;
            } else {

//                Foi detectado algum campo vazio. mostrar mensagem de erro
                Log.i(TAG, "There is something wrong with the fields!");
                fieldsOK = false;
                textInputEditText.setError(getString(R.string.empty_field_msg));
            }

        }

        Log.w(TAG, "verifyLoginFields() returned " + fieldsOK + ".");
        return fieldsOK;
    }
//    [End]:verifyFields()

    //    [Start]:getFirebaseAuthInstance()
    public FirebaseAuth getFirebaseAuthInstance() {
        return mAuth = FirebaseAuth.getInstance();
    }
    //    [End]:getFirebaseAuthInstance()

    //    [Start]: Database Methods
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

        Log.w(TAG, "AuthDatabaseActivity updateUI() foi invocado");

        //Decide o que fazer quando é detectada uma mudança na estrutura dos dados
        messagesReference.child(messageID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Recupera o novo dado em um objeto Message e insere num textview
                Message mMessage = dataSnapshot.getValue(Message.class);

                //Recuperando a mensagem
                String message_received = mMessage != null ? mMessage.getMessageContent() : "null: nada recebido";

                messageView.setText(message_received);

                Log.w(TAG, "Mensagem recuperada: \n\n" + message_received);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Failed to read value
                Log.w(TAG, "Falha ao ler valor (AuthDatabaseActivity updateUI()).", databaseError.toException());
            }
        });
    }
//[End]:updateUI()

    //Busca o usuario que esta logando no banco de dados para capturar os dados
    // [Start]:onUserDataChangedInDatabase()
    public abstract void onUserDataChangedInDatabase();
    // [End]:onUserDataChangedInDatabase()

    //[Start]: connected
    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }
    //[Start]: connected

    // [Start]:writeUserInDatabase()
    public void writeUserInDatabase(User user) {

//        Pegando um id para o user que será salvo
        String userID = usersReference.push().getKey();

//        Salvando as informacoes de um user no banco de dados
        usersReference.child(mAuth.getCurrentUser().getUid()).setValue(user);
    }

    // [End]:writeUserInDatabase()
//    [End]: Database Methods
    @Override
    public void initViews(boolean init) {

    }

    @Override
    public void configureScreen(boolean configure) {

    }

    //    Avisa os observadores sobre mudancas nos dados do usuario atual
    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        int observerPosition = observers.indexOf(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
//            observer.update();
        }
    }
}
