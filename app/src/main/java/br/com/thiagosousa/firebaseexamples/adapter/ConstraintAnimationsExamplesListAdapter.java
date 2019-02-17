package br.com.thiagosousa.firebaseexamples.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import br.com.thiagosousa.firebaseexamples.R;
import br.com.thiagosousa.firebaseexamples.activitys.ConstraintAnimationExample1Activity;

public class ConstraintAnimationsExamplesListAdapter extends RecyclerView.Adapter<ConstraintAnimationsExamplesListAdapter.ConstraintAnimationsAdapterViewHolder> {

    private static final String TAG = "ConstraintAnimationsExa";

    private List<String> itens;
    Context context;

    public ConstraintAnimationsExamplesListAdapter(Context context, List<String> itens) {
        this.itens = itens;
        this.context = context;
    }

    @NonNull
    @Override
    public ConstraintAnimationsAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ConstraintAnimationsAdapterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_constraintanimaion_example, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ConstraintAnimationsAdapterViewHolder holder, final int position) {
        Log.w(TAG, "\n\nonBindViewHolder: item Title -> " + itens.get(position) + "\n\n");
        holder.itemTitle.setText(itens.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position) {
                    case 0:
                        context.startActivity(new Intent(holder.itemView.getContext(), ConstraintAnimationExample1Activity.class));
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

    class ConstraintAnimationsAdapterViewHolder extends RecyclerView.ViewHolder {
        TextView itemTitle;

        public ConstraintAnimationsAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            itemTitle = itemView.findViewById(R.id.constraintAnimationActivity_item_title);
        }
    }
}