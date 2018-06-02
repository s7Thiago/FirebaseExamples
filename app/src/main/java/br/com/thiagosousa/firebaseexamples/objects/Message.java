package br.com.thiagosousa.firebaseexamples.objects;

public class Message {

    private String messageContent;

    //NEcessário para uso no Firebase Database
    public Message() {
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
