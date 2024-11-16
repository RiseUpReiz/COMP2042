package com.example.demo;

import com.example.demo.controller.Main;

public abstract class Projectile extends ActiveActorDestructible {

	public Projectile(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		super(imageName, imageHeight, initialXPos, initialYPos);
	}

	@Override
	public void takeDamage() {
		this.destroy();
	}

	@Override
	public void updateActor(){
		updatePosition();
		if (outOfScreen()) {
			this.destroy();
		}
	}

	public boolean outOfScreen() {
		return getTranslateX() > Main.getScreenWidth();
	}
}
