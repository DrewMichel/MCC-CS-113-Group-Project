package first.approach.one;

import java.awt.Color;

/**
 *
 * Created by Andrew Michel,
 *
 * on 4/24/2017
 *
 * MiraCosta College - CS 113 Monday 5:30-9:20
 */
public class Ghost extends Entity
{
    public Ghost()
    {
        this(new Position2D(), Color.CYAN, true, Shape.CIRCLE);
    }

    public Ghost(Color color)
    {
        this(new Position2D(), color, true, Shape.CIRCLE);
    }

    public Ghost(Position2D pos, Color color, boolean canMove, Shape shape)
    {
        super(pos, color, canMove, shape);

        this.setDirection(Direction.SOUTH);
    }
}