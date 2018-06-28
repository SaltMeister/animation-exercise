package com.missionbit.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;
import java.util.Random;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class AnimationExcercise extends ApplicationAdapter {

    private OrthographicCamera camera;

    private SpriteBatch myBatch;

    FlyingSauser creature;
    private ArrayList<FlyingSauser> creatures = new ArrayList<FlyingSauser>();

    private Random randomSource;
    //private Sprite myImage;
    private Vector2 velocity;

    @Override
    public void create() {

        // Set up camera for 2d view of 800x480 pixels
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        // Create a sprite batch for rendering our image
        myBatch = new SpriteBatch();

        // Create a new creature
        creature = new FlyingSauser(myBatch);
        for(int i = 0; i < 10; i++){
            FlyingSauser f = new FlyingSauser(myBatch);
            creatures.add(f);
        }
        randomSource = new Random();

        // Set up camera for 2d view of 800x480 pixels
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        // Create a sprite batch for rendering our image

        //myImage = new Sprite( new Texture(Gdx.files.internal("images/ufo.png")));
        //myImage.setX(100);
        //myImage.setY(100);

        //TODO: Load our image
        // Create a random X and Y velocity
        velocity = new Vector2(randomSource.nextFloat() * 300, randomSource.nextFloat() * 300);
    }

    @Override
    public void render() {
        // Clear the screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Set up our camera
        camera.update();
        myBatch.setProjectionMatrix(camera.combined);

        creature.update();
/*
        myBatch.begin();
        creature.draw();
        myBatch.end();
*/
        myBatch.begin();
        for(FlyingSauser f : creatures){
            f.update();
            f.draw();
        }
        myBatch.end();

        if(Gdx.input.isTouched()){
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);

            camera.unproject(touchPos);
            for(FlyingSauser f : creatures) {
                f.handleClick(touchPos);
            }
        }


        System.out.println("test");
        if (Gdx.input.isTouched())
        {
         velocity.x -= 10;

        }

        myBatch.begin();
        //myImage.draw(myBatch);
        myBatch.end();


        //TODO: Draw our image!
    }

    @Override
    public void dispose() {
        myBatch.dispose();
    }
}