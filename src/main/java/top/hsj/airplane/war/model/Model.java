package top.hsj.airplane.war.model;

import top.hsj.airplane.war.App;
import top.hsj.airplane.war.constant.Figure;
import top.hsj.airplane.war.constant.Speed;
import top.hsj.airplane.war.util.MathUtils;

import java.util.List;
import java.util.Objects;

/**
 * @author hsj
 */
abstract public class Model {

    private static Long ID = 1L;
    private Long id;
    private float x, y;
    private Figure figure;
    private boolean openCollision;

    private boolean movingLeft;
    private boolean movingRight;
    private boolean movingUp;
    private boolean movingDown;
    private float speed = Speed.FOUR;

    private float collisionX, collisionY, collisionRadius;

    public Model(float x, float y, Figure figure) {
        this(x, y, figure, true);
    }

    public Model(float x, float y, Figure figure, boolean openCollision) {
        id = ID++;
        this.x = x;
        this.y = y;
        this.figure = figure;
        this.openCollision = openCollision;
        updateCollisionCenter();
        this.collisionRadius = (figure.getHeight() + figure.getWidth()) / 4;
    }

    public void update() {
        movingBase();
        updateCollisionCenter();
        Model collisionModel = this.collisionDetection(App.models);
        if (collisionModel != null ) {
            this.handleCollision(collisionModel);
        }
    }

    public void movingBase() {
        if(movingLeft) x -= speed;
        if(movingRight) x += speed;
        if(movingUp) y -= speed;
        if(movingDown) y += speed;
        moving();
    }

    public abstract void moving();


    /**
     * 碰撞处理
     * @param model
     */
    abstract public void handleCollision(Model model);

    /**
     * 计算碰撞中心
     */
    protected void updateCollisionCenter() {
        this.collisionX = this.x + this.figure.getWidth() / 2;
        this.collisionY = this.y + this.figure.getHeight() / 2;
    }

    /**
     * 碰撞检测
     * @param model 另一个碰撞中心
     * @return 是否碰撞
     */
    private Boolean collisionDetection(Model model) {
        if (this.equals(model)) {
            return false;
        }
        //碰撞中心的距离
        float distance = MathUtils.diff(collisionX, collisionY, model.getCollisionX(), model.getCollisionY());
        //两model相临时的距离
        float tangent = collisionRadius + model.collisionRadius - 10;
        return !(Math.signum(distance - tangent) > 0);
    }

    public Model collisionDetection(List<Model> models) {
        for (Model model : models) {
            if (collisionDetection(model)) {
                return model;
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Model model = (Model) o;
        return id.equals(model.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Figure getFigure() {
        return figure;
    }

    public void setFigure(Figure figure) {
        this.figure = figure;
    }

    public boolean isOpenCollision() {
        return openCollision;
    }

    public void setOpenCollision(boolean openCollision) {
        this.openCollision = openCollision;
    }

    public float getCollisionX() {
        return collisionX;
    }

    public void setCollisionX(float collisionX) {
        this.collisionX = collisionX;
    }

    public float getCollisionY() {
        return collisionY;
    }

    public void setCollisionY(float collisionY) {
        this.collisionY = collisionY;
    }

    public float getCollisionRadius() {
        return collisionRadius;
    }

    public void setCollisionRadius(float collisionRadius) {
        this.collisionRadius = collisionRadius;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getMovingLeft() {
        return movingLeft;
    }

    public void setMovingLeft(Boolean movingLeft) {
        this.movingLeft = movingLeft;
    }

    public Boolean getMovingRight() {
        return movingRight;
    }

    public void setMovingRight(Boolean movingRight) {
        this.movingRight = movingRight;
    }

    public Boolean getMovingUp() {
        return movingUp;
    }

    public void setMovingUp(Boolean movingUp) {
        this.movingUp = movingUp;
    }

    public Boolean getMovingDown() {
        return movingDown;
    }

    public void setMovingDown(Boolean movingDown) {
        this.movingDown = movingDown;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}
