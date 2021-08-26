package top.hsj.airplane.war.constant;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import top.hsj.airplane.war.App;
import top.hsj.airplane.war.model.Model;

/**
 * @author hsj
 */
public enum NpcMoveTrack {

    /**
     * Z型曲线
     */
    Z_TYPE {
        @Override
        public void move(Model model) {
            float y = model.getY();
            float k;
            float b = 0;
            if (y < 250) {
                k = 2.0f;
            } else if (y < 550) {
                k = -2.0f;
                b = 1000;
            } else {
                k = 2.0f;
                b = -1200;
            }
            model.setX(k * y + b);
        }
    };

    abstract public void move(Model model);
}
