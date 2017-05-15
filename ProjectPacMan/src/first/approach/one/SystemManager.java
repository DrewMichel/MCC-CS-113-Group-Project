package first.approach.one;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

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
    public static int score = 0;
    public static int cherries = 0;
    public static String message = "";

    /**
     * Default constructor
     * Initializes the entities within the game
     */
    public SystemManager() {

        // @Julian Conner - FOR THE REST OF THE TEAM
        // Note : I colored each seperate Path a different color so its easier to see which Path code made which path
        //        In the future I will make it the same color as the board & fix the wall proportions
        List<Path> testPaths = Path.createTestPaths();
        Ghost blinky = new Ghost(new Position2D(400, 120, 40, 40), Color.RED, true, Entity.Shape.RECTANGLE);
        Ghost pinky = new Ghost(new Position2D(780, 120, 40, 40), Color.PINK, true, Entity.Shape.RECTANGLE);
        Ghost inky = new Ghost(new Position2D(450, 250, 40, 40), Color.CYAN, true, Entity.Shape.RECTANGLE);
        Ghost clyde = new Ghost(new Position2D(730, 250, 40, 40), Color.ORANGE, true, Entity.Shape.RECTANGLE);


        // @Julian Conner - FOR THE REST OF THE TEAM
        // Note : If you want the ghost to stop moving so you can try out the new colored paths
        //        comment out this next line of code
        blinky.setGhostMovementStrategy( new DirectlyToPacmanStrategy(blinky));
        pinky.setGhostMovementStrategy( new DirectlyToPacmanStrategy(pinky));
        inky.setGhostMovementStrategy( new DirectlyToPacmanStrategy(inky));
        clyde.setGhostMovementStrategy( new DirectlyToPacmanStrategy(clyde));

        // initializing the ArrayList "entities"...
        entities = new ArrayList<>();
        // ...and adding Wall objects to entities
        entities.add(new Wall(new Position2D(20, 20, 1150, 20)));
        entities.add(new Wall(new Position2D(20, 650, 1150, 20)));
        entities.add(new Wall(new Position2D(1150, 20, 20, 650)));
        entities.add(new Wall(new Position2D(20, 20, 20, 650)));

        entities.add(new Wall(new Position2D(170,170,20,370)));
        entities.add(new Wall(new Position2D(1005,170,20,370)));

        entities.add(new Wall(new Position2D(310,170,580,20)));
        entities.add(new Wall(new Position2D(310,520,580,20)));

        entities.add(new Wall(new Position2D(310,310,20,220)));
        entities.add(new Wall(new Position2D(870,310,20,220)));

        entities.add(new Wall(new Position2D(440,310,20,110)));
        entities.add(new Wall(new Position2D(740,310,20,110)));

        entities.add(new Wall(new Position2D(440,400,300,20)));

        // adding all testPaths to entities


        //entities.addAll( Path.getAllPathEntities(testPaths));


        // and adding ghost
        entities.add(blinky);
        entities.add(pinky);
        entities.add(inky);
        entities.add(clyde);

        // initializing the new Pacman object "player" and assigning its position, color, and shape
        player = new Pacman(new Position2D(570,440,50,50), Color.YELLOW, true, Entity.Shape.CIRCLE );
        // adding Pacman to entities ArrayList and initializing the PlayerController object to player (pacman)
        entities.add(player);
        pc = new PlayerController(player);
        // run the game
        paused = false;


        entities.add(new Coin(new Position2D(100,100,20,20)));
        entities.add(new Coin(new Position2D(180,100,20,20)));
        entities.add(new Coin(new Position2D(260,100,20,20)));
        entities.add(new Coin(new Position2D(340,100,20,20)));
        entities.add(new Coin(new Position2D(420,100,20,20)));
        entities.add(new Coin(new Position2D(500,100,20,20)));
        entities.add(new Coin(new Position2D(580,100,20,20)));
        entities.add(new Coin(new Position2D(660,100,20,20)));
        entities.add(new Coin(new Position2D(740,100,20,20)));
        entities.add(new Coin(new Position2D(820,100,20,20)));
        entities.add(new Coin(new Position2D(900,100,20,20)));
        entities.add(new Coin(new Position2D(980,100,20,20)));
        entities.add(new Coin(new Position2D(1060,100,20,20)));
        entities.add(new Coin(new Position2D(100,580,20,20)));
        entities.add(new Coin(new Position2D(180,580,20,20)));
        entities.add(new Coin(new Position2D(260,580,20,20)));
        entities.add(new Coin(new Position2D(340,580,20,20)));
        entities.add(new Coin(new Position2D(420,580,20,20)));
        entities.add(new Coin(new Position2D(500,580,20,20)));
        entities.add(new Coin(new Position2D(580,580,20,20)));
        entities.add(new Coin(new Position2D(660,580,20,20)));
        entities.add(new Coin(new Position2D(740,580,20,20)));
        entities.add(new Coin(new Position2D(820,580,20,20)));
        entities.add(new Coin(new Position2D(900,580,20,20)));
        entities.add(new Coin(new Position2D(980,580,20,20)));
        entities.add(new Coin(new Position2D(1060,580,20,20)));
        entities.add(new Coin(new Position2D(100,180,20,20)));
        entities.add(new Coin(new Position2D(100,260,20,20)));
        entities.add(new Coin(new Position2D(100,340,20,20)));
        entities.add(new Coin(new Position2D(100,420,20,20)));
        entities.add(new Coin(new Position2D(100,500,20,20)));
        entities.add(new Coin(new Position2D(1060,180,20,20)));
        entities.add(new Coin(new Position2D(1060,260,20,20)));
        entities.add(new Coin(new Position2D(1060,340,20,20)));
        entities.add(new Coin(new Position2D(1060,420,20,20)));
        entities.add(new Coin(new Position2D(1060,500,20,20)));
        entities.add(new Coin(new Position2D(240,180,20,20)));
        entities.add(new Coin(new Position2D(240,260,20,20)));
        entities.add(new Coin(new Position2D(240,340,20,20)));
        entities.add(new Coin(new Position2D(240,420,20,20)));
        entities.add(new Coin(new Position2D(240,500,20,20)));
        entities.add(new Coin(new Position2D(940,180,20,20)));
        entities.add(new Coin(new Position2D(940,260,20,20)));
        entities.add(new Coin(new Position2D(940,340,20,20)));
        entities.add(new Coin(new Position2D(940,420,20,20)));
        entities.add(new Coin(new Position2D(940,500,20,20)));
        entities.add(new Coin(new Position2D(310,240,20,20)));
        entities.add(new Coin(new Position2D(390,240,20,20)));
        entities.add(new Coin(new Position2D(470,240,20,20)));
        entities.add(new Coin(new Position2D(550,240,20,20)));
        entities.add(new Coin(new Position2D(630,240,20,20)));
        entities.add(new Coin(new Position2D(710,240,20,20)));
        entities.add(new Coin(new Position2D(790,240,20,20)));
        entities.add(new Coin(new Position2D(870,240,20,20)));
        entities.add(new Coin(new Position2D(375,300,20,20)));
        entities.add(new Coin(new Position2D(375,380,20,20)));
        entities.add(new Coin(new Position2D(375,460,20,20)));
        entities.add(new Coin(new Position2D(805,300,20,20)));
        entities.add(new Coin(new Position2D(805,380,20,20)));
        entities.add(new Coin(new Position2D(805,460,20,20)));
        entities.add(new Coin(new Position2D(725,460,20,20)));
        entities.add(new Coin(new Position2D(645,460,20,20)));
        entities.add(new Coin(new Position2D(455,460,20,20)));
        entities.add(new Coin(new Position2D(535,460,20,20)));

        entities.add(new Cherry(new Position2D(582,230,35,35)));

    }


    /**
     * Begins the game
     */
    public void initialize()
    {
        String sourceEntityType  , collidedEntityType;
        // Lets the Ghosts always know where PacMan is
        Ghost.setPacman( player);

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

                    sourceEntityType = collision.getSourceEntityType();
                    collidedEntityType = collision.getCollidedEntityType();
                    if (VERBOSITY > 0)
                    {
                        System.out.println("Collision from " + sourceEntityType
                                + " onto " + collidedEntityType);
                    }

                    switch (sourceEntityType) {
                        case "Pacman":
                            switch ( collidedEntityType ) {
                                case "Wall":
                                    collision.getSourceEntity().setPosition(collision.getPreviousPosition());
                                    System.out.println("Wall : " + collision.getCollidedEntity());
                                    break;
                                case "Ghost":
                                    System.out.println("GAME OVER");
                                    message = "GAME OVER";
                                    return;
                                case "Cherry":
                                    System.out.println("CHERRY");
                                    entities.remove(collision.getCollidedEntity());
                                    score += 300;
                                    cherries ++;
                                    break;
                                case "Coin":
                                    System.out.println("COIN");
                                    entities.remove(collision.getCollidedEntity());
                                    score += 100;
                                    break;
                                default:
                                    throw new RuntimeException("Bad collision from " + collision.getSourceEntityType()
                                            + " onto " + collision.getCollidedEntityType());
                            }
                            break;
                        case "Ghost":
                            switch ( collidedEntityType ) {
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

    // Getter
    public PlayerController getPlayerController()
    {
        return pc;
    }

    /**
     *
     * @param entities ArrayList of Entity that is used to set instance variable entities
     * @return true
     */
    public boolean setEntities(ArrayList<Entity> entities)
    {
        this.entities = entities;
        return true;
    }

    // Getter
    public boolean isPaused()
    {
        return paused;
    }

    // Getter
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
                if (sourceEntity != targetEntity && sourceEntity.canMove() && !(targetEntity instanceof Path)) {
                    Position2D targetEntityPosition = targetEntity.getPosition();
                    if (sourceEntityPosition.overlaps(targetEntityPosition)) {
                        collisionList.add(new Collision(sourceEntity, targetEntity));
                    }
                }
            }
        }
        return collisionList;
    }

    /**
     *
     * @param time long that is used to set the duration of sleep
     */
    // Just here to improve code readability by grabbing the try-catch
    public void threadSleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    // Private class start
    private class PlayerController implements KeyListener
    {
        private Pacman player;

        /**
         *
         * @param player Pacman object that is assigned to instance variable player
         */
        public PlayerController(Pacman player)
        {
            this.player = player;
        }

        /**
         *
         * @param e KeyEvent that fires when this listener detects a key typed
         */
        @Override
        public void keyTyped(KeyEvent e)
        {
        }

        /**
         *
         * @param e KeyEvent that fires when this listener detects a key pressed
         */
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

        /**
         *
         * @param e KeyEvent that fires when this listener detects a key released
         */
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
    } // Private class end
}
