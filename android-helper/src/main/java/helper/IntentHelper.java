package helper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;

/**
 * Created by Bayan on 4/16/2015.
 */
public class IntentHelper {
    public static void startWebActivity(Context context, String url) {
        final Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        context.startActivity(intent);
    }

    public static void startEmailActivity(Context context, int toResId, int subjectResId, int bodyResId) {
        startEmailActivity(context, context.getString(toResId), context.getString(subjectResId),
                context.getString(bodyResId));
    }

    public static void startGalleryActivity(ActionBarActivity context, int requestCode) {
        Intent intent = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        context.startActivityForResult(
                Intent.createChooser(intent, "Select File"),
                requestCode);
    }

    public static void startEmailActivity(Context context, String to, String subject, String body) {
        final Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");

        if (!TextUtils.isEmpty(to)) {
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{to});
        }
        if (!TextUtils.isEmpty(subject)) {
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        }
        if (!TextUtils.isEmpty(body)) {
            intent.putExtra(Intent.EXTRA_TEXT, body);
        }

        final PackageManager pm = (PackageManager) context.getPackageManager();
        try {
            if (pm.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY).isEmpty()) {
                intent.setType("text/plain");
            }
        } catch (Exception e) {
//            Log.w("Exception encountered while looking for email intent receiver.", e);
        }

        context.startActivity(intent);
    }


    public static void startGooglePlayActivity(Context ctx){
        final String appPackageName = ctx.getPackageName();
        try {
            ctx.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (android.content.ActivityNotFoundException anfe) {
            ctx.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + appPackageName)));
        }
    }

    public static void shareToTwitter(Activity ctx, String text) {

        try {
            Intent shareIntent = ShareCompat.IntentBuilder.from((Activity) ctx)
                    .setText(text).setType("image/*").getIntent()
                    .setPackage("com.twitter.android");
            ctx.startActivity(shareIntent);
        } catch (Exception e) {
//            Toast.makeText(getActivity(), getString(R.string.twitter_not_exist), Toast.LENGTH_LONG).show();
        }

    }

    public static void shareToFacebook(Activity ctx, String text) {


        try {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, text);

//                    intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(NewsDetailsFragment.casted_image));
            intent.setPackage("com.facebook.katana");
            ctx.startActivity(intent);
        } catch (Exception e) {
//            Toast.makeText(getActivity(), getString(R.string.facebook_not_exist), Toast.LENGTH_LONG).show();
        }

    }

    public static void shareToGplus(Activity ctx, String text) {

        try {

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, text);
            intent.setPackage("com.google.android.apps.plus");
            ctx.startActivity(intent);
        } catch (Exception e) {
//            Toast.makeText(getActivity(), getString(R.string.google_not_exist), Toast.LENGTH_LONG).show();
        }

    }

    public static void mainShare(Context ctx, String text) {

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, text);
        sendIntent.setType("text/plain");
        ctx.startActivity(sendIntent);
    }
    public static void mainSharePicture(Context ctx, String text) {

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, text);
        sendIntent.setType("image/jpeg");
        ctx.startActivity(sendIntent);
    }


}
