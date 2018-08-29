package br.com.thiagosousa.firebaseexamples.objects;

import android.os.Parcel;
import android.os.Parcelable;

public class Person implements Parcelable{
    protected String name;
    protected String lastName;
    protected String cpf;
    protected String phone_number;
    protected String birthday;

    public Person(){

    }

    //    [Start]: Person()
    public Person(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }
    //    [End]: Person()

    //  [Start]: Person()
    public Person(String name, String lastName, String cpf) {
        this.name = name;
        this.lastName = lastName;
        this.cpf = cpf;
        this.phone_number = phone_number;
    }
//    [End]: Person()

    //   [Start]: Person()
    public Person(String name, String lastName, String cpf, String phone_number) {
        this.name = name;
        this.lastName = lastName;
        this.cpf = cpf;
        this.phone_number = phone_number;
    }
//    [End]: Person()

    protected Person(Parcel in) {
        name = in.readString();
        lastName = in.readString();
        cpf = in.readString();
        phone_number = in.readString();
        birthday = in.readString();
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    //[Start]: Getters() and Setters()
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(lastName);
        dest.writeString(cpf);
        dest.writeString(phone_number);
        dest.writeString(birthday);
    }
//[End]: Getters() and Setters()

}
