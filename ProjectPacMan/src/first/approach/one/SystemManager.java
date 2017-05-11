package first.approach.one;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
    public static final int VERBOSITY = 1; //0 = off, 1 = low, 2 = high ..amount of console output

    private ArrayList<Entity> entities;
    private PlayerController pc;
    private boolean paused;
    private Ghost red;
    private Ghost blue;
    private Ghost yellow;
    private Ghost green;
    private Pacman player;

    public SystemManager()
    {
        entities = new ArrayList<>();
        
        entities.add(new Wall(new Position2D(20,20,1150,20), Color.BLUE, false, Entity.Shape.RECTANGLE));
        entities.add(new Wall(new Position2D(20,650,1150,20), Color.BLUE, false, Entity.Shape.RECTANGLE));
        entities.add(new Wall(new Position2D(1150,20,20,650), Color.BLUE, false, Entity.Shape.RECTANGLE));
        entities.add(new Wall(new Position2D(20,20,20,650), Color.BLUE, false, Entity.Shape.RECTANGLE));
        entities.add(new Ghost(new Position2D(200, 200, 40, 40), Color.RED, true, Entity.Shape.RECTANGLE));

        player = new Pacman(new Position2D(100,100,50,50), Color.YELLOW, true, Entity.Shape.CIRCLE );
        entities.add(player);
        pc = new PlayerController(player);
        paused = false;
    }

    public void initialize()
    {
        // NDR 2017.05.07 I changed 'while(!paused)...' to 'while(true) { if !(paused)...' to fix the bug w/ not being
        // able to return from a paused state. Which kind of worked.. the application starts again and the Pacman will
        // move but for some reason the frame doesn't start redrawing. I have no idea why.
        while(true) {
            if (!paused) {
                for (Entity entity : entities) {
                    if (entity.canMove()) {
                        entity.attemptMove();
                    }
                }

                //Collision Detection
                ArrayList<Collision> collisions = detectCollisions();
                for (Collision collision : collisions) {
                    if (VERBOSITY>0) System.out.println("Collision from " + collision.getSourceEntityType()
                            + " onto " + collision.getCollidedEntityType());
                    switch (collision.getSourceEntityType()) {
                        case "Pacman":
                            switch (collision.getCollidedEntityType()) {
                                case "Wall":
                                    collision.getSourceEntity().setPosition(collision.getPreviousPosition());
                                    break;
                                case "Ghost":
                                    System.out.println("GAME OVER");
                                    return;
                                case "Cherry":
                                    //todo
                                    break;
                                case "Coin":
                                    //todo
                                    break;
                                default:
                                    throw new RuntimeException("Bad collision from " + collision.getSourceEntityType()
                                            + " onto " + collision.getCollidedEntityType());
                            }
                            break;
                        case "Ghost":
                            switch (collision.getCollidedEntityType()) {
                                case "Wall":
                                    //todo this will just make it stop. We'll want to replace it with Ghost algs.
                                    collision.getSourceEntity().setPosition(collision.getPreviousPosition());
                                    break;
                                default:
                                    if (VERBOSITY>0) System.out.println("Ghost collided with "
                                            + collision.getCollidedEntityType() + ", no action necessary");
                            }
                            break;
                        default:
                            throw new RuntimeException("No type found in getSourceEntityType switch: "
                                    + collision.getSourceEntityType());
                    }
                }

                threadSleep(20);
            } else {
                System.out.println("Currently Paused..");
                threadSleep(500);
            }
        }

    }

    public PlayerController getPlayerController()
    {
        return pc;
    }

    public boolean setEntities(ArrayList<Entity> entities)
    {
        this.entities = entities;
        return true;
    }

    public boolean isPaused()
    {
        return paused;
    }

    public ArrayList<Entity> getEntities()
    {
        return entities;
    }

    /** Collision detection. Loops through all the entitys which canMove and detects overlap with other entitys.
     * @return an ArrayList of all the collisions. No collisions will return an empty ArrayList.
     */
    private ArrayList<Collision> detectCollisions() {
        ArrayList<Collision> collisionList = new ArrayList<>();

        for (Entity sourceEntity : entities) {
            if (!sourceEntity.canMove()) {
                continue;
            }
            //iterate through our entities to check for an Entity's hitbox overlapping w/ another entity's location
            Position2D sourceEntityPosition = sourceEntity.getPosition();
            for (Entity targetEntity : entities) {
                if (sourceEntity != targetEntity && sourceEntity.canMove()) {
                    Position2D targetEntityPosition = targetEntity.getPosition();
                    if (sourceEntityPosition.overlaps(targetEntityPosition)) {
                        collisionList.add(new Collision(sourceEntity, targetEntity));
                    }
                }
            }
        }
        return collisionList;
    }

    // Just here to improve code readability by grabbing the try-catch
    public void threadSleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    private class PlayerController implements KeyListener
    {
        private Pacman player;


        public PlayerController(Pacman player)
        {
            this.player = player;
        }

        @Override
        public void keyTyped(KeyEvent e)
        {

        }

        @Override
        public void keyPressed(KeyEvent e)
        {
            if(e.getKeyCode() == (KeyEvent.VK_UP))
            {
                player.setDirection(Entity.Direction.NORTH);
            }
            else if(e.getKeyCode() == (KeyEvent.VK_RIGHT))
            {
                player.setDirection(Entity.Direction.EAST);
            }
            else if(e.getKeyCode() == (KeyEvent.VK_DOWN))
            {
                player.setDirection(Entity.Direction.SOUTH);
            }
            else if(e.getKeyCode() == (KeyEvent.VK_LEFT))
            {
                player.setDirection(Entity.Direction.WEST);
            }
            else if(e.getKeyCode() == KeyEvent.VK_P)
            {
                paused = !paused;
            }

            if (VERBOSITY>0) System.out.println("KEYCODE: " + e.getKeyCode()
                    + ", Player heading: " + player.getDirection());
        }

        @Override
        public void keyReleased(KeyEvent e)
        {

        }



        /*
        @Override
        public void actionPerformed(ActionEvent e)
        {
            System.out.println("ACTIONCOMMAND: " + e.getActionCommand());
            if(e.getActionCommand().equals("UP"))
            {
                player.attemptMove();
            }
            else if(e.getActionCommand().equals("RIGHT"))
            {

            }
            else if(e.getActionCommand().equals("DOWN"))
            {

            }
            else if(e.getActionCommand().equals("LEFT"))
            {

            }
        }
        */
    }
}