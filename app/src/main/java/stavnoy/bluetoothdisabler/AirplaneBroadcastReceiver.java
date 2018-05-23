package stavnoy.bluetoothdisabler;

import android.app.Notification;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.provider.Settings;
import android.widget.Toast;

import static stavnoy.bluetoothdisabler.MainActivity.bluetoothAdapter;

public class AirplaneBroadcastReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(final Context context, Intent intent) {
        if (Intent.ACTION_AIRPLANE_MODE_CHANGED.equals(intent.getAction()))
            if (!isAirplaneEnabled(context) && SharedPrefs.disable(context)) {
                if (bluetoothAdapter != null) {
                    try {
                        Toast.makeText(context, "sleep start", Toast.LENGTH_LONG).show();//todo remove for product
                        Thread.sleep(1000); //so as not to attempt disabling too early todo change to multithreaded listener
                    } catch (InterruptedException e) {
                        Toast.makeText(context, "sleep Interrupted", Toast.LENGTH_LONG).show();//todo remove for production
                        e.printStackTrace();
                    }
                    Toast.makeText(context, "sleep end", Toast.LENGTH_LONG).show();//todo remove for product
                    /*/
                    secondReceiver();
                    //*/
                    if (bluetoothAdapter.isEnabled()) {
                        bluetoothAdapter.disable();
                        //notify user
                        Toast.makeText(context, context.getResources().getString(R.string.app_name) + " has disabled bluetooth", Toast.LENGTH_LONG).show();
                    }
                }
            }
    }

    private static void secondReceiver(Context context){
        IntentFilter intentFilter = new IntentFilter(BluetoothAdapter.ACTION_CONNECTION_STATE_CHANGED);
        context.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                bluetoothAdapter.disable();
                //notify user
                Toast.makeText(context, context.getResources().getString(R.string.app_name) + " has disabled bluetooth", Toast.LENGTH_LONG).show();
            }
        }, new IntentFilter(BluetoothAdapter.ACTION_CONNECTION_STATE_CHANGED));
    }

    private boolean isAirplaneEnabled(Context context){
        return Settings.Global.getInt(context.getContentResolver(),Settings.Global.AIRPLANE_MODE_ON,0) != 0;
    }
}
