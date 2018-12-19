package br.com.thiagosousa.firebaseexamples.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import br.com.thiagosousa.firebaseexamples.R;
import br.com.thiagosousa.firebaseexamples.activitys.AnimatedVectorDrawable_Ex1Activity;

public class AnimatedVectorDrawableActivity_Adapter extends RecyclerView.Adapter<AnimatedVectorDrawableActivity_ViewHolder> {

    List<String> itens;
    Context context;

    public AnimatedVectorDrawableActivity_Adapter(Context context, List<String> itens) {
        this.itens = itens;
        this.context = context;
    }

    @NonNull
    @Override
    public AnimatedVectorDrawableActivity_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AnimatedVectorDrawableActivity_ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.animatedvectordrawableactivity_mainlistitem, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final AnimatedVectorDrawableActivity_ViewHolder holder, final int position) {

        holder.textViewCompat.setText(itens.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position) {
                    case 0:
                        Intent intentEx1 = new Intent(holder.itemView.getContext(), AnimatedVectorDrawable_Ex1Activity.class);
                        intentEx1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                        context.startActivity(intentEx1);

                        break;

                    default:
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return itens != null ? itens.size() : 0;
    }
}

class AnimatedVectorDrawableActivity_ViewHolder extends RecyclerView.ViewHolder {

    AppCompatTextView textViewCompat;

    public AnimatedVectorDrawableActivity_ViewHolder(@NonNull View itemView) {
        super(itemView);
        textViewCompat = itemView.findViewById(R.id.animatedViectorDrawableActivity_IntemTextview);

    }
}