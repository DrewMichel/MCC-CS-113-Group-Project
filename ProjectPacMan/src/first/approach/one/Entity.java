package first.approach.one;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * Created by Andrew Michel,
 *
 * on 4/24/2017
 *
 * MiraCosta College - CS 113 Monday 5:30-9:20
 *
 * This abstract class contains instance variables, constructors,
 * and methods for an Entity to occupy, move through, and check
 * collision in a specific space
 *
 * Color is used in the drawEntity method to set color of the
 * pixels representing this object in a GUI window
 *
 * TODO: Improve move, passable, etc...
 */
public abstract class Entity
{
    public enum Direction {NORTH, EAST, SOUTH, WEST}

    // ENUM OR CONSTANT FOR SHAPE --- FILLARC, FILLRECT, etc...
    public enum Shape {RECTANGLE, CIRCLE}
    
    private Position2D position;
    private Color color;
    private boolean canMove;
    private Shape shape;
    private Direction dir;
    
    public Entity(Position2D position, Color color, boolean canMove, Shape shape)
    {
    	this.position = position;
    	this.color = color;
    	this.canMove = canMove;
    	this.shape = shape;
    	dir = Direction.SOUTH;
    }
    
    public Position2D getPosition()
    {
        return position;
    }
    
    public boolean setPosition(Position2D pos)
    {
        position = new Position2D(pos);
        return true;
    }
    
    public boolean setPosition(int x, int y, int w, int h)
    {
        position = new Position2D(x, y, w, h);
        return true;
    }
    
    public boolean setColor(Color c)
    {
        color = c;
        return true;
    }
    
    public Color getColor()
    {
        return color;
    }
    
    public boolean setMove(boolean movable)
    {
        canMove = movable;
        return true;
    }
    
    public boolean canMove()
    {
        return canMove;
    }
    
    public boolean attemptMove()
    {
        return true;
    }

    public boolean attemptMove(Position2D pos)
    {
        this.position = new Position2D(pos);
        return true;
    }

    public boolean attemptMove(int x, int y)
    {
        this.position = new Position2D(x, y, this.position.getWidth(), this.position.getHeight());
        return true;
    }

    public boolean incrementX()
    {
        this.position.incrementX();
        return true;
    }

    public boolean decrementX()
    {
        this.position.decrementX();
        return true;
    }

    public boolean incrementY()
    {
        this.position.incrementY();
        return true;
    }

    public boolean decrementY()
    {
        this.position.decrementY();
        return true;
    }

    public boolean offset(int x, int y)
    {
        this.position.setXPosition(this.position.getXPosition() + x);
        this.position.setYPosition(this.position.getYPosition() + y);
        return true;
    }

    public Direction getDirection()
    {
        return dir;
    }

    public boolean setDirection(Direction dir)
    {
        this.dir = dir;
        return true;
    }
    
    public boolean paintEntity(Graphics g)
    {
        g.setColor(color);
        
        if(this.shape == Shape.RECTANGLE)
        {
            g.fillRect(position.getXPosition(), position.getYPosition(), position.getWidth(), position.getHeight());
        }
        else if(this.shape == Shape.CIRCLE)
        {
            g.fillArc(position.getXPosition(), position.getYPosition(), position.getWidth(), position.getHeight(), 180, 360);
        }
        
        return true;
    }

}