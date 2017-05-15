package first.approach.one;

import javafx.geometry.Pos;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
/**
 * Created by Julian on 5/10/2017.
 */
public class Path extends Entity{

    // Wall Direction Constants
    public static final Integer[] LEFT_WALL_DIRECTIONS  = {Vertex.WEST ,Vertex.NORTH_WEST ,Vertex.SOUTH_WEST};
    public static final Integer[] RIGHT_WALL_DIRECTIONS  = {Vertex.EAST ,Vertex.SOUTH_EAST ,Vertex.NORTH_EAST};
    public static final Integer[] TOP_WALL_DIRECTIONS  = {Vertex.NORTH , Vertex.NORTH_EAST , Vertex.NORTH_WEST};
    public static final Integer[] BOTTOM_WALL_DIRECTIONS  = {Vertex.SOUTH , Vertex.SOUTH_EAST , Vertex.SOUTH_WEST};

    // Wall Constants
    public static final int TOP_WALL = 0;
    public static final int RIGHT_WALL = 1;
    public static final int BOTTOM_WALL = 2;
    public static final int LEFT_WALL = 3;

    private ArrayList<Wall> walls = new ArrayList<>(6) ;
    private ArrayList<Position2D> personalOpenings = new ArrayList<>();
    private static ArrayList<Position2D> wallOpenings = new ArrayList<>(2);
    private int leftWallWidth , rightWallWidth, topWallHeight , bottomWallHeight;
    private Vertex start , end;
    /**
     * Default constructor for path
     */
    public Path()
    {
        super();
    }

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

    /**
     * Default constructor for path
     * @param position
     * @param color
     * @param canMove
     * @param shape
     */
    public Path(Position2D position, Color color, boolean canMove, Shape shape, int wallWidth , int wallHeight)
    {
        super( position, color, canMove, shape);
        walls = new ArrayList<>();
        setWallDimensions(wallWidth,wallHeight);
    }

    /**
     * Sets the vertex at the start point
     * @param startPoint
     */
    public void setStartPoint( Vertex startPoint)
    {
        start = startPoint;
    }

    /**
     * Sets the Graph vertex at the end point
     * @param endPoint
     */
    public void setEndPoint( Vertex endPoint)
    {
        end = endPoint;
    }

    /**
     * Gets the vertex at the start point
     * @return
     */
    public Vertex getStartPoint()
    {
        return start;
    }

    /**
     * Gets the vertex at the end point
     * @param endPoint
     * @return
     */
    public Vertex getEndPoint( Vertex endPoint)
    {
        return end;
    }

    /**
     * Adds a wall opening marker
     * <Pre-Conditions>
     *     The Position2D passed in has to intersect the outer borders of the Path.
     *     The points and size of the intersection will be the wall opening
     * </Pre-Conditions>
     * @param intersectionObject the wall opening marker
     * @return boolean true if successfully added the open space marker, else returns false
     */
    public boolean addWallOpening( Position2D intersectionObject)
    {
        return wallOpenings.add( intersectionObject );
    }

    /**
     * Adds a Collection of wall opening markers
     * <Pre-Conditions>
     *     The Position2D in the collection in has to intersect the outer borders of the Path.
     *     The points and size of the intersection will be the wall opening
     * </Pre-Conditions>
     * @param intersectionObjects the collection of wall opening markers
     * @return boolean true if successfully added the open space marker, else returns false
     */
    public boolean addWallOpenings(Collection<Position2D> intersectionObjects)
    {
        return wallOpenings.addAll( intersectionObjects );
    }
    /**
     * Removes a wall opening marker
     * @param intersectionObject the wall opening marker
     * @return boolean true if successfully removed the open space marker, else returns false
     */
    public boolean removeWallOpening( Position2D intersectionObject)
    {
        return wallOpenings.remove( intersectionObject );
    }

    /**
     * Removes a Collection of wall openings from the Path
     * @param wallOpeningObjects the wall opening markers to remove
     * @return boolean true if removal was successful, else returns false
     */
    public boolean removeWallOpenings( Collection<Position2D> wallOpeningObjects)
    {
        return wallOpenings.removeAll(wallOpeningObjects);
    }

    /**
     * Removes all wall openings
     */
    public void removeAllWallOpenings()
    {
        wallOpenings.clear();
    }
    
    /**
     * Checks if the Collection has values in any side's direction
     * @param directions the Collection checked
     * @return a boolean[] of size 4, [0] is top , [1] is right , [2] is bottom , [3] is left  `
     */
    public static boolean[] getWallDirections( Collection<Integer> directions )
    {
        return getWallDirections(directions , false );
    }


