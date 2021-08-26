package top.hsj.airplane.war.util;

import javafx.scene.effect.Light;

/**
 * @author hsj
 */
public class MathUtils {
    /**
     * 两点距离
     */
    public static float diff(float startX, float startY, float endX, float endY) {
        float width = Math.abs(endX - startX);
        float height = Math.abs(endY - startY);
        return (float)Math.sqrt(Math.pow(width, 2) + Math.pow(height, 2));
    }

    public static float slope(float x1, float y1, float x2, float y2) {
        if (y2 - y1 == 0.0f || x2 - x1 == 0.0f) {
            return 0;
        }
        return (y2 - y1) / (x2 - x1);
    }

    public static float sin(float opposite, float hypotenuse) {
        return opposite / hypotenuse;
    }

    public static float cos(float adjacent, float hypotenuse) {
        return adjacent / hypotenuse;
    }
}
