package first.approach.one;

import java.awt.Color;

/**
 *
 * Created by Andrew Michel,
 *
 * on 4/24/2017
 *
 * MiraCosta College - CS 113 Monday 5:30-9:20
 *
 * Not implemented
 */
public class Ghost extends Entity
{
    public Ghost()
    {
        super(new Position2D(), Color.CYAN, true, Shape.CIRCLE);

        this.setDirection(Direction.SOUTH);
    }

    public Ghost(Color color)
    {
        super(new Position2D(), color, true, Shape.CIRCLE);

        this.setDirection(Direction.SOUTH);
    }

    public Ghost(Position2D pos, Color color, boolean canMove, Shape shape)
    {
        super(pos, color, canMove, shape);

        this.setDirection(Direction.SOUTH);
    }
}