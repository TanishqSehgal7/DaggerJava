package com.example.daggerjava.DataClasses;

import android.util.Log;

import com.example.daggerjava.DataClasses.Engine;
import com.example.daggerjava.DataClasses.Wheels;

import javax.inject.Inject;


public class Car {
    private static final String TAG = "Car";

    /*
    Dagger follows the following sequence of injection :
    1. Constructor -> 2. Field -> 3. Method
     */

    @Inject Engine engine;
    @Inject Wheels wheels;
    @Inject Remote remote;


    @Inject    // this is constructor injection (lets dagger know about how to instanciate the objects of a class
    public Car(Engine engine, Wheels wheels) {
        this.engine = engine;
        this.wheels = wheels;
    }

    public void driveCar() {
        Log.d(TAG, "Driving the car...");
    }

    @Inject
    public void enableCarRemote(boolean isEnabled) {
        remote.checkIfRemoteDisconnectedCarIsLocked(isEnabled);
    }

    @Inject
    public boolean getRemoteConnectionStatus (boolean status) {
     return remote.getConnectionStatus(status);
    }
}
