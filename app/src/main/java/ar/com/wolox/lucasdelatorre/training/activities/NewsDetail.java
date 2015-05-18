package ar.com.wolox.lucasdelatorre.training.activities;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;

import ar.com.wolox.lucasdelatorre.training.R;

public class NewsDetail extends Activity {

    private boolean mLike;
    private ImageView mBackIv;
    private ImageView mLikeIv;
    private SwipeRefreshLayout mSwipeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        init();
        setUi();
        setListeners();
        populate();
    }

    private void init() {
        mLike = false;
    }

    private void setUi() {
        mBackIv = (ImageView) findViewById(R.id.ig_newsdetail_icon);
        mLikeIv = (ImageView) findViewById(R.id.iv_newsdetail_like);
        mSwipeView = (SwipeRefreshLayout) findViewById(R.id.swipe_newsdetail);
    }

    private void setListeners() {

        mSwipeView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSwipeView.setRefreshing(false);
                    }
                }, 5000);
            }
        });

        mBackIv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        mLikeIv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                changeLike();
                handleLike();
            }
        });
    }

    private void populate() {
        handleLike();
    }

    private void changeLike() {
        mLike ^= true;
    }

    private void handleLike() {
        if (mLike) {
            mLikeIv.setImageDrawable(getResources().getDrawable(R.drawable.like_on));
        } else {
            mLikeIv.setImageDrawable(getResources().getDrawable(R.drawable.like_off));
        }
    }

}