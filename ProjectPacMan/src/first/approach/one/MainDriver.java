package first.approach.one;

import java.awt.*;
import java.util.ArrayList;

/**
 * TODO: NEEDS STYLESHEET(?), INSERT NAMES, JAVADOCS
 *
 * Created by Andrew Michel, Nathan Rooke, Jack Wang, Zach Pownell, Julian Connor
 *
 * on 4/24/2017
 *
 * MiraCosta College - CS 113 Monday 5:30-9:20
 *
 * Simple Driver class, may not require additional functionality
 */
public class MainDriver
{
    public static void main(String[] args)
    {
        boolean testing = false;
        boolean writingVertices = false;
        if( !testing)
        {
            GraphicsController gc = new GraphicsController();

            gc.getManager().initialize();
        }


        Path testPath = new Path();//Graph.testCreatePath(3,4,7,9);
        Position2D testPosition = testPath.getPosition();
        int startXIndex , startYIndex , endXIndex , endYIndex;
        Vertex vertex1 , vertex2, startPoint, endPoint;
        ArrayList<Path> paths = Path.createMazePaths(Color.BLACK);

        Path test = Graph.testCreatePath(6 , 1 , 6 , 30);

        Path current;
        for(int i = 0 ; i <paths.size(); i++)
        {
            current = paths.get(i);
            current.addPathWallOpenings(paths);
            current.removeAllWalls();
            current.createAllWalls();
            paths.set(i, current);

        }

        vertex1 = new Vertex(2,6);
        vertex2 = new Vertex(6 ,6);
        startPoint = vertex1;
        endPoint = vertex2;
       // paths.clear();
        //paths.add( testPath);

        // Calculates the vertices the Path will hit
        startXIndex = startPoint.findSmallerX(endPoint);
        startYIndex = startPoint.findSmallerY(endPoint);
        endXIndex = startPoint.findBiggerX(endPoint);
        endYIndex = startPoint.findBiggerY(endPoint);

        for( Path path : paths)
        {

            System.out.println ( "Path positon : " + path.getPosition() + "\n");
            for( Wall wall : path.getWalls())
            {

                if(path.getPosition().equals(test.getPosition()))
                {
                    System.out.println("Wall :\n");
                    System.out.println( wall + "\n");
                }

            }
        }

        System.out.println("Wall Openings");
        for( Position2D pos : Path.getWallOpenings())
        {
            System.out.println( pos);
        }

        System.out.println("Paths Size : " + paths.size());
        System.out.println("Test Path:\n" + testPosition);
        System.out.println( "Vertex 1 : " + vertex1);
        System.out.println( "Vertex 2 : " + vertex2);
        System.out.println( "Vertex Subtraction " + vertex1.subtract(vertex2) +
         "\nStart X : " + startXIndex + "\tStart Y : " + startYIndex +
                "\tEnd X : " + endXIndex + "\tEnd Y : " + endYIndex);

        if( writingVertices)
        {
            Helper.readGraphVertices( Helper.SOURCE_LOCATION + "Vertices.txt");
            //Helper.writeGraphVertices("ProjectPacMan\\src\\first\\approach\\one\\GraphVertices.txt");
        }
    }
}