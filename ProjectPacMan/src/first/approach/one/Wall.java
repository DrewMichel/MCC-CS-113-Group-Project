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

    public Wall(Position2D position)
        {
            super(position, Color.BLUE, false, Shape.RECTANGLE);
        }

}