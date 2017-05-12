package first.approach.one;

/**
 * Created by Julian on 5/12/2017.
 * Base class for Ghost movement algorithms
 * - Each Ghost algorithm will have its own Strategy class that extends this one
 */
public abstract class GhostMovementStrategy {

    protected Ghost ghost;

    public abstract boolean move();

}
