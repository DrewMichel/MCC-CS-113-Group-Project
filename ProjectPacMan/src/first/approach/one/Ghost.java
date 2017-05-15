package first.approach.one;

import java.util.ArrayList;
import java.awt.Color;
import java.util.List;

/**
 *
 * Created by Andrew Michel,
 *
 * on 4/24/2017
 *
 * MiraCosta College - CS 113 Monday 5:30-9:20
 */
public class Ghost extends Entity
{
    private static ArrayList<Wall> walls = new ArrayList<>();
    private static Pacman pacman;
    private GhostMovementStrategy ghostMovementStrategy  = new MovementStrategyNullObject( this);
    private boolean hitWall;

    public Ghost()
    {
        this(new Position2D(), Color.CYAN, true, Shape.CIRCLE);
        hitWall = false;
    }

    public Ghost(Color color)
    {
        this(new Position2D(), color, true, Shape.CIRCLE);
        hitWall = false;
    }

    public Ghost(Position2D pos, Color color, boolean canMove, Shape shape)
    {
        super(pos, color, canMove, shape);

        this.setDirection(Direction.SOUTH);
        hitWall = false;
    }

    /**
     * Sets whether the Ghost object is currently moving
     * @param canCurrentlyMove whether or not the Ghost is moving or staying still
     */
    public void canCurrentlyMove( boolean canCurrentlyMove )
    {
        ghostMovementStrategy.setHasMovement( canCurrentlyMove );
    }
    /**
     * Sets the Ghost's movement algorithm
     * @param movementStrategy The object that has the Ghost movement algorithm
     */
    public void setGhostMovementStrategy( GhostMovementStrategy movementStrategy)
    {
        ghostMovementStrategy = movementStrategy;
    }


    /**
     * Sets if the Ghost hit a wall
     * @param hitWall
     */
    public void setHitWall( boolean hitWall)
    {
        this.hitWall = hitWall;
    }

    /**
     * Checks if the Ghost hit a wall
     * @return
     */
    public boolean hitWall()
    {
        return hitWall;
    }

    /**
     * Gets the Pacman object all the Ghost objects are chasing
     * @return
     */
    public static Pacman getPacman()
    {
        return pacman;
    }

    /**
     * Sets the Pacman object all the Ghost objects are chasing
     * @param playerPacman
     */
    public static void setPacman( Pacman playerPacman )
    {
        pacman = playerPacman;
    }

    /**
     * Stores the walls in the PacMan game for ease of computing movements
     * @param gameWalls A List containing the Walls in the current game
     */
    public static void setWalls( List<Wall> gameWalls )
    {
        walls.addAll(gameWalls);
    }

    /**
     * Moves the Ghost in the most efficient path to get to PacMan
     * <notes>
     *     Uses the algorithm specified by the selected GhostMovementStrategy object
     * </notes>
     * @return
     */
    @Override
    public boolean attemptMove()
    {
        return ghostMovementStrategy.move();


    }
}