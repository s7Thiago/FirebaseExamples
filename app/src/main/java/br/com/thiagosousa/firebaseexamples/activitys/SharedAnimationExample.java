package br.com.thiagosousa.firebaseexamples.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import br.com.thiagosousa.firebaseexamples.R;
import br.com.thiagosousa.firebaseexamples.useful.UtilActivity;

public class SharedAnimationExample extends UtilActivity implements AdapterView.OnItemClickListener {
    private static final String TAG = "SharedAnimationExample";

    //
    private Class classList[] = new Class[]{CustomListActivity.class, CustomListViewViewHolderActivity.class};
    private String itemTitles[] = new String[]{"Example 1", "Example 2"};

    //    Views
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_animation_example);
        initViews(true);
        configureScreen(true);
    }

    @Override
    public void configureScreen(boolean configure) {
        if (configure) {

//            listView
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itemTitles);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(this);

        } else {
            Log.w(TAG, "configureScreen: the configureScreen() is desactivated!");
        }
    }

    @Override
    public void initViews(boolean init) {
        if (init) {

            listView = findViewById(R.id.sharedanimationactivity_listview);

        } else {
            Log.w(TAG, "initViews: the initViews() is desactivated!");
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        switch (position) {
            case 0:
                openScreen(classList[0]);
                break;

            case 1:
                openScreen(classList[1]);
                break;

            default:
                break;

        }

    }
}
