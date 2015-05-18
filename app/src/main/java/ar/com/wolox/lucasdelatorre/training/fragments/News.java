package ar.com.wolox.lucasdelatorre.training.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;

import ar.com.wolox.lucasdelatorre.training.User;
import ar.com.wolox.lucasdelatorre.training.Utils;
import ar.com.wolox.lucasdelatorre.training.activities.CreateNews;
import ar.com.wolox.lucasdelatorre.training.adapters.NewsRecyclerViewAdapter;
import ar.com.wolox.lucasdelatorre.training.instances.NewsArrayInstance;
import ar.com.wolox.lucasdelatorre.training.api.RestApiAdapter;
import ar.com.wolox.lucasdelatorre.training.R;
import ar.com.wolox.lucasdelatorre.training.instances.NewsInstance;
import ar.com.wolox.lucasdelatorre.training.services.NewsService;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class News extends Fragment {

    private static String sSort = "-createdAt";
    private static int sListViewOffset = 10;
    private View mView;
    private TextView mListEmptyTv;
    private RecyclerView mRecyclerView;
    private NewsRecyclerViewAdapter mNewsRecyclerViewAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private ImageView mImageError;
    private FloatingActionButton mFab;
    private SwipeRefreshLayout mSwipeView;
    private ArrayList<NewsInstance> mNews;
    private int mActualPosition;
    private User mUser;
    private Handler mHandler;
    private boolean mIsRequested; // To avoid simultaneous callbacks
    private boolean mListEnd; // To avoid unnecessary callbacks when you reach the end list

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_news, container, false);

        init();
        setUi();
        populate();
        setListeners();

        return mView;
    }

    private void init() {
        mNews = new ArrayList<>();
        mActualPosition = 0;
        mUser = Utils.getUserSaved(getActivity());
        setRefreshing(false);
        mHandler = new Handler();
        mListEnd = false;
        mLinearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        mNewsRecyclerViewAdapter = new NewsRecyclerViewAdapter(getActivity(), mNews);
    }

    private void setUi() {
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recylerView_news);
        mFab = (FloatingActionButton) mView.findViewById(R.id.fab);
        mImageError = (ImageView) mView.findViewById(R.id.iv_news_error);
        mSwipeView = (SwipeRefreshLayout) mView.findViewById(R.id.swipe);
        mListEmptyTv = (TextView) mView.findViewById(R.id.tv_listempty);

        mSwipeView.setColorSchemeResources(R.color.bluewolox, R.color.greenwolox,
                R.color.orange, R.color.red);
    }

    private void populate() {
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mNewsRecyclerViewAdapter.setOnViewHolderListener(mViewHolderListener);
        mRecyclerView.setAdapter(mNewsRecyclerViewAdapter);
        mFab.attachToRecyclerView(mRecyclerView);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        doACallback();
    }

    private void setListeners() {
        mFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CreateNews.class);
                startActivity(intent);
            }
        });

        mImageError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh();
            }
        });

        mSwipeView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                refresh();
                mHandler.post(refreshing);
            }
        });
    }

    NewsRecyclerViewAdapter.OnViewHolderListener mViewHolderListener = new NewsRecyclerViewAdapter.OnViewHolderListener() {
        @Override
        public void onNextPageRequired() {
            doACallback();
        }
    };

    private final Runnable refreshing = new Runnable(){
        public void run(){
            try {
                if (isRefreshing()) {
                    mHandler.postDelayed(this, 2000);
                } else {
                    mSwipeView.setRefreshing(false);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    private void doACallback() {
        if (isRefreshing() || mListEnd) return;

        setRefreshing(true);

        NewsService newsTry = RestApiAdapter.
                getAdapter().create(NewsService.class);

        newsTry.getNews(sSort, mActualPosition, sListViewOffset, new Callback<NewsArrayInstance>() {
            @Override
            public void success(NewsArrayInstance newsArray, Response response) {
                int resultLength = newsArray.getResults().length;

                if (resultLength == 0) mListEnd = true;

                mActualPosition = mActualPosition + resultLength;

                for (NewsInstance newInstance : newsArray.getResults()) {
                    newInstance.initialize(mUser.getObjectId());
                    mNews.add(newInstance);
                }

                handleVisibility(true);
                setRefreshing(false);

                mNewsRecyclerViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void failure(RetrofitError error) {
                handleVisibility(false);
                setRefreshing(false);
            }
        });
    }

    private void refresh() {
        mNews.clear();
        mActualPosition = 0;
        mListEnd = false;
        doACallback();
    }

    private boolean isRefreshing() {
        return mIsRequested;
    }

    private void setRefreshing(boolean value) {
        mIsRequested = value;
    }

    private void handleVisibility(boolean success) {
        if (success) {
            if (mRecyclerView.getVisibility() != View.VISIBLE) {
                mRecyclerView.setVisibility(View.VISIBLE);
                mImageError.setVisibility(View.GONE);
            }

            if (mNews.isEmpty()) {
                mListEmptyTv.setVisibility(View.VISIBLE);
            } else {
                mListEmptyTv.setVisibility(View.GONE);
            }
        } else {
            mRecyclerView.setVisibility(View.GONE);
            mListEmptyTv.setVisibility(View.GONE);
            mImageError.setVisibility(View.VISIBLE);
        }
    }
}