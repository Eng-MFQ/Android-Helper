package connection;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import dialog.CustomProgressDialog;
import helper.LoggerHelper;
import helper.SharedPreferencesHelper;
import helper.SharedPreferencesKeys;
import helper.StringHelper;


/**
 * Created by Bayan on 4/16/2015.
 */
public class ConnectionManager {


    private static int SERVICE_TIME_OUT = (int) TimeUnit.MINUTES.toMillis(1);

    CustomProgressDialog pg;
    String content = "";

    private void invokeMethodPost(final Context ctx, String methodName, JSONObject request, final Handler successHandler, final Handler failurHandler, final int mId) throws JSONException, UnsupportedEncodingException {

        if (ctx != null) {
            pg = new CustomProgressDialog(ctx);
        }

        final AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Content-Type", "application/json; charset=utf-8");
        client.addHeader("Accept-Charset", "utf-8");

        client.setTimeout(ConnectionManager.SERVICE_TIME_OUT);
        StringEntity entity;
        if (request == null) {
            entity = null;
        } else {
            // Note : It is important to convert the entity to UTF-8
            entity = new StringEntity(request.toString(), "utf-8");
        }

        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json; charset=utf-8");
        LoggerHelper.error("Encoding : " + entity.getContentEncoding().getValue());

        PersistentCookieStore myCookieStore = new PersistentCookieStore(ctx);
// clear cookie to make the fresh cookie, to ensure the newest cookie is being send
        myCookieStore.clear();
// set the new cookie
        client.setCookieStore(myCookieStore);


        client.post(ctx, methodName, entity, "application/json; charset=utf-8", new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                super.onStart();
                if (ctx != null) {
                    pg.show();
                }
            }

            @Override
            public void onFinish() {
                super.onFinish();
                if (ctx != null) {
                    pg.dismiss();
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] data) {

//                PersistentCookieStore myCookieStore = new PersistentCookieStore(MyApplication.GetAppInstance());
                PersistentCookieStore myCookieStore = new PersistentCookieStore(ctx);

                client.setCookieStore(myCookieStore);
                List<Cookie> cookies = myCookieStore.getCookies();
                String phpSession = "";
                for (int i = 0; i < cookies.size(); i++) {
                    phpSession = cookies.get(i).getName() + "=" + cookies.get(i).getValue() + ";";
                }

                LoggerHelper.info("**** PHP SESSION  : " + phpSession);

                for (int i = 0; i < headers.length; i++) {
                    LoggerHelper.debug("header [" + i + "] : " + headers[i].getName() + " : " + headers[i].getValue());
                    if (headers[i].getName().equals("Set-Cookie")) {
                        SharedPreferencesHelper.putSharedPreferencesString(ctx, SharedPreferencesKeys.COOKIES_HEADER, headers[i].getValue());
                    }
                }
//


                content = new String(data);
                LoggerHelper.error("onSuccess : " + content);
                Message mMsg = successHandler.obtainMessage(); // Returns a new Message from the global message pool
                mMsg.what = mId;
                Bundle bundle = new Bundle();
                bundle.putString("DATA", content);

                mMsg.setData(bundle);
                successHandler.sendMessage(mMsg);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                LoggerHelper.error("onFailure ");

                LoggerHelper.error("status code is : " + statusCode);
                LoggerHelper.error("status code is : " + StringHelper.unescapeString(new String(errorResponse)));

                Message mMsg = failurHandler.obtainMessage();
                mMsg.what = mId;
                Bundle bundle = new Bundle();
                bundle.putInt("STATUS_CODE", statusCode);
                bundle.putString("ERROR_MESSAGE", StringHelper.unescapeString(new String(errorResponse)));
                mMsg.setData(bundle);
                failurHandler.sendMessage(mMsg);
                LoggerHelper.debug("----------------------------------------------------");
            }


        });

    }

    private void invokeMethodPost(final Context ctx, String methodName, RequestParams request, final Handler successHandler, final Handler failurHandler, final int mId) throws JSONException, UnsupportedEncodingException {

        if (ctx != null) {
            pg = new CustomProgressDialog(ctx);
        }

        final AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Content-Type", "application/json; charset=utf-8");
        client.addHeader("Accept-Charset", "utf-8");

        client.setTimeout(ConnectionManager.SERVICE_TIME_OUT);
        StringEntity entity;
        if (request == null) {
            entity = null;
        } else {
            // Note : It is important to convert the entity to UTF-8
            entity = new StringEntity(request.toString(), "utf-8");
        }

        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json; charset=utf-8");
        LoggerHelper.error("Encoding : " + entity.getContentEncoding().getValue());

        PersistentCookieStore myCookieStore = new PersistentCookieStore(ctx);
// clear cookie to make the fresh cookie, to ensure the newest cookie is being send
        myCookieStore.clear();
// set the new cookie
        client.setCookieStore(myCookieStore);


        client.post(ctx, methodName, entity, "application/json; charset=utf-8", new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                super.onStart();
                if (ctx != null) {
                    pg.show();
                }
            }

            @Override
            public void onFinish() {
                super.onFinish();
                if (ctx != null) {
                    pg.dismiss();
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] data) {

//                PersistentCookieStore myCookieStore = new PersistentCookieStore(MyApplication.GetAppInstance());
                PersistentCookieStore myCookieStore = new PersistentCookieStore(ctx);

                client.setCookieStore(myCookieStore);
                List<Cookie> cookies = myCookieStore.getCookies();
                String phpSession = "";
                for (int i = 0; i < cookies.size(); i++) {
                    phpSession = cookies.get(i).getName() + "=" + cookies.get(i).getValue() + ";";
                }

                LoggerHelper.info("**** PHP SESSION  : " + phpSession);

                for (int i = 0; i < headers.length; i++) {
                    LoggerHelper.debug("header [" + i + "] : " + headers[i].getName() + " : " + headers[i].getValue());
                    if (headers[i].getName().equals("Set-Cookie")) {
                        SharedPreferencesHelper.putSharedPreferencesString(ctx, SharedPreferencesKeys.COOKIES_HEADER, headers[i].getValue());
                    }
                }
//


                content = new String(data);
                LoggerHelper.error("onSuccess : " + content);
                Message mMsg = successHandler.obtainMessage(); // Returns a new Message from the global message pool
                mMsg.what = mId;
                Bundle bundle = new Bundle();
                bundle.putString("DATA", content);

                mMsg.setData(bundle);
                successHandler.sendMessage(mMsg);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                LoggerHelper.error("onFailure ");

                LoggerHelper.error("status code is : " + statusCode);
                LoggerHelper.error("status code is : " + StringHelper.unescapeString(new String(errorResponse)));

                Message mMsg = failurHandler.obtainMessage();
                mMsg.what = mId;
                Bundle bundle = new Bundle();
                bundle.putInt("STATUS_CODE", statusCode);
                bundle.putString("ERROR_MESSAGE", StringHelper.unescapeString(new String(errorResponse)));
                mMsg.setData(bundle);
                failurHandler.sendMessage(mMsg);
                LoggerHelper.debug("----------------------------------------------------");
            }


        });

    }


    private void invokeGetMethod(final Context ctx, String methodName, final Handler successHandler, final Handler failurHandler, final int mId) {
        if (ctx != null) {
            pg = new CustomProgressDialog(ctx);
        }


        AsyncHttpClient client = new AsyncHttpClient();


        client.addHeader("Content-Type", "application/json; charset=utf-8");
        client.setTimeout(SERVICE_TIME_OUT);
        client.get(methodName, new AsyncHttpResponseHandler() {

            @Override
            public void onFinish() {
                super.onFinish();
                if (ctx != null) {
                    try {
                        pg.dismiss();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onStart() {
                super.onStart();
                if (ctx != null) {
                    pg.show();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                LoggerHelper.error("STATUS_CODE : " + statusCode);
                LoggerHelper.error("getMessage : " + e.getMessage());

                try {
                    Message mMsg = failurHandler.obtainMessage();
                    mMsg.what = mId;
                    Bundle bundle = new Bundle();
                    bundle.putInt("STATUS_CODE", statusCode);
                    bundle.putString("ERROR_MESSAGE", StringHelper.unescapeString(new String(errorResponse)));
                    mMsg.setData(bundle);
                    failurHandler.sendMessage(mMsg);
                } catch (Exception e1) {

                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] data) {
                content = new String(data);
                LoggerHelper.debug(content);
                try {
                    Message mMsg = successHandler.obtainMessage(); // Returns a new Message from the global message pool
                    mMsg.what = mId;
                    Bundle bundle = new Bundle();
                    bundle.putString("DATA", content);
                    mMsg.setData(bundle);
                    successHandler.sendMessage(mMsg);
                } catch (Exception e1) {

                }
            }

        });
    }

    private void invokePutMethod(final Context ctx, String methodName, JSONObject request, final Handler successHandler, final Handler failurHandler, final int mId) throws Exception {
        if (ctx != null) {
            pg = new CustomProgressDialog(ctx);
        }

        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Content-Type", "application/json");


        PersistentCookieStore myCookieStore = new PersistentCookieStore(ctx);
//        client.setCookieStore(myCookieStore);
        List<Cookie> cookies = myCookieStore.getCookies();
        String phpSession = "";
        for (int i = 0; i < cookies.size(); i++) {
            phpSession = cookies.get(i).getName() + "=" + cookies.get(i).getValue() + ";";
        }
//        client.setCookieStore(myCookieStore);

//        LoggerHelper.error("URL : " + methodName);
//        LoggerHelper.error("X-CSRF-Token ; " + model.getToken());
//        LoggerHelper.error("Cookie ; " + model.getSessionCookie());
//        LoggerHelper.info("PHP SESSION  : "+phpSession);
//
//        client.addHeader("X-CSRF-Token", model.getToken());
        client.addHeader("Cookie", phpSession);
        client.setTimeout(SERVICE_TIME_OUT);

        StringEntity entity;
        if (request == null) {
            entity = null;
        } else {
            // Note : It is important to convert the entity to UTF-8
            entity = new StringEntity(request.toString(), "utf-8");
        }

        client.put(ctx, methodName, entity, "application/json", new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                if (ctx != null) {
                    pg.show();
                }
            }

            @Override
            public void onFinish() {
                super.onFinish();
                if (ctx != null) {
                    pg.dismiss();
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] data) {
                content = new String(data);
                LoggerHelper.error("onSuccess : " + content);
                Message mMsg = successHandler.obtainMessage(); // Returns a new Message from the global message pool
                mMsg.what = mId;
                Bundle bundle = new Bundle();
                bundle.putString("DATA", content);
                mMsg.setData(bundle);
                successHandler.sendMessage(mMsg);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                LoggerHelper.error("onFailure ");
                LoggerHelper.error("status code is : " + statusCode);
                LoggerHelper.error("status code is : " + StringHelper.unescapeString(new String(errorResponse)));


                Message mMsg = failurHandler.obtainMessage();
                mMsg.what = mId;
                Bundle bundle = new Bundle();
                bundle.putInt("STATUS_CODE", statusCode);
                bundle.putString("ERROR_MESSAGE", StringHelper.unescapeString(new String(errorResponse)));
                mMsg.setData(bundle);
                failurHandler.sendMessage(mMsg);

            }
        });


    }


    public void postData(Context ctx, String url, JSONObject request, final int id, final Handler successHandler, final Handler failurHandler) {
        try {
            LoggerHelper.debug("Request : " + request.toString());

            try {
                invokeMethodPost(ctx, url, request, new Handler() { //success
                    @Override
                    public void handleMessage(Message msg) {
                        prepareMessage(successHandler, id, msg);
                    }
                }, new Handler() {// Failure
                    @Override
                    public void handleMessage(Message msg) {
                        prepareMessage(failurHandler, id, msg);
                    }
                }, 1);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public void postData(Context ctx, String url, RequestParams request, final int id, final Handler successHandler, final Handler failurHandler) {
        try {
            LoggerHelper.debug("Request : " + request.toString());

            try {
                invokeMethodPost(ctx, url, request, new Handler() { //success
                    @Override
                    public void handleMessage(Message msg) {
                        prepareMessage(successHandler, id, msg);
                    }
                }, new Handler() {// Failure
                    @Override
                    public void handleMessage(Message msg) {
                        prepareMessage(failurHandler, id, msg);
                    }
                }, 1);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    public void putData(Context ctx, String url, JSONObject request, final int id, final Handler successHandler, final Handler failurHandler) throws Exception {
        try {
            LoggerHelper.debug("Request : " + request.toString());

            try {
                invokePutMethod(ctx, url, request, new Handler() { //success
                    @Override
                    public void handleMessage(Message msg) {
                        prepareMessage(successHandler, id, msg);
                    }
                }, new Handler() {// Failure
                    @Override
                    public void handleMessage(Message msg) {
                        prepareMessage(failurHandler, id, msg);
                    }
                }, 1);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    /**
     * this method is to dend get request to a url and
     *
     * @param ctx
     * @param url
     * @param id
     * @param successHandler
     * @param failurHandler
     */
    public void getData(Context ctx, String url, final int id, final Handler successHandler, final Handler failurHandler) {


        invokeGetMethod(ctx, url, new Handler() { //success
            @Override
            public void handleMessage(Message msg) {
                prepareMessage(successHandler, id, msg);
            }
        }, new Handler() {// Failure
            @Override
            public void handleMessage(Message msg) {
                prepareMessage(failurHandler, id, msg);
            }
        }, 1);
    }

    private void prepareMessage(Handler hd, int what, Message msg) {
        Bundle bundle = msg.getData();
        Message mMsg = hd.obtainMessage();
        mMsg.what = what;
        mMsg.setData(bundle);
        hd.sendMessage(mMsg);
    }


    public void postData(Context ctx, String url, boolean isHeaderRequired, JSONObject request, final int id, final Handler successHandler, final Handler failurHandler) {
        try {
            LoggerHelper.debug("Request : " + request.toString());

            try {
                invokeMethodPost(ctx, url, isHeaderRequired, request, new Handler() { //success
                    @Override
                    public void handleMessage(Message msg) {
                        prepareMessage(successHandler, id, msg);
                    }
                }, new Handler() {// Failure
                    @Override
                    public void handleMessage(Message msg) {
                        prepareMessage(failurHandler, id, msg);
                    }
                }, 1);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    private void invokeMethodPost(final Context ctx, String methodName, boolean isHeaderRequired, JSONObject request, final Handler successHandler, final Handler failurHandler, final int mId) throws JSONException, UnsupportedEncodingException {

        if (ctx != null) {
            pg = new CustomProgressDialog(ctx);
        }

        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Content-Type", "application/json");
        client.setTimeout(SERVICE_TIME_OUT);

//        PersistentCookieStore myCookieStore = new PersistentCookieStore(MyApplication.GetAppInstance());
        PersistentCookieStore myCookieStore = new PersistentCookieStore(ctx);
        client.setCookieStore(myCookieStore);
        List<Cookie> cookies = myCookieStore.getCookies();
        String phpSession = "";
        for (int i = 0; i < cookies.size(); i++) {
            phpSession = cookies.get(i).getName() + "=" + cookies.get(i).getValue() + ";";
        }

        LoggerHelper.info("PHP SESSION  : " + phpSession);

        if (isHeaderRequired) {
//            LoginModel model = null;
//            try {
//                model = BusinessManagers.getInstance().getLoginManager().getAll().get(0);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            client.addHeader("X-CSRF-Token",model.getToken() );
            client.addHeader("Cookie", phpSession);
        }


        StringEntity entity;


        if (request == null) {
            entity = null;
        } else {
            // Note : It is important to convert the entity to UTF-8
            entity = new StringEntity(request.toString(), "utf-8");
        }


//        entity.setContentEncoding("UTF-8");
//        entity.setContentType("application/json; charset=utf-8");
//        LoggerHelper.error("Encoding : " + entity.getContentEncoding().getValue());

        client.post(ctx, methodName, entity, "application/json", new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                super.onStart();
                if (ctx != null) {
                    pg.show();
                }
            }

            @Override
            public void onFinish() {
                super.onFinish();
                if (ctx != null) {
                    pg.dismiss();
                }
            }


            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] data) {
                content = new String(data);
                LoggerHelper.error("onSuccess : " + content);
                Message mMsg = successHandler.obtainMessage(); // Returns a new Message from the global message pool
                mMsg.what = mId;
                Bundle bundle = new Bundle();
                bundle.putString("DATA", content);

                mMsg.setData(bundle);
                successHandler.sendMessage(mMsg);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                LoggerHelper.error("onFailure ");

                LoggerHelper.error("status code is : " + statusCode);
                LoggerHelper.error("status code is : " + StringHelper.unescapeString(new String(errorResponse)));

                Message mMsg = failurHandler.obtainMessage();
                mMsg.what = mId;
                Bundle bundle = new Bundle();
                bundle.putInt("STATUS_CODE", statusCode);
                bundle.putString("ERROR_MESSAGE", StringHelper.unescapeString(new String(errorResponse)));
                mMsg.setData(bundle);
                failurHandler.sendMessage(mMsg);
                LoggerHelper.debug("----------------------------------------------------");
            }


        });

    }


    private void checkCookies(CookieStore myCookieStore, AsyncHttpClient client) {
        List<Cookie> cookies = myCookieStore.getCookies();
        String phpSession = "";

        if (cookies.isEmpty()) {
            Log.i("TAG", "None");
        } else {
            for (int i = 0; i < cookies.size(); i++) {
                phpSession = cookies.get(i).getName() + "=" + cookies.get(i).getValue() + ";";

            }
            LoggerHelper.debug(" >>>>>  [session] : " + phpSession);
        }

    }

}
