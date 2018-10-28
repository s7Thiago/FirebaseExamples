package br.com.thiagosousa.firebaseexamples.useful.asynktasks;

import android.os.AsyncTask;

import br.com.thiagosousa.firebaseexamples.objects.User;

public class UserDataDownloader extends AsyncTask<String, Void, User> {

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected User doInBackground(String... strings) {

        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(User s) {
        super.onPostExecute(s);
    }
}
