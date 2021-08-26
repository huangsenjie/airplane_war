package top.hsj.airplane.war.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import top.hsj.airplane.war.App;
import top.hsj.airplane.war.constant.Figure;
import top.hsj.airplane.war.model.bullet.Bullet;
import top.hsj.airplane.war.model.plane.AirPlane;
import top.hsj.airplane.war.model.plane.PlayerAirPlane;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author hsj
 */
public class View extends Canvas {
    private GraphicsContext gc;
    private List<AnimateFrames> animateFramesList = new CopyOnWriteArrayList<>();

    public View() {
        this.gc = getGraphicsContext2D();
    }

    public void update(long now) {
        gc.clearRect(getLayoutX(), getLayoutY(), getWidth(), getHeight());

        App.models.forEach(model -> {
            if (model instanceof AirPlane) {
                AirPlane airPlane = (AirPlane) model;
                Bullet bullet = airPlane.shootBullet();
                if (null != bullet) {
                    App.models.add(bullet);
                }
                if (airPlane.isDie()) {
                    Figure[] figures = {Figure.NPC_0_DIE_1, Figure.NPC_0_DIE_2, Figure.NPC_0_DIE_3,Figure.NPC_0_DIE_4};
                    AnimateFrames animateFrames = new AnimateFrames(airPlane.getCollisionX(), airPlane.getCollisionY(), figures, 100 * 1000000, now);
                    animateFramesList.add(animateFrames);
                    App.models.remove(airPlane);
                }
                if (airPlane instanceof PlayerAirPlane) {
                    gc.setFont(Font.getDefault());
                    gc.fillText(String.valueOf(airPlane.getBlood()), App.pane.getWidth() * 0.01, App.pane.getHeight());
                    gc.fill();
                }
            } else if (model instanceof Bullet) {
                Bullet bullet = (Bullet) model;
                if (bullet.isHit()) {
                    Figure[] figures = {Figure.BOOM_BULLET_1, Figure.BOOM_BULLET_2,
                            Figure.BOOM_BULLET_3, Figure.BOOM_BULLET_4, Figure.BOOM_BULLET_5};
                    AnimateFrames animateFrames = new AnimateFrames(bullet.getCollisionX(), bullet.getCollisionY(), figures, 60 * 1000000, now);
                    animateFramesList.add(animateFrames);
                    App.models.remove(bullet);
                }
            }

            Figure figure = model.getFigure();
            Image image = new Image(figure.getImagePath(), figure.getWidth(), figure.getHeight(), true, true);
            gc.drawImage(image, model.getX(), model.getY(), image.getWidth(), image.getHeight());
        });
    }

    public void animatePlay(long now) {
        animateFramesList.forEach(frames -> {
            Figure figure = frames.getFrames(now);
            Image image = new Image(figure.getImagePath(), figure.getWidth(), figure.getHeight(), true, true);
            gc.drawImage(image, frames.getX() - figure.getWidth() / 2, frames.getY() - figure.getHeight() / 2);
            if (frames.isEnd()) {
                animateFramesList.remove(frames);
            }
        });
    }
}
