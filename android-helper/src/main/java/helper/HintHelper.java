package helper;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Arafat on 2015-06-23.
 */
public class HintHelper {


    public static void toastCreator(Context c, String text) {
        Toast.makeText(c, text, Toast.LENGTH_SHORT).show();
    }
}
