package helper;

import android.content.Context;
import android.content.res.Resources;

import java.util.Locale;

/**
 * Created by Bayan on 4/16/2015.
 */
public class LocaleHelper {

    public static void chanegeLocale(String loc , Context ctx){
        Resources res = ctx.getResources();
        android.content.res.Configuration conf = res.getConfiguration();
        conf.locale = new Locale(loc);

        res.updateConfiguration(conf , null);
//        SharedPreferencesHelper.putSharedPreferencesString(ctx, SharedPreferencesKeys.APP_LANGUAGE , loc);
    }
}
