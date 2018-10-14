package br.com.thiagosousa.firebaseexamples.activitys;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.widget.LinearLayout;

import br.com.thiagosousa.firebaseexamples.R;
import br.com.thiagosousa.firebaseexamples.useful.AuthActivity;

public class GradientBackgroundAnimActivity extends AuthActivity {

    private static final String GRADIENTBACKGROUNDANIMACTIVITYTAG = "Activity_anim event";

    LinearLayout layout;
    ConstraintLayout constraintLayout;
    AnimationDrawable animationDrawable;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gradient_background_anim);
        initViews(true);
        animBackbround(true);
    }

    public void initViews(boolean init) {
        if (init) {
            layout = findViewById(R.id.activity_gradient_background_anim_linearlayout);
            constraintLayout = findViewById(R.id.constraint_background_anim_background);
        } else {

        }
    }

    //    animBackbround(boolean anim) <- se init = true, o fundo desta tela é animado com um efeito gradiente
    public void animBackbround(boolean anim) {

        if (anim) {

            Log.w(GRADIENTBACKGROUNDANIMACTIVITYTAG, "Animação gradiente ativa");

            animationDrawable = (AnimationDrawable) layout.getBackground();
            animationDrawable.setEnterFadeDuration(3000);
            animationDrawable.setExitFadeDuration(3000);
            animationDrawable.start();
        } else {
            Log.w(GRADIENTBACKGROUNDANIMACTIVITYTAG, "Background anim desactivated");
        }

    }

    //    [Start]: onUserDataChangedInDatabase()
    @Override
    public void onUserDataChangedInDatabase() {
    }
//    [End]: onUserDataChangedInDatabase()
}
