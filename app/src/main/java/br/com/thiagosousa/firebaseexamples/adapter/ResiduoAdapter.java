package br.com.thiagosousa.firebaseexamples.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.thiagosousa.firebaseexamples.R;
import br.com.thiagosousa.firebaseexamples.objects.Residuo;

public class ResiduoAdapter extends BaseAdapter {

    Context context;
    List<Residuo> residuos;

    public ResiduoAdapter(Context context, List<Residuo> residuos) {
        this.context = context;
        this.residuos = residuos;
    }

    @Override
    public int getCount() {
        return residuos.size();
    }

    @Override
    public Object getItem(int position) {
        return residuos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

//        Passo 1
        Residuo residuo = residuos.get(position);

//        Passo 2
        View linha = LayoutInflater.from(context).inflate(R.layout.item_residuo, parent, false);

//        Passo 3
        ImageView residueRepresentation = (ImageView) linha.findViewById(R.id.residue_representation);
        ImageView residueCategory = (ImageView) linha.findViewById(R.id.residue_category);
        TextView residueName = (TextView) linha.findViewById(R.id.residue_name_textview);
        TextView residueIsReciclable = (TextView) linha.findViewById(R.id.residue_reciclable);

//        Passo 4
        Resources res = context.getResources();

//        Obtendo os arrays com informações uteis

//        Representacoes
        TypedArray residueRepresentationsArray = res.obtainTypedArray(R.array.residuos);

        /* Categorias
         * 1: baterias - 2: eletronicos - 3:metais
         * 4: organicos - 5: outros - 6: papeis
         * 7: plasticos - 8: vidros*/
        TypedArray residueCategorysArray = res.obtainTypedArray(R.array.categorias_reciclaveis);

//        Preenchendo os componentes do item referente
        residueRepresentation.setImageDrawable(residueRepresentationsArray.getDrawable(residuo.getRepresentacao()));
        residueCategory.setImageDrawable(residueCategorysArray.getDrawable(residuo.getCategoria()));
        residueName.setText(residuo.getNome());
        residueIsReciclable.setText(residuo.isReciclavel()? "reciclável" : "não\nreciclável");

//        Passo 5
        return linha;
    }
}
