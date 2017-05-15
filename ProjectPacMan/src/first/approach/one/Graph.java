package first.approach.one;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by Julian on 5/13/2017.
 */
public class Graph {


    public static final int SINGLE_PATH_WIDTH = 100;
    public static final int DEFAULT_COLUMNS = 21;
    public static final int DEFAULT_ROWS = 21;
    public static final int DISPLAY_SCALE_X =  (int) (1300 / DEFAULT_COLUMNS );
    public static final int DISPLAY_SCALE_Y =  (int) (630/ DEFAULT_ROWS );

    private int[][] graph ;
    private TreeMap<Vertex, Vertex> edges ;
    private ArrayList<Vertex> vertices ;
    private static ArrayList<Position2D> pathPositions = new ArrayList<>();
    private VertexYComparator vertexYComparator;
    private VertexXComparator vertexXComparator;
    private VertexDistanceComparator vertexDistanceComparator;

    public Graph()
    {
        graph = new int[DEFAULT_ROWS][DEFAULT_COLUMNS];
        initializeComparators();
        edges = new TreeMap<>();
        vertices = new ArrayList<>();
    }


    public int getVerticesCount()
    {
       return vertices.size();
    }
    /**
     * Initializes the comparators
     */
    public void initializeComparators( )
    {
        vertexDistanceComparator = new VertexDistanceComparator();
        vertexXComparator = new VertexXComparator();
        vertexYComparator = new VertexYComparator();
    }

    /**
     * Testing method to test createPath() method
     * @return Path thats a test path
     */
    public static Path testCreatePath()
    {
        Vertex vertex1 , vertex2;
        Path testPath;

        // Test Vertices
        vertex1 = new Vertex(2,6);
        vertex2 = new Vertex(6 ,6);

        testPath = createPath(vertex1 , vertex2);

        return testPath;
    }

    /**
     * Testing method to test createPath() method
     * @return Path thats a test path
     */
    public static Path testCreatePath(int x1 , int y1 , int x2 , int y2)
    {
        Vertex vertex1 , vertex2;
        Path testPath;

        // Test Vertices
        vertex1 = new Vertex(x1,y1);
        vertex2 = new Vertex(x2 ,y2);

        testPath = createPath(vertex1 , vertex2);

        return testPath;
    }

    /**
     * Creates a Path based on the edge's vertices and their info
     * @param startPoint start vertex of edge
     * @param endPoint  end vertex of edge
     * @return Path object with the correct Walls and dimensions
     */
    public static Path createPath( Vertex startPoint , Vertex endPoint )
    {
        return createPath( startPoint , endPoint , Color.ORANGE);
    }

