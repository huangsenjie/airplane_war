package top.hsj.airplane.war.model.plane;

import top.hsj.airplane.war.constant.Figure;
import top.hsj.airplane.war.model.Model;
import top.hsj.airplane.war.model.buff.AirPlaneBuff;
import top.hsj.airplane.war.model.buff.BulletBuff;
import top.hsj.airplane.war.model.bullet.Bullet;
import top.hsj.airplane.war.util.MathUtils;

/**
 * @author hsj
 */
abstract public class AirPlane extends Model {
    private boolean isShooting;
    private int blood;
    private int shootSpeed = 50;

    private AirPlaneBuff airPlaneBuff = AirPlaneBuff.DEFAULT;

    private BulletBuff bulletBuff = BulletBuff.SINGLE;

    public AirPlane(float x, float y, Figure figure) {
        this(x, y, figure, true, 20);
    }

    public AirPlane(float x, float y, Figure figure, int blood) {
        this(x, y, figure, true, blood);
    }

    public AirPlane(float x, float y, Figure figure, boolean isShooting) {
        this(x, y, figure, isShooting, 20);
    }

    public AirPlane(float x, float y, Figure figure, boolean isShooting, int blood) {
        super(x, y, figure);
        this.isShooting = isShooting;
        this.blood = blood;
    }



    public Bullet shootBullet() {
        if (shootSpeed-- <= 0) {
            shootSpeed = airPlaneBuff.getShootInterval();
        } else {
            return null;
        }
        Figure figure = airPlaneBuff.getBullet();
        Bullet bullet = new Bullet(this.getX() + getFigure().getWidth() / 2 - figure.getWidth() / 2,
                this.getY() - figure.getHeight(), airPlaneBuff.getBullet(), 1);
        bullet.setMovingUp(true);
        bullet.setSpeed(airPlaneBuff.getShootSpeed());
        return bullet;
    }

    protected void planeCollision(AirPlane airPlane) {
        float opposite = Math.abs(airPlane.getCollisionY() - this.getCollisionY());
        float adjacent = Math.abs(airPlane.getCollisionX() - this.getCollisionX());
        float hypotenuse = MathUtils.diff(
                airPlane.getCollisionX(), airPlane.getCollisionY(),
                this.getCollisionX(), this.getCollisionY()
        );
        float sin = MathUtils.sin(opposite, hypotenuse);
        float cos = MathUtils.cos(adjacent, hypotenuse);
        float moveX = this.getCollisionRadius() * cos;
        float moveY = this.getCollisionRadius() * sin;
        float targetX, targetY;
        if (this.getCollisionX() < airPlane.getCollisionX()) {
            targetX = getX() - moveX;
        } else {
            targetX = getX() + moveX;
        }

        if (this.getCollisionY() < airPlane.getCollisionY()) {
            targetY = getY() - moveY;
        } else {
            targetY = getY() + moveY;
        }
        setX(targetX);
        setY(targetY);
    }


    public boolean isDie() {
        return this.blood <= 0;
    }

    public void minusBlood(float value) {
        this.blood -= value;
    }

    public void plusBlood(float value) {
        this.blood += value;
    }

    public boolean isShooting() {
        return isShooting;
    }

    public void setShooting(boolean shooting) {
        isShooting = shooting;
    }

    public int getBlood() {
        return blood;
    }

    public void setBlood(int blood) {
        this.blood = blood;
    }

    public AirPlaneBuff getAirPlaneBuff() {
        return airPlaneBuff;
    }

    public void setAirPlaneBuff(AirPlaneBuff airPlaneBuff) {
        this.airPlaneBuff = airPlaneBuff;
    }

    public BulletBuff getBulletBuff() {
        return bulletBuff;
    }

    public void setBulletBuff(BulletBuff bulletBuff) {
        this.bulletBuff = bulletBuff;
    }
}
