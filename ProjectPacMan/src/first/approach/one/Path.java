package first.approach.one;

import javafx.geometry.Pos;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/**
 * Created by Julian on 5/10/2017.
 */
public class Path extends Entity{

    private ArrayList<Wall> walls = new ArrayList<>() ;
    private int leftWallWidth , rightWallWidth, topWallHeight , bottomWallHeight;
    /**
     * Default constructor for path
     * @param position
     * @param color
     * @param canMove
     * @param shape
     */
    public Path(Position2D position, Color color, boolean canMove, Shape shape)
    {
            super( position, color, canMove, shape);
            walls = new ArrayList<>();

    }

    public ArrayList<Wall> getWalls()
    {
        return new ArrayList<Wall>(walls.subList(0 , walls.size()));
    }
    public void setWallDimensions( int width , int height)
    {
        leftWallWidth = width;
        rightWallWidth = width;
        topWallHeight = height;
        bottomWallHeight = height;
    }

    /**
     * Testing create a path
     * @return
     */
    public static Path createTestPath()
    {
        Path test = new Path(new Position2D(50, 200, 500, 100), Color.WHITE, false, Entity.Shape.RECTANGLE);
        test.setWallDimensions(20,20);
        test.createBottomWall();

        return test;
    }

    /**
     * Creates a boxed Path
     * <notes>
     *     Can be used to make the gameboard or smaller barriers on the gameboard
     * </notes>
     * @param x the x position
     * @param y the y position
     * @param width the width
     * @param height the height
     * @return Path
     */
    public static Path createBoxedPath( int x , int y , int width, int height , int wallWidth,
                                        int wallHeight ,Color color)
    {
        Path boxedPath = new Path( new Position2D( x , y , width , height ), color , false , Shape.RECTANGLE);
        boxedPath.setWallDimensions(wallWidth, wallHeight);
        boxedPath.createAllWalls();

        return boxedPath;
    }
    /**
     * Gets all the Path objects & their Walls
     * @param paths Path objects wanted
     * @return ArrayList<Entity> that holds all the Path objects and their Walls
     */
    public static ArrayList<Entity> getAllPathEntities( Path ...paths)
    {
        ArrayList<Entity> entities = new ArrayList<>();

        for( Path path : paths)
        {
            entities.addAll( path.getAllPathEntities());
        }

        return entities;
    }

    /**
     * Gets all the Paths & their Walls
     * @param paths the Path objects
     * @return ArrayList<Entity> that holds all the Path objects and their Wall objects
     */
    public static ArrayList<Entity> getAllPathEntities( Collection<Path> paths)
    {
        return getAllPathEntities(paths.toArray( new Path[2]));
    }

    /**
     * Gets the Path and it's Walls
     * @return a ArrayList<Entity> that holds the current Path and its Walls
     */
    public ArrayList<Entity> getAllPathEntities()
    {
        ArrayList entities = new ArrayList<Entity>( getWalls());
        entities.add(this);
        return entities;
    }

    /**
     * Testing how to create connected paths
     * @return
     */
    public static List<Path> createTestPaths()
    {
        Path testBarrier , testCorner , testAttach;
        // TODO : Use the start path's position and size to determine the connecting path's dimensions & location
        // TODO : Repeat with each new path relying on the previous path's location and dimensions
        ArrayList<Path> testPaths = new ArrayList<>();
        testPaths.add( createTestPath());

        testCorner = new Path(new Position2D(550, 200, 100, 100), Color.GREEN, false, Entity.Shape.RECTANGLE);
        testCorner.setWallDimensions(20,20);
        testCorner.createTopRightCorner();
        //testCorner.createTopWall();

        testAttach = new Path(new Position2D(550, 300, 100, 200), Color.PINK, false, Entity.Shape.RECTANGLE);
        testAttach.setWallDimensions(20,20);
        testAttach.createSideWalls();

        testBarrier = Path.createBoxedPath(800 , 250, 200 , 300 , 20, 20 ,Color.ORANGE);

        testPaths.add(testCorner);
        testPaths.add(testAttach);
        testPaths.add(testBarrier);

        return testPaths;



    }

    /**
     * Creates walls on all sides of the path
     */
    public void createAllWalls()
    {
        createTopWall();

        createRightWall();
        createLeftWall();

        createBottomWall();
    }

