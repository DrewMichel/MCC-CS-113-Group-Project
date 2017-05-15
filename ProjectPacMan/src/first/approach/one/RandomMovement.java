package first.approach.one;

import java.util.Random;

/**
 * Created by Julian on 5/13/2017.
 */
public class RandomMovement extends GhostMovementStrategy {

    public static final double SWITCH_UP_TIME = 2 ;
    private static Random random ;
    private int yChange , xChange;
    private long startTime ;

    /**
     * Constructor for Ghost random movement
     * @param ghost
     */
    public RandomMovement( Ghost ghost)
    {
        super( ghost);
        random = new Random( System.currentTimeMillis());
        startTime = 0 ;

    }

    /**
     * Makes the ghost teleport when it hits a wall
     */
    @Override
    protected void wallCollisionAction()
    {
        int newX , newY;

        newX = random.nextInt( 1110) + 20;
        newY = random.nextInt( 650) + 20;
        ghost.attemptMove( newX , newY );
        ghost.setHitWall(false);

    }

    /**
     * Implementation of random movement algorithm for Ghost's movement
     *
     * @return
     */
    @Override
    public boolean movementAlgorithm() {

        int newX , newY , chance, mod;
        int ghostX , ghostY ;
        double secondsMoved;

        ghostX = ghost.getPosition().getXPosition();
        ghostY = ghost.getPosition().getYPosition();

        if ( startTime == 0 )
        {
            startTime = System.currentTimeMillis();
        }

        secondsMoved = ( System.currentTimeMillis() - startTime ) /  1000.0 ;

        // If time to switch up the Ghost's movement
        if( secondsMoved > SWITCH_UP_TIME)
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

                // If the Ghost moves laterally
                if( chance % 2 == 0 )
                {

                    chance = random.nextInt(1000);
                    if( chance % 2 == 0)
                    {
                        xChange = 1;
                    }

                    else
                    {
                        xChange = -1;
                    }

                    yChange = 0;
                }

                // If the Ghost will move vertically
                else
                {
                    chance = random.nextInt(1000);
                    if( chance % 2 == 0)
                    {
                        yChange = 1;
                    }

                    else
                    {
                        yChange = -1 ;
                    }

                    xChange = 0;
                }


            }

            // Resets motion time
            startTime = System.currentTimeMillis();
        }

        // Moves the Ghost
        newX = ghostX + xChange;
        newY = ghostY + yChange;

        ghost.attemptMove( newX , newY);



        return false;
    }
}
