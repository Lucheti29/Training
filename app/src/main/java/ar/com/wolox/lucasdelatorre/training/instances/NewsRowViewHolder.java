package ar.com.wolox.lucasdelatorre.training.instances;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ar.com.wolox.lucasdelatorre.training.R;

public class NewsRowViewHolder extends RecyclerView.ViewHolder {

    public TextView mTitle;
    public TextView mContent;
    public ImageView mImage;
    public ImageView mLike;
    public TextView mDate;

    public ViewHolderClicks mListener;

    public NewsRowViewHolder(View itemLayoutView, ViewHolderClicks viewHolderClicks) {
        super(itemLayoutView);
        this.mTitle = (TextView) itemLayoutView.findViewById(R.id.tv_new_title);
        this.mContent = (TextView) itemLayoutView.findViewById(R.id.tv_new_description);
        this.mImage = (ImageView) itemLayoutView.findViewById(R.id.ig_new_avatar);
        this.mLike = (ImageView) itemLayoutView.findViewById(R.id.ig_new_like);
        this.mDate = (TextView) itemLayoutView.findViewById(R.id.tv_new_time);

        this.mListener = viewHolderClicks;

        itemLayoutView.setOnClickListener(mRowClickListener);
    }

    View.OnClickListener mRowClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mListener.onClickRow(view);
        }
    };

    public static interface ViewHolderClicks {
        public void onClickRow(View caller);
    }
}