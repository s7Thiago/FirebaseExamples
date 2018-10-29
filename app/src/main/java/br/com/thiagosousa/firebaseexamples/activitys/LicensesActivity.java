package br.com.thiagosousa.firebaseexamples.activitys;

import androidx.appcompat.app.AppCompatActivity;
import br.com.thiagosousa.firebaseexamples.R;
import br.com.thiagosousa.firebaseexamples.useful.UtilActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class LicensesActivity extends UtilActivity {
    private static final String TAG = "LicensesActivity";

    private ListView licensesListView;
    private String licensesReferences[] = new String[]{"Glide", "File Picker 1", "File Picker 2"};
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_licenses);
        initViews(true);
        configureScreen(true);

        licensesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        String glideLicense = getLicenseFromAssets("licenses/glide.txt");

                        Log.w(TAG, "onItemClick: license content:\n\n" + glideLicense + "\n\n");

                        Intent glideIntent = new Intent(getBaseContext(), LicenseViewActivity.class);
                        glideIntent.putExtra("license_name", "Glide - License");
                        glideIntent.putExtra("license_content", glideLicense);
                        startActivity(glideIntent);

                        break;

                    case 1:
                        String filePickerOneLicense = getLicenseFromAssets("licenses/file_picker1.txt");

                        Intent filePickerIntent = new Intent(getBaseContext(), LicenseViewActivity.class);
                        filePickerIntent.putExtra("license_name", "File Picker 1 - License");
                        filePickerIntent.putExtra("license_content", filePickerOneLicense);
                        startActivity(filePickerIntent);

                        break;

                    case 2:
                        String filePickerTwoLicense = getLicenseFromAssets("licenses/file_picker2.txt");

                        Intent filePickerTwoIntent = new Intent(getBaseContext(), LicenseViewActivity.class);
                        filePickerTwoIntent.putExtra("license_name", "File Picker 2 - License");
                        filePickerTwoIntent.putExtra("license_content", filePickerTwoLicense);
                        startActivity(filePickerTwoIntent);

                        break;

                    default:
                        showToastShort(getString(R.string.no_action_attirbuted));
                        break;
                }
            }
        });


    }

    @Override
    public void initViews(boolean init) {
        if (init) {

            licensesListView = findViewById(R.id.licensesListView);

        }else {
            Log.w(TAG, "initViews: esta desativado");
        }
    }

    @Override
    public void configureScreen(boolean configure) {
        if (configure) {

//            Configurando a lista
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, licensesReferences);
            licensesListView.setAdapter(adapter);

        }else {
            Log.w(TAG, "configureScreen: esta desativado");
        }
    }

//    Acessa o diretorio assets/licenses e le o arquivo de nome especificado no parametro
    public String getLicenseFromAssets(String filename) {

        StringBuilder license = new StringBuilder();
        InputStream inputStream;
        int data;

        try {
            inputStream = new BufferedInputStream(getAssets().open(filename));
            data = inputStream.read();

            Log.i(TAG, "getLicenseFromAssets: arquivo que sera lido: " + filename);

            while (data != -1) {
                license.append((char) data);

                data = inputStream.read();

                Log.d(TAG, "getLicenseFromAssets: conteudo lido: " + license.toString());
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return license.toString();
    }
}
