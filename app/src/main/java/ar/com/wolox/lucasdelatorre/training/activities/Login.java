package ar.com.wolox.lucasdelatorre.training.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import ar.com.wolox.lucasdelatorre.training.R;
import ar.com.wolox.lucasdelatorre.training.Utils;

public class Login extends Activity {

    private TextView mTermsTv;
    private Button mLoginBn;
    private Button mSignupBn;
    private EditText mUsernameEt;
    private EditText mPasswordEt;
    private String mUsername;
    private String mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (isLogged()) openBoard();

        setContentView(R.layout.activity_login);

        setUi();
        populate();
        setListeners();
    }

    private void setUi() {
        mTermsTv = (TextView) findViewById(R.id.tv_terms);
        mLoginBn = (Button) findViewById(R.id.bn_login_login);
        mSignupBn = (Button) findViewById(R.id.bn_login_signup);
        mUsernameEt = (EditText) findViewById(R.id.et_login_user);
        mPasswordEt = (EditText) findViewById(R.id.et_login_pass);
    }

    private void populate() {
        mTermsTv.setText(Html.fromHtml(getString(R.string.login_terms)));
        mTermsTv.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void setListeners() {
        mLoginBn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                attemptToLogin();
            }
        });

        mSignupBn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openSignup();
            }
        });
    }

    private void attemptToLogin() {
        mUsername = mUsernameEt.getText().toString();
        mPassword = mPasswordEt.getText().toString();

        checkCredentials();
    }

    private void checkCredentials() {
        if (!validateInputs()) return;

        //TODO: Check if the user exists and if the pass is correct
        //When the backend is implemented, it returns true
    }

    private boolean validateInputs()
    {
        if (mUsername.isEmpty()) {
            Utils.showToast(this, R.string.login_emptyuser);
            return false;
        } else if (mPassword.isEmpty()) {
            Utils.showToast(this, R.string.login_emptypass);
            return false;
        } else if (!Utils.validate(mUsername, Utils.EMAIL_PATTERN)) {
            Utils.showToast(this, R.string.login_invaliduser);
            return false;
        }

        return true;
    }

    private void saveCredentials(String username, String password) {
        //TODO: Save credentials
    }

    //TODO: implement it
    private boolean isLogged()
    {
        return false;
    }

    private void openBoard() {
        Intent intent = new Intent(this, Board.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

        finish();
    }

    private void openSignup() {
        Intent intent = new Intent(this, Signup.class);
        startActivity(intent);
    }
}
