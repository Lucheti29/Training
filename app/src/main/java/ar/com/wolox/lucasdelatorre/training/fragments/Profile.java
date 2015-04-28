package ar.com.wolox.lucasdelatorre.training.fragments;

 import android.content.Intent;
 import android.os.Bundle;
 import android.support.annotation.Nullable;
 import android.support.v4.app.Fragment;
 import android.view.LayoutInflater;
 import android.view.View;
 import android.view.ViewGroup;
 import android.widget.ImageView;
 import android.widget.TextView;

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
        mUser = Utils.getUserSaved(getActivity());
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

        if (Utils.isValidImageUrl(mUser.getPicture())) {
            Picasso.with(mView.getContext()).load(mUser.getPicture()).into(mAvatarIv);
        }

        if (Utils.isValidImageUrl(mUser.getCover())) {
            Picasso.with(mView.getContext()).load(mUser.getCover()).into(mCoverIv);
        }
    }

    private void setListeners() {
        mAvatarIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isValidImageUrl(mUser.getPicture())) {
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
                if (Utils.isValidImageUrl(mUser.getCover())) {
                    Intent intent = new
                            Intent(getActivity().getApplicationContext(), SplashScreen.class);
                    intent.putExtra(Utils.IMAGE_KEY, mUser.getCover());
                    startActivity(intent);
                }
            }
        });
    }
}