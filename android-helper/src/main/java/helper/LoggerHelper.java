package helper;

import android.util.Log;

/**
 * Created by Bayan on 4/16/2015.
 */
public class LoggerHelper {
    public static final boolean DEBUG_EANBLED = true;
    public static final String TAG = "Logger";

    public static void info(String msg){
        if(DEBUG_EANBLED){
            Log.i(TAG, msg);
        }
    }

    public static void error(String msg){
        if(DEBUG_EANBLED){
            Log.e(TAG, msg);
        }
    }

    public static void verbose(String msg){
        if(DEBUG_EANBLED){
            Log.v(TAG, msg);
        }
    }

    public static void debug(String msg){
        if(DEBUG_EANBLED){
            Log.d(TAG, msg);
        }
    }

    public static void warning(String msg){
        if(DEBUG_EANBLED){
            Log.w(TAG, msg);
        }
    }
}