    /**
     * Gets the String form of the wall side constant
     * @param sideConstant the wall side constant
     * @return 
     */
    public static String getWallSide( int sideConstant)
    {
        String side;
        switch(sideConstant)
        {
            case TOP_WALL:
                side =  "Top";
                break;
            case RIGHT_WALL:
                side =  "Right";
                break;
            case BOTTOM_WALL:
                side =  "Bottom";
                break;
            case LEFT_WALL:
                side =  "Left";
                break;
            default:
                side =  "ERROR : Invalid Side";
                break;
        }
        
        return side;
    }
    /**
     * Checks if the Collection has values in any side's direction
     * @param directions the Collection checked
     * @param checkWallNotHit whether or not to check if it doesn't hit the Wall directions
     * @return a boolean[] of size 4, [0] is top , [1] is right , [2] is bottom , [3] is left  `
     */
    public static boolean[] getWallDirections( Collection<Integer> directions , boolean checkWallNotHit)
    {
        boolean[] hasWallDirections = new boolean[4];


        // Check Top wall
        hasWallDirections[0] = hasTopWallDirection( directions);
        
        // Check Right wall
        hasWallDirections[1] = hasRightWallDirection( directions);
        
        // Check Bottom wall
        hasWallDirections[2] = hasBottomWallDirection( directions);
        
        // Check Left wall
        hasWallDirections[3] = hasLeftWallDirection( directions);

        if( checkWallNotHit )
        {
            hasWallDirections = Helper.negateArray(hasWallDirections);
        }

        return hasWallDirections;
    }

    /**
     * Checks if the boolean[] direction result has values that point in any Wall direction on the Path
     * @param directionResults the direction results being checked
     * @return a boolean true if the values point to at least one side's wall,
     *         else returns false
     */
    public static boolean hasWallDirections( boolean[] directionResults )
    {
        return directionResults[0] || directionResults[1] || directionResults[2] || directionResults[3];
    }

    /**
     * Checks if the boolean[] direction result has values that point to all Wall directions on the Path
     * @param directionResults the direction results being checked
     * @return a boolean true if the values point to at least one side's wall,
     *         else returns false
     */
    public static boolean hasAllWallDirections( boolean[] directionResults )
    {
        return directionResults[0] && directionResults[1] && directionResults[2] && directionResults[3];
    }

    /**
     * Checks if the Collection has values that point in any Wall direction on the Path
     * @param directions the collection being checked
     * @return a boolean true if the values point to at least one side's wall,
     *         else returns false
     */
    public static boolean hasWallDirections( Collection<Integer> directions )
    {
        return hasTopWallDirection(directions) || hasBottomWallDirection(directions)
                || hasLeftWallDirection(directions) || hasRightWallDirection(directions);
    }

    /**
     * Checks if the Collection contains values that point in all Wall directions
     * @param directions the collection being checked
     * @return a boolean true if the values point to all sides of the Path
     */
    public static boolean hasAllWallDirections( Collection<Integer> directions)
    {
        return hasTopWallDirection(directions) && hasBottomWallDirection(directions)
                && hasLeftWallDirection(directions) && hasRightWallDirection(directions);
    }

    /**
     * Gets the left wall directions as an ArrayList
     * @return ArrayList with type Integer 
     */
    public static ArrayList<Integer> getLeftWallDirections( )
    {
        return Helper.arrayToArrayList( LEFT_WALL_DIRECTIONS);
    }

    /**
     * Gets the right wall directions as an ArrayList
     * @return ArrayList with type Integer 
     */
    public static ArrayList<Integer> getRightWallDirections( )
    {
        return Helper.arrayToArrayList( RIGHT_WALL_DIRECTIONS);
    }

    /**
     * Gets the top wall directions as an ArrayList
     * @return ArrayList with type Integer 
     */
    public static ArrayList<Integer> getTopWallDirections( )
    {
        return Helper.arrayToArrayList( TOP_WALL_DIRECTIONS);
    }

    /**
     * Gets the bottom wall directions as an ArrayList
     * @return ArrayList with type Integer 
     */
    public static ArrayList<Integer> getBottomWallDirections( )
    {
        return Helper.arrayToArrayList( BOTTOM_WALL_DIRECTIONS);
    }


