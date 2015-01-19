package com.example.ex1.androidhelicopterapplication.main;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;


import com.example.ex1.androidhelicopterapplication.R;
import com.example.ex1.androidhelicopterapplication.main.task1.Task1;
import com.example.ex1.androidhelicopterapplication.main.task2.Task2;
import com.example.ex1.androidhelicopterapplication.main.task3.Task3;
import com.example.ex1.androidhelicopterapplication.main.task4.GameLayerPong;
import com.example.ex1.androidhelicopterapplication.main.task4.Task4Pong;

import sheep.game.Game;




public class Main extends Activity {

    public static int BACKGROUND_COLOR = Color.rgb(254, 0, 254);
    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        // Create the game.
        game = new Game(this, null);
        // Push the main state.
        Task4Pong state = new Task4Pong();
        game.pushState(state);

        // View the game.
        setContentView(game);

        game.setOnTouchListener(state);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.task1) {
            game.popState();
            game.pushState(new Task1());
        }
        else if (id == R.id.task2) {
            game.popState();
            game.pushState(new Task2());
        }
        else if (id == R.id.task3) {
            game.popState();
            game.pushState(new Task3());
        }

        return super.onOptionsItemSelected(item);
    }


}
