package com.sampler;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.sampler.common.SampleBase;
import com.sampler.common.SampleInfo;

public class GdxGeneratedSample extends SampleBase {
	SpriteBatch batch;
	Texture img;
	public static final SampleInfo SAMPLE_INFO = new SampleInfo(GdxGeneratedSample.class);
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
