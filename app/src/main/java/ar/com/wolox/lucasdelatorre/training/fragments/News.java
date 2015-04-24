package ar.com.wolox.lucasdelatorre.training.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;

import ar.com.wolox.lucasdelatorre.training.instances.NewsInstance;
import ar.com.wolox.lucasdelatorre.training.adapters.NewsAdapter;
import ar.com.wolox.lucasdelatorre.training.R;

public class News extends Fragment {

    private View mView;
    private ListView mListView;
    private TextView mListEmptyTv;
    private FloatingActionButton mFab;
    private SwipeRefreshLayout mSwipeView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_news, container, false);

        setUi();
        populate();
        setListeners();

        return mView;
    }

    private void setUi() {
        mListView = (ListView) mView.findViewById(R.id.lv_news);
        mListEmptyTv = (TextView) mView.findViewById(R.id.tv_listempty);
        mFab = (FloatingActionButton) mView.findViewById(R.id.fab);
        mSwipeView = (SwipeRefreshLayout) mView.findViewById(R.id.swipe);
    }

    private void populate() {
        mFab.attachToListView(mListView);

        ArrayList<NewsInstance> arrayOfUsers = NewsInstance.getUsers();

        if (arrayOfUsers.isEmpty()) {
            mListEmptyTv.setVisibility(View.VISIBLE);
        } else {
            mListEmptyTv.setVisibility(View.GONE);
            NewsAdapter adapter = new NewsAdapter(this.getActivity(), arrayOfUsers);
            mListView.setAdapter(adapter);
        }
    }

    private void setListeners() {
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                int topRowVerticalPosition =
                        (mListView == null || mListView.getChildCount() == 0) ?
                                0 : mListView.getChildAt(0).getTop();

                if (firstVisibleItem == 0 && topRowVerticalPosition >= 0) {
                    mSwipeView.setEnabled(true);

                    //TODO: When the callback is implemented, delete this
                    // --------------------
                    new Handler().postDelayed(new Runnable() {
                        @Override public void run() {
                            mSwipeView.setRefreshing(false);
                        }
                    }, 10000);
                    // --------------------
                }
            }
        });
    }
}