package top.hsj.airplane.war.model.bullet;

import top.hsj.airplane.war.App;
import top.hsj.airplane.war.constant.Figure;
import top.hsj.airplane.war.model.Model;
import top.hsj.airplane.war.model.plane.NpcAirPlane;
import top.hsj.airplane.war.model.plane.PlayerAirPlane;

/**
 * @author hsj
 */
public class Bullet extends Model {
    private static boolean openBulletCollision = true;
    private int attack;
    private boolean isHit = false;
    private boolean isPlayBullet;

    public Bullet(float x, float y, Figure figure, int attack) {
        super(x, y, figure);
        this.attack = attack;
    }

    public Bullet(float x, float y, Figure figure, boolean openCollision, int attack) {
        super(x, y, figure, openCollision);
        this.attack = attack;
    }

    @Override
    public void moving() {
        if (this.getX() < App.boundLeft.subtract(this.getFigure().getWidth()).floatValue() ||
                this.getX() > App.boundRight.add(this.getFigure().getWidth()).floatValue() ||
                this.getY() < App.boundUp.subtract(this.getFigure().getHeight()).floatValue() ||
                this.getY() > App.boundDown.add(this.getFigure().getHeight()).floatValue()
        ) {
            isHit = true;
        }
    }

    @Override
    public void handleCollision(Model model) {
        if (model instanceof PlayerAirPlane) {
            if (isPlayBullet) {
                return;
            }
            this.isHit = true;
            ((PlayerAirPlane) model).minusBlood(this.getAttack());
        }
        if (model instanceof NpcAirPlane) {
            if (!isPlayBullet) {
                return;
            }
            this.isHit = true;
            ((NpcAirPlane) model).minusBlood(this.getAttack());
            //((NpcAirPlane) model).minusBlood(attack);
        }
        if (model instanceof Bullet && openBulletCollision) {
            //TODO 子弹与子弹碰撞
        }
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public static boolean isOpenBulletCollision() {
        return openBulletCollision;
    }

    public static void setOpenBulletCollision(boolean openBulletCollision) {
        Bullet.openBulletCollision = openBulletCollision;
    }

    public boolean isHit() {
        return isHit;
    }

    public void setHit(boolean hit) {
        isHit = hit;
    }

    public boolean isPlayBullet() {
        return isPlayBullet;
    }

    public void setPlayBullet(boolean playBullet) {
        isPlayBullet = playBullet;
    }
}
