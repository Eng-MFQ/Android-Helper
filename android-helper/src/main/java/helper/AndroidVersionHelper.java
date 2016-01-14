package helper;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Build.VERSION_CODES;

/**
 * Created by Arafat on 2015-07-19.
 */
public class AndroidVersionHelper {

    private static final String VERSION_UNAVAILABLE = "N/A";

    private AndroidVersionHelper() {
    }

    public static boolean hasFroyo() {
        // Can use static final constants like FROYO, declared in later versions
        // of the OS since they are inlined at compile time. This is guaranteed behavior.
        return Build.VERSION.SDK_INT >= VERSION_CODES.FROYO;
    }

    public static boolean hasGingerbread() {
        return Build.VERSION.SDK_INT >= VERSION_CODES.GINGERBREAD;
    }

    public static boolean hasHoneycomb() {
        return Build.VERSION.SDK_INT >= VERSION_CODES.HONEYCOMB;
    }

    public static boolean hasHoneycombMR1() {
        return Build.VERSION.SDK_INT >= VERSION_CODES.HONEYCOMB_MR1;
    }

    public static boolean hasJellyBean() {
        return Build.VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN;
    }

    public static boolean hasICS() {
        return Build.VERSION.SDK_INT >= VERSION_CODES.ICE_CREAM_SANDWICH;
    }

    public static boolean hasKitKat() {
        return Build.VERSION.SDK_INT >= VERSION_CODES.KITKAT;
    }

    public static boolean hasLollipop() {
        return Build.VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP;
    }

    public static String getVersionName(Context context) {
        // Get app version
        PackageManager pm = context.getPackageManager();
        String packageName = context.getPackageName();
        String versionName;
        try {
            PackageInfo info = pm.getPackageInfo(packageName, 0);
            versionName = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            versionName = VERSION_UNAVAILABLE;
        }
        return versionName;
    }
}
