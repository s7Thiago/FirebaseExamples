package br.com.thiagosousa.firebaseexamples.activitys;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import br.com.thiagosousa.firebaseexamples.R;

public class BottomSheetActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String BOTTOMSHEETACTIVITYTAG = "BottomSheet Event";

    private LinearLayout bottomSheetLayout;
    private ConstraintLayout bottomSheetConstraintLayout;
    private TextView bottomSheetActivityTextView;
    private Button bottomSheetButton;
    private BottomSheetBehavior bottomSheetBehavior;
    private FloatingActionButton bottomSheetFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet);
        initViews(true);
        configureScreen(true);
    }

    private void initViews(boolean init) {
        if (init) {
            bottomSheetLayout = findViewById(R.id.bottom_sheet);
            bottomSheetConstraintLayout = findViewById(R.id.bottomsheet_content_constraintlayout);
            bottomSheetActivityTextView = findViewById(R.id.bottomsheetactivity_textview);
            bottomSheetButton = findViewById(R.id.bottomsheetactivityy_button);
            bottomSheetFab = findViewById(R.id.bottom_sheet_activity_fab);
        } else {

            Log.w(BOTTOMSHEETACTIVITYTAG, "initViews() is desactivated");

        }
    }

    private void configureScreen(boolean configure) {
        if (configure) {

//            Configuando eventos de clique
            bottomSheetButton.setOnClickListener(this);

            bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetLayout);

//            configurando os eventos para o bottomSheet
            bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                @Override
                public void onStateChanged(@NonNull View bottomSheet, int newState) {

//                    Reagindo aos estados do bottomsheet:
                    if (newState == BottomSheetBehavior.STATE_EXPANDED) {

                        Toast.makeText(getBaseContext(), "Bottom Sheet has expanded", Toast.LENGTH_SHORT).show();

                    } else if (newState == BottomSheetBehavior.STATE_COLLAPSED) {

                        Toast.makeText(getBaseContext(), "Bottom Sheet has collapsed", Toast.LENGTH_SHORT).show();

                    } else if (newState == BottomSheetBehavior.STATE_DRAGGING) {

                        bottomSheetConstraintLayout.setBackgroundResource(android.R.color.holo_green_light);
                        bottomSheetActivityTextView.setText("Bottom Sheet has Dragged");

                    } else if (newState == BottomSheetBehavior.STATE_SETTLING) {

                        bottomSheetConstraintLayout.setBackgroundResource(android.R.color.white);
                        bottomSheetActivityTextView.setText("Bottom Sheet has SETTLING");
                    }

/*//                    Faz com que o fab diminua o tamanho a imediatamente, assim que o bottom sheet é deslizado
                    // this part hides the button immediately and waits bottom sheet
                    // to collapse to show
                    if (BottomSheetBehavior.STATE_DRAGGING == newState) {
                        bottomSheetFab.animate().scaleX(0).scaleY(0).setDuration(300).start();
                    } else if (BottomSheetBehavior.STATE_COLLAPSED == newState) {
                        bottomSheetFab.animate().scaleX(1).scaleY(1).setDuration(300).start();
                    }*/
                }

                @Override
                public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                    //Aplicando escala no fab durante o deslizamento do bottom sheet

//                    Faz com que o fab diminua o tamanho a medida que o bottom sheet é deslizado
                    bottomSheetFab.animate()
                            .scaleX(1 - slideOffset)
                            .scaleY(1 - slideOffset)
                            .setDuration(0)
                            .start();
                }
            });

        } else {
            Log.w(BOTTOMSHEETACTIVITYTAG, "configureScreen() is desactivated");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.bottomsheetactivityy_button:
                if((bottomSheetBehavior.getState()) == (BottomSheetBehavior.STATE_HIDDEN)) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
                break;
            default:
                Toast.makeText(getBaseContext(),getString(R.string.no_action_attirbuted), Toast.LENGTH_SHORT).show();

        }

    }
}
