package helper;

import android.app.Activity;
import android.graphics.Typeface;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Bayan on 4/16/2015.
 */
public class FontHelper {
    public static final String FONT_NAME = "gd2";

    public static void setFont(Activity currentActivity , TextView view , String style){
        String fontname = FONT_NAME + style;
        Typeface font = Typeface.createFromAsset(currentActivity.getAssets(), fontname);
        view.setTypeface(font);
    }

    public static void setFont(Activity currentActivity , Button view, String style){
        String fontname = FONT_NAME + style;
        Typeface font = Typeface.createFromAsset(currentActivity.getAssets(), fontname);
        view.setTypeface(font);
    }

    public static void setFont(Activity currentActivity , EditText view, String style){
        String fontname = FONT_NAME + style;
        Typeface font = Typeface.createFromAsset(currentActivity.getAssets(), fontname);
        view.setTypeface(font);
    }


    public  interface FontStyle{
        String MEDIUM = "_meduim.otf";
        String LIGHT = "_light.ttf";
    }
}
