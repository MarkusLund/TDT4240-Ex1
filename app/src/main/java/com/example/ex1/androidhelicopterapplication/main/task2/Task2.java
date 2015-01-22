package com.example.ex1.androidhelicopterapplication.main.task2;

import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

import com.example.ex1.androidhelicopterapplication.main.Main;

import sheep.game.State;
import sheep.game.World;

public class Task2 extends State implements View.OnTouchListener{

    private World gameWorld;
    private GameLayer gameLayer;

    public Task2() {
        this.gameWorld = new World();
        this.gameLayer = new GameLayer();

        this.gameWorld.addLayer(gameLayer);
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        gameLayer.update(dt);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawColor(Main.BACKGROUND_COLOR);
        gameWorld.draw(canvas);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int eventAction = event.getActionMasked();
        switch (eventAction) {
            case MotionEvent.ACTION_DOWN:
                gameLayer.onTouchDown(event);
                break;
            default:
                break;
        }
        return false;
    }
}
