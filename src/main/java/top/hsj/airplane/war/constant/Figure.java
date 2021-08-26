package top.hsj.airplane.war.constant;

/**
 * @author hsj
 */
public enum  Figure {

    PLANE_DEFAULT("image/plane/plane_default.png", 50, 64),
    NPC_0("image/plane/npc_0.png", 51, 39),
    NPC_0_DIE_1("image/plane/npc_0_die_1.png", 51, 39),
    NPC_0_DIE_2("image/plane/npc_0_die_2.png", 51, 39),
    NPC_0_DIE_3("image/plane/npc_0_die_3.png", 51, 39),
    NPC_0_DIE_4("image/plane/npc_0_die_4.png", 47, 39),
    BULLET_DEFAULT("image/bullet/bullet_default.png", 30, 30),
    BOOM_BULLET_1("image/boom/boom_bullet_0.png", 51 / 5, 39 / 5),
    BOOM_BULLET_2("image/boom/boom_bullet_0.png", 51 / 4, 39 / 4),
    BOOM_BULLET_3("image/boom/boom_bullet_0.png", 51 / 3, 39 / 3),
    BOOM_BULLET_4("image/boom/boom_bullet_0.png", 51 / 2, 39 / 2),
    BOOM_BULLET_5("image/boom/boom_bullet_0.png", 51, 39),
    ;

    private String imagePath;
    private float width, height;

    Figure(String imagePath, float width, float height) {
        this.imagePath = imagePath;
        this.width = width;
        this.height = height;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }
}
