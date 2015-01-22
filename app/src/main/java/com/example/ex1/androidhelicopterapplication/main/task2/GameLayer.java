package com.example.ex1.androidhelicopterapplication.main.task2;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;
import android.view.MotionEvent;


import com.example.ex1.androidhelicopterapplication.R;
import com.example.ex1.androidhelicopterapplication.main.task1.Helikopeter;

import sheep.game.Layer;
import sheep.graphics.Font;
import sheep.graphics.Image;
import sheep.input.TouchListener;
import sheep.math.BoundingBox;

/**
 * Created by Lima on 13.01.2015.
 */
public class GameLayer extends Layer{

    private Helikopeter helikopeter;
    private Font font;


    public GameLayer() {
        super();
        helikopeter = new Helikopeter(new Image(R.drawable.heli2));
        helikopeter.setSpeed(-2,2);
        //font = new Font(0, 0, 0, 18, Typeface.SERIF, Typeface.NORMAL);
        font = new Font(255, 255, 255, 50, Typeface.MONOSPACE, Typeface.NORMAL);
        font.setTextAlign(Paint.Align.CENTER);
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

        //Draw heli position
        canvas.drawText(String.format("Pos: %7.2f,%7.2f",helikopeter.getPosition().getX(),helikopeter.getPosition().getY()),canvas.getWidth()/2,50,font);
        canvas.drawText("Tap to move helicopter", canvas.getWidth()/2, 120, font);
    }

    @Override
    public void update(float v) {
        helikopeter.update(v);

    }

    public boolean onTouchDown(MotionEvent motionEvent) {
        Log.i("Touch",motionEvent.toString());
        if(motionEvent.getY()<300){
            Log.i("Touch","Up");
            helikopeter.setSpeed(helikopeter.getSpeed().getX(),helikopeter.getSpeed().getY()-1);
        }else if(motionEvent.getY()>900){
            Log.i("Touch","Down");
            helikopeter.setSpeed(helikopeter.getSpeed().getX(),helikopeter.getSpeed().getY()+1);
        }
        if(motionEvent.getX()<200){
            Log.i("Touch","Left");
            helikopeter.setSpeed(helikopeter.getSpeed().getX()-1,helikopeter.getSpeed().getY());
        }else if(motionEvent.getX()>500){
            Log.i("Touch","Right");
            helikopeter.setSpeed(helikopeter.getSpeed().getX()+1,helikopeter.getSpeed().getY());
        }
        return true;
    }
}