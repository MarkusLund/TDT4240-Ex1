package com.example.ex1.androidhelicopterapplication.main.task3;


import android.graphics.Canvas;

import com.example.ex1.androidhelicopterapplication.R;

import java.util.Random;

import sheep.game.Layer;
import sheep.graphics.Image;
import sheep.math.BoundingBox;
import sheep.math.Vector2;

/**
 * Created by Lima on 13.01.2015.
 */
public class GameLayerTask3 extends Layer {
    private HelikopeterTask3 helikopeter1, helikopeter2, helikopeter3;


    public GameLayerTask3() {
        super();
        helikopeter1 = new HelikopeterTask3(new Image(R.drawable.heli2));
        helikopeter2 = new HelikopeterTask3(new Image(R.drawable.heli2));
        helikopeter3 = new HelikopeterTask3(new Image(R.drawable.heli2));

        helikopeter1.setSpeed(getRandSpeed());
        helikopeter2.setSpeed(getRandSpeed());
        helikopeter3.setSpeed(getRandSpeed());

        helikopeter1.setPosition(100,100);
        helikopeter2.setPosition(200,400);
        helikopeter3.setPosition(400,800);
    }


    @Override
    public void draw(Canvas canvas, BoundingBox boundingBox) {
        checkEdges(canvas,helikopeter1);
        checkEdges(canvas,helikopeter2);
        checkEdges(canvas,helikopeter3);

        helikopeter1.draw(canvas);
        helikopeter2.draw(canvas);
        helikopeter3.draw(canvas);

    }

    private void heliCollide(HelikopeterTask3 h1, HelikopeterTask3 h2){
        if(h1.getX() - h2.getX() < 0){
            if(h1.getY()>h2.getY()){
                h1.setPosition(h1.getPosition().getX(),h1.getPosition().getY()-5);
            }else{
                h1.setPosition(h1.getPosition().getX(),h1.getPosition().getY()+5);
            }
            h1.setYSpeed(-h1.getSpeed().getY());
            h2.setYSpeed(-h2.getSpeed().getY());
        }else {
            if (h1.getX() > h2.getX()) {
                h1.setXSpeed(-h1.getSpeed().getX());
                h1.setScale(1, 1);
                h1.setPosition(h1.getPosition().getX() + h1.getWidth() + 1, h1.getPosition().getY());
            } else {
                h2.setXSpeed(-h2.getSpeed().getX());
                h2.setScale(-1, 1);
                h2.setPosition(h2.getPosition().getX() - h2.getWidth() - 1, h2.getPosition().getY());
            }
        }
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

        moveHeli(helikopeter1);
        moveHeli(helikopeter2);
        moveHeli(helikopeter3);

        if(helikopeter1.collides(helikopeter2)){
            heliCollide(helikopeter1,helikopeter2);
        }else if(helikopeter1.collides(helikopeter3)) {
            heliCollide(helikopeter1, helikopeter3);
        }else if(helikopeter2.collides(helikopeter3)){
            heliCollide(helikopeter2,helikopeter3);
        }

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

}
