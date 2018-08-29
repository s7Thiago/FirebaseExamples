package br.com.thiagosousa.firebaseexamples.objects;

import android.os.Parcel;
import android.os.Parcelable;

public class Message implements Parcelable{

    private String messageContent;
    private String hora;
    private String responsavel;

    //Necessário para o Firebase Database
    public Message() {
    }

    public Message(String messageContent, String hora, String responsavel) {
        this.messageContent = messageContent;
        this.hora = hora;
        this.responsavel = responsavel;
    }

    protected Message(Parcel in) {
        messageContent = in.readString();
        hora = in.readString();
        responsavel = in.readString();
    }

    public static final Creator<Message> CREATOR = new Creator<Message>() {
        @Override
        public Message createFromParcel(Parcel in) {
            return new Message(in);
        }

        @Override
        public Message[] newArray(int size) {
            return new Message[size];
        }
    };

    public String getMessageContent() {

        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(messageContent);
        dest.writeString(hora);
        dest.writeString(responsavel);
    }
}
