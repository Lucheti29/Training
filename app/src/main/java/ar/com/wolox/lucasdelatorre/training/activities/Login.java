package ar.com.wolox.lucasdelatorre.training.activities;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import ar.com.wolox.lucasdelatorre.training.R;
import ar.com.wolox.lucasdelatorre.training.Utils;

/**
 * Created by lucasdelatorre on 14/04/15.
 */
public class Login extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView tv = (TextView) findViewById(R.id.tv_terms);
        tv.setText(Html.fromHtml(getString(R.string.login_terms)));
        tv.setMovementMethod(LinkMovementMethod.getInstance());

        Button clickLogin = (Button) findViewById(R.id.bn_login_login);
        Button clickSignup = (Button) findViewById(R.id.bn_login_signup);

        clickLogin.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                attemptToLogin();
            }
        });

        clickSignup.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openSignup();
            }
        });
    }

    private void attemptToLogin() {
        String username = ((EditText) findViewById(R.id.et_login_user)).getText().toString();
        String pass = ((EditText) findViewById(R.id.et_login_pass)).getText().toString();

        Resources res = this.getResources();

        if(username.isEmpty())
        {
            Utils.showToast(res.getString(R.string.login_emptyuser), getApplicationContext());
            return;
        }

        if(pass.isEmpty())
        {
            Utils.showToast(res.getString(R.string.login_emptypass), getApplicationContext());
            return;
        }

        if(!Utils.validate(username, Utils.EMAIL_PATTERN))
        {
            Utils.showToast(res.getString(R.string.login_invaliduser), getApplicationContext());
            return;
        }

        MessageType type = checkCredentials(username, pass);

        if(type == MessageType.SUCCESS)
        {
            saveCredentials(username, pass);

            //TODO: Implement it when Board.class exists
            //Intent intent = new Intent(this, Board.class);
            //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            //startActivity(intent);
        }
        else if(type == MessageType.FAILED_USER)
        {
            Utils.showToast(res.getString(R.string.login_wronguser), getApplicationContext());
        }
        else if(type == MessageType.FAILED_PASS)
        {
            Utils.showToast(res.getString(R.string.login_wrongpass), getApplicationContext());
        }
    }

    private void openSignup() {
        //TODO: Implement it when Signup.class exists
        //Intent intent = new Intent(this, Signup.class);
        //startActivity(intent);
    }

    private MessageType checkCredentials(String username, String password)
    {
        //TODO: Check if the user exists and if the pass is correct
        return MessageType.FAILED_USER;
    }

    private void saveCredentials(String username, String password)
    {
        //TODO: Save credentials
    }

    private enum MessageType {
        SUCCESS,
        FAILED_USER,
        FAILED_PASS
    }
}
