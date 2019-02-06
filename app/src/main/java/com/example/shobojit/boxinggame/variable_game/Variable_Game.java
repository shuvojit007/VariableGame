package com.example.shobojit.boxinggame.variable_game;

import android.content.Context;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shobojit.boxinggame.R;
import com.example.shobojit.boxinggame.Utils.PlayMusic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

public class Variable_Game extends AppCompatActivity implements View.OnClickListener {

    Context cn;
    private CountDownTimer countDownTimer;
    private ProgressBar progressBarCircle;
    private long timeCountInMilliSeconds = 1 * 4000;

    private TextView progresstext, showquestion;

    private TextView op1, op2, op3, op4;
    List<String> question, ans;
    private RelativeLayout pg_rl;

    int Index = 0;

    boolean Clicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_variable__game);

        cn = this;
        init();
        setUpQuestionAndAnswer();
    }

    private void setUpQuestionAndAnswer() {
        question = Arrays.asList("summer = True", "name = \"Bruno Mars\"", "exciting = false", "price = 14", "friend = \"Lady Gaga", "weight = 21");
        ans = Arrays.asList("Boolean", "String", "Incorrect", "Number", "Incorrect", "Number");

        new Handler().postDelayed(() -> {
            pg_rl.setAlpha(1f);
            setListenerToOption();
            startCountDownTimer();
        }, 5000);
    }

    public void init() {
        showquestion = findViewById(R.id.showquestion);
        progressBarCircle = findViewById(R.id.progressBar);
        progresstext = findViewById(R.id.progresstext);
        op1 = findViewById(R.id.op1);
        op2 = findViewById(R.id.op2);
        op3 = findViewById(R.id.op3);
        op4 = findViewById(R.id.op4);

        pg_rl = findViewById(R.id.pg_rl);
    }


    public void setListenerToOption() {
        op1.setOnClickListener(this);
        op2.setOnClickListener(this);
        op3.setOnClickListener(this);
        op4.setOnClickListener(this);
    }


    private void startCountDownTimer() {
        setProgressBarValues();
        setQuestionAndOption();
        countDownTimer = new CountDownTimer(timeCountInMilliSeconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                progresstext.setText(hmsTimeFormatter(millisUntilFinished));
                progressBarCircle.setProgress((int) (millisUntilFinished / 1000));
            }

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onFinish() {
                progresstext.setText(hmsTimeFormatter(00));
                progressBarCircle.setProgress(0);
                ChangetheQuestion();
            }
        }.start();

        countDownTimer.start();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void ChangetheQuestion() {
        Index++;
        setProgressBarValues();

        if (countDownTimer!=null){
            countDownTimer.cancel();
        }

        if (Index < question.size()) {
            ResetOption();
            startCountDownTimer();
        } else {
            progresstext.setText(hmsTimeFormatter(00));
            progressBarCircle.setProgress(0);
        }

    }

    private void setQuestionAndOption() {
        if (Index < question.size()) {
            showquestion.setText(question.get(Index));
        }
    }

    private void setProgressBarValues() {
        progressBarCircle.setMax((int) timeCountInMilliSeconds / 1000);
        progressBarCircle.setProgress((int) timeCountInMilliSeconds / 1000);
    }

    private String hmsTimeFormatter(long milliSeconds) {
        String hms = String.format("%02d",
                TimeUnit.MILLISECONDS.toSeconds(milliSeconds) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliSeconds)));
        return hms;
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void ResetOption() {
        op1.setBackground(getResources().getDrawable(R.drawable.variable_game_option_gradient));
        op2.setBackground(getResources().getDrawable(R.drawable.variable_game_option_gradient));
        op3.setBackground(getResources().getDrawable(R.drawable.variable_game_option_gradient));
        op4.setBackground(getResources().getDrawable(R.drawable.variable_game_option_gradient));

        op1.setTextColor(getResources().getColor(R.color.white));
        op2.setTextColor(getResources().getColor(R.color.white));
        op3.setTextColor(getResources().getColor(R.color.white));
        op4.setTextColor(getResources().getColor(R.color.white));
    }



    public void CancelTheTimer(){
        if (countDownTimer!=null){
            countDownTimer.cancel();
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (Index < question.size()) {
            if (!Clicked) {
                switch (id) {
                    case R.id.op1:
                        Clicked =true;
                        if (ans.get(Index).equals("Number")) {
                            new PlayMusic().CorrectAnsMusic(cn);
                            op1.setBackground(getResources().getDrawable(R.drawable.variablegame_correct));
                            Toast.makeText(cn, "Right Answer", Toast.LENGTH_SHORT).show();
                        } else {
                            new PlayMusic().WrongAnsMusic(cn);
                            op1.setBackground(getResources().getDrawable(R.drawable.variablegame_incorrect));
                            Toast.makeText(cn, "Wrong Answer", Toast.LENGTH_SHORT).show();
                        }
                        op1.setTextColor(getResources().getColor(R.color.black));
                        CancelTheTimer();
                        new Handler().postDelayed(() -> {
                            Clicked = false;
                            ChangetheQuestion();
                        }, 2000);

                        break;
                    case R.id.op2:
                        Clicked =true;
                        if (ans.get(Index).equals("String")) {
                            new PlayMusic().CorrectAnsMusic(cn);
                            op2.setBackground(getResources().getDrawable(R.drawable.variablegame_correct));
                            Toast.makeText(cn, "Right Answer", Toast.LENGTH_SHORT).show();
                        } else {
                            new PlayMusic().WrongAnsMusic(cn);
                            op2.setBackground(getResources().getDrawable(R.drawable.variablegame_incorrect));
                            Toast.makeText(cn, "Wrong Answer", Toast.LENGTH_SHORT).show();
                        }
                        op2.setTextColor(getResources().getColor(R.color.black));
                        CancelTheTimer();
                        new Handler().postDelayed(() -> {
                            Clicked = false;
                            ChangetheQuestion();
                        }, 2000);

                        break;
                    case R.id.op3:
                        Clicked =true;
                        if (ans.get(Index).equals("Boolean")) {
                            new PlayMusic().CorrectAnsMusic(cn);
                            op3.setBackground(getResources().getDrawable(R.drawable.variablegame_correct));
                            Toast.makeText(cn, "Right Answer", Toast.LENGTH_SHORT).show();
                        } else {
                            new PlayMusic().WrongAnsMusic(cn);
                            op3.setBackground(getResources().getDrawable(R.drawable.variablegame_incorrect));
                            Toast.makeText(cn, "Wrong Answer", Toast.LENGTH_SHORT).show();
                        }
                        op3.setTextColor(getResources().getColor(R.color.black));
                        CancelTheTimer();
                        new Handler().postDelayed(() -> {
                            Clicked = false;
                            ChangetheQuestion();
                        }, 2000);

                        break;
                    case R.id.op4:
                        Clicked =true;
                        if (ans.get(Index).equals("Incorrect")) {
                            new PlayMusic().CorrectAnsMusic(cn);
                            op4.setBackground(getResources().getDrawable(R.drawable.variablegame_correct));
                            Toast.makeText(cn, "Right Answer", Toast.LENGTH_SHORT).show();
                        } else {
                            new PlayMusic().WrongAnsMusic(cn);
                            op4.setBackground(getResources().getDrawable(R.drawable.variablegame_incorrect));
                            Toast.makeText(cn, "Wrong Answer", Toast.LENGTH_SHORT).show();
                        }
                        op4.setTextColor(getResources().getColor(R.color.black));
                        CancelTheTimer();
                        new Handler().postDelayed(() -> {
                            Clicked = false;
                            ChangetheQuestion();
                        }, 2000);
                        break;
                }
            }

        }


    }
}
