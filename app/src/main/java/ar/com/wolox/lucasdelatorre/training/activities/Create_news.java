package ar.com.wolox.lucasdelatorre.training.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import ar.com.wolox.lucasdelatorre.training.R;

public class Create_news extends Activity {

    private ImageView mBackIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_news);

        setUi();
        setListeners();
    }

    private void setUi() {
        mBackIv = (ImageView) findViewById(R.id.ig_create_back_icon);
    }

    private void setListeners() {
        mBackIv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }

}
