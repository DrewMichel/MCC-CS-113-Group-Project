package first.approach.one;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;

/**
 *
 * Created by Andrew Michel,
 *
 * on 4/24/2017
 *
 * MiraCosta College - CS 113 Monday 5:30-9:20
 *
 * Player class
 * Track player lives here or SystemManager(ideal?)
 */
public class Pacman extends Entity
{
    private HashMap<Boolean, Integer> pacState;

    public Pacman()
    {
        this(new Position2D(), Color.YELLOW, true, Shape.CIRCLE);

        this.setDirection(Direction.SOUTH);

        pacState = new HashMap<Boolean, Integer> ();

        pacState.put(true, 0);
    }

    /**
     *
     * @param pos Position2D object that is used to set this entity's world position
     * @param color Color object that is used to draw this entity as that color
     * @param canMove boolean that determines whether this entity can move or not
     * @param shape Shape enum within the Entity class that is used to draw this entity's shape
     */
    public Pacman(Position2D pos, Color color, boolean canMove, Shape shape)
    {
        super(pos, color, canMove, shape);

        this.setDirection(Direction.SOUTH);

        pacState = new HashMap<Boolean, Integer> ();

        pacState.put(true, 0);
    }

    // Allows easy removal of Pacman regardless of instance variables

    /**
     *
     * @param other Object that is compared against this object
     * @return true if equal, else false
     */
    @Override
    public boolean equals(Object other)
    {
        if(other == null)
        {
            return false;
        }

        if(other.getClass() != this.getClass())
        {
            return false;
        }

        return true;
    }

    /**
     *
     * @return true if move is successful, else false
     */
    @Override
    public boolean attemptMove()
    {
        if (SystemManager.VERBOSITY>1) System.out.println(getPosition());

        switch (getDirection()) {
            case NORTH:
                return decrementY();
            case EAST:
                return incrementX();
            case SOUTH:
                return incrementY();
            case WEST:
                return decrementX();
            default:
                return false;
        }
    }

    /**
     *
     * @param g Graphics object that is used to paint this entity
     * @return true if paint was successful
     */
    @Override
    public boolean paintEntity(Graphics g)
    {
        super.paintEntity(g);

        if(pacState.containsKey(true))
        {
            g.setColor(Color.BLACK);

            g.fillArc(this.getPosition().getXPosition() + (int) (this.getPosition().getWidth() * .30), this.getPosition().getYPosition() + 12,(int) (this.getPosition().getWidth() * .70),(int) (this.getPosition().getHeight() * .70), -190, pacState.get(true));

            if(pacState.get(true) >= 220)
            {
                Integer temp = pacState.get(true);

                pacState.remove(true);

                pacState.put(false, temp);
            }
            else
            {
                pacState.put(true, pacState.get(true) + 10);
            }


        }
        else if(pacState.containsKey(false))
        {
            if(pacState.get(false) <= 40)
            {
                Integer temp = pacState.get(false);

                pacState.remove(false);

                pacState.put(true, temp);
            }
            else
            {
                pacState.put(false, pacState.get(false) - 10);

                g.fillArc(this.getPosition().getXPosition() + (int) (this.getPosition().getWidth() * .30), this.getPosition().getYPosition() + 12,(int) (this.getPosition().getWidth() * .70),(int) (this.getPosition().getHeight() * .70), -190, pacState.get(false));

            }
        }




        return true;
    }

}