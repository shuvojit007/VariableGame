package com.example.shobojit.boxinggame.Utils;

import android.content.Context;
import android.media.MediaPlayer;

import com.example.shobojit.boxinggame.R;

public class PlayMusic {
    public void CorrectAnsMusic(Context cn) {


        MediaPlayer mp = MediaPlayer.create(cn, R.raw.correct);
        mp.setOnCompletionListener(mp1 -> {
            mp.stop();
            mp.release();
        });
        mp.start();
        // MediaPlayer.create(cn, R.raw.correct).start();

    }

    public void WrongAnsMusic(Context cn) {

        MediaPlayer mp = MediaPlayer.create(cn, R.raw.wrong);
        mp.setOnCompletionListener(mp1 -> {
            mp.stop();
            mp.release();
        });
        mp.start();
        //  MediaPlayer.create(cn, R.raw.wrong).start();

    }
}
