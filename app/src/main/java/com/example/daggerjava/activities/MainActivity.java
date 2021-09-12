package com.example.daggerjava.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.daggerjava.DataClasses.Car;
import com.example.daggerjava.DaggerComponent.CarComponentClass;
import com.example.daggerjava.DataClasses.Remote;
import com.example.daggerjava.R;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

//    private Car car;

    @Inject
    Car car;

    @Inject
    Remote remote;

    TextView connectionStatusText;
    Button connectBtn;

    private boolean status = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Dependency injection
        CarComponentClass carComponentClass = DaggerCarComponentClass.create(); // dagger component (auto generated class)
//        carComponent.getCar(); used to inject a particular method using dagger
        // but if we want to inject all the field at once we use field injection method
        carComponentClass.inject(this); // injects all fields of the class needed into the activity
        // we cannot inject the constructor directly so we use the use defined method "inject" in the CarComponent


            connectBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!car.getRemoteConnectionStatus(status)) {
                        car.enableCarRemote(true);
                        Dialog connectingDialog = new Dialog(getApplicationContext());
                        connectingDialog.setContentView(R.layout.dialog_layout);
                        connectionStatusText.setText("Remote is Connected and Car is Locked");
                        status= true;

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                connectingDialog.dismiss();
                            }
                        }, 2000);

                    }  else if (car.getRemoteConnectionStatus(status)){
                    connectBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            car.enableCarRemote(false);
                            Dialog disConnectingDialog = new Dialog(getApplicationContext());
                            disConnectingDialog.setContentView(R.layout.dialog_layout);
                            TextView dialogTv = findViewById(R.id.dialogTv);
                            dialogTv.setText("DisConnecting Remote..Make sure you Lock the Car Manually!");
                            disConnectingDialog.show();
                            connectionStatusText.setText("Remote is Connected! \n Please make sure the Car is Locked");
                            status=false;

                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    disConnectingDialog.dismiss();
                                }
                            }, 2000);
                        }
                    });
                }
                }
            });
    }
}