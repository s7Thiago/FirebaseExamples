package br.com.thiagosousa.firebaseexamples.objects;

public class Message {

    private String messageContent;
    private String hora;

    //Necessário para o Firebase Database
    public Message() {
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Message(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getMessageContent() {

        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }
}
