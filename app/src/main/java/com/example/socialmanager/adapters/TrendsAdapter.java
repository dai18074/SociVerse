package com.example.socialmanager.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.socialmanager.R;

import java.util.List;

public class TrendsAdapter extends ArrayAdapter<String> {

    private List<String> dataset;
    private final LayoutInflater inflater;
    private final int layoutResource;

    public TrendsAdapter(@NonNull Context context, int resource, @NonNull List<String> objects) {
        super(context, resource, objects);
        dataset = objects;
        inflater = LayoutInflater.from(context);
        layoutResource = resource;
    }

    public String getTrend(int position){
        if(position < dataset.size() ){
            return dataset.get(position);
        }
        return "";
    }

    public void setTrends(@NonNull List<String> trends) {
        this.dataset = trends;
        notifyDataSetChanged();
    }

    static class TrendsViewHolder {

        public TextView txtTrend;

        public TrendsViewHolder(View itemView) {
            txtTrend = itemView.findViewById(R.id.txtTrend);
        }
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TrendsViewHolder holder;
        if(convertView == null){
            convertView = inflater.inflate(layoutResource, parent, false);
            holder = new TrendsViewHolder(convertView);
            convertView.setTag(holder);
        }
        else{
            holder = (TrendsViewHolder)convertView.getTag();
        }

        String trend = dataset.get(position);
        holder.txtTrend.setText(trend);

        return convertView;
    }

    @Override
    public int getCount() {
        return dataset.size();
    }

}
