package stavnoy.bluetoothdisabler;

import android.content.Context;
import android.content.SharedPreferences;
/**
 *Shared preference manager - for static synchronized methods only
**/
final class SharedPrefs {
    private SharedPrefs(){}

    private static final String PREFERENCES_KEY = "Bluetooth_Disabler";
    private static final String DISABLE_KEY = "disable";

    private static synchronized SharedPreferences getSharedPreferences(Context context){
        return context.getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE);
    }
    static synchronized boolean disable(Context context){
        return getSharedPreferences(context).getBoolean(DISABLE_KEY,false);
    }
    static synchronized void setDisable(Context context, boolean disable){
        getSharedPreferences(context).edit().putBoolean(DISABLE_KEY, disable).apply();
    }
}
