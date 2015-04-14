package ar.com.wolox.lucasdelatorre.training;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import ar.com.wolox.lucasdelatorre.training.activities.Login;


public class Starter extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_starter);

        if(!isLogged())
        {
            Intent intent = new Intent(this, Login.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }

        finish();
    }

    private boolean isLogged()
    {
        return false;
    }
}
