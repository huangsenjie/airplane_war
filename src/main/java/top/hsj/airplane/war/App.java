package top.hsj.airplane.war;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import top.hsj.airplane.war.constant.Figure;
import top.hsj.airplane.war.controller.player.PlayerController;
import top.hsj.airplane.war.model.Model;
import top.hsj.airplane.war.model.buff.AirPlaneBuff;
import top.hsj.airplane.war.model.plane.NpcAirPlane;
import top.hsj.airplane.war.model.plane.PlayerAirPlane;
import top.hsj.airplane.war.view.View;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author hsj
 */
public class App extends Application {
    public static Stage stage;
    public static Scene scene;
    public static Pane pane;
    public static View view;

    public static DoubleProperty boundLeft = new ReadOnlyDoubleWrapper();

    public static DoubleProperty boundRight = new ReadOnlyDoubleWrapper();

    public static DoubleProperty boundUp = new ReadOnlyDoubleWrapper();

    public static DoubleProperty boundDown = new ReadOnlyDoubleWrapper();

    public static List<Model> models = new CopyOnWriteArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = new Pane();
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        App.stage = primaryStage;
        App.scene = scene;
        App.pane = pane;
        initStage();
        primaryStage.show();
    }

    private void initStage() {
        stage.setWidth(400);
        stage.setHeight(800);
        boundLeft.setValue(0);
        boundRight.bind(App.pane.widthProperty());
        boundUp.setValue(0);
        boundDown.bind(App.pane.heightProperty());

        Figure playFigure = Figure.PLANE_DEFAULT;
        PlayerAirPlane playerAirPlane = new PlayerAirPlane(190, 600, playFigure, true, 100, "one");
        registerController(playerAirPlane);
        models.add(playerAirPlane);

        View view = new View();
        App.view = view;
        view.widthProperty().bind(stage.widthProperty());
        view.heightProperty().bind(stage.heightProperty());

        pane.getChildren().addAll(view);


        AnimationTimer modelTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                models.forEach(model -> {
                    model.update();
                });
            }
        };
        modelTimer.start();

        AnimationTimer viewTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                view.update(now);
                view.animatePlay(now);
            }
        };
        viewTimer.start();

        AnimationTimer gcTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (now % 1000 == 0) {
                    System.gc();
                }
            }
        };
        gcTimer.start();

        NpcAirPlane npcAirPlane = new NpcAirPlane(175.0f, -80.0f, Figure.NPC_0);
        npcAirPlane.setMovingDown(true);
        npcAirPlane.setAirPlaneBuff(AirPlaneBuff.NPC_0);
        npcAirPlane.setBlood(10);
        npcAirPlane.setSpeed(1);
        NpcAirPlane npcAirPlane2 = new NpcAirPlane(175.0f, -80.0f, Figure.NPC_0);
        npcAirPlane2.setMovingDown(true);
        npcAirPlane2.setAirPlaneBuff(AirPlaneBuff.NPC_0);
        npcAirPlane2.setBlood(10);
        npcAirPlane2.setSpeed(2);
        models.add(npcAirPlane);
        models.add(npcAirPlane2);
    }

    private void registerController(PlayerAirPlane playerAirPlane) {
        scene.addEventHandler(KeyEvent.KEY_PRESSED, new PlayerController(playerAirPlane));
        scene.addEventHandler(KeyEvent.KEY_RELEASED, new PlayerController(playerAirPlane));
    }


}
