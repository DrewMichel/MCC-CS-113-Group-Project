package first.approach.one;

import java.awt.Color;

/**
 * Created by Andrew Michel,
 *
 * on 4/24/2017
 *
 * MiraCosta College - CS 113 Monday 5:30-9:20
 *
 * Not implemented
 *
 * TODO: set canMove to false by default/always?
 */

public class Wall extends Entity
{
    private String side = "Unknown";
    private Path path ;
	public Wall(Position2D position, Color color, boolean canMove, Shape shape)
    {
    	super(position, color, canMove, shape);
    }

    public void setSide( String side)
    {
        this.side = side;
    }

    public void setPath( Path path)
    {
        this.path = path;
    }

    public Path getPath()
    {
        return path;
    }

    public String side()
    {
        return side;
    }
    @Override
    public String toString()
    {
        String pathString = "None";

        if( path != null )
        {
            pathString = path.toString();
        }
        return "Path : " + pathString + "\nSide : " + side + "\nPosition : " + getPosition();
    }
}