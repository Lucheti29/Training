package ar.com.wolox.lucasdelatorre.training.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import ar.com.wolox.lucasdelatorre.training.NewInstance;
import ar.com.wolox.lucasdelatorre.training.NewsAdapter;
import ar.com.wolox.lucasdelatorre.training.R;

public class News extends Fragment {

    private View mView;
    private ListView mListView;

    /*
    //TODO: Implement it
    ListView listView = (ListView) findViewById(android.R.id.list);
    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.attachToListView(listView);
    */

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_news, container,false);

        setUi();
        populateListView();

        return mView;
    }

    private void setUi() {
        mListView = (ListView) mView.findViewById(R.id.lv_news);
    }

    private void populateListView() {
        ArrayList<NewInstance> arrayOfUsers = NewInstance.getUsers();
        NewsAdapter adapter = new NewsAdapter(this.getActivity(), arrayOfUsers);

        mListView.setAdapter(adapter);
    }
}