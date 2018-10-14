package br.com.thiagosousa.firebaseexamples.useful.Observer;

public interface Subject {

    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}
