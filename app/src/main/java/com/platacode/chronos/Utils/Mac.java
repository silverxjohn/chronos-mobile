package com.platacode.chronos.Utils;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;

import com.platacode.chronos.App;

public class Mac {
    public static String get() {
        WifiManager telephony = (WifiManager) App.getContext().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = telephony.getConnectionInfo();


        return info.getMacAddress();
    }
}