    /**
     * Creates walls on the top and bottom
     */
    public void createTopAndBottomWalls()
    {
        createBottomWall();
        createTopWall();
    }

    /**
     * Creates walls on both sides
     */
    public void createSideWalls()
    {
        createLeftWall();
        createRightWall();
    }

    /**
     * Creates a top right corner
     */
    public void createTopRightCorner()
    {
        createTopWall();
        createRightWall();
    }

    /**
     * Creates a bottom right corner
     */
    public void createBottomRightCorner()
    {
        createBottomWall();
        createRightWall();
    }

    /**
     * Creates a bottom dead end
     */
    public void createBottomDeadEnd()
    {
        createSideWalls();
        createBottomWall();
    }

    /**
     * Creates a top dead end
     */
    public void createTopDeadEnd()
    {
        createTopWall();
        createSideWalls();
    }
    /**
     * Creates a Wall that borders the entire bottom of the Path
     */
    public void createBottomWall()
    {
        createBottomWall( getPosition().getWidth());
    }

    /**
     * Creates a Wall that borders a selected amount of the bottom of the Path
     * @param width
     */
    public void createBottomWall(int width)
    {
        createBottomWall( bottomWallHeight , width);
    }
    /**
     * Creates a Wall that borders that bottom of the Path
     * @param height
     * @param width
     */
    public void createBottomWall(int height , int width)
    {
        int wallY = getPosition().getYPositionEnd();
        int wallX = getPosition().getXPosition();

        walls.add( new Wall( new Position2D( wallX , wallY , width , height), Color.BLUE , false, Shape.RECTANGLE));
    }


    /**
     * Creates a Wall that borders entire left side of the path
     */
    public void createLeftWall()
    {
        createLeftWall( getPosition().getHeight() );
    }

    /**
     * Creates a Wall that borders a selected amount of the Path's left side
     * @param height
     */
    public void createLeftWall(int height)
    {
        createLeftWall( height , leftWallWidth);
    }
    
    /**
     * Creates a Wall that borders that left side of the Path
     * @param height
     * @param width
     */
    public void createLeftWall(int height , int width)
    {
        Position2D position = getPosition();
        int wallY = getPosition().getYPosition();
        int wallX = getPosition().getXPosition() - width;

        new Wall(new Position2D(20, 20, 1150, 20), Color.BLUE, false, Entity.Shape.RECTANGLE);
        walls.add( new Wall( new Position2D( wallX , wallY , width , height), Color.BLUE , false, Shape.RECTANGLE));
    }

    /**
     * Creates a Wall that borders entire right side of the path
     */
    public void createRightWall()
    {
        createRightWall(  getPosition().getHeight());
    }

    /**
     * Creates a Wall that borders a selected amount of the Path's right side
     * @param height
     */
    public void createRightWall(int height)
    {
        createRightWall( height , rightWallWidth);
    }
    /**
     * Creates a Wall that borders that right side of the Path
     * @param height
     * @param width
     */
    public void createRightWall(int height , int width)
    {
        Position2D position = getPosition();
        int wallY = getPosition().getYPosition();
        int wallX = getPosition().getXPosition() + position.getWidth();

        new Wall(new Position2D(20, 20, 1150, 20), Color.BLUE, false, Entity.Shape.RECTANGLE);
        walls.add( new Wall( new Position2D( wallX , wallY , width , height), Color.BLUE , false, Shape.RECTANGLE));
    }

    /**
     * Creates a Wall that borders the entire top of the Path
     */
    public void createTopWall()
    {
        createTopWall(  getPosition().getWidth());
    }

    /**
     * Creates a Wall that borders a selected amount of the top of the Path
     * @param width
     */
    public void createTopWall(int width)
    {
        createTopWall( topWallHeight , width);
    }
    /**
     * Creates a Wall that borders that top of the Path
     * @param height
     * @param width
     */
    public void createTopWall(int height , int width)
    {
        int wallY = getPosition().getYPosition() - height;
        int wallX = getPosition().getXPosition();

        walls.add( new Wall( new Position2D( wallX , wallY , width , height), Color.BLUE , false, Shape.RECTANGLE));
    }




    

}