package com.example.ex1.androidhelicopterapplication.main.task3;


import android.graphics.Canvas;

import com.example.ex1.androidhelicopterapplication.R;

import java.util.ArrayList;

import sheep.game.Layer;
import sheep.graphics.Image;
import sheep.math.BoundingBox;

/**
 * Created by Lima on 13.01.2015.
 */
public class GameLayerTask3 extends Layer {

    private HelikopeterTask3 helikopeter;

    private Image[] heliImages = {new Image(R.drawable.heli_animation_01),
                                  new Image(R.drawable.heli_animation_02),
                                  new Image(R.drawable.heli_animation_03),
                                  new Image(R.drawable.heli_animation_04)};


    public GameLayerTask3() {
        super();
        helikopeter = new HelikopeterTask3(heliImages);
        helikopeter.setSpeed(-2,2);
    }


    @Override
    public void draw(Canvas canvas, BoundingBox boundingBox) {
        helikopeter.draw(canvas);

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

        helikopeter.setPosition(helikopeter.getPosition().getX() + helikopeter.getSpeed().getX(),helikopeter.getPosition().getY() + helikopeter.getSpeed().getY());
    }

    @Override
    public void update(float v) {
        helikopeter.update(v);

    }

}
