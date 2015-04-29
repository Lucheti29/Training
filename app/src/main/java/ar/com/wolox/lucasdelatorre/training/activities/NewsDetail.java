package ar.com.wolox.lucasdelatorre.training.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import ar.com.wolox.lucasdelatorre.training.R;

public class NewsDetail extends Activity {

    private ImageView mBackIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        setUi();
        setListeners();
    }

    private void setUi() {
        mBackIv = (ImageView) findViewById(R.id.ig_newsdetail_icon);
    }

    private void setListeners() {
        mBackIv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }

}
