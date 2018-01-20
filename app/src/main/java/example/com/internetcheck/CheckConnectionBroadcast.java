package example.com.internetcheck;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * Created by User on 20-Jan-18.
 */

public class CheckConnectionBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        int[] type = {ConnectivityManager.TYPE_MOBILE, ConnectivityManager.TYPE_WIFI};

        if (isNetworkAvalbile(context, type) == true) {
            return;
        } else {
            Toast.makeText(context, "No connection", Toast.LENGTH_SHORT).show();
        }
    }

    public static boolean isNetworkAvalbile(Context context, int[] type) {

        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            for (int networkType : type) {
                NetworkInfo networkInfo = connectivityManager.getNetworkInfo(networkType);
                if (networkInfo != null && networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return false;
    }
}
