package com.example.shobojit.boxinggame;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    private ImageView icon;
    private TextView boxname;
    public int SPLASH_DELAY = 3000;

    private LinearLayout game_bottom,gamelinupper;


    private Timer timer = new Timer();
    private Timer texttimer ;
    private Handler handler = new Handler();
    //Drag And Drop
    boolean isDragging = false;
    float lastX;
    float lastY;
    float deltaX;

    private GestureDetector gestureDetector;
    private int ScreenWidth;
    private int ScreenHeight;
    private float UpX;
    private float UpY;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        init();
        SetUpSplash();


    }

    private void init() {
        gamelinupper =  findViewById(R.id.gamelinupper);
        game_bottom = findViewById(R.id.gamelin);
        icon = findViewById(R.id.icon);
        boxname = findViewById(R.id.boxname);
        Animation fadein = AnimationUtils.loadAnimation(MainActivity.this, R.anim.slide_up);
        boxname.setAnimation(fadein);
    }

    private void SetUpSplash() {

        new Handler().postDelayed(() -> {
            icon.setVisibility(View.GONE);
            boxname.setVisibility(View.GONE);
            game_bottom.setVisibility(View.VISIBLE);
            gamelinupper.setVisibility(View.VISIBLE);
        }, SPLASH_DELAY);
    }
}
