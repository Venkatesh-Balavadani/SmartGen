package com.example.venki.smartgen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = (TextView)findViewById(R.id.textView2);
        tv.setText("This is the smart generator application. " +
                "This application is used control the power from the UPS to various appliances. " +
                "To continue enjoying this application " +
                "Connect with the Bluetooth Module");
        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, Second.class);
                startActivity(in);
            }
        });

    }
}
