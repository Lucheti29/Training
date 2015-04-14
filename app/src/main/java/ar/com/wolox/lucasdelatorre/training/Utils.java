package ar.com.wolox.lucasdelatorre.training;

import android.content.Context;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lucasdelatorre on 14/04/15.
 */
public class Utils {

    public static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static boolean validate(final String hex, final String stringPattern) {

        Pattern pattern = Pattern.compile(stringPattern);
        Matcher matcher;

        matcher = pattern.matcher(hex);
        return matcher.matches();
    }

    public static void showToast(CharSequence text, Context context)
    {
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
