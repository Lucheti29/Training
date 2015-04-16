package ar.com.wolox.lucasdelatorre.training.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import ar.com.wolox.lucasdelatorre.training.R;
import ar.com.wolox.lucasdelatorre.training.Utils;

public class Signup extends ActionBarActivity {

    private TextView tvTerms;
    private Button bnAccept;
    private String etUsername;
    private String etPassword;
    private String etCPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        setUi();
        populate();
        setListeners();
    }

    private void setUi() {
        tvTerms = (TextView) findViewById(R.id.tv_signup_terms);
        bnAccept = (Button) findViewById(R.id.bn_signup_accept);
    }

    private void populate() {
        tvTerms.setText(Html.fromHtml(getString(R.string.signup_terms)));
        tvTerms.setMovementMethod(LinkMovementMethod.getInstance());
        setTitle(getResources().getText(R.string.signup_label));
    }

    private void setListeners() {
        bnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptToSignup();
            }
        });
    }

    private void attemptToSignup() {
        getInputs();

        if (userExists(etUsername)) {
            Utils.showToast(this, R.string.signup_userexists);
            return;
        }

        //TODO: Register new user
    }

    private void getInputs()
    {
        etUsername = ((EditText) findViewById(R.id.et_signup_user)).getText().toString();
        etPassword = ((EditText) findViewById(R.id.et_signup_pass)).getText().toString();
        etCPassword = ((EditText) findViewById(R.id.et_signup_cpass)).getText().toString();
    }

    private boolean userExists(String username) {
        boolean value = false;

        if (etUsername.isEmpty()) {
            Utils.showToast(this, R.string.signup_emptyuser);
        }
        else if (etPassword.isEmpty()) {
            Utils.showToast(this, R.string.signup_emptypass);
        }
        else if (etCPassword.isEmpty()) {
            Utils.showToast(this, R.string.signup_cpassnotmatch);
        }
        else if (!Utils.validate(etUsername, Utils.EMAIL_PATTERN)) {
            Utils.showToast(this, R.string.signup_invaliduser);
        }
        else if (!etPassword.equals(etCPassword)) {
            Utils.showToast(this, R.string.signup_cpassnotmatch);
        }

        //TODO: Implement it
        //If the user exists, value turns true

        return value;
    }
}
