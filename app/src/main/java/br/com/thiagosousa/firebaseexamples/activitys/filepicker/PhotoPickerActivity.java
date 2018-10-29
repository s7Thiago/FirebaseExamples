package br.com.thiagosousa.firebaseexamples.activitys.filepicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.jaiselrahman.filepicker.activity.FilePickerActivity;
import com.jaiselrahman.filepicker.config.Configurations;
import com.jaiselrahman.filepicker.model.MediaFile;

import java.util.ArrayList;

import androidx.appcompat.widget.AppCompatImageView;
import br.com.thiagosousa.firebaseexamples.R;
import br.com.thiagosousa.firebaseexamples.useful.UtilActivity;

public class PhotoPickerActivity extends UtilActivity {
    private static final String TAG = "PhotoPickerActivity";
    private final static int FILE_REQUEST_CODE = 1;

    private MaterialButton button;
    private AppCompatImageView imageContainer;

    //    Lista que armazena os arquivos
    ArrayList<MediaFile> mediaFiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_picker);
        initViews(true);
        configureScreen(true);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), FilePickerActivity.class);
                intent.putExtra(FilePickerActivity.CONFIGS, new Configurations.Builder()
                        .setCheckPermission(true)
                        .setSelectedMediaFiles(mediaFiles)
                        .enableImageCapture(true)
                        .enableVideoCapture(false)
                        .setShowVideos(false)
                        .setSkipZeroSizeFiles(false)
                        .setMaxSelection(1).build());

                startActivityForResult(intent, FILE_REQUEST_CODE);
            }
        });
    }

    @Override
    public void initViews(boolean init) {

        if (init) {
            button = findViewById(R.id.imagepickerActivity_openButton);
            imageContainer = findViewById(R.id.imagepickerActivity_containerImageView);
        } else {
            Log.w(TAG, "initViews: as views não estão sendo inicializadas");
        }

    }

    @Override
    public void configureScreen(boolean configure) {

        if (configure) {
            mediaFiles = new ArrayList<>();
        } else {
            Log.w(TAG, "configureScreen: esta desabilitado");
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FILE_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            mediaFiles.clear();
            mediaFiles.addAll(data.<MediaFile>getParcelableArrayListExtra(FilePickerActivity.MEDIA_FILES));

            if (mediaFiles.size() != 0) {
// Verificando se o arquivo selecionado e uma imagem, e fazendo o necessario
                if (mediaFiles.get(0).getMediaType() == MediaFile.TYPE_IMAGE) {
                    Glide.with(getBaseContext())
                            .load(mediaFiles.get(0).getPath())
                            .into(imageContainer);
                } else {
                    Log.w(TAG, "onClick: Nenhuma imagem recebida, ou outro erro.");
                }
            }
        }
    }
}
