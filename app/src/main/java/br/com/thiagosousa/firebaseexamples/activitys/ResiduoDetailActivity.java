package br.com.thiagosousa.firebaseexamples.activitys;

import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

import br.com.thiagosousa.firebaseexamples.R;

public class ResiduoDetailActivity extends AppCompatActivity {
    private static final String RESIDUODETAILACTIVITYTAG = "ResiduoDetail event";

    private ImageView resuduoRepresentation;
    private ImageView resuduoCategory;
    private TextView resuduoIsReciclable;

    //    [Start]: onCreate()
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_residuo_detail);
        initViews(true);
        updateUI(true);
    }
//    [End]: onCreate()

    //        [Start]: initViews()
    public void initViews(boolean init) {

        if (init) {

            resuduoRepresentation = findViewById(R.id.image_residuo_Detail_representation);
            resuduoCategory = findViewById(R.id.image_residuo_Detail_category);
            resuduoIsReciclable = findViewById(R.id.texto_detail_residuo_reciclavel);

        } else {
            Log.w(RESIDUODETAILACTIVITYTAG, "The initViews() is desactivated");
        }
    }
//        [End]: initViews()

//    [Start]: updateUI()
    public void updateUI(boolean update){
        if(update) {
            Intent intent = getIntent();

//            Preenchendo as views da tela com os dados recebidos via intent
            Objects.requireNonNull(getSupportActionBar()).setTitle(intent.getStringExtra("nome"));
            Resources res = getApplicationContext().getResources();

            TypedArray residueRepresentationsArray = res.obtainTypedArray(R.array.residuos);
            TypedArray residueCategoryArray = res.obtainTypedArray(R.array.categorias_reciclaveis);

            resuduoRepresentation
                    .setImageDrawable(residueRepresentationsArray.getDrawable(intent.getIntExtra("representacao", 0)));

            resuduoCategory
                    .setImageDrawable(residueCategoryArray.getDrawable(intent.getIntExtra("categoria", 0)));

            resuduoIsReciclable
                    .setText(intent.getBooleanExtra("isReciclavble", false)? "RECICLÁVEL" : "NÃO \nRECICLÁVEL");



        }else {
            Log.w(RESIDUODETAILACTIVITYTAG, "A atualização dos componentes desta tela está desativada");
        }
    }
//    [End]: updateUI()
}
