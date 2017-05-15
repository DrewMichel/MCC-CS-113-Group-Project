package first.approach.one;

import java.awt.Color;

/**
 * Created by Zach Pownell
 *
 * on 4/24/2017
 *
 * MiraCosta College - CS 113 Monday 5:30-9:20
 *
 * Not implemented
 *
 * TODO: set shape to CIRCLE
 */
public class Coin extends Entity
{
    /**
     *
     * @param position Position2D object that is used to set this entity's world position
     */
    public Coin(Position2D position)
    {
        super(position, Color.YELLOW, false, Shape.CIRCLE);
    }
}