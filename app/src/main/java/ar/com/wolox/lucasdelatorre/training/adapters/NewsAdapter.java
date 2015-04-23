package ar.com.wolox.lucasdelatorre.training.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import ar.com.wolox.lucasdelatorre.training.R;
import ar.com.wolox.lucasdelatorre.training.instances.NewInstance;

public class NewsAdapter extends ArrayAdapter<NewInstance> {

    public NewsAdapter(Context context, ArrayList<NewInstance> news) {
        super(context, 0, news);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        NewInstance singleNew = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.single_mini_new, parent, false);
        }

        TextView tvName = (TextView) convertView.findViewById(R.id.tv_new_title);
        TextView tvHome = (TextView) convertView.findViewById(R.id.tv_new_description);

        tvName.setText(singleNew.title);
        tvHome.setText(singleNew.description);

        return convertView;
    }
}