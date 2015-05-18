package ar.com.wolox.lucasdelatorre.training.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ar.com.wolox.lucasdelatorre.training.Config;
import ar.com.wolox.lucasdelatorre.training.R;
import ar.com.wolox.lucasdelatorre.training.activities.NewsDetail;
import ar.com.wolox.lucasdelatorre.training.instances.NewsInstance;
import ar.com.wolox.lucasdelatorre.training.instances.NewsRowViewHolder;

public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Activity mActivity;
    private ArrayList<NewsInstance> mItemsNews = new ArrayList<>();
    private NewsRowViewHolder mNewsRowViewHolder;
    private OnViewHolderListener mOnViewHolderListener;
    private int mCurrentPos = 0;

    public NewsRecyclerViewAdapter(Activity activity, ArrayList<NewsInstance> news) {
        mActivity = activity;
        mItemsNews = news;
    }

    public interface OnViewHolderListener {
        void onNextPageRequired();
    }

    public void setOnViewHolderListener(OnViewHolderListener mOnViewHolderListener) {
        this.mOnViewHolderListener = mOnViewHolderListener;
    }

    @Override
    public int getItemViewType(int position) {
        if (mItemsNews.get(position).isLoader()) return 1;
        else return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(
            ViewGroup parent,
            int viewType) {

        View itemLayoutView;

        switch (viewType) {
            default:
            case 0:
                itemLayoutView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.single_mini_new, null);
                return new NewsRowViewHolder(itemLayoutView, mViewHolderClickListener);
        }
    }

    NewsRowViewHolder.ViewHolderClicks mViewHolderClickListener = new NewsRowViewHolder
            .ViewHolderClicks() {
        @Override
        public void onClickRow(View caller) {
            Intent intent = new Intent(mActivity, NewsDetail.class);
            mActivity.startActivity(intent);
        }
    };

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        switch (getItemViewType(position)) {
            default:
            case 0:
                mNewsRowViewHolder = (NewsRowViewHolder) viewHolder;
                NewsInstance singleNew = mItemsNews.get(position);
                mNewsRowViewHolder.mTitle.setText(singleNew.getTitle());
                mNewsRowViewHolder.mContent.setText(singleNew.getText());
                Picasso.with(mActivity).load(singleNew.getPicture())
                        .into(mNewsRowViewHolder.mImage);
                mNewsRowViewHolder.mDate.setText(singleNew.getDiffTime());

                if (mItemsNews.get(position).isLike()) {
                    mNewsRowViewHolder.mLike.setImageDrawable(mActivity
                            .getResources().getDrawable(R.drawable.like_on));
                } else {
                    mNewsRowViewHolder.mLike.setImageDrawable(mActivity
                            .getResources().getDrawable(R.drawable.like_off));
                }

                if (mOnViewHolderListener != null && position >= getItemCount() - Config.LIST_OFFSET) {
                    mOnViewHolderListener.onNextPageRequired();
                }
        }

        mCurrentPos = position;
    }

    @Override
    public int getItemCount() {
        return mItemsNews.size();
    }

}