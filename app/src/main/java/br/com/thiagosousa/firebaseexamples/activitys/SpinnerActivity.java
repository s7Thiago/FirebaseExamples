package br.com.thiagosousa.firebaseexamples.activitys;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import br.com.thiagosousa.firebaseexamples.R;
import br.com.thiagosousa.firebaseexamples.fragments.SimpleListFragment;
import br.com.thiagosousa.firebaseexamples.useful.AuthActivity;

public class SpinnerActivity extends AuthActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    final static String SPINNERACTIVITYTAG = "SpinnerActivity event";
    private FragmentManager fragmentManager = getFragmentManager();
    private FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    private Spinner mSpinner;
    private Button actionButton1, actionButton2;

    private String[] spinnerItens = {
            "Opção 1",
            "Abrir database activity",
            "Opção 3",
            "Opção 4",
            "Opção 5",
            "Opção 7",
            "Opção 8",
            "Opção 9",
            "Opção 10",
            "Opção 11",
            "Opção 12",
            "Simple list fragment",
            "Opção 14",
            "Opção 15",
            "Opção 16",
            "Opção 17",
            "Opção 18",
            "Opção 19",
            "Opção 20",
            "Opção 21",
            "Fechar esta tela"};

    //[Start]: onCreate()
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);
        initViews(true);

//        definindo o gerenciador de cliques como sendo o listener padrão da classe
        mSpinner.setOnItemSelectedListener(this);
        actionButton1.setOnClickListener(this);
        actionButton2.setOnClickListener(this);

        //Configurando o adapter para o spinner
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_list_item_1, spinnerItens);
        mSpinner.setAdapter(spinnerAdapter);
        mSpinner.clearFocus();
    }
    //[End]: onCreate()

    //[Start]: initViews()
    public void initViews(boolean init) {
        if (init) {
            mSpinner = findViewById(R.id.spinner_SpinnerActivity);
            actionButton1 = findViewById(R.id.spinnerActivity_ActionButton1);
            actionButton2 = findViewById(R.id.spinnerActivity_ActionButton2);
        } else {
            Log.w(SPINNERACTIVITYTAG, "O método initViews() está desativado");
        }
    }
    //[End]: initViews()

    //[Start]: onItemSelected()
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (spinnerItens[position]) {

            case "Abrir database activity":
                startActivity(new Intent(getBaseContext(), FirebaseDatabaseActivity.class));
                break;

            case "Fechar esta tela":
                finish();
                break;

            case "Simple list fragment":

                fragmentTransaction.replace(R.id.fragments_container_spinnerActivity, new SimpleListFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

                break;

            default:
                showToastShort("Este item não tem nenhuma ação definida");
                break;
        }
        //[End]: onItemSelected()
    }

    //[Start]: onNothingSelected()
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    //[End]: onNothingSelected()

    //[Start]: OnClick()
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.spinnerActivity_ActionButton2:
                Log.i(SPINNERACTIVITYTAG, "remove fragment in SpinnerActivity is clicked");

                Fragment fragment = fragmentManager.findFragmentById(R.id.fragments_container_spinnerActivity);
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.remove(fragment);
                fragmentTransaction.addToBackStack(null);
                ft.commit();
                break;

            default:
                showToastShort("Ainda não há uma ação definida para este componente");
                Log.w(SPINNERACTIVITYTAG, "default view in SpinnerActivity is clicked");
                break;
        }
    }
    //[End]: OnClick()
}