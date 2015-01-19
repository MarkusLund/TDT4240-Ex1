package com.example.ex1.androidhelicopterapplication.main.task4;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import sheep.game.Sprite;
import sheep.graphics.Image;

/**
 * Created by markuslund92 on 16.01.15.
 */
public class PongPaddle extends Sprite {

    public PongPaddle(Image image, int i) {

        super(image);

        if (i == 1) {
            setPosition(100, 250);
        } else {
            setPosition(900, 250);
        }

    }
}
