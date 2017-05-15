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
    // Enum for Direection NORTH, EAST, SOUTH, or WEST
    public enum Direction {NORTH, EAST, SOUTH, WEST}

    // ENUM OR CONSTANT FOR SHAPE --- FILLARC, FILLRECT, etc...
    public enum Shape {RECTANGLE, CIRCLE}

    // Parameters for entity. Position, shape, color, etc.
    private Position2D previousPosition;
    private Position2D position;
    private Color color;
    private boolean canMove;
    private Shape shape;
    private Direction dir;
    
    // Constructor method "Entity" which takes in Position 2d "Position", Color "color", boolean "canMove", and Shape "shape"
    public Entity(Position2D position, Color color, boolean canMove, Shape shape)
    {
        setPosition(position);
    	setColor(color);
    	setMove(canMove);
    	this.shape = shape;
    	dir = Direction.SOUTH;
    }

    /**
     * Sets the previous position
     * @param pos
     * @return
     */
    public boolean setPreviousPosition( Position2D pos)
    {
        if( pos == null)
        {
            return false;
        }
        else
        {
            previousPosition = pos;

            return true;
        }
    }

    // get method "getPreviousPosition" to return the Position2D "previousPosition"
    public Position2D getPreviousPosition()
    {
        return previousPosition;
    }

    // get method "getPosition" to return the Position2D "position"
    public Position2D getPosition()
    {
        return position;
    }
    
    // set boolean method "setPosition" to set the Position "position" and return true is successful
    public boolean setPosition(Position2D pos)
    {
        previousPosition = (previousPosition == null) ? new Position2D(pos) : position;
        position = new Position2D(pos);
        return true;
    }
    
    // set method "setPosition" with parameters int x, y, w, h to return a new Position2D object with given parameters
    public boolean setPosition(int x, int y, int w, int h)
    {
        return setPosition( new Position2D(x, y, w, h) );
    }

    // set boolean method "setPosition" with parameters int x, y to return a new Position with given parameters
    public boolean setPosition(int x, int y) {
        return setPosition(x,y,position.getWidth(), position.getHeight());
    }
    
    // set method "setColor" to set the Color "color" to the given parameter
    public boolean setColor(Color c)
    {
        color = c;
        return true;
    }
    
    // get method "getColor" to return the Color "color
    public Color getColor()
    {
        return color;
    }
    
    // set boolean method "setMove" to set the boolean "canMove" to the given parameter, return true
    public boolean setMove(boolean movable)
    {
        canMove = movable;
        return true;
    }
    
    // get method "canMove" to return the boolean "canMove"
    public boolean canMove()
    {
        return canMove;
    }
    
    public boolean attemptMove()
    {
        return true;
    }

    // attemptMove boolean method which takes in Position2D "pos" parameter and return boolean setPosition method
    public boolean attemptMove(Position2D pos)
    {
        return setPosition(pos);
    }

    // attemptMove boolean method with parameters int x, y to return the boolean setPosition method
    public boolean attemptMove(int x, int y)
    {
        return setPosition(x, y);
    }

    // incrementX boolean method to update the Position of Entity X position
    public boolean incrementX()
    {
        return setPosition(this.position.getXPosition() + 1, this.position.getYPosition());
    }

    // decrementX boolean method to update the Position of Entity X position
    public boolean decrementX()
    {
        return setPosition(this.position.getXPosition() - 1, this.position.getYPosition());
    }

    // incrementY boolean method to update the Position of Entity Y position
    public boolean incrementY()
    {
        return setPosition(this.position.getXPosition(), this.position.getYPosition() + 1);
    }

    // decrementY boolean method to update the Position of Entity Y position
    public boolean decrementY()
    {
        return setPosition(this.position.getXPosition(), this.position.getYPosition() - 1);
    }

    public boolean offset(int x, int y)
    {
        return setPosition(this.position.getXPosition() + x, this.position.getYPosition() + y);
    }

    // get method "getDirection" to return the Direction object "dir"
    public Direction getDirection()
    {
        return dir;
    }

    // set method "setDirection" to set the Direction object "dir" to given parameter
    public boolean setDirection(Direction dir)
    {
        this.dir = dir;
        return true;
    }
    
    // paintEntity method
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
