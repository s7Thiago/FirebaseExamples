package br.com.thiagosousa.firebaseexamples.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.thiagosousa.firebaseexamples.R;
import br.com.thiagosousa.firebaseexamples.objects.ItemForActivityWithListViewHolder;

public class AdapterWithViewHolder extends BaseAdapter {

    private Context context;
    private List<ItemForActivityWithListViewHolder> itens;

    public AdapterWithViewHolder(Context context, List<ItemForActivityWithListViewHolder> itens) {
        this.context = context;
        this.itens = itens;
    }

    @Override
    public int getCount() {
        return itens.size();
    }

    @Override
    public Object getItem(int position) {
        return itens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemForActivityWithListViewHolder item = (ItemForActivityWithListViewHolder) getItem(position);
        View linha = null;
        ViewHolder mViewHolder = null;

        if (convertView == null) {
            linha = LayoutInflater.from(context).inflate(R.layout.item_listviewholder_activity_example, parent, false);
            mViewHolder = new ViewHolder(linha);
            linha.setTag(mViewHolder);
        } else {
            linha = convertView;
            mViewHolder = (ViewHolder) linha.getTag();
        }

//        Linkando as views
        mViewHolder.getTitle().setText(item.getTitle());
        mViewHolder.getTime().setText(item.getTime());
        mViewHolder.getAddress().setText(item.getAddress());

        return linha;
    }
}

class ViewHolder {

    private TextView title;
    private TextView time;
    private TextView address;

    public ViewHolder(View view) {
        this.title = view.findViewById(R.id.item_customlistviewholderactivity_title_textview);
        this.time = view.findViewById(R.id.item_customlistviewholderactivity_time_textview);
        this.address = view.findViewById(R.id.item_customlistviewholderactivity_address_textview);
    }

    public TextView getTitle() {
        return title;
    }

    public void setTitle(TextView title) {
        this.title = title;
    }

    public TextView getTime() {
        return time;
    }

    public void setTime(TextView time) {
        this.time = time;
    }

    public TextView getAddress() {
        return address;
    }

    public void setAddress(TextView address) {
        this.address = address;
    }
}
