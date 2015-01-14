package com.example.ex1.androidhelicopterapplication.main.task1;

import android.graphics.Canvas;

import com.example.ex1.androidhelicopterapplication.main.Main;

import sheep.game.State;
import sheep.game.World;

/**
 * Created by Lima on 13.01.2015.
 */
public class Task1 extends State {

    private World gameWorld;
    private GameLayer gameLayer;

    public Task1() {
        this.gameWorld = new World();
        this.gameLayer = new GameLayer();
        gameWorld.addLayer(gameLayer);
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

}
