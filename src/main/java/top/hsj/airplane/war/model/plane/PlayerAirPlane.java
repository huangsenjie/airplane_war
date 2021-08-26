package top.hsj.airplane.war.model.plane;

import top.hsj.airplane.war.App;
import top.hsj.airplane.war.constant.Figure;
import top.hsj.airplane.war.model.Model;
import top.hsj.airplane.war.model.bullet.Bullet;

/**
 * @author hsj
 */
public class PlayerAirPlane extends AirPlane {

    private String name;

    public PlayerAirPlane(float x, float y, Figure figure, String name) {
        super(x, y, figure);
        this.name = name;
    }

    public PlayerAirPlane(float x, float y, Figure figure, int blood, String name) {
        super(x, y, figure, blood);
        this.name = name;
    }

    public PlayerAirPlane(float x, float y, Figure figure, boolean isShooting, String name) {
        super(x, y, figure, isShooting);
        this.name = name;
    }

    public PlayerAirPlane(float x, float y, Figure figure, boolean isShooting, int blood, String name) {
        super(x, y, figure, isShooting, blood);
        this.name = name;
    }

    @Override
    public Bullet shootBullet() {
        Bullet bullet = super.shootBullet();
        if (bullet == null) {
            return null;
        }
        bullet.setPlayBullet(true);
        return bullet;
    }

    @Override
    public void moving() {
        if (getX() >= App.boundRight.floatValue() - getFigure().getWidth()) {
            setX(App.boundRight.floatValue() - getFigure().getWidth());
        }

        if (getX() <= App.boundLeft.floatValue()) {
            setX(App.boundLeft.floatValue());
        }

        if (getY() <= App.boundUp.floatValue()) {
            setY(App.boundUp.floatValue());
        }

        if (getY() >= App.boundDown.floatValue() - getFigure().getHeight()) {
            setY(App.boundDown.floatValue() - getFigure().getHeight());
        }
    }

    @Override
    public void handleCollision(Model model) {

     if (model instanceof NpcAirPlane) {
         planeCollision((NpcAirPlane)model);
         minusBlood(1);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
