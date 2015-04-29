package ar.com.wolox.lucasdelatorre.training;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public final static String CODE_INVALID_AUTH = "101";
    public final static String CODE_INVALID_TOKEN = "209";
    public final static String CODE_USER_TAKEN = "202";

    public final static String USER_KEY = "UserSaved";
    public final static String IMAGE_KEY = "ImageKey";

    public final static String ERROR = "Error";

    public static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static final int[] TAB_IMAGES = {
            R.drawable.icon_news,
            R.drawable.icon_profile
    };

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

    public static boolean isValidImageUrl(String url) {
        return (url != null && !url.isEmpty());
    }

    public static User getUserSaved(Activity activity) {
        SharedPreferences sharedPref = PreferenceManager
                .getDefaultSharedPreferences(activity);
        String jsonUser = sharedPref.getString(Utils.USER_KEY, "");

        Gson gson = new Gson();
        User user = gson.fromJson(jsonUser, User.class);

        return user;
    }

    public static boolean isAValidSession(User user) {
        return (user != null && user.getSessionToken() != null &&
                !user.getSessionToken().isEmpty());
    }

    public static String getDiffTimeInString(String time) {
        String diffTime;

        try {
            DateFormat df = new SimpleDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss.SSS'Z'", Locale.ENGLISH);
            Date result =  df.parse(time);

            long actual = System.currentTimeMillis();
            long timeNew = result.getTime();
            long diff = actual - timeNew;

            int diffInDays = (int) (diff / (1000 * 60 * 60 * 24));

            if (diffInDays > 1) {
                diffTime = diffInDays + "d";
                return diffTime;
            }

            long diffHours = diff / (60 * 60 * 1000);

            if (diffHours > 24) {
                diffTime = diffHours + "h";
                return diffTime;
            }

            long diffMinutes = diff / (60 * 1000) % 60;

            if ((diffHours == 24) && (diffMinutes >= 1)) {
                diffTime = diffMinutes + "m";
                return diffTime;
            }

            long diffSeconds = diff / 1000 % 60;
            diffTime = diffSeconds + "s";

        } catch (ParseException e) {
            diffTime = Utils.ERROR;
        }
        return diffTime;
    }
}
