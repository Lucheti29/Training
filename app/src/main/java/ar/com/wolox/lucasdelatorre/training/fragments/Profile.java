package ar.com.wolox.lucasdelatorre.training.fragments;

 import android.content.Intent;
 import android.content.SharedPreferences;
 import android.os.Bundle;
 import android.preference.PreferenceManager;
 import android.support.annotation.Nullable;
 import android.support.v4.app.Fragment;
 import android.view.LayoutInflater;
 import android.view.View;
 import android.view.ViewGroup;
 import android.widget.ImageView;
 import android.widget.TextView;

 import com.google.gson.Gson;
 import com.squareup.picasso.Picasso;

 import ar.com.wolox.lucasdelatorre.training.R;
 import ar.com.wolox.lucasdelatorre.training.User;
 import ar.com.wolox.lucasdelatorre.training.Utils;
 import ar.com.wolox.lucasdelatorre.training.activities.SplashScreen;
 import de.hdodenhof.circleimageview.CircleImageView;

public class Profile extends Fragment {

    private View mView;
    private User mUser;
    private CircleImageView mAvatarIv;
    private ImageView mCoverIv;
    private TextView mNameTv;
    private TextView mDescriptionTv;
    private TextView mLocationTv;
    private String mPictureUrl;
    private String mCoverUrl;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_profile, container, false);

        init();
        setUi();
        populate();
        setListeners();

        return mView;
    }

    private void init() {
        loadUser();
        mPictureUrl = mUser.getPicture();
        mCoverUrl = mUser.getCover();
    }

    private void setUi() {
        mNameTv = (TextView) mView.findViewById(R.id.tv_profile_name);
        mDescriptionTv = (TextView) mView.findViewById(R.id.tv_profile_description);
        mLocationTv = (TextView) mView.findViewById(R.id.tv_profile_location);
        mAvatarIv = (CircleImageView) mView.findViewById(R.id.iv_profile_avatar);
        mCoverIv = (ImageView) mView.findViewById(R.id.iv_profile_cover);
    }

    private void populate() {
        mNameTv.setText(mUser.getName());
        mDescriptionTv.setText(mUser.getDescription());
        mLocationTv.setText(mUser.getLocation());

        if (Utils.isValidImageUrl(mPictureUrl)) {
            Picasso.with(mView.getContext()).load(mPictureUrl).into(mAvatarIv);
        }

        if (Utils.isValidImageUrl(mCoverUrl)) {
            Picasso.with(mView.getContext()).load(mCoverUrl).into(mCoverIv);
        }
    }

    private void setListeners() {
        mAvatarIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isValidImageUrl(mPictureUrl)) {
                    Intent intent = new
                            Intent(getActivity().getApplicationContext(), SplashScreen.class);
                    intent.putExtra(Utils.IMAGE_KEY, mUser.getPicture());
                    startActivity(intent);
                }
            }
        });

        mCoverIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isValidImageUrl(mCoverUrl)) {
                    Intent intent = new
                            Intent(getActivity().getApplicationContext(), SplashScreen.class);
                    intent.putExtra(Utils.IMAGE_KEY, mUser.getCover());
                    startActivity(intent);
                }
            }
        });
    }

    private void loadUser() {
        SharedPreferences sharedPref = PreferenceManager
                .getDefaultSharedPreferences(this.getActivity().getApplicationContext());

        Gson gson = new Gson();
        String json = sharedPref.getString(Utils.USER_KEY, "");
        mUser = gson.fromJson(json, User.class);
    }
}