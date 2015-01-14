package com.example.ex1.androidhelicopterapplication.main.task1;

import android.graphics.Canvas;

import com.example.ex1.androidhelicopterapplication.R;

import sheep.game.Layer;
import sheep.graphics.Image;
import sheep.math.BoundingBox;

/**
 * Created by Lima on 13.01.2015.
 */
public class GameLayer extends Layer {

    private Helikopeter helikopeter;
    public GameLayer() {
        super();
        helikopeter = new Helikopeter(new Image(R.drawable.heli2));
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
