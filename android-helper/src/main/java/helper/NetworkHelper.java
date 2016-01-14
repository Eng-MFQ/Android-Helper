package helper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Bayan on 4/16/2015.
 */
public class NetworkHelper {

    public static boolean isWiFiConnected(Context ctx) {
        ConnectivityManager manager = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        return netInfo.isConnected();
    }

    public  static boolean isNetworkAvailable(Context ctx) {
        ConnectivityManager connectivityManager  = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }

    public static int getConnectivityStatus(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null != activeNetwork) {

            return activeNetwork.getType();
        }
        return -1;
    }
}
