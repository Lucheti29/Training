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
        ViewHolderItem viewHolder;
        NewsInstance singleNew = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.single_mini_new, parent, false);
            viewHolder = new ViewHolderItem();

            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tv_new_title);
            viewHolder.tvHome = (TextView) convertView.findViewById(R.id.tv_new_description);
            viewHolder.igLike = (ImageView) convertView.findViewById(R.id.ig_new_like);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolderItem) convertView.getTag();
        }

        if (singleNew.isLike()) {
            viewHolder.igLike.setImageDrawable(convertView.getResources().getDrawable(R.drawable.like_on));
        }

        viewHolder.tvName.setText(singleNew.getTitle());
        viewHolder.tvHome.setText(singleNew.getDescription());

        return convertView;
    }

    static class ViewHolderItem {
        TextView tvName;
        TextView tvHome;
        ImageView igLike;
    }
}