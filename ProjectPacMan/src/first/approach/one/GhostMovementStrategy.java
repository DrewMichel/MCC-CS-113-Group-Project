package first.approach.one;

/**
 * Created by Julian on 5/12/2017.
 * Base class for Ghost movement algorithms
 * - Each Ghost algorithm will have its own Strategy class that extends this one
 */
public abstract class GhostMovementStrategy {

    protected Ghost ghost;
    protected boolean hasMovement = true ;
    private Position2D previousPosition ;


    /**
     * Sets whether the Ghost object can currently move
     * @param canCurrentlyMove if the Ghost object is currently moving
     */
    protected final void setHasMovement( boolean canCurrentlyMove )
    {
        hasMovement = canCurrentlyMove;
    }

    /**
     * Saves the Ghost object's previous position
     * <Pre-Conditions>
     *     The Ghost instance variable isn't null .
     *     The method is called in the template method.
     * </Pre-Conditions>
     * @return boolean true if the previous position isn't null, else returns false
     */
    private boolean savePreviousPosition()
    {

        Position2D currentPosition = ghost.getPosition();

        // Check if there is a current position
        if( currentPosition == null )
        {
            return false ;
        }

        else
        {
            previousPosition = new Position2D( currentPosition);

            return previousPosition != null;
        }

    }

    /**
     * Sets the Ghost's previous position to the one before the movement algorithm started
     * <Pre-Conditions>
     *     The Ghost instance variable isn't null.
     *     The method is called in the template method.
     * </Pre-Conditions>
     * @return boolean true if the Ghost previous position was successfully set, else returns false
     */
    private boolean setGhostPreviousPosition()
    {
        return ghost.setPreviousPosition( previousPosition );
    }

    /**
     * Abstract method that holds the Ghost's object's movement algorithm
     * @return
     */
    public abstract boolean movementAlgorithm();

    /**
     * Template method that holds the project's algorithm for moving the Ghost and saving it's position
     * @return a boolean true if the move was successful, else returns false
     */
    protected final boolean move() {

        boolean moveResult ;

        // If the Ghost isn't moving then don't move it
        if( hasMovement )
        {

            // If the Ghost isn't valid
            if (ghost == null) {
                return false;
            }

            // TODO : Have the program send error messages to another component for each kind of unsuccessful result

            // Saves the Ghost's current position
            moveResult = savePreviousPosition();

            // Moves the Ghost using the implemented algorithm
            moveResult &= movementAlgorithm();

            // Sets the Ghost's previous position to the saved start position
            moveResult &= setGhostPreviousPosition();

            // Returns whether or not the move was successful
            return moveResult;

        }

        else
        {
            return false ;
        }
    }

}
