package com.example.ex1.androidhelicopterapplication.main.task3;


import android.graphics.Canvas;

import com.example.ex1.androidhelicopterapplication.R;
import com.example.ex1.androidhelicopterapplication.main.Util;

import sheep.game.Layer;
import sheep.graphics.Image;
import sheep.math.BoundingBox;

/**
 * Created by Lima on 13.01.2015.
 */
public class GameLayerTask3 extends Layer {
    private HelikopeterTask3 helikopeter1, helikopeter2, helikopeter3;
    private float dt;

    public GameLayerTask3() {
        super();

        Image[] heli_ani = {
                new Image(R.drawable.heli_animation_01),
                new Image(R.drawable.heli_animation_02),
                new Image(R.drawable.heli_animation_03),
                new Image(R.drawable.heli_animation_04),
        };

        helikopeter1 = new HelikopeterTask3(heli_ani);
        helikopeter2 = new HelikopeterTask3(heli_ani);
        helikopeter3 = new HelikopeterTask3(heli_ani);

        helikopeter1.setSpeed(Util.getRandSpeed(-4, 4));
        helikopeter2.setSpeed(Util.getRandSpeed(-4,4));
        helikopeter3.setSpeed(Util.getRandSpeed(-4,4));

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
                h1.setPosition(h1.getPosition().getX(),h1.getPosition().getY());
            }else{
                h1.setPosition(h1.getPosition().getX(),h1.getPosition().getY());
            }
            h1.setYSpeed(-h1.getSpeed().getY());
            h2.setYSpeed(-h2.getSpeed().getY());
        }else {
            if (h1.getX() > h2.getX()) {
                h1.setXSpeed(-h1.getSpeed().getX());
                h1.setScale(1, 1);
                h1.setPosition(h1.getPosition().getX() + h1.getWidth(), h1.getPosition().getY());
            } else {
                h2.setXSpeed(-h2.getSpeed().getX());
                h2.setScale(-1, 1);
                h2.setPosition(h2.getPosition().getX() - h2.getWidth(), h2.getPosition().getY());
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




    @Override
    public void update(float v) {

        Util.moveSprite(helikopeter1);
        Util.moveSprite(helikopeter2);
        Util.moveSprite(helikopeter3);

        if(dt>0.1){

            if(helikopeter1.collides(helikopeter2)){
                heliCollide(helikopeter1,helikopeter2);
            }else if(helikopeter1.collides(helikopeter3)) {
                heliCollide(helikopeter1, helikopeter3);
            }else if(helikopeter2.collides(helikopeter3)){
                heliCollide(helikopeter2,helikopeter3);
            }
            dt=0;
        }
        dt+=v;

        helikopeter1.update(v);
        helikopeter2.update(v);
        helikopeter3.update(v);

    }

}
