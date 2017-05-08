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
    private ArrayList<Entity> entities;

    private PlayerController pc;

    private boolean paused;

    private Ghost red;

    private Ghost blue;

    private Ghost yellow;

    private Ghost green;

    public Pacman player;
    
    public SystemManager()
    {
        entities = new ArrayList<>();
        
        entities.add(new Wall(new Position2D(20,20,500,20), Color.BLUE, false, Entity.Shape.RECTANGLE));
        
        entities.add(new Wall(new Position2D(20,450,500,20), Color.BLUE, false, Entity.Shape.RECTANGLE));
        
        entities.add(new Wall(new Position2D(500,20,20,450), Color.BLUE, false, Entity.Shape.RECTANGLE));

        entities.add(new Wall(new Position2D(20,20,20,450), Color.BLUE, false, Entity.Shape.RECTANGLE));

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
                    if (entity != null && entity.canMove()) {
                        entity.attemptMove();
                    }
                }
                // Hit detection
                // I'm make the assumption here that we're okay with
                if (detectCollision()) {
                    System.out.println("DEBUG: Collision detected");
                };
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }

            } else {
                System.out.println("Currently Paused..");
                try { Thread.sleep(100); } catch (Exception e) {e.printStackTrace();}
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

    //todo expand this from boolean to enum to capture what kind of collision occurs (e.g. Wall vs Coin vs Ghost)
    private boolean detectCollision() {
        //get the current bounding box around the player object
        Position2D playerPosition = player.getPosition();
        /*
        int xStart = playerPosition.getXPosition();
        int xEnd = playerPosition.getXPositionEnd();
        int yStart = playerPosition.getYPosition();
        int yEnd = playerPosition.getyPositionEnd();
        */

        //iterate through our entities to check for Pacmans hitbox overlapping w/ another entity's location
        for (Entity entity : entities) {
            if (!player.equals(entity)) {
                Position2D entityPosition = entity.getPosition();
                if (playerPosition.overlaps(entityPosition)) {
                    return true;
                }
            }
        }

        return false;

        // Potential pitfall note: we're probably going to need something to 'kick' the player from the wall in the event
        // of a collision or to perform this check before the actual collision.
        // Otherwise they could end up stuck inside a wall unable to move due to endless collisions.
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
            System.out.println("KEYCODE: " + e.getKeyCode());

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