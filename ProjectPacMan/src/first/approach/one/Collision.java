package first.approach.one;

// NDR 2017.05.10 note that this class does not detect Collisions.
// It only stores them to make them easier to work with.
public final class Collision {

    private final Entity sourceEntity; //You're analyzing the hit from this Entity's point of view
    private final Entity collidedEntity;
    private final Position2D previousPosition;
    private final Position2D Position;

    public Collision(Entity sourceEntity, Entity collidedEntity) {
        this.sourceEntity = sourceEntity;
        this.collidedEntity = collidedEntity;
        this.previousPosition = new Position2D(sourceEntity.getPreviousPosition());
        this.Position = new Position2D(sourceEntity.getPosition());
    }

    public Entity getSourceEntity() {
        return sourceEntity;
    }

    public Entity getCollidedEntity() {
        return collidedEntity;
    }

    public Position2D getPreviousPosition() {
        return previousPosition;
    }

    public Position2D getPosition() {
        return Position;
    }

    public String getSourceEntityType() {
        return getEntityType(sourceEntity);
    }

    public String getCollidedEntityType() {
        return getEntityType(collidedEntity);
    }

    private String getEntityType(Entity entity) {
        return entity.getClass().getSimpleName();
    }

    //might continue this to have flags for common stuff like wall collisions or ghost collisions. idk yet.

}
