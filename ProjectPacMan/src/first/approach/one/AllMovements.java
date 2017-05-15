package first.approach.one;

import first.approach.one.GhostMovementStrategy;

import java.util.Random;

/**
 * Created by Julian on 5/15/2017.
 */
public class AllMovements extends GhostMovementStrategy {

    private static Random random = new Random( System.currentTimeMillis());
    public static double CHANGE_INTERVAL = 3.2;
    private GhostMovementStrategy currentMovement ;
    private long startTime ;

    /**
     * Constructor for Ghost movement that uses all movements
     * @param ghost
     */
    public AllMovements( Ghost ghost)
    {
        super( ghost);
        startTime = 0;
    }

    /**
     * The movement will periodically change between all the different Ghost movement algorithms
     *
     * @return
     */
    @Override
    public boolean movementAlgorithm() {

        int chance = random.nextInt(75);
        double secondsPassed ;

        secondsPassed = ( System.currentTimeMillis() - startTime ) / 1000.0 ;

        // If enough time passed to randomlly change the algorithm
        if( startTime == 0 || secondsPassed > CHANGE_INTERVAL )
        {
            if( chance > - 1 && chance < 25 )
            {
                currentMovement = new DirectlyToPacmanStrategy(ghost);
            }

            else if ( chance > 24 && chance < 50)
            {
                currentMovement = new RandomMovement(ghost);
            }

            else if ( chance > 49 )
            {
                currentMovement = new MatchKeyStroke(ghost);
            }

            startTime = System.currentTimeMillis();
        }

        return currentMovement.move();

    }
}
