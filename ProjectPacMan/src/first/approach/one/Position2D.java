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
    private int defaultXIncrementValue  = 1;
    private int defaultYIncrementValue  = 1;
    private int xPosition;
    private int yPosition;
    
    private int width;
    private int height;
    

    
    public Position2D(int x, int y, int w, int h)
    {
        xPosition = x;
        yPosition = y;
        width = w;
        height = h;
    }

    public Position2D()
    {
        this(0,0,0,0);
    }

    public Position2D(Position2D pos)
    {
        this(pos.xPosition, pos.yPosition, pos.width, pos.height);
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

    public int getDefaultXIncrementValue() { return defaultXIncrementValue;}

    public int getDefaultYIncrementValue() { return defaultYIncrementValue; }

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

    /**
     * Sets the default X position increment value
     * <note>
     *     This is in case we want to change how much one increment should move the entity
     * </note>
     * @param amount
     */
    public void setDefaultXIncrementValue( int amount)
    {
        defaultXIncrementValue = amount;
    }

    /**
     * Sets the default Y position increment value
     * <note>
     *     This is in case we want to change how much one increment should move the entity
     * </note>
     * @param amount
     */
    public void setDefaultYIncrementValue( int amount)
    {
        defaultYIncrementValue = amount;
    }

    /**
     * Increments the x position by the default amount
     */
    public void incrementX()
    {
        xPosition += defaultXIncrementValue;
    }

    /**
     * Increments the y position by the default amount
     */
    public void incrementY()
    {
        yPosition += defaultYIncrementValue;
    }

    /**
     * Increments the x position by a specified amount
     * @param amount
     */
    public void incrementX( int amount)
    {
        xPosition += amount;
    }

    /**
     * Increments the y position by a specified amount
     * @param amount
     */
    public void incrementY( int amount)
    {
        yPosition += amount;
    }
    /**
     * Decrements the x position by the default amount
     */
    public void decrementX()
    {
        xPosition -= defaultXIncrementValue;
    }

    /**
     * Decrements the y position by the default amount
     */
    public void decrementY()
    {
        yPosition -= defaultYIncrementValue;
    }

    /**
     * Decrements the x position by a specified amount
     * @param amount
     */
    public void decrementX( int amount)
    {
        xPosition -= amount;
    }

    /**
     * Decrements the y position by a specified amount
     * @param amount
     */
    public void decrementY( int amount)
    {
        yPosition -= amount;
    }


    public Vertex firstIntersectionPoint( Vertex vector , Position2D compared)
    {
        Vertex intersectionPoint = null;
        double heightHalved ;
        double widthHalved ;
        double lineSlope ;

        if( !overlaps(compared))
        {
            return null;
        }

        else
        {
            heightHalved = height / 2.0;
            widthHalved  = width / 2.0;
            lineSlope = ( compared.height  / ( 1.0 * compared.width));

            return intersectionPoint;
        }
    }

    /**
     * Checks if the Position2D hits the right border
     * @param otherPos
     * @return
     */
    public boolean touchesRightBorder( Position2D otherPos)
    {
        int x2 = getXPosition() + getWidth();
        int y1 = getYPosition();
        int x = otherPos.getXPosition();
        int width = otherPos.getWidth();


        return ( x2 >= x && x2 <= x + width ) && overlaps( otherPos);
    }

    /**
     * Checks if the Position2D hits the left border
     * @param otherPos
     * @return
     */
    public boolean touchesLeftBorder( Position2D otherPos)
    {
        int x1 = getXPosition();
        int y1 = getYPosition();
        int x = otherPos.getXPosition();
        int width = otherPos.getWidth();


        return ( x1 >= x && x1 <= x + width ) && overlaps( otherPos);
    }

    /**
     * Checks if the Position2D hits the bottom border
     * @param otherPos
     * @return
     */
    public boolean touchesBottomBorder( Position2D otherPos)
    {
        int y2 = getYPosition() + getHeight();
        int y1 = getYPosition();
        int y = otherPos.getYPosition();
        int height = otherPos.getHeight();


        return ( y2 >= y && y2 <= y + height ) && overlaps( otherPos);
    }

    /**
     * Checks if the Position2D hits the top border
     * @param otherPos
     * @return
     */
    public boolean touchesTopBorder( Position2D otherPos)
    {
        int y1 = getYPosition();
        int x1 = getYPosition();
        int y = otherPos.getYPosition();
        int height = otherPos.getHeight();


        return ( y1 >= y && y <= y + height ) && overlaps( otherPos);
    }

    /**
     * Finds the shortest distance between two positions
     * @param otherPosition
     * @return int[] that contains the shortest x and y distances between two positions,
     *          index[0] = x distance
     *          index[1] = y distance
     */
    public int[] smallestDistanceBetweenPositions( Position2D otherPosition)
    {
        int[] distance = new int[2];


        int smallestX ;
        int xFromStartToStart = getXPosition() - otherPosition.getXPosition();
        int xFromStartToEnd = xFromStartToStart - otherPosition.getWidth();
        int xFromEndToStart = xFromStartToStart + getWidth();
        int xFromEndToEnd = xFromEndToStart + getWidth();

        int yFromStartToStart = getYPosition() - otherPosition.getYPosition();
        int yFromStartToEnd = yFromStartToStart - otherPosition.getWidth();
        int yFromEndToStart = yFromStartToStart + getWidth();
        int yFromEndToEnd = yFromEndToStart + getWidth();

        // Set the smallest x distance
        distance[0] = Helper.findSmallest( true, xFromEndToEnd , xFromEndToStart , xFromStartToEnd , xFromStartToStart);

        // Set the smallest y distance
        distance[1] = Helper.findSmallest(true, yFromEndToEnd , yFromEndToStart , yFromStartToEnd , yFromStartToStart);

        return distance;
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


    /**
     * Checks if another Position2D's position coordinates overlap with this object
     * @param otherPos
     * @return boolean true if x and y coordinate overlaps with this object, else returns false
     */
    public boolean positionOverlaps( Position2D otherPos)
    {
        return overlaps( otherPos.getXPosition() ,otherPos.getYPosition());
    }

    /**
     * Checks if a position overlaps with this Position2D
     * @param x x coordinate
     * @param y y coordinate
     * @return boolean true if the coordinate overlaps with this object , else returns false
     */
    public boolean overlaps( int x , int y )
    {
        int xStart = getXPosition();
        int xEnd = getXPositionEnd();
        int yStart = getYPosition();
        int yEnd = getYPositionEnd();

        return x >= xStart && x <= xEnd && y >= yStart && y <= yEnd ;

    }

    public boolean overlapsX( int x)
    {
        int xStart = getXPosition();
        int xEnd = getXPositionEnd();
        int yStart = getYPosition();
        int yEnd = getYPositionEnd();

        return x >= xStart && x <= xEnd;
    }
    public boolean overlapsY( int y )
    {
        int xStart = getXPosition();
        int xEnd = getXPositionEnd();
        int yStart = getYPosition();
        int yEnd = getYPositionEnd();

        return y >= yStart && y <= yEnd ;
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