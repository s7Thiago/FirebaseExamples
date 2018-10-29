package br.com.thiagosousa.firebaseexamples;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.thiagosousa.firebaseexamples.objects.ItemForActivityWithListViewHolder;

public class DetailViewHolderActivity extends AppCompatActivity {

    private static final String DETAILVIEWHOLDERACTIVITYTAG= "DtailViewHolderActivity";
    public static final String EXTRA_ITEM_OBJECT = "ITEM_OBJECT";

    private ImageView image;
    private TextView title;
    private TextView time;
    private TextView addressDescriptio;
    private ItemForActivityWithListViewHolder item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view_holder);
        initViews(true);
        configureScreen(true);
    }

    private void configureScreen(boolean configure) {
        if (configure) {

            //Coloca os dados recebidos via intent na tela
            preencherTela();

        } else {
            Log.w(DETAILVIEWHOLDERACTIVITYTAG, "O método configureScreen() está desativado");
        }
    }

    private void initViews(boolean init) {
        if (init) {

            image = findViewById(R.id.detail_viewholderactivity_image);
            title = findViewById(R.id.detail_viewholderactivity_text_title);
            time = findViewById(R.id.detail_viewholderactivity_text_time);
            addressDescriptio = findViewById(R.id.detail_viewholderactivity_text_address_description);


        } else {
            Log.w(DETAILVIEWHOLDERACTIVITYTAG, "O método initViews() está desativado");
        }
    }

    public void preencherTela() {
        item = getIntent().getParcelableExtra(EXTRA_ITEM_OBJECT);

        title.setText(item.getTitle());
        time.setText(item.getTime());
        addressDescriptio.setText(getString(R.string.lorem_ipsum_description));

    }
}
