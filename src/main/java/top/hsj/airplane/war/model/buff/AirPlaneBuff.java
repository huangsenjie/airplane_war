package top.hsj.airplane.war.model.buff;

import top.hsj.airplane.war.constant.Speed;
import top.hsj.airplane.war.constant.Figure;

/**
 * @author hsj
 */
public enum AirPlaneBuff {

    DEFAULT(Figure.BULLET_DEFAULT, Figure.PLANE_DEFAULT, 10, Speed.EIGHT),
    NPC_0(Figure.BULLET_DEFAULT, Figure.NPC_0, 70, Speed.FOUR);

    private Figure bullet;
    private Figure player;
    private int shootInterval;
    private float shootSpeed;

    AirPlaneBuff(Figure bullet, Figure player, int shootInterval, float shootSpeed) {
        this.bullet = bullet;
        this.player = player;
        this.shootInterval = shootInterval;
        this.shootSpeed = shootSpeed;
    }

    public Figure getBullet() {
        return bullet;
    }

    public Figure getPlayer() {
        return player;
    }

    public void setPlayer(Figure player) {
        this.player = player;
    }

    public int getShootInterval() {
        return shootInterval;
    }

    public float getShootSpeed() {
        return shootSpeed;
    }
}
