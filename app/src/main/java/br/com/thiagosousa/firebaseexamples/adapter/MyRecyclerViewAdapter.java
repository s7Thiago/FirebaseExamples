package br.com.thiagosousa.firebaseexamples.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.thiagosousa.firebaseexamples.R;
import br.com.thiagosousa.firebaseexamples.objects.ItemRecyclerView;
import br.com.thiagosousa.firebaseexamples.useful.UtilActivity;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {

    private List<ItemRecyclerView> itens;

    public MyRecyclerViewAdapter(List<ItemRecyclerView> itens) {
        this.itens = itens;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recyclerview, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title.setText(itens.get(position).getTitle());

        //Configurando o description para não ser maior do que 25 caracteres na representacao dos itens
        StringBuilder description = new StringBuilder();
        UtilActivity utilActivity = new UtilActivity() {
            @Override
            public void initViews(boolean init) {

            }

            @Override
            public void configureScreen(boolean configure) {

            }
        };

        //Inserindo uma string com tamanho limitado a 30 caracteres, caso a descricao seja muito grande

        StringBuilder descriptionSb = new StringBuilder();
        descriptionSb.append(
                utilActivity.limitLongString(itens.get(position).getDescription(), 70));

        holder.description.setText(descriptionSb.append("...").toString());


        holder.date.setText(itens.get(position).getDate());
        holder.logo.setImageResource(itens.get(position).getLogo());

    }


    @Override
    public int getItemCount() {
        return itens != null ? itens.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public TextView description;
        public TextView date;
        public ImageView logo;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.recyclerviewactivity_item_textview_title);
            description = itemView.findViewById(R.id.recyclerviewactivity_item_textview_description);
            date = itemView.findViewById(R.id.recyclerviewactivity_item_textview_date);
            logo = itemView.findViewById(R.id.recyclerviewactivity_item_imageview_logo);
        }
    }
}