package helper;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;


/**
 * Created by Arafat on 2015-07-19.
 */
public class MovableBackgroundActivity extends AppCompatActivity {

    private static final int RightToLeft = 1;
    private static final int LeftToRight = 2;
    private static int DURATION = 4000;
    private RectF mDisplayRect = new RectF();
    private final Matrix mMatrix = new Matrix();
    private int mDirection = RightToLeft;
    private ValueAnimator mCurrentAnimator;
    private float mScaleFactor;
    protected ImageView mBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(0, 0);
        super.onCreate(savedInstanceState);

    }

    /**
     * to move the backgroun image
     *
     * @param imageView you want it to move
     * @param infinity  if you want to move the img for infinity set it true
     */
    protected void moveBackground(ImageView imageView, final boolean infinity, int duration) {
        initilize(imageView, duration);
        if (AndroidVersionHelper.hasHoneycomb()) {
            mBackground.post(new Runnable() {
                @Override
                public void run() {
                    mScaleFactor = (float) mBackground.getHeight() / (float) mBackground.getDrawable().getIntrinsicHeight();
                    mMatrix.postScale(mScaleFactor, mScaleFactor);
                    mBackground.setImageMatrix(mMatrix);
                    animate();
                    if (!infinity)
                        stopMoving();

                }
            });
        }
    }

    private void initilize(ImageView imageView, int duration) {
        imageView.setVisibility(View.VISIBLE);
        mBackground = imageView;
        DURATION = duration;

    }

    private void stopMoving() {
        mBackground.postDelayed(new Runnable() {
            @Override
            public void run() {
                ObjectAnimator o = ObjectAnimator.ofFloat(mBackground, "alpha", 1, 0);
                o.setDuration(700);
                o.start();
                mBackground.setClickable(false);
            }
        }, DURATION - 500);

        mBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == mBackground.getId()) {
                    ObjectAnimator o = ObjectAnimator.ofFloat(mBackground, "alpha", 1, 0);
                    o.setDuration(800);
                    o.start();
                    v.setClickable(false);
                    mBackground.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mBackground.setVisibility(View.GONE);
                        }
                    }, 800);

                }
            }
        });
    }


    private void animate() {
        updateDisplayRect();
        if (mDirection == RightToLeft) {
            animate(mDisplayRect.left, mDisplayRect.left - (mDisplayRect.right - mBackground.getWidth()));
        } else {
            animate(mDisplayRect.left, 0.0f);
        }
    }


    private void updateDisplayRect() {
        mDisplayRect.set(0, 0, mBackground.getDrawable().getIntrinsicWidth(), mBackground.getDrawable().getIntrinsicHeight());
        mMatrix.mapRect(mDisplayRect);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void animate(float from, float to) {
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

                animate();
            }
        });
        mCurrentAnimator.start();
    }


}
