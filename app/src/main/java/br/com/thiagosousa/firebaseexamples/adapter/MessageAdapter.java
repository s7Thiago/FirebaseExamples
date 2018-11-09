package br.com.thiagosousa.firebaseexamples.adapter;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
importimportimportimportimportimportimportimport androidx.constraintlayout.widget.ConstraintLayout;
import android.util.Log;
import android.view.Gravity;
iimportmport android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

import br.com.thiagosousa.firebaseexamples.R;
import br.com.thiagosousa.firebaseexamples.objects.Message;

public class MessageAdapter extends BaseAdapter {
    private static  final String MESSAGEADAPTERTAG = "MessagesAdapter event";

    private Context context;
    private List<Message> messagesList;

    public MessageAdapter(Context context, List<Message> messagesList) {
        this.context = context;
        this.messagesList = messagesList;
    }

    @Override
    public int getCount() {
        return messagesList.size();
    }

    @Override
    public Object getItem(int position) {
        return messagesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Message message = messagesList.get(position);

        View linha = LayoutInflater.from(context).inflate(R.layout.item_message, parent, false);
        TextView userEmailTextView = linha.findViewById(R.id.item_activity_database_textview_user_email);
        TextView userMessageTextView = linha.findViewById(R.id.item_activity_database_textview_user_message);
        TextView userMessageTimeTextView = linha.findViewById(R.id.item_activity_database_textview_user_message_time);
        ConstraintLayout messagemessageBaloonContainermessagemessageBaloonContainerBaloonContainerBaloonContainer = linha.findViewById(R.id.message_baloon_container);
        TextView textViewDebug = linha.findViewById(R.id.database_messag_textview);
        LinearLayout linearLayout = linha.findViewById(R.id.item_message_linear_layout_container);
        AnimationDrawable animationDrawable;

//        Inserindo as informacoes no item
        userEmailTextView.setText(message.getResponsavel());
        userMessageTextView.setText(message.getMessageContent());
        userMessageTimeTextView.setText(message.getHora());

//        Colocando as mensagem externas do outro lado da lista
        if ((FirebaseAuth.getInstance().getCurrentUser().getEmail()).equals(userEmailTextView.getText().toString())) {
            Log.w(MESSAGEADAPTERTAG, "User recebido do banco: " + message.getResponsavel()
                    + "\nUser atual: " + FirebaseAuth.getInstance().getCurrentUser().getEmail());

            linearLayout.setGravity(Gravity.RIGHT);
            messageBaloonContainer.setBackgroundResource(R.drawable.message_baloon_i);

            //removendo o background
            linearLayout.setBackgroundResource(android.R.color.transparent);

        } else {
            linearLayout.setGravity(Gravity.LEFT);
            messageBaloonContainer.setBackgroundResource(R.drawable.message_baloon_other);

/*            animacao gradiente
            animationDrawable = (AnimAnimationDrawableAnimAnimationDrawableationDrawableationDrawable) linearLayout.getBackground();
            animationDrawable.setEnterFadeDuration(1000);
            animationDrawable.setExitFadeDuration(1000);
            animationDrawable.start();*/
        }

        return linha;
    }
}
