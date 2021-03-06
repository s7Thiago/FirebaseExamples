package br.com.thiagosousa.firebaseexamples.activitys;

import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import br.com.thiagosousa.firebaseexamples.R;

public class ConstraintAnimationExample1Activity extends AppCompatActivity {

    private static final String TAG = "ConstraintAnimationExam";
    private static final long TRANSITION_DURATION = 300;

    Button animationButton;
    boolean switched = false;
    ConstraintLayout constraint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint_animation_example1);

        animationButton = findViewById(R.id.bt);
        constraint = findViewById(R.id.constraintAnimationRootLayout);

        animationButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Log.d(TAG, "onClick: Botao de animacao constraint acionado");
                Log.d(TAG, "onClick: switched: " + switched);
                switchConstraints();
            }
        });
    }

    private void switchConstraints() {

        if (switched) {
            switched = false;

            Transition transition = new ChangeBounds();
            transition.setInterpolator(new OvershootInterpolator());
            transition.setDuration(TRANSITION_DURATION);

            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(this, R.layout.activity_constraint_animation_example1);
            TransitionManager.beginDelayedTransition(constraint, transition);
            constraintSet.applyTo(constraint);
        } else {
            switched = true;

            Transition transition = new ChangeBounds();
            transition.setInterpolator(new OvershootInterpolator());
            transition.setDuration(TRANSITION_DURATION);

            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(this, R.layout.activity_constraint_animation_example1_frame2);
            TransitionManager.beginDelayedTransition(constraint, transition);
            constraintSet.applyTo(constraint);
        }

    }
}
