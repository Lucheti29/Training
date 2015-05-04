package ar.com.wolox.lucasdelatorre.training.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ar.com.wolox.lucasdelatorre.training.R;
import ar.com.wolox.lucasdelatorre.training.Utils;

public class CreateNews extends Activity {

    private Activity mActivity;
    private ImageView mBackIv;
    private ImageView mTickIv;
    private TextView mTitleTv;
    private TextView mDescriptionTv;
    private final static int sMaxLengthTitle = 60;
    private final static int sMaxLenghtDescription = 250;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_news);

        init();
        setUi();
        setListeners();
    }

    private void init() {
        mActivity = this;
    }

    private void setUi() {
        mBackIv = (ImageView) findViewById(R.id.ig_create_back_icon);
        mTickIv = (ImageView) findViewById(R.id.ig_create_tick_icon);
        mTitleTv = (TextView) findViewById(R.id.et_create_title);
        mDescriptionTv = (TextView) findViewById(R.id.et_create_description);
    }

    private void setListeners() {
        mBackIv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        mTickIv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (checkFields()) {
                    //Save
                    Utils.showToast(mActivity, R.string.create_saved);
                }
            }
        });
    }

    private boolean checkFields() {
        if (mTitleTv.length() > sMaxLengthTitle) {
            Utils.showToast(mActivity, R.string.create_title_length);
            return false;
        } else if (mTitleTv.length() == 0) {
            Utils.showToast(mActivity, R.string.create_title_empty);
            return false;
        }

        if (mDescriptionTv.length() > sMaxLenghtDescription) {
            Utils.showToast(mActivity, R.string.create_description_length);
            return false;
        } else if (mDescriptionTv.length() == 0) {
            Utils.showToast(mActivity, R.string.create_description_empty);
            return false;
        }

        return true;
    }

}