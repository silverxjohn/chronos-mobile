package com.platacode.chronos;

import android.nfc.cardemulation.HostApduService;
import android.os.Bundle;
import android.util.Log;

import com.platacode.chronos.Utils.Mac;

public class NfcHostService extends HostApduService {

    private int messageCounter = 0;

    @Override
    public byte[] processCommandApdu(byte[] commandApdu, Bundle extras) {
        if (selectAidApdu(commandApdu)) {
            messageCounter = 0;
            Log.i("HCEDEMO", "Application selected");
            return getMacAddress();
        }
        else {
            Log.i("CHRONOS", "Received: " + new String(commandApdu));
            return getNextMessage();
        }
    }

    private byte[] getMacAddress() {
        return Mac.get().getBytes();
    }

    private byte[] getNextMessage() {
        messageCounter++;
        return ("CHRONOS." + messageCounter + "." + Mac.get()).getBytes();
    }

    private boolean selectAidApdu(byte[] apdu) {
        return apdu.length >= 2 && apdu[0] == (byte)0 && apdu[1] == (byte)0xa4;
    }

    @Override
    public void onDeactivated(int reason) {
        Log.i("CHRONOS", "Deactivated: " + reason);
    }
}
