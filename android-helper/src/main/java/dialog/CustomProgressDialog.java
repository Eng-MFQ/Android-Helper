package dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.mfq.byn.androidhelper.R;


/**
 * Created by Bayan on 4/16/2015.
 */
public class CustomProgressDialog extends Dialog {

    //TODO make this dialog more usable
    private ProgressBar iv;

    public CustomProgressDialog(Context context) {
        super(context, R.style.TransparentProgressDialog);
        prepareDialog(context);
    }

    private void prepareDialog(Context context) {


        WindowManager.LayoutParams wlmp = getWindow().getAttributes();
        wlmp.gravity = Gravity.CENTER_HORIZONTAL;
        getWindow().setAttributes(wlmp);
        setTitle(null);
        setCancelable(false);
        setOnCancelListener(null);
        setCanceledOnTouchOutside(false);
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        View v = LayoutInflater.from(context).inflate(R.layout.progress_dialog, layout);
        iv = (ProgressBar) v.findViewById(R.id.progressBarCircularIndeterminate);

        setContentView(v);

    }

    @Override
    public void show() {
        super.show();
        iv.setVisibility(View.VISIBLE);
    }


}
