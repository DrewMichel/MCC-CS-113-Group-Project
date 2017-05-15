package first.approach.one;

// NDR 2017.05.10 note that this class does not detect Collisions.
// It only stores them to make them easier to work with.
// Most importantly- it saves a snapshot of where the Entity is when it collides so that if we're
// trying to physically roll back an Entity at the time of collision we dont have to worry about
// multiple collisions resulting in Entity rolling back multiple times.
public final class Collision {

    private Entity sourceEntity; //You're analyzing the hit from this Entity's point of view
    private Entity collidedEntity;
    private Position2D previousPosition;
    private Position2D Position;

    /**
     *
     * @param sourceEntity Entity object that detected a collision
     * @param collidedEntity Entity object that was detected by the source
     */
    // Constructor method "Collision" to take in two Entity objects, the source and collided
    public Collision(Entity sourceEntity, Entity collidedEntity) {
        this.sourceEntity = sourceEntity;
        this.collidedEntity = collidedEntity;
        this.previousPosition = new Position2D(sourceEntity.getPreviousPosition());
        this.Position = new Position2D(sourceEntity.getPosition());
    }

    // get method "getSourceEntity" to return the Entity "sourceEntity"
    public Entity getSourceEntity() {
        return sourceEntity;
    }

    // get method "getCollidedEntity" to return the Entity "collidedEntity"
    public Entity getCollidedEntity() {
        return collidedEntity;
    }

    // get method "getPreviousPosition" to return the Position2D "previousPosition"
    public Position2D getPreviousPosition() {
        return previousPosition;
    }

    // get method "getPosition" to return the Position "Position"
    public Position2D getPosition() {
        return Position;
    }

    // get method "getSourceEntityType" to return the Entity "sourceEntity"
    public String getSourceEntityType() {
        return getEntityType(sourceEntity);
    }

    // get method "getCollidedEntityType" to return the Entity "collidedEntity"
    public String getCollidedEntityType() {
        return getEntityType(collidedEntity);
    }

    // get method "getEntityType" to return the Entity
    private String getEntityType(Entity entity) {
        return entity.getClass().getSimpleName();
    }
    
    // set method "setSourceEntity" to set the Entity "sourceEntity"
    public void setSourceEntity(Entity newSourceEntity) {
        this.sourceEntity = newSourceEntity;
    }
    
    // set method "setCollided" to set the Entity "collidedEntity"
    public void setCollidedEntity(Entity newCollidedEntity) {
        this.collidedEntity = newCollidedEntity;
    }
    
    // set method "setPreviousPosition" to set the Position2d "previousPosition"
    public void setPreviousPosition(Position2D newPreviousPosition) {
        this.previousPosition = newPreviousPosition;
    }
    
    // set method "setPosition" to set the Position2D "Posotion"
    public void setPosition(Position2D newPosition) {
        this.Position = newPosition;
    }
    
}
