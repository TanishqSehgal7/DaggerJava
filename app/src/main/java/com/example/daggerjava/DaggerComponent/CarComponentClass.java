package com.example.daggerjava.DaggerComponent;

import com.example.daggerjava.DataClasses.Car;
import com.example.daggerjava.activities.MainActivity;
import dagger.Component;

@Component
public interface CarComponentClass {
    Car getCar();

    // this would inject all the fields of the class to be used
    void inject(MainActivity mainActivity);
}
