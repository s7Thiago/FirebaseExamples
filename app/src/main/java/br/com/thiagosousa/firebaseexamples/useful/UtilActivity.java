package br.com.thiagosousa.firebaseexamples.useful;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.Objects;

import br.com.thiagosousa.firebaseexamples.R;

/**
 * Created by Thiago on 13/02/2018.
 */

@SuppressLint("Registered")
public class UtilActivity extends AppCompatActivity {

    private static final String UTILACTIVITYTAG = "UtilActivity Event";

    private ProgressBar mProgressBar;
    private ProgressDialog progressDialog;
    boolean visible; /*used by animateViewVisibility method*/

    //    [Start]: hiddenKeyboard()
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        Objects.requireNonNull(inputMethodManager).hideSoftInputFromWindow(view.getWindowToken(), 0);
        Log.i(UTILACTIVITYTAG, "hideKeyboard is called");
    }
//    [End]: hiddenKeyboard()

    //    [Start]: isShowingKeyboard()
    public boolean isShowingKeyboard() {
        boolean showed;
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);

        if (Objects.requireNonNull(inputMethodManager).isAcceptingText()) {
            showed = true;
            showToastShort("Keyboard is showing");
            Log.i(UTILACTIVITYTAG, "isShowingKeyboard: Keyboard is showing");
        } else {
            showed = false;
            showToastShort("Keyboard is hidden");
            Log.i(UTILACTIVITYTAG, "isShowingKeyboard: Keyboard is hidden");
        }

        return showed;
    }
//    [End]: isShowingKeyboard()

    //    [Start]: showProgressBar()
    public void showProgressBar(boolean display) {
        if (!display) {

            Log.w(UTILACTIVITYTAG, "Progressbar is invisible");
            mProgressBar.setVisibility(View.GONE);

        } else {

            Log.w(UTILACTIVITYTAG, "Progressbar is visible");
        }
    }
//    [End]: showProgressBar()

    //    [Start]: showProgressDialog()
    public void showProgressDialog(boolean display) {
        if (display) {
            if (progressDialog == null) {
                progressDialog = new ProgressDialog(this);
                progressDialog.setMessage(getString(R.string.loading));
                progressDialog.setIndeterminate(true);
            }
            progressDialog.show();
        } else {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }
    }
//    [End]: showProgressDialog()

    //    [Start]: showToastShort()
    public void showToastShort(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
    //    [Start]: showToastShort()

    //    [Start]: showSnackBarLong()
    public void showSnackBarLong(String message, View container) {
        Snackbar.make(container, message, Snackbar.LENGTH_LONG).show();
    }    //    [End]: showSnackBarLong()

    //    [Start]: animateViewVisibility()
    public void animateViewVisibility(ViewGroup transitionContainer, View targetTransition) {

        TransitionManager.beginDelayedTransition(transitionContainer);
        visible = !visible;
        targetTransition.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
    }
    //    [End]: animateViewVisibility()

    //    [Start]: openScreen()
    public void openScreen(Class activity) {
        showToastShort("Opening " + activity.getName());
        startActivity(new Intent(getBaseContext(), activity));
    }
//    [End]: openScreen()

    //    [Start]: isEmptyField()
    public boolean isEmptyField(TextInputEditText field) {
        Log.i(UTILACTIVITYTAG, "isEmpty() is called");
        boolean isEmpty;

        if (field.getText().toString().equals("")) {
            //            If the refered filed is empty
            isEmpty = true;

            Log.w(UTILACTIVITYTAG, "isEmpty() returned true");

        } else {
            isEmpty = false;
            Log.w(UTILACTIVITYTAG, "isEmpty() returned false");
            Log.w(UTILACTIVITYTAG, "field content: " + field.getText());
        }

        return isEmpty;
    }
    //    [End]: isEmptyField()

//    [Start]: isEqualFields()
public boolean isEqualFields(TextInputEditText fieldA, TextInputEditText fieldB) {

    boolean isEqual = fieldB.getText().toString().equals(fieldA.getText().toString());

    Log.d(UTILACTIVITYTAG, "isEqualField() is called : " + isEqual + "\nFields contents: \n"
            + "FieldA: " + fieldA.getText().toString() + "\n"
            + "FieldB: " + fieldB.getText().toString());


    return isEqual;
}
//    [End]: isEqualFields()

}
