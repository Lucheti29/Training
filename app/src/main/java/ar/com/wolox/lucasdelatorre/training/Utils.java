package ar.com.wolox.lucasdelatorre.training;

import android.app.Activity;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static String URL = "https://api.parse.com";
    public static String PARSE_APP_HEADER = "X-Parse-Application-Id";
    public static String PARSE_APP_ID = "kLRpYZ9OqIMFJ8zDyrqFlJhWEAabsO20bfZfBXgT";
    public static String PARSE_REST_API_HEADER = "X-Parse-REST-API-Key";
    public static String PARSE_REST_API_KEY = "c2Vi5MRQx5WxhppghBZy4KYIGjQ6U0CeLAY6UHXO";
    public static String PARSE_TOKEN_HEADER = "X-Parse-Session-Token";

    public static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static boolean validate(final String hex, final String stringPattern) {
        Pattern pattern = Pattern.compile(stringPattern);
        Matcher matcher = pattern.matcher(hex);
        return matcher.matches();
    }

    public static void showToast(Activity activity, int id) {
        Toast.makeText(activity.getApplicationContext(),
                activity.getResources().getString(id),
                Toast.LENGTH_SHORT).show();
    }
}
