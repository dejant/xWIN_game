package com.bitdemic.dreigewinnt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // Programmstart
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Rotate the xLogo
        //
        rotateLogo();

    }

    public void pressStartButton(View view){
        Intent intent = new Intent(MainActivity.this, playActivity.class);
        startActivity(intent);
    }

    public void rotateLogo(){
        ImageView imageView = findViewById(R.id.xLogo);
        RotateAnimation xButton = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        xButton.setDuration(20000);
        xButton.setRepeatCount(Animation.INFINITE);
        imageView.startAnimation(xButton);
    }

}