    /**
     * Checks if a Collection contains the left wall direction constants
     * @param directions the Collection being checked
     * @return boolean true if the Collection contains the left wall direction,
     *         else returns false
     */
    public static boolean hasLeftWallDirection( Collection<Integer> directions)
    {
        return Helper.arraySharesElement( directions , LEFT_WALL_DIRECTIONS);
    }

    /**
     * Checks if a Collection contains the bottom wall direction constants
     * @param directions the Collection being checked
     * @return boolean true if the Collection contains the bottom wall direction,
     *         else returns false
     */
    public static boolean hasBottomWallDirection( Collection<Integer> directions)
    {
        return Helper.arraySharesElement( directions , BOTTOM_WALL_DIRECTIONS);
    }

    /**
     * Checks if a Collection contains the right wall direction constants
     * @param directions the Collection being checked
     * @return boolean true if the Collection contains the right wall direction,
     *         else returns false
     */
    public static boolean hasRightWallDirection( Collection<Integer> directions)
    {
        return Helper.arraySharesElement( directions , RIGHT_WALL_DIRECTIONS);
    }

    /**
     * Checks if a Collection contains the top wall direction constants
     * @param directions the Collection being checked
     * @return boolean true if the Collection contains the top wall direction,
     *         else returns false
     */
    public static boolean hasTopWallDirection( Collection<Integer> directions)
    {
        return Helper.arraySharesElement( directions , TOP_WALL_DIRECTIONS);
    }
    
    /**
     * Gets the Walls of this Path object
     * @return ArrayList<Wall> containing all the Path's Walls
     */
    public ArrayList<Wall> getWalls()
    {
        return new ArrayList<Wall>(walls.subList(0 , walls.size()));
    }

    /**
     * Sets the wall dimensions
     * @param width  the width of the walls on the sides
     * @param height the height of the walls on the top and bottom
     */
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
     * Testing method creates a Path
     * @return
     */
    public static Path createTestPath2()
    {
        Path testAttach = new Path(new Position2D(550, 300, 100, 200), Color.PINK, false, Entity.Shape.RECTANGLE);
        testAttach.setWallDimensions(20,20);
        testAttach.createSideWalls();
        return testAttach;
    }

    public static Path createPath ( Vertex center, Color color)
    {
        return createPath( center , center, color );
    }
    public static Path createPath( Vertex startPoint , Vertex endPoint, Color color)
    {
        // Declaration
        int startXIndex, startYIndex, endXIndex, endYIndex, xWidth, yHeight, scaledWidth , scaledHeight, x , y;
        boolean[] openWalls ;
        Path newPath ;
        List<Vertex> startPointConnectedVertices, endPointConnectedVertices;
        ArrayList<Integer> directionsOfConnection;
        boolean verticle, horizontal;

        int x1 = startPoint.getXIndex();
        int x2 = endPoint.getXIndex();
        verticle = x1 == x2;
        horizontal = startPoint.getYIndex() == endPoint.getYIndex();

        // Calculates the vertices the Path will hit
        startXIndex = startPoint.findSmallerX(endPoint);
        startYIndex = startPoint.findSmallerY(endPoint);
        endXIndex = startPoint.findBiggerX(endPoint);
        endYIndex = startPoint.findBiggerY(endPoint);



 System.out.println( "Start : " + startPoint + " End : " + endPoint);

        // Calculates the width and height then scaling it display correctly on the screen
        xWidth = endXIndex - startXIndex;
        yHeight = endYIndex - startYIndex ;
        scaledWidth = (xWidth + 1 ) * Graph.DISPLAY_SCALE_X;
        scaledHeight = ( yHeight + 1) * Graph.DISPLAY_SCALE_Y ;

        x = startXIndex * Graph.DISPLAY_SCALE_X + 20;
        y = startYIndex * Graph.DISPLAY_SCALE_Y + 20;

        if( xWidth == 0)
        {
            scaledWidth = Graph.SINGLE_PATH_WIDTH ;
        }

        if( yHeight == 0)
        {
            scaledHeight = Graph.SINGLE_PATH_WIDTH;
        }
        // Test path
        newPath = new Path(new Position2D( x, y, scaledWidth, scaledHeight), color,
                false, Entity.Shape.RECTANGLE, 20, 20);

        newPath.setStartPoint(startPoint);
        newPath.setEndPoint(endPoint);

        System.out.println( String.format("x1 : %d x2 : %d  Verticle : %b", x1 , x2 , verticle));

        if( !(horizontal && verticle))
        {

            if( horizontal)
            {
                newPath.createTopAndBottomWalls();

            }

            else if( verticle)
            {
                newPath.createSideWalls();
            }
        }


        return newPath;

    }


