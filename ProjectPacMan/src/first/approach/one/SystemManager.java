package first.approach.one;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Created by Andrew Michel,
 *
 * on 4/24/2017
 *
 * MiraCosta College - CS 113 Monday 5:30-9:20
 *
 * This class manages an ArrayList of entities to regulate movement, logic,
 * and states
 *
 *
 * TODO: controls logic, threading(?), player lives(GUI/pacman?),
 * TODO: player score(GUI/pacman?), hit detection, player movement(GUI/hashmap?)
 *
 * Recommended to place Pacman and Ghosts as instance variables instead
 * of into ArrayList?
 */

public class SystemManager
{
    private ArrayList<Entity> entities;
    
    public SystemManager()
    {
        entities = new ArrayList<>();
        
        entities.add(new Wall(new Position2D(20,20,500,20), Color.BLUE, false, Entity.Shape.RECTANGLE));
        
        entities.add(new Wall(new Position2D(20,400,500,20), Color.BLUE, false, Entity.Shape.RECTANGLE));
        
        entities.add(new Wall(new Position2D(300,20,20,450), Color.BLUE, false, Entity.Shape.RECTANGLE));
    }
    
    public boolean setEntities(ArrayList<Entity> entities)
    {
        this.entities = entities;
        return true;
    }
    
    public ArrayList<Entity> getEntities()
    {
        return entities;
    }
}