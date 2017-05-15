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

    /**
     *
     * @param position Position2D object that is used to set this entity's world position
     */
    public Wall(Position2D position)
        {
            super(position, Color.BLUE, false, Shape.RECTANGLE);
        }

    @Override
    public String toString()
    {

        return "Wall Position : " + getPosition();
    }

}