    /**
     * Sets the scaled x and y position
     * @param position
     * @param x
     * @param y
     */
    public static void setPosition( Position2D position , int x , int y )
    {
        position.setXPosition( x * DISPLAY_SCALE_X);
        position.setYPosition( y * DISPLAY_SCALE_Y);
    }
    /**
     * Creates a Path based on the edge's vertices and their info
     * @param startPoint start vertex of edge
     * @param endPoint  end vertex of edge
     * @return Path object with the correct Walls and dimensions
     */
    public static Path createPath( Vertex startPoint , Vertex endPoint , Color color)
    {
        // Declaration
        int startXIndex, startYIndex, endXIndex, endYIndex, xWidth, yHeight, scaledWidth , scaledHeight, x , y;
        boolean[] openWalls ;
        Path newPath ;
        List<Vertex> startPointConnectedVertices, endPointConnectedVertices;
        ArrayList<Integer> directionsOfConnection;
        boolean verticle, horizontal;

        // Calculates the vertices the Path will hit
        startXIndex = startPoint.findSmallerX(endPoint);
        startYIndex = startPoint.findSmallerY(endPoint);
        endXIndex = startPoint.findBiggerX(endPoint);
        endYIndex = startPoint.findBiggerY(endPoint);

        verticle = startPoint.getXIndex() == endPoint.getXIndex();
        horizontal = startPoint.getYIndex() == endPoint.getYIndex();


        // Calculates the width and height then scaling it display correctly on the screen
        xWidth = endXIndex - startXIndex;
        yHeight = endYIndex - startYIndex ;
        scaledWidth = (xWidth + 1 ) * DISPLAY_SCALE_X;
        scaledHeight = ( yHeight + 1) * DISPLAY_SCALE_Y ;

        x = startXIndex * DISPLAY_SCALE_X + 20;
        y = startYIndex * DISPLAY_SCALE_Y + 20;

        if( xWidth == 0)
        {
            //scaledWidth = SINGLE_PATH_WIDTH ;
        }

        if( yHeight == 0)
        {
            //scaledHeight =SINGLE_PATH_WIDTH;
        }
        // Test path
        newPath = new Path(new Position2D( x, y, scaledWidth, scaledHeight), color,
                false, Entity.Shape.RECTANGLE, 20, 20);

        // Determines whether to have an opening on the wall
        // TODO: For now just make a opening in the middle of the box if there are multiple connections
        // TODO : Implement alternative method for barriers and decorate items
        // TODO : Create algorithm and test how it looks in display then tweak if neccessary
        if( startPoint.hasConnectedVertices() )
        {
            // TODO : Might put all this in a method

            // Find which side its connecting from
            directionsOfConnection = startPoint.getConnectedVerticesDirections();


        }

        if ( endPoint.hasConnectedVertices() )
        {
            // Find which side its connecting from
            directionsOfConnection = endPoint.getConnectedVerticesDirections();

            // Create Walls on appropriate sides
            //newPath.createWalls( directionsOfConnection);
        }
/*
       newPath.addWallOpening( new Position2D(200, 136 , 40, 20));
        newPath.addWallOpening( new Position2D(275 , 136, 50, 300));
        newPath.addWallOpening( new Position2D( 150,220,500,20));
        if( pathPositions == null)
        {
            pathPositions = new ArrayList<>();
        }*/
       // newPath.addWallOpenings( pathPositions);
        // Create Walls on appropriate sides

        // Is a connection spot
        if( horizontal  )
        {
            newPath.createSideWalls();
            newPath.createLeftWall();
        }

        else if ( verticle)
        {
            newPath.createTopAndBottomWalls();
        }

        return newPath;
    }

