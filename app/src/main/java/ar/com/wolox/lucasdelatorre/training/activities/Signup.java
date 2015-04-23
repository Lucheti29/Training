package ar.com.wolox.lucasdelatorre.training.activities;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import ar.com.wolox.lucasdelatorre.training.R;
import ar.com.wolox.lucasdelatorre.training.User;
import ar.com.wolox.lucasdelatorre.training.Utils;
import ar.com.wolox.lucasdelatorre.training.api.RestApiAdapter;
import ar.com.wolox.lucasdelatorre.training.services.SignupService;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Signup extends ActionBarActivity {

    private TextView mTermsTv;
    private Button mAcceptBn;
    private EditText mUsernameEt;
    private EditText mPasswordEt;
    private EditText mCPasswordEt;
    private String mUsername;
    private String mPassword;
    private String mCPassword;
    private ActionBarActivity mActivity;
    private View mDivider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        init();
        setUi();
        populate();
        setListeners();
    }

    private void init() {
        mActivity = this;
    }

    private void setUi() {
        mTermsTv = (TextView) findViewById(R.id.tv_signup_terms);
        mAcceptBn = (Button) findViewById(R.id.bn_signup_accept);
        mUsernameEt = (EditText) findViewById(R.id.et_signup_user);
        mPasswordEt = (EditText) findViewById(R.id.et_signup_pass);
        mCPasswordEt = (EditText) findViewById(R.id.et_signup_cpass);
        mDivider = findViewById(R.id.view_divider);
    }

    private void populate() {
        mTermsTv.setText(Html.fromHtml(getString(R.string.signup_terms)));
        mTermsTv.setMovementMethod(LinkMovementMethod.getInstance());
        setTitle(getResources().getText(R.string.signup_label));

        if (Build.VERSION.SDK_INT == 21) {
            mDivider.setElevation(5);
        }
        //Else -> Implement image
    }

    private void setListeners() {
        mAcceptBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptToSignup();
            }
        });
    }

    private void attemptToSignup() {
        //Check inputs before create a new User
        mUsername = mUsernameEt.getText().toString();
        mPassword = mPasswordEt.getText().toString();
        mCPassword = mCPasswordEt.getText().toString();

        if (!validateInputs()) return;

        User user = new User(mUsername, mPassword);

        SignupService signupTry = RestApiAdapter.getAdapter().create(SignupService.class);
        signupTry.signup(user, new Callback<User>() {
            @Override
            public void success(User user, Response response) {
                Utils.showToast(mActivity, R.string.signup_successful);
                finish();
            }

            @Override
            public void failure(RetrofitError error) {
                User errorEntity = (User) error.getBody();

                //Handle if the user exists
                if (errorEntity.getCode().equalsIgnoreCase(Utils.CODE_USER_TAKEN)) {
                    Utils.showToast(mActivity, R.string.signup_usernametaken);
                } else {
                    Utils.showToast(mActivity, R.string.signup_usernametaken);
                }
            }
        });
    }

    private boolean validateInputs() {
        if (mUsername.isEmpty()) {
            Utils.showToast(this, R.string.signup_emptyuser);
            return false;
        } else if (mPassword.isEmpty()) {
            Utils.showToast(this, R.string.signup_emptypass);
            return false;
        } else if (mCPassword.isEmpty()) {
            Utils.showToast(this, R.string.signup_cpassnotmatch);
            return false;
        } else if (!Utils.validate(mUsername, Utils.EMAIL_PATTERN)) {
            Utils.showToast(this, R.string.signup_invaliduser);
            return false;
        } else if (!mPassword.equals(mCPassword)) {
            Utils.showToast(this, R.string.signup_cpassnotmatch);
            return false;
        }

        return true;
    }
}