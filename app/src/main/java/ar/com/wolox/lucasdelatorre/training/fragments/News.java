package ar.com.wolox.lucasdelatorre.training.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;

import ar.com.wolox.lucasdelatorre.training.User;
import ar.com.wolox.lucasdelatorre.training.Utils;
import ar.com.wolox.lucasdelatorre.training.activities.CreateNews;
import ar.com.wolox.lucasdelatorre.training.activities.NewsDetail;
import ar.com.wolox.lucasdelatorre.training.instances.NewsArrayInstance;
import ar.com.wolox.lucasdelatorre.training.api.RestApiAdapter;
import ar.com.wolox.lucasdelatorre.training.adapters.NewsAdapter;
import ar.com.wolox.lucasdelatorre.training.R;
import ar.com.wolox.lucasdelatorre.training.instances.NewsInstance;
import ar.com.wolox.lucasdelatorre.training.services.NewsService;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class News extends Fragment {

    private static String sSort = "-createdAt";
    private static int sListViewOffset = 8;
    private View mView;
    private ListView mListView;
    private TextView mListEmptyTv;
    private ImageView mImageError;
    private FloatingActionButton mFab;
    private SwipeRefreshLayout mSwipeView;
    private ArrayList<NewsInstance> mNews;
    private int mActualPosition;
    private User mUser;
    private NewsAdapter mAdapter;
    private Handler mHandler;
    private boolean mIsRequested; // To avoid simultaneous callbacks
    private boolean listEnd; // To avoid unnecessary callbacks when you reach the end list

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
        mAdapter = new NewsAdapter(getActivity(), mNews);
        mHandler = new Handler();
        listEnd = false;
    }

    private void setUi() {
        mListView = (ListView) mView.findViewById(R.id.lv_news);
        mListEmptyTv = (TextView) mView.findViewById(R.id.tv_listempty);
        mFab = (FloatingActionButton) mView.findViewById(R.id.fab);
        mImageError = (ImageView) mView.findViewById(R.id.iv_news_error);
        mSwipeView = (SwipeRefreshLayout) mView.findViewById(R.id.swipe);

        mSwipeView.setColorSchemeResources(R.color.bluewolox, R.color.greenwolox,
                R.color.orange, R.color.red);
    }

    private void populate() {
        mListView.setAdapter(mAdapter);
        mFab.attachToListView(mListView);

        doACallback();
    }

    private void setListeners() {

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                Intent intent = new Intent(getActivity(), NewsDetail.class);
                startActivity(intent);
            }
        });

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

        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {

                boolean enable = isTheFirstElement();
                mSwipeView.setEnabled(enable);

                if (!enable && (firstVisibleItem + visibleItemCount
                                + sListViewOffset) >= totalItemCount) {
                        doACallback();
                    }
                }
        });
    }

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
        if (isRefreshing() || listEnd) return;

        setRefreshing(true);

        NewsService newsTry = RestApiAdapter.
                getAdapter().create(NewsService.class);

        newsTry.getNews(sSort, mActualPosition, sListViewOffset, new Callback<NewsArrayInstance>() {
            @Override
            public void success(NewsArrayInstance newsArray, Response response) {
                int resultLength = newsArray.getResults().length;

                if (resultLength == 0) listEnd = true;

                mActualPosition = mActualPosition + resultLength;

                for (NewsInstance newInstance : newsArray.getResults()) {
                    newInstance.initialize(mUser.getObjectId());
                    mNews.add(newInstance);
                }

                handleVisibility(true);

                ((NewsAdapter) mListView.getAdapter()).notifyDataSetChanged();
                setRefreshing(false);
            }

            @Override
            public void failure(RetrofitError error) {
                handleVisibility(false);
                setRefreshing(false);
            }
        });
    }

    private boolean isTheFirstElement() {
        if (mListView != null && !mNews.isEmpty() && mListView.getChildCount() > 0) {
            return mListView.getFirstVisiblePosition() == 0
                    && mListView.getChildAt(0).getTop() == 0;
        }
        return false;
    }

    private void refresh() {
        mNews.clear();
        mActualPosition = 0;
        listEnd = false;
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
            if (mListView.getVisibility() != View.VISIBLE) {
                mListView.setVisibility(View.VISIBLE);
                mImageError.setVisibility(View.GONE);
            }

            if (mNews.isEmpty()) {
                mListEmptyTv.setVisibility(View.VISIBLE);
            } else {
                mListEmptyTv.setVisibility(View.GONE);
            }
        } else {
            mListView.setVisibility(View.GONE);
            mListEmptyTv.setVisibility(View.GONE);
            mImageError.setVisibility(View.VISIBLE);
        }
    }
}