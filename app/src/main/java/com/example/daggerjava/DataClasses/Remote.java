package com.example.daggerjava.DataClasses;

import android.util.Log;

import javax.inject.Inject;

public class Remote {

    private static final String TAG = "Remote";
    @Inject boolean statusOfConnection = false;

    @Inject
    public Remote() {
    }

    public boolean checkIfRemoteDisconnectedCarIsLocked(boolean isEnabled) {
        if (getConnectionStatus(isEnabled)) {
            Log.d(TAG, "Remote is Connected and Car is Locked");
            statusOfConnection = true;
            return statusOfConnection;
        } else {
            Log.d(TAG, "Remote is not Connected! Please connect the remote to lock/unlock the car");
            return statusOfConnection;
        }
    }

    @Inject
    public boolean getConnectionStatus(boolean isEnabled) {
        if (checkIfRemoteDisconnectedCarIsLocked(isEnabled)) {
            return statusOfConnection;
        }
        return statusOfConnection;
    }
}
