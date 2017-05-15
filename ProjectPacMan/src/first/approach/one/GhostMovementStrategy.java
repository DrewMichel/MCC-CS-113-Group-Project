package first.approach.one;

/**
 * Created by Julian on 5/12/2017.
 * Base class for Ghost movement algorithms
 * - Each Ghost algorithm will have its own Strategy class that extends this one
 */
public abstract class GhostMovementStrategy {

    protected Ghost ghost;
    protected double closeDistance;
    protected boolean hasMovement = true ;
    private Position2D previousPosition ;


    /**
     * Abstract constructor
     * @param ghost
     */
    public GhostMovementStrategy( Ghost ghost)
    {
        this.ghost = ghost;
        this.closeDistance = 250;
    }
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
     * Sets the action taken when a Ghost hits a wall
     * <Pre-Conditions>
     *     By default it performs the same movement algorithm.
     *     It has to be overriden to do specific tasks.
     *     When overriden if then change the hit wall
     *     boolean acoordingly during te implementation
     * </Pre-Conditions>
     */
    protected void wallCollisionAction()
    {
        movementAlgorithm();
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
     * Sets the distance that makes Pacman close to the Ghost
     * @param closeDistance
     */
    protected final void setCloseDistance( double closeDistance)
    {
        this.closeDistance = closeDistance;
    }

    /**
     * Checks if Pacman is close to the Ghost
     * <notes>
     *     Uses a adjustable distance to see if Pacman is close
     * </notes>
     * @return boolean true if Pacman is close , else returns false
     */
    protected final boolean isPacmanClose()
    {

        Pacman pacman = Ghost.getPacman();
        return ghost.getPosition().distance(pacman.getPosition()) <= closeDistance ;

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

            // Saves the Ghost's current position
            moveResult = savePreviousPosition();

            // Chooses the action when the ghost hits the wall
            if( ghost.hitWall() )
            {
                wallCollisionAction();
            }


            // Chooses the action when the Ghost hasn't collided with a wall
            else
            {
                // When Pacman is close try to get him
                if( !( this instanceof  DirectlyToPacmanStrategy ) && isPacmanClose())
                {
                    ghost.setGhostMovementStrategy(new DirectlyToPacmanStrategy(ghost));
                    ghost.attemptMove();
                    ghost.setGhostMovementStrategy(this);
                }

                else
                {
                    // Moves the Ghost using the implemented algorithm
                    moveResult &= movementAlgorithm();
                }

            }


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
