package helper;

import android.content.Context;
import android.content.Intent;

/**
 * Created by Bayan on 4/16/2015.
 */
public class ActivityHelper {

    public  static void goToActivity(Context ctx, Class<?> to , boolean isFinished) {

        android.content.Intent i = new android.content.Intent(ctx, to);
        i.addFlags(android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP );
        ctx.startActivity(i);
        if(isFinished)
            ((android.app.Activity) ctx).finish();

    }

    public  static void goToActivity(android.content.Context ctx, Class<?> to , boolean isFinished , android.os.Bundle bundle) {

        android.content.Intent i = new android.content.Intent(ctx, to);
        i.addFlags(android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP );
        i.putExtras(bundle);
        ctx.startActivity(i);
        if(isFinished)
            ((android.app.Activity) ctx).finish();

    }

    public  static void goToActivityFinishAllPreviousActivity(android.content.Context ctx, Class<?> to , boolean isFinished , android.os.Bundle bundle) {

        android.content.Intent i = new android.content.Intent(ctx, to);
        i.addFlags(android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP );
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.putExtras(bundle );
        ctx.startActivity(i);
        if(isFinished)
            ((android.app.Activity) ctx).finish();

    }

    public  static void goToActivityFinishAllPreviousActivity(android.content.Context ctx, Class<?> to , boolean isFinished) {

        android.content.Intent i = new android.content.Intent(ctx, to);
        i.addFlags(android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP );
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        ctx.startActivity(i);
        if(isFinished)
            ((android.app.Activity) ctx).finish();

    }


}
