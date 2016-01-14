package helper;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;

/**
 * Created by Bayan on 4/29/2015.
 */
public class NetworkFailureHandler extends Handler {
    private FragmentManager manager;
    public NetworkFailureHandler(){

    }

    public NetworkFailureHandler(FragmentManager manager){
        this.manager = manager;
    }


    @Override
    public void handleMessage(Message msg) {
        // TODO Auto-generated method stub
        super.handleMessage(msg);
        printErrors(msg);
    }



    private void printErrors(Message msg){
        final int statusCode = msg.getData().getInt("STATUS_CODE");
        String error_response = msg.getData().getString("ERROR_MESSAGE");
        LoggerHelper.error("printErrors >> STATUS_CODE : " + statusCode);
        LoggerHelper.error("printErrors >> ERROR_RESPONSE : " + msg.getData().getString("ERROR_RESPONSE"));



    }
}
