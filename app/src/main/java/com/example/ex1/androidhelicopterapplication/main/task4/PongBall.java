package com.example.ex1.androidhelicopterapplication.main.task4;

import com.example.ex1.androidhelicopterapplication.R;

import sheep.game.Sprite;
import sheep.graphics.Image;

/**
 * Created by Lima on 19.01.2015.
 */
public class PongBall extends Sprite{
    private static PongBall ourInstance ;
    public static PongBall getInstance() {
        return ourInstance;
    }
    private static Image img = new Image(R.drawable.pong_ball);

   public PongBall() {
       super(img);
       ourInstance = this;
       this.setPosition(100,100);

    }

    public static float getHeight() {
        return img.getHeight();
    }

    public static float getWidth() {
        return img.getWidth();
    }



}