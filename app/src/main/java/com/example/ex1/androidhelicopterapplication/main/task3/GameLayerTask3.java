package com.example.ex1.androidhelicopterapplication.main.task3;


import android.graphics.Canvas;
import android.util.Log;

import java.util.Random;

import sheep.collision.CollisionListener;
import sheep.game.Layer;
import sheep.game.Sprite;
import sheep.math.BoundingBox;
import sheep.math.Vector2;

/**
 * Created by Lima on 13.01.2015.
 */
public class GameLayerTask3 extends Layer implements CollisionListener {

    private HelikopeterTask3 helikopeter1, helikopeter2, helikopeter3;


    public GameLayerTask3() {
        super();
        helikopeter1 = new HelikopeterTask3();
        helikopeter2 = new HelikopeterTask3();
        helikopeter3 = new HelikopeterTask3();

        helikopeter1.setSpeed(getRandSpeed());
        helikopeter2.setSpeed(getRandSpeed());
        helikopeter3.setSpeed(getRandSpeed());

    }


    @Override
    public void draw(Canvas canvas, BoundingBox boundingBox) {
        helikopeter1.draw(canvas);
        helikopeter2.draw(canvas);
        helikopeter3.draw(canvas);

        checkEdges(canvas,helikopeter1);
        checkEdges(canvas,helikopeter2);
        checkEdges(canvas,helikopeter3);

        moveHeli(helikopeter1);
        moveHeli(helikopeter2);
        moveHeli(helikopeter3);

    }

    private void checkEdges(Canvas canvas, HelikopeterTask3 helikopeter){
        //Moving helikopeter
        if(helikopeter.getPosition().getX()>=canvas.getWidth()+(helikopeter.getWidth()/2)){
            helikopeter.setSpeed(-helikopeter.getSpeed().getX(),helikopeter.getSpeed().getY());
            helikopeter.setScale(1,1);
            helikopeter.setPosition(helikopeter.getPosition().getX()-helikopeter.getWidth(), helikopeter.getPosition().getY());
        }
        else if(helikopeter.getPosition().getX()<=(helikopeter.getWidth()/2)){
            helikopeter.setSpeed(-helikopeter.getSpeed().getX(),helikopeter.getSpeed().getY());
            helikopeter.setScale(-1,1);
            helikopeter.setPosition(helikopeter.getPosition().getX()+helikopeter.getWidth(), helikopeter.getPosition().getY());
        }
        else if(helikopeter.getPosition().getY()>=canvas.getHeight()-(helikopeter.getHeight()/2)){
            helikopeter.setSpeed(helikopeter.getSpeed().getX(),-helikopeter.getSpeed().getY());
        }
        else if(helikopeter.getPosition().getY()<=(helikopeter.getHeight()/2)){
            helikopeter.setSpeed(helikopeter.getSpeed().getX(),-helikopeter.getSpeed().getY());
        }
    }


    private void moveHeli(HelikopeterTask3 helikopeter){
        helikopeter.setPosition(helikopeter.getPosition().getX() + helikopeter.getSpeed().getX(),helikopeter.getPosition().getY() + helikopeter.getSpeed().getY());
    }

    @Override
    public void update(float v) {
        helikopeter1.update(v);
        helikopeter2.update(v);
        helikopeter3.update(v);

    }


    private static Vector2 getRandSpeed(){
        return new Vector2(getRandInt(-4,4),getRandInt(-4,4));
    }

    private static int getRandInt(int min, int max){
        // NOTE: Usually this should be a field rather than a method
        // variable so that it is not re-seeded every call.
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    @Override
    public void collided(Sprite sprite, Sprite sprite2) {
        Log.i("Crash",String.format("Sprite %s collided with %s!",sprite.getPosition().toString(),sprite2.getPosition().toString()));
    }
}
