package first.approach.one;

import java.awt.*;

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
    public Pacman()
    {
        this(new Position2D(), Color.YELLOW, true, Shape.CIRCLE);
    }

    public Pacman(Position2D pos, Color color, boolean canMove, Shape shape)
    {
        super(pos, color, canMove, shape);

        this.setDirection(Direction.SOUTH);
    }

    // Allows easy removal of Pacman regardless of instance variables
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

    @Override
    public boolean paintEntity(Graphics g)
    {
        super.paintEntity(g);

        g.setColor(Color.BLACK);

        g.fillArc(this.getPosition().getXPosition() + (int) (this.getPosition().getWidth() * .30), this.getPosition().getYPosition() + 12,(int) (this.getPosition().getWidth() * .70),(int) (this.getPosition().getHeight() * .70), -190, 180);

        return true;
    }

}