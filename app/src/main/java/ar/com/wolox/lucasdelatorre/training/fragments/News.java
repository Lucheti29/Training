package ar.com.wolox.lucasdelatorre.training.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import ar.com.wolox.lucasdelatorre.training.instances.NewsInstance;
import ar.com.wolox.lucasdelatorre.training.adapters.NewsAdapter;
import ar.com.wolox.lucasdelatorre.training.R;

public class News extends Fragment {

    private View mView;
    private ListView mListView;
    private TextView mListEmptyTv;
    private String mTextListEmpty;

    /*
    //TODO: Implement it
    ListView listView = (ListView) findViewById(android.R.id.list);
    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.attachToListView(listView);
    */

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_news, container,false);

        init();
        setUi();
        populateListView();

        return mView;
    }

    private void init() {
        mTextListEmpty = mView.getResources().getString(R.string.news_empty);
    }

    private void setUi() {
        mListView = (ListView) mView.findViewById(R.id.lv_news);
        mListEmptyTv = (TextView) mView.findViewById(R.id.tv_listempty);
    }

    private void populateListView() {
        ArrayList<NewsInstance> arrayOfUsers = NewsInstance.getUsers();

        if (!arrayOfUsers.isEmpty()) {
            NewsAdapter adapter = new NewsAdapter(this.getActivity(), arrayOfUsers);
            mListView.setAdapter(adapter);
        } else {
            mListEmptyTv.setText(mTextListEmpty);
        }
    }
}