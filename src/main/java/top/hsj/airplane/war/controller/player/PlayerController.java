package top.hsj.airplane.war.controller.player;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import top.hsj.airplane.war.model.plane.PlayerAirPlane;

import static javafx.scene.input.KeyEvent.KEY_PRESSED;


/**
 * @author hsj
 */
public class PlayerController implements EventHandler<KeyEvent> {
    private PlayerAirPlane player;

    public PlayerController(PlayerAirPlane player) {
        this.player = player;
    }

    @Override
    public void handle(KeyEvent event) {
        EventType<KeyEvent> eventType = event.getEventType();
        KeyCode keyCode = event.getCode();
        switch (keyCode) {
            case LEFT:
                player.setMovingLeft(isMove(eventType));
                break;
            case RIGHT:
                player.setMovingRight(isMove(eventType));
                break;
            case UP:
                player.setMovingUp(isMove(eventType));
                break;
            case DOWN:
                player.setMovingDown(isMove(eventType));
                break;
            case SPACE:
                if (eventType.equals(KEY_PRESSED)) {
                    player.setShooting(Boolean.TRUE);
                }
                break;
        }
    }
    private boolean isMove(EventType<KeyEvent> eventType) {
        return eventType.equals(KEY_PRESSED);
    }
}
