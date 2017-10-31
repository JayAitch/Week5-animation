package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Comparator;

/**
 * Created by jh1152 on 31/10/2017.
 */

public class BasicAnimation extends ApplicationAdapter{
    static final Color BACKGROUND_COLOUR = new Color(0f,0f,0f,1f);
    static  final float SCENE_WIDTH = 12.0f;
    static  final float SCENE_HEIGHT = 7.0f;
    static  final float FRAME_DURATION = 1.0f / 30.0f;

    OrthographicCamera camera;
    Viewport view;
    SpriteBatch batch;
    TextureAtlas atlas;
    Animation threeRingAnim;
    float animationTime;


    @Override
    public void create () {
        camera = new OrthographicCamera();
        view = new FitViewport(SCENE_WIDTH,SCENE_HEIGHT,camera);

        batch = new SpriteBatch();
        animationTime = 0.0f;
        atlas = new TextureAtlas((Gdx.files.internal("GFX" +
                "/ring_animation.atlas")));

        //load anims
        Array<TextureAtlas.AtlasRegion> ringRegion = new Array<TextureAtlas.AtlasRegion>(atlas.getRegions());
        ringRegion.sort(new RegionComparator());
        threeRingAnim = new Animation(FRAME_DURATION,ringRegion, Animation.PlayMode.LOOP);

        //Postion the camera
        camera.position.set(SCENE_WIDTH * 0.5f, SCENE_HEIGHT * 0.5f, 0.0f);
    }

    @Override
    public void render(){
        Gdx.gl.glClearColor(BACKGROUND_COLOUR.r,BACKGROUND_COLOUR.g,BACKGROUND_COLOUR.g,BACKGROUND_COLOUR.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        animationTime += Gdx.graphics.getDeltaTime();
        batch.begin();
        TextureRegion ringFrame = (TextureRegion) threeRingAnim.getKeyFrame(animationTime);
        batch.draw(ringFrame, (SCENE_WIDTH * 100)/2,(SCENE_HEIGHT * 100)/2);
        batch.end();

    }



    private static class RegionComparator implements Comparator<TextureAtlas.AtlasRegion>{
        @Override
        public int compare(TextureAtlas.AtlasRegion region1, TextureAtlas.AtlasRegion region2){
            return region1.name.compareTo(region2.name);
        }

    }



}
