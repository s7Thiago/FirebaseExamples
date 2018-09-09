package br.com.thiagosousa.firebaseexamples.activitys;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
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
                    if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {

                        Toast.makeText(getBaseContext(), "Bottom Sheet has expanded", Toast.LENGTH_SHORT).show();

                    } else if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {

                        Toast.makeText(getBaseContext(), "Bottom Sheet has collapsed", Toast.LENGTH_SHORT).show();

                    } else if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_DRAGGING) {

                        bottomSheetConstraintLayout.setBackgroundResource(android.R.color.holo_green_light);
                        bottomSheetActivityTextView.setText("Bottom Sheet has Dragged");

                    } else if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_SETTLING) {

                        bottomSheetConstraintLayout.setBackgroundResource(android.R.color.white);
                        bottomSheetActivityTextView.setText("Bottom Sheet has SETTLING");
                    }
                }

                @Override
                public void onSlide(@NonNull View bottomSheet, float slideOffset) {

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
