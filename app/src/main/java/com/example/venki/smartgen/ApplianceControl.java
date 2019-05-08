package com.example.venki.smartgen;

import android.app.Activity;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;

public class ApplianceControl extends Activity {
    Button on, off,onL,offL,onac,offac;
    String address = null;
    int i;
    TextView tempStatus, batterystatus,sugest;
    private boolean isBtConnected = false;
    private ProgressDialog progress;
    BluetoothAdapter myBluetooth = null;
    BluetoothSocket btSocket = null;
    Random random;
    int nextRandom;
    int tongue = 0x1F61C;
    int charge=0x1F50C;
    int music =  0x1F3B6;
    int ntht= 0x1F605;

    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent newint = getIntent();
        address = newint.getStringExtra(Second.EXTRA_ADDRESS);
        random = new Random();
        nextRandom = random.nextInt(9 - 1) + 30;
        setContentView(R.layout.appliance);
        sugest=(TextView)findViewById(R.id.suggestion);
        on = (Button) findViewById(R.id.button2);
        onL=(Button)findViewById(R.id.lightOn);
        offL=(Button)findViewById(R.id.button5);
        onac=(Button)findViewById(R.id.acOn);
        offac=(Button)findViewById(R.id.button7);
        off = (Button) findViewById(R.id.button3);
        batterystatus = (TextView) findViewById(R.id.batteryLevel);
        tempStatus = (TextView) findViewById(R.id.temperature);

        tempStatus.setText(Integer.toString(nextRandom));
        batterystatus.setText(Integer.toString(0));
        try {
            BluetoothAdapter myBluetooth = BluetoothAdapter.getDefaultAdapter();//get the mobile bluetooth device
            BluetoothDevice dispositivo = myBluetooth.getRemoteDevice(address);//connects to the device's address and checks if it's available
            btSocket = dispositivo.createInsecureRfcommSocketToServiceRecord(myUUID);
            btSocket.connect();
        }
        catch (IOException e){

        }
        on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ledOn();
            }
        });
        off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ledOff();
            }
        });
        onL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lighton();
            }
        });
        offL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lightoff();
            }
        });
        onac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                acOn();
            }
        });
        offac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                acoff();
            }
        });

        if (nextRandom > 33) {
            sugest.setText("After finding the weather conditions in your location, for optimum usage of inverter power, we suggest"+ "Brace yourself. It's toooo hot. Cool yourself by turning on the air-cooler, for a short span ."+getEmojiByUnicode(tongue)+ "Power up all your gadgets!!!"+getEmojiByUnicode(charge));
        } else {
            sugest.setText("After finding the weather conditions in your location, for optimum usage of inverter power, we suggest"+ "Not sooo Hot!!!!"+getEmojiByUnicode(ntht)+"Enjoy your day by watching television"+ "Enjoy music with the music system"+getEmojiByUnicode(music));
        }
    }

    private void ledOff() {
        if (btSocket != null) {
            try {
                btSocket.getOutputStream().write("0".toString().getBytes());
            } catch (IOException e) {

            }
        }
    }

    private void ledOn() {
        if (btSocket != null) {
            try {
                btSocket.getOutputStream().write("1".toString().getBytes());
            } catch (IOException e) {

            }
        }
    }
    private void lighton() {
        if (btSocket != null) {
            if (btSocket != null) {
                try {
                    btSocket.getOutputStream().write("3".toString().getBytes());
                } catch (IOException e) {

                }
            }
        }
    }
    private void lightoff() {
        if (btSocket != null) {
            try {
                btSocket.getOutputStream().write("2".toString().getBytes());
            } catch (IOException e) {

            }
        }
    }
    private void acOn() {
        if (btSocket != null) {
            try {
                btSocket.getOutputStream().write("5".toString().getBytes());
            } catch (IOException e) {

            }
        }
    }
    private void acoff() {
        if (btSocket != null) {
            try {
                btSocket.getOutputStream().write("4".toString().getBytes());
            } catch (IOException e) {

            }
        }
    }
    public String getEmojiByUnicode(int unicode){
        return new String(Character.toChars(unicode));
    }
    private void msg(String str) {
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();
    }
}


