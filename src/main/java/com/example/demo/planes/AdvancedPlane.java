package com.example.demo.planes;


import com.example.demo.ActiveActorDestructible;
import com.example.demo.projectiles.EnemyProjectile;

public class AdvancedPlane extends FighterPlane {

    private static final String IMAGE_NAME = "advancedplane.png";
    private static final int IMAGE_HEIGHT = 60;
    private static final int HORIZONTAL_VELOCITY = -4;
    private static final double PROJECTILE_X_POSITION_OFFSET = -100.0;
    private static final double PROJECTILE_Y_POSITION_OFFSET = 50.0;
    private static final int INITIAL_HEALTH = 3;
    public static double FIRE_RATE = .03;

    public AdvancedPlane(double initialXPos, double initialYPos) {
        super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, INITIAL_HEALTH);
    }

    @Override
    public void updatePosition() {
        moveHorizontally(HORIZONTAL_VELOCITY);
    }

    @Override
    public ActiveActorDestructible fireProjectile() {
        if (Math.random() < FIRE_RATE) {
            double projectileXPosition = getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET);
            double projectileYPostion = getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET);
            return new EnemyProjectile(projectileXPosition, projectileYPostion);
        }
        return null;
    }

    @Override
    public void updateActor(){
        updatePosition();
    }

}
