package com.example.ex1.androidhelicopterapplication.main.task4;

import com.example.ex1.androidhelicopterapplication.R;

import sheep.game.Sprite;
import sheep.graphics.Image;

/**
 * Created by Lima on 19.01.2015.
 */
public class PongBall extends Sprite{
    private static PongBall ourInstance = new PongBall();
    public static PongBall getInstance() {
        return ourInstance;
    }

   public PongBall() {
        super(new Image(R.drawable.pong_ball));

    }
}