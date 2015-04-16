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

    private TextView tvTerms;
    private Button bnLogin;
    private Button bnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(isLogged()) {
            startBoard();
        }

        setContentView(R.layout.activity_login);

        setUi();
        populate();
        setListeners();
    }

    private void setUi() {
        tvTerms = (TextView) findViewById(R.id.tv_terms);
        bnLogin = (Button) findViewById(R.id.bn_login_login);
        bnSignup = (Button) findViewById(R.id.bn_login_signup);
    }

    private void populate() {
        tvTerms.setText(Html.fromHtml(getString(R.string.login_terms)));
        tvTerms.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void setListeners() {
        bnLogin.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                attemptToLogin();
            }
        });

        bnSignup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openSignup();
            }
        });
    }

    private void attemptToLogin() {
        String username = ((EditText) findViewById(R.id.et_login_user)).getText().toString();
        String pass = ((EditText) findViewById(R.id.et_login_pass)).getText().toString();

        if(username.isEmpty()) {
            Utils.showToast(this, R.string.login_emptyuser);
            return;
        }

        if(pass.isEmpty()) {
            Utils.showToast(this, R.string.login_emptypass);
            return;
        }

        if(!Utils.validate(username, Utils.EMAIL_PATTERN)) {
            Utils.showToast(this, R.string.login_invaliduser);
            return;
        }

        MessageType type = checkCredentials(username, pass);

        if(type == MessageType.SUCCESS) {
            saveCredentials(username, pass);

            //TODO: Implement it when Board.class exists
            //Intent intent = new Intent(this, Board.class);
            //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            //startActivity(intent);
        }
        else if(type == MessageType.FAILED_USER) {
            Utils.showToast(this, R.string.login_wronguser);
        }
        else if(type == MessageType.FAILED_PASS) {
            Utils.showToast(this, R.string.login_wrongpass);
        }
    }

    private void openSignup() {
        Intent intent = new Intent(this, Signup.class);
        startActivity(intent);
    }

    private MessageType checkCredentials(String username, String password) {
        //TODO: Check if the user exists and if the pass is correct
        return MessageType.FAILED_USER;
    }

    private void saveCredentials(String username, String password) {
        //TODO: Save credentials
    }

    //TODO: implement it
    private boolean isLogged()
    {
        return false;
    }

    private void startBoard() {
        Intent intent = new Intent(this, Board.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

        finish();
    }

    private enum MessageType {
        SUCCESS,
        FAILED_USER,
        FAILED_PASS
    }
}
