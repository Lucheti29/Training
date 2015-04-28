package ar.com.wolox.lucasdelatorre.training.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import ar.com.wolox.lucasdelatorre.training.R;
import ar.com.wolox.lucasdelatorre.training.Utils;

public class SplashScreen extends Activity {

    private String mImageUrl;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_image_screen);

        init();
        setUi();
        populate();
    }

    private void init() {
        Bundle extras = this.getIntent().getExtras();
        mImageUrl  = extras.getString(Utils.IMAGE_KEY);
    }

    private void setUi() {
        mImageView = (ImageView) findViewById(R.id.iv_splash);
    }

    private void populate() {
        Picasso.with(this).load(mImageUrl).into(mImageView);
    }
}
