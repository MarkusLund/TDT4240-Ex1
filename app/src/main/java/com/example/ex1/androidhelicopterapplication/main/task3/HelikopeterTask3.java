package com.example.ex1.androidhelicopterapplication.main.task3;


import sheep.game.Sprite;
import sheep.graphics.Image;

/**
 * Created by Lima on 13.01.2015.
 */
public class HelikopeterTask3 extends Sprite{

    private float height, width;

    public HelikopeterTask3() {
    }

    public HelikopeterTask3(Image image) {
        super(image);

        height = image.getHeight();
        width = image.getWidth();

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
