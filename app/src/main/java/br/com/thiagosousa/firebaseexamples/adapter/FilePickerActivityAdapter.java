package br.com.thiagosousa.firebaseexamples.adapter;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import br.com.thiagosousa.firebaseexamples.R;
import br.com.thiagosousa.firebaseexamples.activitys.filepicker.FilePickerHolder;
import br.com.thiagosousa.firebaseexamples.activitys.filepicker.PhotoPickerActivity;

//    Adapter for recyclerview
public class FilePickerActivityAdapter extends RecyclerView.Adapter<FilePickerHolder> {

    private List<String> itensCollection;
    private Context context;

    public FilePickerActivityAdapter(Context context, List<String> itensCollection) {
        this.itensCollection = itensCollection;
        this.context = context;
    }

    @NonNull
    @Override
    public FilePickerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new FilePickerHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_filepickerscreen, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final FilePickerHolder holder, final int position) {
        holder.itemText.setText(itensCollection.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                switch (position){
                    case 0:
                        context.startActivity(new Intent(holder.itemView.getContext(), PhotoPickerActivity.class));
                        break;

                    default:
                        Toast.makeText(holder.itemView.getContext(), "Ação não definda", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return itensCollection != null ? itensCollection.size() : 0;
    }
}