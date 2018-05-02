package br.com.thiagosousa.firebaseexamples.objects;

public class Person {
    private String name;
    private String lastName;
    private String cpf;
    private String phone_number;
    private String birthday;

    public Person() {

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
//[End]: Getters() and Setters()

}
