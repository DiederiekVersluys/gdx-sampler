package com.sampler;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sampler.common.SampleBase;
import com.sampler.common.SampleInfo;
import com.sampler.utils.GdxUtils;

public class InputPollingSample extends SampleBase {

	private OrthographicCamera camera;
	private Viewport viewport;
	private SpriteBatch batch;
	private BitmapFont font;
	public static final SampleInfo SAMPLE_INFO = new SampleInfo(InputPollingSample.class);

	@Override
	public void create() {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		camera = new OrthographicCamera();
		viewport = new FitViewport(1080,720, camera);
		batch = new SpriteBatch();
		font = new BitmapFont(Gdx.files.internal("fonts/oswald-32.fnt"));
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width,height, true);

	}

	@Override
	public void render() {
		//clear screen
		GdxUtils.clearScreen();

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		draw();

		batch.end();

	}

	private void draw() {
		int mouseX = Gdx.input.getX();
		int mouseY = Gdx.input.getY();

		//buttons
		boolean leftPressed = Gdx.input.isButtonPressed(Input.Buttons.LEFT);
		boolean rightPressed = Gdx.input.isButtonPressed(Input.Buttons.RIGHT);
		font.draw(batch, "Mouse/Touch : x= "+ mouseX + "y= " +mouseY, 20f, 720 - 20f);

		font.draw(batch, leftPressed? "Left Button Pressed" : "Left Button Not Pressed", 20f, 720 - 50f);

		//? operator =>  if(leftPressed) {Str= "Left Pressed} else { Stf = "not pressed"}

		font.draw(batch, rightPressed? "Right Button Pressed" : "Right Button Not Pressed", 20f, 720 - 80f);



		//keys
		boolean wPressed = Gdx.input.isKeyPressed(Input.Keys.W);
		boolean sPressed = Gdx.input.isKeyPressed(Input.Keys.S);

		font.draw(batch, wPressed? "W Is Pressed" : "W is not pressed", 20f, 720 - 120f);
		font.draw(batch, sPressed? "S Is Pressed" : "S is not pressed", 20f, 720 - 140f);
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		batch.dispose();
		font.dispose();

	}
}
