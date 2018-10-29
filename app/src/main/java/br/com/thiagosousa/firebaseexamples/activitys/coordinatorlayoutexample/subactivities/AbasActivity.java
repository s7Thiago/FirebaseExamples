package br.com.thiagosousa.firebaseexamples.activitys.coordinatorlayoutexample.subactivities;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.View;

import br.com.thiagosousa.firebaseexamples.R;

public class AbasActivity extends AppCompatActivity {
    private static final String ABASACTIVITYTAG = "AbasActivity event";

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abas);
        initViews(true);
        configureScreen(true);
    }

    @SuppressLint("WrongViewCast")
    private void initViews(boolean init){
        if(init){
            mTabLayout = findViewById(R.id.activityPager_tabLayout);
            mViewPager = findViewById(R.id.activityPager_viewpager);
            mToolbar = findViewById(R.id.activityPager_toolbar);

        }else{
            Log.w(ABASACTIVITYTAG, "O metodo initViews() em AbasActivity está desabilitado");
        }
    }

    private void configureScreen(boolean configure){
        if(configure){

//            Configurando a toolbar
            setSupportActionBar(mToolbar);


//            Configurando o viewpager
            PagerAdapter pagerAdapter = new PagerAdapter() {
                @Override
                public int getCount() {
                    return 0;
                }

                @Override
                public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                    return false;
                }
            };

            mViewPager.setAdapter(pagerAdapter);

//            Configurando o viewpager no tablayout
            mTabLayout.setupWithViewPager(mViewPager);

            //            Configurando as abas
            mTabLayout.addTab(mTabLayout.newTab().setText("Aba 1"));
            mTabLayout.addTab(mTabLayout.newTab().setText("Aba 2"));
            mTabLayout.addTab(mTabLayout.newTab().setText("Aba 3"));
            mTabLayout.addTab(mTabLayout.newTab().setText("Aba 4"));

        }else {
            Log.w(ABASACTIVITYTAG, "O metodo configureScreen() em AbasActivity está desabilitado");
        }
    }
}
