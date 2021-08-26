package top.hsj.airplane.war.view;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.ReadOnlyFloatWrapper;
import top.hsj.airplane.war.constant.Figure;

/**
 * @author hsj
 */
public class AnimateFrames {
    private FloatProperty x, y;
    private Figure[] frames;
    private long during;
    private int cycleCount = 1;
    private long startTime;
    //标记当前是否处于某次循环中
    private boolean cycleFlag = false;

    public AnimateFrames(float x, float y, Figure[] frames, long during, long startTime) {
        this.x = new ReadOnlyFloatWrapper(x);
        this.y = new ReadOnlyFloatWrapper(y);
        this.frames = frames;
        this.during = during;
        this.startTime = startTime;
    }

    public AnimateFrames(Figure[] frames, long during) {
        this(frames, during, 1);
    }

    public AnimateFrames(Figure[] frames, long during, int cycleCount) {
        if (during < 17 * 100000) {
            throw new RuntimeException("每张图片显示时间不能小于一帧");
        }
        this.frames = frames;
        this.during = during;
        this.cycleCount = cycleCount;
    }

    public Figure getFrames(long currentTime) {
        long time = currentTime - startTime;
        int index = (int)(time % (frames.length * during) / during);
        if(index == 0 && !cycleFlag){
            cycleCount--;
            cycleFlag = true;
        }
        if (index == frames.length - 1) {
            cycleFlag = false;
        }
        /**
         * 防止先渲染再判断死亡逻辑的情况
         */
        if (isEnd()) {
            return frames[frames.length - 1];
        }
        return frames[index];
    }

    public boolean isEnd() {
        if (cycleCount < 0) {
            return true;
        }
        return false;
    }

    public float getX() {
        return x.get();
    }

    public FloatProperty xProperty() {
        return x;
    }

    public float getY() {
        return y.get();
    }

    public FloatProperty yProperty() {
        return y;
    }
}
