package com.sampler;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sampler.common.CustomActor;
import com.sampler.common.SampleBase;
import com.sampler.common.SampleInfo;
import com.sampler.utils.GdxUtils;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class ActionsSample extends SampleBase {

    private static final Logger log = new Logger(ActionsSample.class.getName(), Logger.DEBUG);

    public static final SampleInfo SAMPLE_INFO = new SampleInfo(ActionsSample.class);

    private static final float WORLD_WIDTH = 1080f;
    private static final float WORLD_HEIGHT = 720f;

    private Viewport viewport;

    private Stage stage;
    private Texture texture;
    private CustomActor customActor;

    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT);
        stage = new Stage(viewport);

        texture = new Texture(Gdx.files.internal("raw/custom-actor.png"));

        customActor = new CustomActor(new TextureRegion(texture));
        customActor.setSize(160, 80);
        customActor.setPosition(
                (WORLD_WIDTH - customActor.getWidth()) / 2f,
                (WORLD_HEIGHT - customActor.getHeight()) / 2f
        );


        stage.addActor(customActor);

        Gdx.input.setInputProcessor(this);

        String LS = System.getProperty("line.separator");
        String TAB = "\t";

        log.debug(LS + "Press Keys: " + LS +
                TAB + "1 - RotateBy Action" + LS +
                TAB + "2 - FadeOut Action" + LS +
                TAB + "3 - FadeIn Action" + LS +
                TAB + "4 - ScaleTo Action" + LS +
                TAB + "5 - MoveTo Action"
        );
    }

    @Override
    public boolean keyDown(int keycode) {
        customActor.clearActions();

        if (keycode == Input.Keys.NUM_1) {
            log.debug("RotateBy Action");
            customActor.addAction(rotateBy(90f, 1f));
        } else if (keycode == Input.Keys.NUM_2) {
            log.debug("FadeOut Action");
            customActor.addAction(fadeOut(2f));
        } else if (keycode == Input.Keys.NUM_3) {
            log.debug("FadeIn Action");
            customActor.addAction(fadeIn(2f));
        } else if (keycode == Input.Keys.NUM_4) {
            log.debug("ScaleTo Action");
            customActor.addAction(scaleTo(1.5f, 1.5f, 2f));
        } else if (keycode == Input.Keys.NUM_5) {
            log.debug("MoveTo Action");
            customActor.addAction(moveTo(100, 100, 2f));
        }

        return true;
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void render() {
        GdxUtils.clearScreen();

        // no need to call setProjectionMatrix, begin/end, everything is handled internally
        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        texture.dispose();
    }
}
