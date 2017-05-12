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
 *
 * Not implemented
 */
public class Ghost extends Entity
{
    private static ArrayList<Wall> walls = new ArrayList<>();
    private static Pacman pacman;
    private GhostMovementStrategy ghostMovementStrategy  = new MovementStrategyNullObject();

    public Ghost()
    {
        super(new Position2D(), Color.CYAN, true, Shape.CIRCLE);

        this.setDirection(Direction.SOUTH);
    }

    public Ghost(Color color)
    {
        super(new Position2D(), color, true, Shape.CIRCLE);

        this.setDirection(Direction.SOUTH);
    }

    public Ghost(Position2D pos, Color color, boolean canMove, Shape shape)
    {
        super(pos, color, canMove, shape);

        this.setDirection(Direction.SOUTH);
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
        ghostMovementStrategy.move();
        return true;

    }
}