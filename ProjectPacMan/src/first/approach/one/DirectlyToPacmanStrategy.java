package first.approach.one;

/**
 * Created by Julian on 5/12/2017.
 * Contains Ghost 3's algorithm that goes directly to Pacman
 */
public class DirectlyToPacmanStrategy extends GhostMovementStrategy {


    /**
     * Constructor for Ghost movement Strategy
     * @param ghost
     */
    public DirectlyToPacmanStrategy( Ghost ghost )
    {
        super(ghost);
    }

    /**
     * Implements the algorithm for the Ghost to move directly to the Pacman Object
     */
    @Override
    public boolean movementAlgorithm()
    {
        Pacman pacman = Ghost.getPacman();
        int[] distanceFromPacman = ghost.getPosition().smallestDistanceBetweenPositions(pacman.getPosition());
        int xCompareTo = new Integer(distanceFromPacman[0]).compareTo( new Integer(0 ));
        int yCompareTo = new Integer(distanceFromPacman[1]).compareTo( new Integer(0));

        // Determine which x direction to move
        if( xCompareTo < 0 )
        {
            ghost.incrementX();
        }

        else if( xCompareTo > 0 )
        {
            ghost.decrementX();
        }

        // Determine which y direction to move
        if( yCompareTo < 0 )
        {
            ghost.incrementY();
        }

        else if( yCompareTo > 0 )
        {
            ghost.decrementY();
        }

        // TODO : Figure out how to choose which boolean to return
        return true;
    }


}
