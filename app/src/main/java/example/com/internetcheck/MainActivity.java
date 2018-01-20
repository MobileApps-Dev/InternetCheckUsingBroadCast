package example.com.internetcheck;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView internetStatus;
    BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        internetStatus = (TextView) findViewById(R.id.internet_status);
        checkInternet();
    }

    private void checkInternet() {
        IntentFilter intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");

         broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int[] type = {ConnectivityManager.TYPE_WIFI, ConnectivityManager.TYPE_MOBILE};
                if (CheckConnectionBroadcast.isNetworkAvalbile(context, type)){
                    Toast.makeText(context, "Internet", Toast.LENGTH_SHORT).show();
                    internetStatus.setText("True");
                    return;
                }else{
                    Toast.makeText(context, "No Internet", Toast.LENGTH_SHORT).show();
                    internetStatus.setText("False");
                }

            }
        };
        registerReceiver(broadcastReceiver,intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }
}
