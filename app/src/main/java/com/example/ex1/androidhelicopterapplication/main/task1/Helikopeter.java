package com.example.ex1.androidhelicopterapplication.main.task1;

import sheep.game.Sprite;
import sheep.graphics.Image;
/**
 * Created by Lima on 13.01.2015.
 */
public class Helikopeter extends Sprite {

    private float height, width;


    public Helikopeter(Image image) {
        super(image);

        height = image.getHeight();
        width = image.getWidth();

        setPosition(350, 200);
    }

    @Override
    public void update(float dt) {

        super.update(dt);
    }
    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }
}
