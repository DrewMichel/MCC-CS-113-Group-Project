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
 */
public class Cherry extends Entity
{
    /**
     *
     * @param position Position2D object that is used to set this entity's world position
     */
    public Cherry(Position2D position)
    {
        super(position, Color.RED, false, Shape.CIRCLE);
    }
}