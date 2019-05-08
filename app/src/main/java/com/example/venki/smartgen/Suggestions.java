package com.example.venki.smartgen;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.Random;


public class Suggestions extends Activity {
    Random random;
    int nextRandom;
    TextView suggestion;
    ImageView img;
    RelativeLayout layout;
    ImageView nextBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suggest);
        layout=(RelativeLayout)findViewById(R.id.suggestionLayout);
        suggestion = (TextView) findViewById(R.id.suggest);
        img=(ImageView)findViewById(R.id.sunnyImage);
        nextBtn = (ImageView) findViewById(R.id.arrowbtn);
        random = new Random();
        nextRandom = random.nextInt(9 - 1) + 30;

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Suggestions.this,ApplianceControl.class);
                intent.putExtra("temperature",nextRandom);
                startActivity(intent);
            }
        });
    }
}
