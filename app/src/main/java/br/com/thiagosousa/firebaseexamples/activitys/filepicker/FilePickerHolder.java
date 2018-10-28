package br.com.thiagosousa.firebaseexamples.activitys.filepicker;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.com.thiagosousa.firebaseexamples.R;

public class FilePickerHolder extends RecyclerView.ViewHolder {

    public TextView itemText;

    public FilePickerHolder(@NonNull View itemView) {
        super(itemView);

        itemText = itemView.findViewById(R.id.itemtext_filepickerRecyclerView);
    }
}
