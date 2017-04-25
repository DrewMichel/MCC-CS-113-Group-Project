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
 * Player class
 * Track player lives here or SystemManager(ideal?)
 */
public class Pacman extends Entity
{
    public Pacman()
    {
        super(new Position2D(), Color.YELLOW, true, Shape.CIRCLE);
    }
}