package helper;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;


/**
 * Created by Arafat on 2015-06-25.
 */
public class UiHelper {

    private static final int RightToLeft = 1;
    private static final int LeftToRight = 2;
    private static final int DURATION = 30000;
    private static RectF mDisplayRect = new RectF();
    private static final Matrix mMatrix = new Matrix();
    private static int mDirection = RightToLeft;
    private static ValueAnimator mCurrentAnimator;
    private static float mScaleFactor;
//    protected static ImageView mBackground;



    public static int dpToPx(int dp, Context con) {
        return (int) (dp * con.getResources().getDisplayMetrics().density + 0.5f);
    }

    public static int pxToDp(int px, Context con) {
        return (int) (px / con.getResources().getDisplayMetrics().density + 0.5f);
    }

    public static boolean isOneEditTextsEmpty(EditText... editTexts) {
        for (int i = 0; i < editTexts.length; i++) {
            if (TextUtils.isEmpty(editTexts[i].getText()))
                return true;
        }
        return false;
    }


    public static void moveBackground(final ImageView mBackground) {
        if (AndroidVersionHelper.hasHoneycomb()) {
            mBackground.post(new Runnable() {
                @Override
                public void run() {
                    mScaleFactor = (float) mBackground.getHeight() / (float) mBackground.getDrawable().getIntrinsicHeight();
                    mMatrix.postScale(mScaleFactor, mScaleFactor);
                    mBackground.setImageMatrix(mMatrix);
                    animate(mBackground);
//                    mBackground.setVisibility(View.GONE);
                }
            });
        }
    }


    private static void animate(ImageView mBackground) {
        updateDisplayRect(mBackground);
        if (mDirection == RightToLeft) {
            animate(mDisplayRect.left, mDisplayRect.left - (mDisplayRect.right - mBackground.getWidth()), mBackground);
        } else {
            animate(mDisplayRect.left, 0.0f, mBackground);
        }
    }


    private static void updateDisplayRect(ImageView mBackground) {
        mDisplayRect.set(0, 0, mBackground.getDrawable().getIntrinsicWidth(), mBackground.getDrawable().getIntrinsicHeight());
        mMatrix.mapRect(mDisplayRect);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private static void animate(float from, float to, final ImageView mBackground) {
        mCurrentAnimator = ValueAnimator.ofFloat(from, to);
        mCurrentAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();

                mMatrix.reset();
                mMatrix.postScale(mScaleFactor, mScaleFactor);
                mMatrix.postTranslate(value, 0);

                mBackground.setImageMatrix(mMatrix);

            }
        });
        mCurrentAnimator.setDuration(DURATION);
        mCurrentAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                if (mDirection == RightToLeft)
                    mDirection = LeftToRight;
                else
                    mDirection = RightToLeft;

                animate(mBackground);
            }
        });
        mCurrentAnimator.start();
    }

    public static void fadeInView(View v, int duration){
        ObjectAnimator o = ObjectAnimator.ofFloat(v, "alpha", 0,1);
        o.setDuration(duration);
        o.start();
    }
    public static void fadeOutView(View v, int duration){
        ObjectAnimator o = ObjectAnimator.ofFloat(v, "alpha", 1,0);
        o.setDuration(duration);
        o.start();
    }


}
