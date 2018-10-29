package br.com.thiagosousa.firebaseexamples.objects;

import android.os.Parcelable;
import androidx.annotation.NonNull;

import java.io.Serializable;

public class User extends Person implements Parcelable, Serializable {

    private String username;
    private String password;
    private String email;
    private boolean admin;

    //    [Start]: User()
    public User() {
    }
//    [End]: User()

    //    [Start]: User()
    public User(String name, String lastname, String email, String password, boolean admin) {
        this.name = name;
        this.lastName = lastname;
        this.email = email;
        this.password = password;
        this.admin = admin;
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

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
//    [End]: Getters() and Setters()


    @Override
    public String toString() {
        return String.valueOf(new StringBuilder()
                .append("Nome: " + this.getName())
                .append("\nSobrenome: " + this.getLastName())
                .append("\nEmail: " + this.getEmail())
                .append("\nSenha: " + this.getPassword()));
    }
}
