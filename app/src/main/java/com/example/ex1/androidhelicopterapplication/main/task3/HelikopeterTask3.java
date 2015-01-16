package com.example.ex1.androidhelicopterapplication.main.task3;

import sheep.game.Sprite;
import sheep.graphics.Image;

/**
 * Created by Lima on 13.01.2015.
 */

public class HelikopeterTask3 extends Sprite{

    private float height, width;
    private float timeLeft;
    private int frameCount;
    private int currentFrame;
    private double tickTime;
    private Image[] heliImages;

    public HelikopeterTask3(Image[] heliImages) {

        super(heliImages[1]);

        height = heliImages[1].getHeight();
        width = heliImages[1].getWidth();

        this.heliImages = heliImages;

        timeLeft = 0f;
        currentFrame = 0;
        tickTime = 0.1;
        frameCount = 4;

        setPosition(350, 200);
    }

    @Override
    public void update(float dt) {


        //Animating Helicopter
        timeLeft += dt;

        if(timeLeft >= tickTime)
        {
            currentFrame = (currentFrame + 1) % frameCount;
            this.setView(heliImages[currentFrame]);
            timeLeft -= tickTime;
        }

        super.update(dt);
    }
    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }
}
