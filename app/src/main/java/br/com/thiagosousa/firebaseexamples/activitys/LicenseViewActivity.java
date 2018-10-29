package br.com.thiagosousa.firebaseexamples.activitys;

import androidx.appcompat.app.AppCompatActivity;
import br.com.thiagosousa.firebaseexamples.R;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class LicenseViewActivity extends AppCompatActivity {
    private static final String TAG = "LicenseViewActivity";

    private TextView licenseContentTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_license_view);

//        Capturando os dados da tela anterior
        String licenseName = getIntent().getStringExtra("license_name");
        String licenseContent = getIntent().getStringExtra("license_content");

        //Inserindo os dados da licensa na tela:
        getSupportActionBar().setTitle(licenseName);
        ((TextView) findViewById(R.id.licenseScreen_Text)).setText(licenseContent);
    }
}
