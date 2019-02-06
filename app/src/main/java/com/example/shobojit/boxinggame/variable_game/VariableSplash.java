package com.example.shobojit.boxinggame.variable_game;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.shobojit.boxinggame.MainActivity;
import com.example.shobojit.boxinggame.R;

public class VariableSplash extends AppCompatActivity {

    TextView ready ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_variable_splash);
        SetUpSplash();
    }


    private void SetUpSplash() {

        ready = findViewById(R.id.ready);
        //ready.setVisibility(View.INVISIBLE);

        ready.setOnClickListener(v -> {
         startActivity(new Intent(VariableSplash.this,Variable_Game.class));
        });

//
//        new Handler().postDelayed(() -> {
//            Animation fadein = AnimationUtils.loadAnimation(VariableSplash.this, R.anim.slide_up);
//            ready.setVisibility(View.VISIBLE);
//            ready.setAnimation(fadein);
//        }, 3000);
    }
}
