package first.approach.one;

/**
 *
 * Created by Andrew Michel,
 *
 * on 4/24/2017
 *
 * MiraCosta College - CS 113 Monday 5:30-9:20
 *
 * This class uses integers to define an Entity's x and y positions
 * In Addition, contains width and height which can be used to
 * occupy a rectangular space starting from x and y positions
 *
 */

public class Position2D
{
    private int xPosition;
    private int yPosition;
    
    private int width;
    private int height;
    
    public Position2D()
    {
        xPosition = 0;
        yPosition = 0;
        width = 0;
        height = 0;
    }
    
    public Position2D(int x, int y, int w, int h)
    {
        xPosition = x;
        yPosition = y;
        width = w;
        height = h;
    }
    
    public Position2D(Position2D pos)
    {
        if(pos != null)
        {
            this.xPosition = pos.xPosition;
            this.yPosition = pos.yPosition;
            this.width = pos.width;
            this.height = pos.height;
        }
        else
        {
            xPosition = 0;
            yPosition = 0;
            width = 0;
            height = 0;
        }
    }
    
    // GETTERS
    public int getXPosition()
    {
        return xPosition;
    }
    
    public int getYPosition()
    {
        return yPosition;
    }

    public int getXPositionEnd() {
        return xPosition + width;
    }

    public int getYPositionEnd() {
        return yPosition + height;
    }
    
    public int getWidth()
    {
        return width;
    }
    
    public int getHeight()
    {
        return height;
    }
    
    // SETTERS
    public boolean setXPosition(int x)
    {
        xPosition = x;
        return true;
    }
    public boolean setYPosition(int y)
    {
        yPosition = y;
        return true;
    }
    public boolean setWidth(int w)
    {
        width = w;
        return true;
    }
    public boolean setHeight(int h)
    {
        height = h;
        return true;
    }
    
    public String toString()
    {
        return "2DPOSITION -> X: " + xPosition + " Y: " + yPosition + " Width: " + width + " Height: " + height;
    }
    
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
        
        Position2D temp = (Position2D) other;
        
        if(this.xPosition == temp.xPosition
                && this.yPosition == temp.yPosition
                && this.width == temp.width
                && this.height == temp.height)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean overlaps(Position2D otherPos) {
        boolean xStartOverlap = (getXPosition() >= otherPos.getXPosition() && getXPosition() <= otherPos.getXPositionEnd());
        boolean xMiddleOverlap = (getXPosition() <= otherPos.getXPosition() && getXPositionEnd() >= otherPos.getXPositionEnd());
        boolean xEndOverlap = (getXPositionEnd() >= otherPos.getXPosition() && getXPositionEnd() <= otherPos.getXPositionEnd());
        boolean yStartOverlap = (getYPosition() >= otherPos.getYPosition() && getYPosition() <= otherPos.getYPositionEnd());
        boolean yMiddleOverlap = (getYPosition() <= otherPos.getYPosition() && getYPositionEnd() >= otherPos.getYPositionEnd());
        boolean yEndOverlap = (getYPositionEnd() >= otherPos.getYPosition() && getYPositionEnd() <= otherPos.getYPositionEnd());
        if (xStartOverlap && yStartOverlap
                || xStartOverlap && yMiddleOverlap
                || xStartOverlap && yEndOverlap
                || xMiddleOverlap && yStartOverlap
                || xMiddleOverlap && yMiddleOverlap
                || xMiddleOverlap && yEndOverlap
                || xEndOverlap && yStartOverlap
                || xEndOverlap && yMiddleOverlap
                || xEndOverlap && yEndOverlap) {
            return true;
        }
        return false;
    }
}