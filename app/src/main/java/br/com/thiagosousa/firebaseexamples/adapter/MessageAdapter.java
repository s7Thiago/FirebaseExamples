package br.com.thiagosousa.firebaseexamples.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.thiagosousa.firebaseexamples.R;
import br.com.thiagosousa.firebaseexamples.objects.Message;

public class MessageAdapter extends BaseAdapter{

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

        View linha = LayoutInflater.from(context).inflate(R.layout.item_message, parent,false);
        TextView userEmailTextView = linha.findViewById(R.id.item_activity_database_textview_user_email);
        TextView userMessageTextView = linha.findViewById(R.id.item_activity_database_textview_user_message);
        TextView userMessageTimeTextView = linha.findViewById(R.id.item_activity_database_textview_user_message_time);

//        Inserindo as informacoes no item
        userEmailTextView.setText(message.getResponsavel());
        userMessageTextView.setText(message.getMessageContent());
        userMessageTimeTextView.setText(message.getHora());

        return linha;
    }
}
