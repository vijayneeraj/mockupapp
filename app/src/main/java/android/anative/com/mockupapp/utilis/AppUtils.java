package android.anative.com.mockupapp.utilis;

/**
 * Created by Neeraj VijayVargiya on 10/11/17.
 */

public class AppUtils {
    public static boolean isValidString(String string) {
        if (string != null && !string.isEmpty()) {
            return true;
        }
        return false;
    }
}
