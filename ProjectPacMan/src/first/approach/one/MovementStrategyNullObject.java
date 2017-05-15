package first.approach.one;

/**
 * Created by Julian on 5/12/2017.
 *
 * Creates a "Null Object" which is used as a default value for a GhostMovementStrategy
 * instead of null.
 * - This makes sure the program doesn't crash if the GhostMovementStrategy accidently wasn't set
 * - This also makes it easier for the programmer so we don't have to keep checking for null when using the
 *   GhostMovementStrategy objects
 */
public class MovementStrategyNullObject extends GhostMovementStrategy {

    /**
     * Constructor for no movement null replacement object
     */
    public MovementStrategyNullObject( Ghost ghost)
    {
          this.ghost = ghost;
          setHasMovement(false);
    }

    /**
     * Default implementation for no movement set strategy object
     */
    public boolean movementAlgorithm()
    {
        // TODO : May change what the null object does for the Ghost's movement
        // TODO: May change this to false so we know it wasn't implemented
        return true;
    }
}
