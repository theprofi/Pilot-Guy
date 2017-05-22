package com.elkadakh.pilotguy.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.elkadakh.others.ActivityRequestHandler;
import com.elkadakh.pilotguy.GameMainClass;

public class DesktopLauncher implements ActivityRequestHandler {
    private static DesktopLauncher application;
    
	public static void main (String[] arg) {
		if (application == null)
			application = new DesktopLauncher();
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1280;
		config.height = 720;
		new LwjglApplication(new GameMainClass(application), config);
	}

	@Override
	public void showAds(boolean show) {
		// TODO Auto-generated method stub
		
	}
}
