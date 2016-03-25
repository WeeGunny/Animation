package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Movement extends ApplicationAdapter {
	private static final int    COLS = 4;
	private static final int    ROWS = 4;

	SpriteBatch batch;
	Texture Sprite;
	Texture BackGround;
	TextureRegion[] frames;
	TextureRegion CurrentFrame;
	float SpriteX=0;
	float SpriteY=0;
	float SpriteSpeed=45f;
	float Time=0f;
	Animation animation;

	@Override
	public void create () {
		batch = new SpriteBatch();
		BackGround= new Texture(Gdx.files.internal("lostwoods2.jpg"));
		Sprite = new Texture(Gdx.files.internal("CinderellaSpriteSheet.png"));
		TextureRegion[][] tmp = TextureRegion.split(Sprite,Sprite.getWidth()/COLS,Sprite.getHeight()/ROWS);
		frames = new TextureRegion[COLS * ROWS];
		int index = 0;
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				frames[index++] = tmp[i][j];
			}
		}
		animation= new Animation(1f,frames);
	}

	@Override
	public void render () {
		if(Time<4){
		Time += Gdx.graphics.getDeltaTime();}
		else{
			Time=0;
		}

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		CurrentFrame = animation.getKeyFrame(0);

		if(Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT)) {
			SpriteX -= Gdx.graphics.getDeltaTime() * SpriteSpeed;
			CurrentFrame = animation.getKeyFrame(4+Time);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT)) {
			SpriteX += Gdx.graphics.getDeltaTime() * SpriteSpeed;
			CurrentFrame = animation.getKeyFrame(8+Time);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.DPAD_UP)) {
			SpriteY += Gdx.graphics.getDeltaTime() * SpriteSpeed;
			CurrentFrame = animation.getKeyFrame(12+Time);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.DPAD_DOWN)) {
			SpriteY -= Gdx.graphics.getDeltaTime() * SpriteSpeed;
			CurrentFrame = animation.getKeyFrame(0+Time);
		}

		batch.begin();
		batch.draw(BackGround,0,0);
		batch.draw(CurrentFrame,(int)SpriteX, (int)SpriteY);
		batch.end();
	}
}
