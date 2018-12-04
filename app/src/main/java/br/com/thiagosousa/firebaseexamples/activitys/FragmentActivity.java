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
        import br.com.thiagosousa.firebaseexamples.fragments.RecyclerViewActivityFragment;
        import br.com.thiagosousa.firebaseexamples.fragments.ResiduosListFragment;
        import br.com.thiagosousa.firebaseexamples.fragments.SimpleListFragment;
        import br.com.thiagosousa.firebaseexamples.firebase.AuthActivity;

public class FragmentActivity extends AuthActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private static final String TAG = "FragmentActivity";
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
            "Residuos List Fragment",
            "Opção 8",
            "Opção 9",
            "Fragments Activity",
            "Opção 11",
            "Opção 12",
            "Simple list fragment",
            "Opção 14",
            "Opção 15",
            "Opção 16",
            "RecyclerView Fragment",
            "Opção 18",
            "Opção 19",
            "Opção 20",
            "Opção 21",
            "Fechar esta tela"};

    //[Start]: onCreate()
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
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
            Log.w(TAG, "O método initViews() está desativado");
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

            case "Residuos List Fragment":
                ResiduosListFragment fragment = new ResiduosListFragment();

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragments_container_spinnerActivity, fragment);
                ft.addToBackStack(null);
                ft.commit();
                break;

            case "RecyclerView Fragment":
                RecyclerViewActivityFragment recyclerViewFragment = new RecyclerViewActivityFragment();

                FragmentTransaction ft2 = getFragmentManager().beginTransaction();
                ft2.replace(R.id.fragments_container_spinnerActivity, recyclerViewFragment);
                ft2.addToBackStack(null);
                ft2.commit();
                break;

            case "Fragments Activity":
                startActivity(new Intent(getApplicationContext(), FragmentsActivity.class));
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
                Log.i(TAG, "remove fragment in FragmentActivity is clicked");

                Fragment fragment = fragmentManager.findFragmentById(R.id.fragments_container_spinnerActivity);
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.remove(fragment);
                fragmentTransaction.addToBackStack(null);
                ft.commit();
                break;

            default:
                showToastShort("Ainda não há uma ação definida para este componente");
                Log.w(TAG, "default view in FragmentActivity is clicked");
                break;
        }
    }
    //[End]: OnClick()

    //    [Start]:onUserDataChangedInDatabase()
    @Override
    public void onUserDataChangedInDatabase() {
    }
//    [End]:onUserDataChangedInDatabase()
}