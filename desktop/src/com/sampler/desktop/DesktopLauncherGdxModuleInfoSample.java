package com.sampler.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import com.sampler.ModuleInfoSample;

public class DesktopLauncherGdxModuleInfoSample {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		System.out.println("apps= " + Gdx.app);
		System.out.println("input= " + Gdx.input);

		new LwjglApplication(new ModuleInfoSample(), config);

		System.out.println("apps= " + Gdx.app);
		System.out.println("input= " + Gdx.input);
	}
}
