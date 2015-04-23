package ar.com.wolox.lucasdelatorre.training.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import ar.com.wolox.lucasdelatorre.training.R;
import ar.com.wolox.lucasdelatorre.training.instances.NewsInstance;

public class NewsAdapter extends ArrayAdapter<NewsInstance> {

    public NewsAdapter(Context context, ArrayList<NewsInstance> news) {
        super(context, 0, news);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        NewsInstance singleNew = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.single_mini_new, parent, false);
        }

        TextView tvName = (TextView) convertView.findViewById(R.id.tv_new_title);
        TextView tvHome = (TextView) convertView.findViewById(R.id.tv_new_description);
        ImageView igLike = (ImageView) convertView.findViewById(R.id.ig_new_like);

        if (singleNew.isLike()) {
            igLike.setImageDrawable(convertView.getResources().getDrawable(R.drawable.like_on));
        }

        tvName.setText(singleNew.getTitle());
        tvHome.setText(singleNew.getDescription());

        return convertView;
    }
}