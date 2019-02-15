package br.com.thiagosousa.firebaseexamples.activitys;

import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;
import br.com.thiagosousa.firebaseexamples.R;
import br.com.thiagosousa.firebaseexamples.useful.UtilActivity;

public class AnimatedVectorDrawable_Ex1Activity extends UtilActivity {
    private static final String TAG = "AVD_EX1Activity";

    private FloatingActionButton fab;
    private static boolean isFABSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animated_vector_drawable__ex1);
        initViews(true);
        configureScreen(true);
    }

    @Override
    public void initViews(boolean init) {
        if (init) {
            fab = findViewById(R.id.animatedVectorDrawableActivity_Ex1Activity_FAB);
        } else {
            Log.w(TAG, "initViews: initViews is desactivated");
        }
    }

    @Override
    public void configureScreen(boolean configure) {
        if (configure) {

        } else {
            Log.w(TAG, "configureScreen: configurescreen is desctivated");
        }

    }

    public void animatePlayButton(View v) {
        Log.d(TAG, "animatePlayButton() called with: v = [" + v + "]");
        Log.w(TAG, "animatePlayButton: isFABSelected [" + isFABSelected + "]");

        FloatingActionButton fab = (FloatingActionButton) v;
        Drawable drawable = ((FloatingActionButton) v).getDrawable();

        Log.i(TAG, "animatePlayButton: selected state changed initial: " + fab.isSelected());

//        if FAB are a AnimatedVectorDrawable
        Log.w(TAG, "animatePlayButton: drawable received is animatable? " + ((drawable instanceof AnimatedVectorDrawable)));
        if (drawable instanceof AnimatedVectorDrawable) {
//            case false, animate from play to pause

            Log.i(TAG, "animatePlayButton: checking if is selected...");
            if (fab.isSelected()) {
                isFABSelected = false;
                Log.i(TAG, "animatePlayButton: is selected = false, animation play to pause");
                AnimatedVectorDrawable avd = (AnimatedVectorDrawable) drawable;
                avd.start();
                fab.setSelected(isFABSelected);
            } else {
//                case true, animate from pause to play
                isFABSelected = true;
                Log.i(TAG, "animatePlayButton: is selected = true, animation pause to play");
                fab.setImageResource(R.drawable.pause_play_anim);
                drawable = ((FloatingActionButton) v).getDrawable();
                fab.setSelected(isFABSelected);

                Log.w(TAG, "animatePlayButton: drawable received is animatable? " + ((drawable instanceof AnimatedVectorDrawable)));
                ((AnimatedVectorDrawableCompat) drawable).start();
            }
//         if FAB are a AnimatedVectorDrawableCompat
        } else if (drawable instanceof AnimatedVectorDrawableCompat) {


//                    case false, animate from play to pause
            if (fab.isSelected()) {
                AnimatedVectorDrawableCompat avdc = (AnimatedVectorDrawableCompat) drawable;
                avdc.start();
                fab.setSelected(false);
            } else {
//                case true, animate from pause to play
                fab.setImageResource(R.drawable.pause_play_anim);
                drawable = ((FloatingActionButton) v).getDrawable();

                AnimatedVectorDrawableCompat avdc = (AnimatedVectorDrawableCompat) drawable;
                avdc.start();
                fab.setSelected(true);
            }
        }
//                    End animate fab

        Snackbar.make(v, "Action called!!", Snackbar.LENGTH_SHORT).show();

    }

    int teste = 0;

    public void testeClickFAB(View v) {
        FloatingActionButton fab = (FloatingActionButton) v;
        Drawable drawable = fab.getDrawable();

        Log.w(TAG, "testeClickFAB: alternator value: [" + teste + "]");

        if (teste == 0) {
            teste = 1;
            //fab.setImageDrawable(getResources().getDrawable(R.drawable.play_pause_anim, this.getTheme()));

            //[start]: animation treatment
            Log.i(TAG, "testeClickFAB: play to pause");
            Log.w(TAG, "testeClickFAB: AnimatedVectorDrawable animation starting");

//                Futuramente a linha abaixo precisa ser corrigida. caso hajam problemas
            AnimatedVectorDrawableCompat avd = AnimatedVectorDrawableCompat.create(this, R.drawable.play_pause_anim);
            fab.setImageDrawable(avd);

            assert avd != null;
                avd.start();
            //[end]: animation treatment
        } else {
            teste = 0;

            //[start]: animation treatment
            Log.i(TAG, "testeClickFAB: pause to play");
            Log.w(TAG, "testeClickFAB: AnimatedVectorDrawable animation starting");

//                Futuramente a linha abaixo precisa ser corrigida. caso hajam problemas
            AnimatedVectorDrawableCompat avd = AnimatedVectorDrawableCompat.create(this, R.drawable.pause_play_anim);
            fab.setImageDrawable(avd);

            assert avd != null;
            avd.start();
            //[end]: animation treatment
        }
    }
}