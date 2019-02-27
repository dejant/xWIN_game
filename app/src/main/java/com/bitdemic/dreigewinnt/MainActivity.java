package com.bitdemic.dreigewinnt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

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
        // rotate X logo
        //
        YoYo.with(Techniques.RotateInDownRight)
                .duration(2000)
                .repeat(0)
                .playOn(findViewById(R.id.xLogo));
        // ring xWIN header
        //
        YoYo.with(Techniques.FlipInX)
                .duration(2000)
                .repeat(0)
                .playOn(findViewById(R.id.xWINtextView));

        /*
        ImageView imageView = findViewById(R.id.xLogo);
        RotateAnimation xButton = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        xButton.setDuration(20000);
        xButton.setRepeatCount(Animation.INFINITE);
        imageView.startAnimation(xButton);
        */
    }

}