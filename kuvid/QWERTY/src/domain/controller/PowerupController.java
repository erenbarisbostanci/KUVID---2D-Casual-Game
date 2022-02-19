package domain.controller;

import domain.ScreenObjects;

public class PowerupController {

    public PowerupController() {}

    public void selectPowerup(int typeId) {
        switch (typeId) {
            case 0:
                ScreenObjects.getScreenObjects().getShooter().loadPowerup(typeId);
                break;
            case 1:
                ScreenObjects.getScreenObjects().getShooter().loadPowerup(typeId);
                break;
            case 2:
                ScreenObjects.getScreenObjects().getShooter().loadPowerup(typeId);
                break;
            case 3:
                ScreenObjects.getScreenObjects().getShooter().loadPowerup(typeId);
                break;
        }
    }
}