package ar.com.wolox.lucasdelatorre.training.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

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
            viewHolder.tvTime = (TextView) convertView.findViewById(R.id.tv_new_time);
            viewHolder.igLike = (ImageView) convertView.findViewById(R.id.ig_new_like);
            viewHolder.picture = (ImageView) convertView.findViewById(R.id.ig_new_avatar);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolderItem) convertView.getTag();
        }

        viewHolder.tvName.setText(singleNew.getTitle());
        viewHolder.tvHome.setText(singleNew.getDescription());
        viewHolder.tvTime.setText(singleNew.getDiffTime());

        Picasso.with(convertView.getContext()).load(singleNew.getPicture()).into(viewHolder.picture);

        if (singleNew.isLike()) {
            viewHolder.igLike.setImageDrawable(convertView.getResources().getDrawable(R.drawable.like_on));
        } else {
            viewHolder.igLike.setImageDrawable(convertView.getResources().getDrawable(R.drawable.like_off));
        }

        return convertView;
    }

    private static class ViewHolderItem {
        public TextView tvName;
        public TextView tvHome;
        public TextView tvTime;
        public ImageView igLike;
        public ImageView picture;
    }
}