    public TreeMap<Vertex, Vertex> getEdges()
    {
        return edges;
    }
    public static ArrayList<Path> createPacmanMaze()
    {
        ArrayList<Path> mazePaths;
        Graph graph = new Graph();
        Path path;

        mazePaths = new ArrayList<>();
        TreeMap<Vertex, Vertex> edges;
        Set<Vertex> setKeys;
        Vertex v1 , v2;

        /*
        mazePaths.add( graph.addEdge( 0,15,6,15) ) ;
        mazePaths.add( graph.addEdge( 6, 15 , 8, 15 ) ) ;
        mazePaths.add( graph.addEdge( 6, 15 , 6, 9 ) ) ;
        mazePaths.add( graph.addEdge( 6, 15 , 6, 4 ) ) ;
        mazePaths.add( graph.addEdge( 6, 15 , 6, 1 ) ) ;
        mazePaths.add( graph.addEdge( 6, 15 , 6, 21 ) ) ;
*/


        mazePaths.add( createPath( new Vertex( 12, 1 ) , new Vertex(12, 5 ), Color.WHITE  ) );
        mazePaths.add( createPath( new Vertex( 9, 5 ) , new Vertex(9, 9 ), Color.WHITE  ) );
        mazePaths.add( createPath( new Vertex( 9, 9 ) , new Vertex(12, 9 ), Color.WHITE  ) );
        mazePaths.add( createPath( new Vertex( 12, 9 ) , new Vertex(12, 11 ), Color.WHITE  ) );
        mazePaths.add( createPath( new Vertex( 9, 11 ) , new Vertex(9, 21 ), Color.WHITE  ) );
        mazePaths.add( createPath( new Vertex( 1, 21 ) , new Vertex(12, 21 ), Color.WHITE  ) );
        mazePaths.add( createPath( new Vertex( 1, 21 ) , new Vertex(1, 24 ), Color.WHITE  ) );
        mazePaths.add( createPath( new Vertex( 12, 21 ) , new Vertex(12, 24 ), Color.WHITE  ) );
        mazePaths.add( createPath( new Vertex( 12, 24 ) , new Vertex(6, 24 ), Color.WHITE  ) );
        mazePaths.add( createPath( new Vertex( 9, 24 ) , new Vertex(9, 27 ), Color.WHITE  ) );
        mazePaths.add( createPath( new Vertex( 9, 27 ) , new Vertex(12, 27 ), Color.WHITE  ) );
        mazePaths.add( createPath( new Vertex( 12, 27 ) , new Vertex(12, 30 ), Color.WHITE  ) );
        mazePaths.add( createPath( new Vertex( 15, 30 ) , new Vertex(15, 27 ), Color.WHITE  ) );
        mazePaths.add( createPath( new Vertex( 6, 27 ) , new Vertex(1, 27 ), Color.WHITE  ) );
        mazePaths.add( createPath( new Vertex( 1, 1 ) , new Vertex(12, 1 ), Color.WHITE  ) );
        mazePaths.add( createPath( new Vertex( 1, 1 ) , new Vertex(1, 9 ), Color.WHITE  ) );



        // Other side
    //    mazePaths.add( createPath( new Vertex( 20, 1 ) , new Vertex(20, 30), Color.WHITE  ) );
  //      mazePaths.add( createPath( new Vertex( 1, 1 ) , new Vertex(1, 9 ), Color.WHITE  ) );
//        mazePaths.add( createPath( new Vertex( 0, 15 ) , new Vertex(1, 9 ), Color.WHITE  ) );

        /*mazePaths.add( createPath( new Vertex( 1, 5 ) , new Vertex(21, 5 ), Color.WHITE  ) );
        mazePaths.add( createPath( new Vertex( 1, 27 ) , new Vertex(1, 30 ), Color.WHITE  ) );
       */ //mazePaths.add( createPath( new Vertex( 1, 30 ) , new Vertex(25, 30 ), Color.WHITE  ) );
       // mazePaths.add( createPath( new Vertex( 0, 15 ) , new Vertex(8, 15 ) , Color.WHITE) );
      // mazePaths.add( createPath( new Vertex( 6, 1 ) , new Vertex(6, 30 ), Color.WHITE  ) );

       // Test box
      /*  mazePaths.add( createPath( new Vertex( 0, 8 ) , new Vertex(4, 8 ), Color.WHITE  ) );
        mazePaths.add( createPath( new Vertex( 6, 1 ) , new Vertex(6, 30 ), Color.WHITE  ) );
        mazePaths.add( createPath( new Vertex( 1, 21 ) , new Vertex(26, 21 ), Color.WHITE  ) );
       mazePaths.add( createPath( new Vertex( 23, 1 ) , new Vertex(23, 30 ), Color.WHITE  ) );
        //mazePaths.add( createPath( new Vertex( 2, 1 ) , new Vertex(26, 1 ), Color.WHITE  ) );
        mazePaths.add( createPath( new Vertex( 0, 15 ) , new Vertex(15, 16), Color.WHITE  ) );
        //mazePaths.add( createPath( new Vertex( 1, 27 ) , new Vertex(26, 26 ), Color.WHITE  ) );

        */
       /* mazePaths.add( createPath( new Vertex( 23, 1 ) , new Vertex(23, 30 ), Color.WHITE  ) );
        mazePaths.add( createPath( new Vertex( 15, 15) , new Vertex(27, 15 ), Color.WHITE  ) );

        // Unit test
        // test
        mazePaths.add(Graph.createPath( new Vertex( 0, 8 ) , new Vertex(8, 8 ), Color.WHITE   ));
        mazePaths.add(Graph.createPath( new Vertex( 6, 1 ) , new Vertex(6, 30 ), Color.WHITE   ));
        mazePaths.add(Graph.createPath( new Vertex( 1, 21 ) , new Vertex(26, 21 ), Color.WHITE  ) );

        mazePaths.add(  Graph.createPath( new Vertex( 2, 1 ) , new Vertex(26, 1 ), Color.WHITE  ) );
        mazePaths.add(  Graph.createPath( new Vertex( 0, 15 ) , new Vertex(15, 15 ), Color.WHITE  ) );
*/
        edges = graph.getEdges();
        setKeys = edges.keySet();
        /*for( Vertex vertex : setKeys)
        {
            v1 = vertex;
            v2 = edges.get( vertex);
            path = createPath( v1 , v2);
            graph.addPathPosition(path);
            mazePaths.add(path);

        }

        int count = 0;
        for( Path pathT :  mazePaths)
        {
            pathT.addWallOpenings( pathPositions);
            mazePaths.set( count , pathT);
        }*/
        return mazePaths;
    }


    public boolean addPathPosition( Path path)
    {
        return pathPositions.add( path.getPosition());
    }
    public Path addEdge( int x1 , int y1 , int x2 , int y2)
    {
        Vertex v1 = new Vertex( x1, y1);
        Vertex v2 = new Vertex( x2, y2);
        edges.put( v1, v2);
        
        return createPath( v1 , v2);
    }

}
