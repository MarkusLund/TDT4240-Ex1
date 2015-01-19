package com.example.ex1.androidhelicopterapplication.main;

import java.util.Random;

import sheep.game.Sprite;
import sheep.math.Vector2;

/**
 * Created by Lima on 19.01.2015.
 */
public abstract class Util {

    public static Vector2 getRandSpeed(int min, int max){
        return new Vector2(getRandInt(min,max),getRandInt(min,max));
    }

    public static int getRandInt(int min, int max){
        // NOTE: Usually this should be a field rather than a method
        // variable so that it is not re-seeded every call.
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
    
    public static void moveSprite(Sprite sprite){
        sprite.setPosition(sprite.getPosition().getX() + sprite.getSpeed().getX(), sprite.getPosition().getY() + sprite.getSpeed().getY());

    }
}
