package br.com.thiagosousa.firebaseexamples.activitys;

import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Objects;

import br.com.thiagosousa.firebaseexamples.R;
import br.com.thiagosousa.firebaseexamples.objects.Residuo;

public class ResiduoDetailActivity extends AppCompatActivity {
    private static final String RESIDUODETAILACTIVITYTAG = "ResiduoDetail event";

    private ImageView residuoRepresentation;
    private ImageView resuduoCategory;
    private TextView resuduoIsReciclable;
    private LinearLayout isReciclableBackground;

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

            residuoRepresentation = findViewById(R.id.image_residuo_Detail_representation);
            resuduoCategory = findViewById(R.id.image_residuo_Detail_category);
            resuduoIsReciclable = findViewById(R.id.texto_detail_residuo_reciclavel);
            isReciclableBackground = findViewById(R.id.layout_residuo_reciclable);

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
            Resources res = getApplicationContext().getResources();

            TypedArray residueRepresentationsArray = res.obtainTypedArray(R.array.residuos);
            TypedArray residueCategoryArray = res.obtainTypedArray(R.array.categorias_reciclaveis);

//            recebendo um objeto residuo com implementecao de parcelable
            Residuo residuo = intent.getParcelableExtra("residuo");
            if (residuo != null){
                Objects.requireNonNull(getSupportActionBar()).setTitle(residuo.getNome());

                residuoRepresentation
                        .setImageDrawable(residueRepresentationsArray
                                .getDrawable(residuo.getRepresentacao()));

                resuduoCategory
                        .setImageDrawable(residueCategoryArray
                                .getDrawable(residuo.getCategoria()));

                resuduoIsReciclable
                        .setText(residuo.isReciclavel()? "RECICLÁVEL" : "NÃO RECICLÁVEL");

                //Mudando a cor do background do layout de acordo com o valor de isReciclable
                if(residuo.isReciclavel()) {
                    isReciclableBackground.setBackgroundResource(android.R.color.holo_green_light);
                }else {
                    isReciclableBackground.setBackgroundResource(android.R.color.holo_red_light);
                }

            }else {
                Log.w(RESIDUODETAILACTIVITYTAG, "Nenhum dado recebido para preencher esta tela");

                residuoRepresentation
                        .setImageDrawable(getDrawable(R.drawable.firebase_logo));

                resuduoCategory
                        .setImageDrawable(getDrawable(R.drawable.firebase_logo));

                resuduoIsReciclable
                        .setText("NADA RECEBIDO");
            }

        }else{
            Log.w(RESIDUODETAILACTIVITYTAG, "A atualização dos componentes desta tela está desativada");
        }
    }
//    [End]: updateUI()
}