    /**
     * Creates a pacman maze
     * @param color
     * @return
     */
    public static ArrayList<Path> createMazePaths(Color color)
    {

        ArrayList<Path> mazePaths = new ArrayList<>();


        Path path0 = createPath( new Vertex( 0, 11 ), new Vertex( 4 , 11 ), color );
        mazePaths.add( path0 );
        Path path1 = createPath( new Vertex( 5, 11 ), color );
        mazePaths.add( path1 );
        Path path2 = createPath( new Vertex( 5, 3 ), new Vertex( 5 , 10 ), color );
        mazePaths.add( path2 );
        Path path3 = createPath( new Vertex( 5, 0 ), color );
        path3.createLeftWall();
        path3.createTopWall();
        mazePaths.add( path3 );
        Path path4 = createPath( new Vertex( 7, 0 ), new Vertex( 15 , 0 ), color );
        mazePaths.add( path4 );
        Path path5 = createPath( new Vertex( 16, 0 ), color );
        path5.createRightWall();
        path5.createTopWall();
        mazePaths.add( path5 );
        Path path6 = createPath( new Vertex( 16, 2 ), new Vertex( 16 , 9 ), color );
        mazePaths.add( path6 );
        Path path7 = createPath( new Vertex( 16, 5 ), color );
        mazePaths.add( path7 );
        Path path8 = createPath( new Vertex( 16, 14 ), new Vertex( 16 , 20 ), color );
        mazePaths.add( path8 );
        Path path9 = createPath( new Vertex( 16, 21 ), color );
        path9.createRightWall();
        path9.createBottomWall();
        mazePaths.add( path9 );
        Path path10 = createPath( new Vertex( 15, 21 ), new Vertex( 7 , 21 ), color );
        mazePaths.add( path10 );
        Path path11 = createPath( new Vertex( 5, 21 ), color );
        path11.createLeftWall();
        path11.createBottomWall();
        mazePaths.add( path11 );
        Path path12 = createPath( new Vertex( 5, 20 ), new Vertex( 5 , 14 ), color );
        mazePaths.add( path12 );
        Path path13 = createPath( new Vertex( 7, 11 ), new Vertex( 9 , 11 ), color );
        mazePaths.add( path13 );
        Path path14 = createPath( new Vertex( 11, 11 ), color );
        mazePaths.add( path14 );

        // Space fillers
        mazePaths.add(createPath(new Vertex( 10 ,11), color));
        mazePaths.add( createPath( new Vertex( 6, 21), color));

        // Exit
        mazePaths.add( createPath( new Vertex( 17, 11), color));
        mazePaths.add( createPath( new Vertex( 18, 11), new Vertex( 21, 11), color));

        Path path15 = createPath( new Vertex( 12, 11 ), new Vertex( 15 , 11 ), color );
        mazePaths.add( path15 );
        Path path16 = createPath( new Vertex( 16, 11 ), color );
        mazePaths.add( path16 );
        //Path path17 = createPath( new Vertex( 11, 12 ), new Vertex( 11 , 14 ), color );
        //mazePaths.add( path17 );
       /* Path path18 = createPath( new Vertex( 11, 15 ), color );
        path18.createLeftWall();
        path18.createBottomWall();
        mazePaths.add( path18 );*/
        Path path19 = createPath( new Vertex( 5, 9 ), new Vertex( 5 , 2 ), color );




        return  mazePaths;
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

        testBarrier = Path.createBoxedPath(800 , 300, 200 , 250 , 20, 20 ,Color.ORANGE);

        testPaths.add(testCorner);
        testPaths.add(testAttach);
        testPaths.add(testBarrier);

        return testPaths;



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

        System.out.println("Paths length : \n" + paths.length);
        for( Path path : paths)
        {
            System.out.println( "Path : " + path + "\n");
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

        return getAllPathEntities(paths.toArray( new Path[1]));
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
     * Creates the Walls according to the wanted sides
     * @param directions the collection that contains the ints for the direction
     */
    public void createWalls( Collection<Integer> directions)
    {
        boolean[] wallsBuiltResults = getWallDirections(directions , true );
        createWalls( wallsBuiltResults);
    }

    /**
     * Removes all walls
     */
    public void removeAllWalls()
    {
        walls.clear();
    }

    /**
     * Creates the Walls according to the wanted sides
     * @param wallSides the wall direction check result
     */
    public void createWalls( boolean[] wallSides)
    {
        if( wallSides[TOP_WALL])
        {
            createTopWall();
        }

        if ( wallSides[LEFT_WALL])
        {
            createLeftWall();
        }

        if ( wallSides[BOTTOM_WALL])
        {
            createBottomWall();
        }

        if ( wallSides[RIGHT_WALL])
        {
            createRightWall();
        }


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
     * Creates a top left corner
     */
    public void createTopLeftCorner()
    {
        createTopWall();
        createLeftWall();
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
     * Creates a bottom left corner
     */
    public void createBottomLeftCorner()
    {
        createBottomWall();;
        createLeftWall();
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
     * Creates a dead end in the left side
     */
    public void createLeftDeadEnd()
    {
        createTopAndBottomWalls();
        createLeftWall();
    }

    /**
     * Creates a dead end in the right side
     */
    public void createRightDeadEnd()
    {
        createTopAndBottomWalls();
        createRightWall();
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
        Wall wall;
        Position2D initialPosition = new Position2D( wallX , wallY , width , height);


        wall = new Wall( initialPosition, Color.BLUE , false, Shape.RECTANGLE);
        wall.setSide("Bottom");
        wall.setPath(this);
        walls.add(wall );

        //addWallWithOpenings(wallX , wallY , height , width , BOTTOM_WALL , initialPosition);

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
        Wall wall;
        Position2D initialPosition = new Position2D( wallX , wallY , width , height);


        wall = new Wall( initialPosition, Color.BLUE , false, Shape.RECTANGLE);
        wall.setSide( "Left");
        wall.setPath(this);
        walls.add( wall);

        //addWallWithOpenings(wallX , wallY , height , width , LEFT_WALL , initialPosition);

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
        Wall wall;
        Position2D position = getPosition();
        int wallY = getPosition().getYPosition();
        int wallX = getPosition().getXPosition() + position.getWidth();
        Position2D initialPosition = new Position2D( wallX , wallY , width , height);

        wall = new Wall( initialPosition, Color.BLUE , false, Shape.RECTANGLE);
        wall.setSide( getWallSide(RIGHT_WALL) );
        wall.setPath(this);
        walls.add( wall);

       // addWallWithOpenings(wallX , wallY , height , width , RIGHT_WALL , initialPosition);

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
        Position2D initialPosition = new Position2D( wallX , wallY , width , height);
        Wall wall = new Wall( initialPosition , Color.BLUE , false, Shape.RECTANGLE) ;
        wall.setPath(this);
        wall.setSide( "Top");
        //addWallWithOpenings(wallX , wallY , height , width , TOP_WALL , initialPosition);
        walls.add( wall);
    }

    public static ArrayList<Position2D> sortByY( ArrayList<Position2D> array) {
        ArrayList<Position2D> list = new ArrayList<>(array);
        Position2D temp, currentSmallest, current, next;
        int end, start, nextStart, nextEnd, smallest, nextSmallest;
        currentSmallest = array.get(0);
        for (int i = 0; i < array.size() - 1; i++) {
            current = list.get(i);
            for (int j = array.size() - 1; j > i; j--) {
                next = list.get(j);
                start = list.get(i).getYPosition();
                end = list.get(i).getYPositionEnd() + start;

                nextStart = next.getYPosition();
                nextEnd = next.getYPositionEnd() + nextStart;
                smallest = Helper.findSmallest(end, start);
                nextSmallest = Helper.findSmallest(nextEnd, nextStart);
                if (nextSmallest < smallest) {
                    temp = current;
                    list.set(i, next);
                    list.set(j, temp);
                    current = next;

                }
            }

        }
        return list;

    }

    public static ArrayList<Position2D> sortByX( ArrayList<Position2D> array)
    {
        ArrayList<Position2D> list = new ArrayList<>(array);
        Position2D temp , currentSmallest,current, next;
        int end, start , nextStart, nextEnd, smallest, nextSmallest;
        currentSmallest = array.get(0);
        for( int i = 0 ; i < array.size() - 1 ; i++)
        {
            current = list.get(i);
            for ( int j = array.size()-1 ; j > i ; j--)
            {
                next = list.get(j);
                start = list.get(i).getXPosition();
                end = list.get(i).getXPositionEnd() + start;

                nextStart = next.getXPosition();
                nextEnd = next.getXPositionEnd() + nextStart;
                smallest = Helper.findSmallest(end, start);
                nextSmallest = Helper.findSmallest( nextEnd, nextStart);
                if( nextSmallest < smallest)
                {
                    temp = current;
                    list.set(i , next);
                    list.set( j , temp);
                    current = next;

                }
            }

        }
        return list;

    }

    /**
     * Adds Wall objects with openings if needed to the Path
     * @param wallX
     * @param wallY
     * @param height
     * @param width
     * @param wallDirection
     * @param initialPosition
     * @return
     */
    public ArrayList<Wall> addWallWithOpenings( int wallX , int wallY , int height , int width, int wallDirection,
                                                       Position2D initialPosition
                                                       )
    {

        // Declaration
        boolean hitOpening;
        boolean endOfWall , noWall;
        Iterator<Position2D> iterator ;
        Position2D currentOpeningMarker , wallPosition , endPosition;
        ArrayList<Position2D>  openingsFound ;
        ArrayList<Wall> seperateWalls ;
        int newX , newY , newHeight, newWidth, widthCalc , heightCalc ;
        int adjustedWidth , adjustedHeight;
        Wall newWall;

        // Initialization
        seperateWalls = new ArrayList<>();
        openingsFound = new ArrayList<>();
        wallPosition = initialPosition;
        currentOpeningMarker = null;
        endPosition = new Position2D( initialPosition.getXPositionEnd()+1, initialPosition.getYPositionEnd()+1 , 0, 0);
        newX = 0;
        newY = 0;
        newHeight = 0;
        newWidth = 0;


        // Check which wall openings the border intersects
        for( Position2D opening : wallOpenings)
        {
           // System.out.println( "Opening :\t" +  opening);

            hitOpening =  initialPosition.overlaps(opening) ;//&& !opening.equals(getPosition());

            switch( wallDirection)
            {
                case LEFT_WALL:
                    hitOpening &= initialPosition.touchesLeftBorder(opening);
                    break;
                case RIGHT_WALL:
                    hitOpening &= initialPosition.touchesRightBorder(opening);
                    break;
                case TOP_WALL:
                    hitOpening &= initialPosition.touchesTopBorder(opening);

                    break;
                case BOTTOM_WALL:
                    hitOpening &= initialPosition.touchesBottomBorder(opening);
                    break;
                default:
                    System.err.println("ERROR : Invalid wall direction.");
                    break;
            }
            // Only add the wall opening markers that this Wall will hit
            if( hitOpening)
            {

                openingsFound.add(opening);


                if( !personalOpenings.contains(opening))
                {
                    personalOpenings.add(opening);

                }
            }
        }

        System.out.println( "Path : " + this);
        endOfWall = false;

        if ( openingsFound != null && !openingsFound.isEmpty() )
        {


            if ( wallDirection == LEFT_WALL || wallDirection == RIGHT_WALL)
            {
                openingsFound = sortByY(openingsFound);
            }

            else if ( wallDirection == TOP_WALL || wallDirection == BOTTOM_WALL)
            {
                openingsFound = sortByX(openingsFound);
            }

        }
        do
        {

            iterator = openingsFound.iterator();
            hitOpening = false;
            endOfWall = true;
            noWall = false;
            // Check if the border intersects with this wall's possible opening markers
            while( !hitOpening && false && iterator.hasNext() )
            {

                currentOpeningMarker = iterator.next();
                //System.out.println( "Marker :\t" +  currentOpeningMarker);
                hitOpening = currentOpeningMarker.overlaps(wallPosition);

                // If an wall opening is needed
                if( hitOpening )
                {
                    // Performs the calculations needed to fit the wall so there's an opening
                    switch(wallDirection) {
                        case TOP_WALL:
                        case BOTTOM_WALL:
                        /*
                          System.out.println("Hit the Opening Top Bottom...");

                            // Checks if the Wall's start position overlaps the opening
                            if (currentOpeningMarker.positionOverlaps(wallPosition) )
                        {//overlapsX(wallPosition.getXPosition())) {
                               System.out.println("Position coordinates overlap if statment...");
                               System.out.println( "Wall Position : " + wallPosition + "\n\n");
                                // Aligns the Wall just outside of the wall opening
                                wallPosition.setXPosition(currentOpeningMarker.getXPositionEnd() + 1);


                                // Sets the dimensions for the new Wall segement if this Wall is added
                                newWidth = wallPosition.getWidth();
                                newHeight = wallPosition.getHeight();
                                newX = wallPosition.getXPositionEnd() + 1;
                                newY = wallPosition.getYPosition();
                            }

                            // If the Wall extend's into the wall opening
                            else
                            {
                               System.out.println("Wall extended into hole TOp BOTTOM..." +
                                       "\nWall : " + wallPosition +
                                       "\nMarker : " + currentOpeningMarker);

                                // Set the Wall width to stop just before the opening
                                adjustedWidth = ( currentOpeningMarker.getXPosition()
                                        - wallPosition.getXPosition());

                                wallPosition = new Position2D( wallPosition);
                                wallPosition.setWidth(adjustedWidth);
                                // Sets the dimensions for the new Wall segement if this Wall is added
                                newWidth = endPosition.getXPosition() - currentOpeningMarker.getXPositionEnd();

                                if( newWidth < 0)
                                {
                                    newWidth = 0;
                                }
                                newHeight = wallPosition.getHeight();
                                newX = currentOpeningMarker.getXPositionEnd() + 1;
                                newY = wallPosition.getYPosition();
                            }
                            */

                            System.out.println( "Side : " + getWallSide(wallDirection)  +
                                    "Wall Position :" + wallPosition + "\nMarker : "
                                    + currentOpeningMarker + "\n");
                        // Calculates the new wall dimensions
                        widthCalc = currentOpeningMarker.getXPosition() - wallPosition.getXPosition();

                        wallPosition.setWidth(widthCalc );
                        newX = currentOpeningMarker.getXPositionEnd() + 1;
                        newWidth = endPosition.getXPosition() - newX;
                        newY = wallY;
                        newHeight = height;

                        if( newWidth <= 0)
                        {
                            endOfWall = true;
                        }

                        if ( widthCalc == 0)
                        {
                            noWall = true;
                        }

                        else if ( widthCalc < 0)
                        {
                            widthCalc = currentOpeningMarker.getXPositionEnd() - wallPosition.getXPosition();
                            wallPosition.setWidth(newWidth);
                            //wallPosition.setXPosition(newX);
                            noWall = true ;
                            //newWidth = widthCalc;
                            System.err.println("ERROR : Negative Width (" + widthCalc + ")");
                        }

                        break;
                        case LEFT_WALL:
                        case RIGHT_WALL:
                            /*
                            System.out.println("Hit the Opening LEFT RIGHT...");

                            // Checks if the Wall's start position overlaps the opening
                            if (currentOpeningMarker.overlapsY(wallPosition.getYPosition())) {
                                //System.out.println("Position coordinates overlap if statment...");

                                // Aligns the Wall just outside of the wall opening
                                wallPosition.setYPosition(currentOpeningMarker.getYPositionEnd() +1);

                                // Sets the dimensions for the new Wall segement if this Wall is added
                                newWidth = wallPosition.getWidth();
                                newHeight = wallPosition.getHeight();
                                newX = wallPosition.getXPosition();
                                newY = wallPosition.getYPosition() + 1;
                            }

                            // If the Wall extend's into the wall opening
                            else
                            {
                                System.out.println("Wall extended into hole LEFT RIGHT...\nMarker : " + currentOpeningMarker);
                                System.err.println( "Side : " + getWallSide(wallDirection)  +
                                        "Wall Position :" + wallPosition + "\nMarker : "
                                                + currentOpeningMarker + "\n");
                                // Set the Wall height to stop just before the opening
                                adjustedHeight = ( currentOpeningMarker.getYPosition()
                                        - wallPosition.getYPosition());

                                wallPosition = new Position2D( wallPosition);
                                wallPosition.setHeight(adjustedHeight);

                                // Sets the dimensions for the new Wall segement if this Wall is added
                                newHeight = endPosition.getYPosition() - currentOpeningMarker.getYPositionEnd();

                                if ( newHeight < 0)
                                {
                                    newHeight = 0;
                                }
                                newWidth = wallPosition.getWidth();
                                newY = currentOpeningMarker.getYPositionEnd() + 1;
                                newX = wallPosition.getXPosition();
                            }
                            */
                            // Calculates the new wall dimensions

                            System.out.println( "Side : " + getWallSide(wallDirection)  +
                                    "\nWall Position :" + wallPosition + "\nMarker : "
                                    + currentOpeningMarker + "\n");
                            heightCalc = currentOpeningMarker.getYPosition() - wallPosition.getYPosition();
                            wallPosition = new Position2D( wallPosition);

                            //wallPosition.setHeight(heightCalc );

                            newY = currentOpeningMarker.getYPositionEnd() + 1;
                            //wallPosition.setYPosition(newY);
                            newHeight = endPosition.getYPosition() - newY;
                            newX = wallPosition.getXPosition();
                            newWidth = wallPosition.getWidth();


                            if( newHeight <= 0)
                            {
                                endOfWall = true;
                            }

                            if ( heightCalc == 0)
                            {
                                noWall = true;
                            }
                            else if ( heightCalc > 0)
                            {
                                wallPosition.setHeight(heightCalc);
                            }
                            else if ( heightCalc <= 0)
                            {
                               heightCalc = currentOpeningMarker.getYPositionEnd() - wallPosition.getYPosition();
                              //wallPosition.setHeight(newHeight);
                               //wallPosition.setYPosition(newY);
                               noWall = true ;


                               newHeight = wallPosition.getHeight();
                               newWidth = wallPosition.getWidth();
                               newX = wallPosition.getXPosition();
                               //newY = wallPosition.getYPosition() + 1;
                               //newHeight = heightCalc;
                                System.err.println("ERROR : Negative Height (" + heightCalc + ")");

                            }
//                            wallPosition = new Position2D( newX , newY , newWidth  , heightCalc);
                            break;
                        default:

                            break;

                    }
                }

            }





            // Check if reached the end of the Path on this side
            if( !endOfWall)
            {
                endOfWall = wallPosition.overlaps(endPosition);
                    /*&& (wallPosition.getXPositionEnd() == endPosition.getXPositionEnd())
                    && ( wallPosition.getYPositionEnd() == endPosition.getYPositionEnd());*/
            }


            System.out.println("\nBefore End of wall IF statement...\nEnd Position" + endPosition +"" +
                    "\nWall posiiton : " + wallPosition + "\n");

            // Not at the end of the Path and at a Wall segment
            if(( !noWall && !endOfWall && !openingsFound.isEmpty()) )
            {
                System.out.println("!End of wall...");

                newWall = new Wall( wallPosition , Color.BLUE , false, Shape.RECTANGLE);
                newWall.setSide( getWallSide( wallDirection) + ": Segement Wall");
                newWall.setPath( this);
                // Add the current Wall segment to the list
                walls.add( newWall);

                // Add to test list
                seperateWalls.add( newWall );
                // Updates the new Wall segment to check
                wallPosition = new Position2D(newX , newY , newWidth , newHeight);
                System.out.println( "New Wall Position : " + wallPosition);
               /* wallPosition.setXPosition(newX);
                wallPosition.setYPosition(newY);
                wallPosition.setWidth(newWidth);
                wallPosition.setHeight(newHeight);
*/
            }

            // If there are no wall openings
            else if ( openingsFound.isEmpty())
            {
                newWall = new Wall( wallPosition , Color.BLUE , false, Shape.RECTANGLE);
                newWall.setSide( getWallSide( wallDirection) + " Wall");
                newWall.setPath(this);
                walls.add(newWall);
            }

            // If there needs another wall opening but no current wall
            else if ( noWall)
            {
                System.out.println( "No Wall If Statement...");
                wallPosition = new Position2D(newX , newY , newWidth , newHeight);
                System.out.println( "New Wall Position : " + wallPosition);
            }


        }while( !endOfWall && hitOpening );

        return seperateWalls;

    }

    public static boolean addWallOpening( Path path )
    {
        return wallOpenings.add( path.getPosition());
    }

    public static void addAllPathWallOpenings( Collection<Path> paths)
    {
        for( Path path : paths)
        {
            wallOpenings.add( path.getPosition());
        }
    }


    public static ArrayList<Position2D> getWallOpenings()
    {
        return wallOpenings;
    }
    public void addPathWallOpenings( Collection<Path> paths)
    {
        for( Path path : paths)
        {
            wallOpenings.add( path.getPosition());
        }
    }

    public static void clearWallOpenings()
    {
        wallOpenings.clear();
    }

    public void clearPersonalOpenings()
    {
        personalOpenings.clear();
    }


    public ArrayList<Position2D> getPersonalOpenings()
    {
        return personalOpenings;
    }

    @Override
    public String toString()
    {
        int xIndex, yIndex, x2 , y1;
        xIndex = (getPosition().getXPosition() - 20 ) / Graph.DISPLAY_SCALE_X ;
        y1 = ( getPosition().getYPosition() - 20) / Graph.DISPLAY_SCALE_Y;
        yIndex = (getPosition().getYPositionEnd() - 20) / Graph.DISPLAY_SCALE_Y;
        x2 = (getPosition().getXPositionEnd() - 20) / Graph.DISPLAY_SCALE_X;

        return "Path :\nIndex (" + xIndex + ", " + y1 + " ) , ( " + x2 + ", " + yIndex + " )" +
                "\nPosition : " + getPosition();
    }




    

}
