package first.approach.one;

import java.util.Random;

/**
 * Created by Julian on 5/13/2017.
 */
public class RandomMovement extends GhostMovementStrategy {

    /**
     * Constructor for Ghost random movement
     * @param ghost
     */
    public RandomMovement( Ghost ghost)
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

        // If the ghost hit a wall
        if( ghost.hitWall() )
        {
            newX = random.nextInt( 1110) + 20;
            newY = random.nextInt( 650) + 20;
            ghost.attemptMove( newX , newY );
            ghost.setHitWall(false);
        }

        else
        {
            // Sets the random chance the Ghost will teleport
            chance = random.nextInt( 100001);
            mod =random.nextInt(11) + 1;

            // If the Ghost will randomly teleport
            if( chance  < 50 )
            {
                newX = random.nextInt( 1110) + 20;
                newY = random.nextInt( 650) + 20;

                ghost.attemptMove( newX , newY);
            }

            // The Ghost moves randomly
            else
            {
                if( chance % 8 == 0)
                {
                    ghost.incrementX();
                }

                else
                {
                    ghost.decrementX();
                }

                chance = random.nextInt(50);

                if( chance % 3 == 0)
                {
                    ghost.incrementY();
                }

                else
                {
                    ghost.decrementY();
                }
            }
        }

        // TODO : Decide whether to move to random points on the Graph or random directions from current position
        return false;
    }
}
