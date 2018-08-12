package br.com.thiagosousa.firebaseexamples.objects;

import android.support.annotation.NonNull;

public class User extends Person {

    private String username;
    private String password;
    private String email;

    //    [Start]: User()
    public User() {
    }
//    [End]: User()

    //    [Start]: User()
    public User(String username, @NonNull String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
//    [End]: User()

    //    [Start]: User()
    public User(String name, String lastname, String cpf, String username, String password) {
        super(name, lastname, cpf);
        this.username = username;
        this.password = password;
    }
//    [End]: User()

//   [Start]: User()
    public User(String name, String lastname, String cpf, String username, String password, String phoneNumber) {
        super(name, lastname, cpf, phoneNumber);
        this.username = username;
        this.password = password;
    }
//    [End]: User()

    //    [Start]: Getters() and Setters()
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
//    [End]: Getters() and Setters()
}
