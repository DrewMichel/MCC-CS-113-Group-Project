package first.approach.one;

import java.util.Random;

/**
 * Created by Julian on 5/15/2017.
 */
public class MatchKeyStroke extends GhostMovementStrategy {

    /**
     * Constructor for Ghost random movement
     * @param ghost
     */
    public MatchKeyStroke( Ghost ghost)
    {
        super( ghost);
    }
    /**
     * Implementation of random movement algorithm for Ghost's movement
     *
     * @return
     */
    @Override
    public boolean movementAlgorithm() {

        Random random = new Random(System.currentTimeMillis());

        int newX , newY , chance, mod;
        Pacman pacman = Ghost.getPacman();
        Entity.Direction keyStroke = pacman.getDirection();

        // If Ghost is far away from pacman then match key stroke
        if( ghost.getPosition().distance(pacman.getPosition()) > 250 )
        {
            switch (keyStroke)
            {
                case NORTH:
                    return ghost.incrementY();
                case EAST:
                    return ghost.incrementX();
                case SOUTH:
                    return ghost.decrementY();
                case WEST:
                    return ghost.decrementX();
                default:
                    return false;
            }

        }

        // If Ghost is close to Pacman then go directly to him
        else
        {
            ghost.setGhostMovementStrategy( new DirectlyToPacmanStrategy(ghost));
            ghost.attemptMove();
            ghost.setGhostMovementStrategy(this);
        }
        return true;
    }
}
