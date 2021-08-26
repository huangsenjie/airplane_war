package top.hsj.airplane.war.model.plane;

import top.hsj.airplane.war.App;
import top.hsj.airplane.war.constant.Figure;
import top.hsj.airplane.war.constant.NpcMoveTrack;
import top.hsj.airplane.war.model.Model;
import top.hsj.airplane.war.model.bullet.Bullet;

/**
 * @author hsj
 */
public class NpcAirPlane extends AirPlane {

    @Override
    public void moving() {
        NpcMoveTrack.Z_TYPE.move(this);
        if (this.getY() > App.boundDown.add(this.getFigure().getHeight()).floatValue()) {
             this.setBlood(-1);
        }
    }

    @Override
    public Bullet shootBullet() {
        Bullet bullet = super.shootBullet();
        if (bullet == null) {
            return null;
        }
        Figure figure = getAirPlaneBuff().getBullet();
        bullet.setPlayBullet(false);
        bullet.setMovingUp(false);
        bullet.setMovingDown(true);
        bullet.setY(this.getY() + figure.getHeight());
        return bullet;
    }

    @Override
    public void handleCollision(Model model) {
       if (model instanceof PlayerAirPlane) {
           planeCollision((PlayerAirPlane)model);
           minusBlood(1);
       }
    }

    public NpcAirPlane(float x, float y, Figure figure) {
        super(x, y, figure);
    }

    public NpcAirPlane(float x, float y, Figure figure, int blood) {
        super(x, y, figure, blood);
    }

    public NpcAirPlane(float x, float y, Figure figure, boolean isShooting) {
        super(x, y, figure, isShooting);
    }

    public NpcAirPlane(float x, float y, Figure figure, boolean isShooting, int blood) {
        super(x, y, figure, isShooting, blood);
    }
}
