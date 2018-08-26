package br.com.thiagosousa.firebaseexamples.objects;

public class Message {

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

}
