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
 * Not implemented
 * Player class
 * Track player lives here or SystemManager(ideal?)
 */
public class Pacman extends Entity
{
    public Pacman()
    {
        super(new Position2D(), Color.YELLOW, true, Shape.CIRCLE);

        this.setDirection(Direction.SOUTH);
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
        System.out.println(getPosition());

        if(this.getDirection() == Direction.NORTH)
        {
            return this.decrementY();
        }
        if(this.getDirection() == Direction.EAST)
        {
            return this.incrementX();
        }
        if(this.getDirection() == Direction.SOUTH)
        {
            return this.incrementY();
        }
        if(this.getDirection() == Direction.WEST)
        {
            return this.decrementX();
        }
        else
        {
            return false;
        }
    }


    @Override
    public boolean paintEntity(Graphics g)
    {
        super.paintEntity(g);

        g.setColor(Color.BLACK);

        g.drawArc(this.getPosition().getXPosition() + (int) (this.getPosition().getWidth() * .30), this.getPosition().getYPosition(), this.getPosition().getWidth(), this.getPosition().getHeight(), 180, 50);

        return true;
    }

}