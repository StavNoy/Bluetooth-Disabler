package stavnoy.bluetoothdisabler;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    static final BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((ToggleButton) findViewById(R.id.appToggler)).setChecked(SharedPrefs.disable(MainActivity.this));
        ((ToggleButton) findViewById(R.id.bluetoothToggler)).setChecked(bluetoothAdapter.isEnabled());
    }

    public void toggleClick(View view) {
        SharedPrefs.setDisable(view.getContext(),((ToggleButton) view).isChecked());
    }

    public void toggleBluetooth(View view) {
        // TODO: 23-5-18 sync with system
        if (((ToggleButton)view).isChecked()) {
            bluetoothAdapter.enable();
        } else {
            bluetoothAdapter.disable();
        }
    }
}
