package first.approach.one;

import java.util.*;

/**
 * Created by Julian on 5/13/2017.
 */
public class Vertex implements Comparable<Vertex> {

    // Direction Constants
    public static final int NORTH = 0;
    public static final int NORTH_EAST = 1;
    public static final int EAST = 2 ;
    public static final int SOUTH_EAST = 3;
    public static final int SOUTH = 4;
    public static final int SOUTH_WEST = 5;
    public static final int WEST = 6 ;
    public static final int NORTH_WEST = 7;
    public static final int ON_POINT = 8 ;
    public static final int ERROR_DIRECTION = 9;

    private int xIndex , yIndex;
    List<Vertex> connectedVertices;
    VertexDistanceComparator comparator ;

    /**
     * Default constructor
     */
    public Vertex()
    {
        xIndex = 0;
        yIndex = 0;
        connectedVertices = new ArrayList<>();
        comparator = new VertexDistanceComparator();
    }

    /**
     * Constructor sets the x and y indexes on the Graph
     * @param xIndex the x index on the Graph
     * @param yIndex the y index on the Graph
     */
    public Vertex( int xIndex , int yIndex )
    {
        this.xIndex = xIndex;
        this.yIndex = yIndex;
        connectedVertices = new ArrayList<>();
        comparator = new VertexDistanceComparator();
    }

    /**
     * Full Constructor sets the x and y indexes on the graph as well as the connected vertices
     * @param xIndex
     * @param yIndex
     * @param connectedVertices
     */
    public Vertex(int xIndex , int yIndex , Collection<Vertex> connectedVertices )
    {
        this.xIndex = xIndex;
        this.yIndex = yIndex;
        this.connectedVertices = new ArrayList<>( connectedVertices );
        comparator = new VertexDistanceComparator();
    }

    /**
     * Sets the Vertex's x index on the graph
     * @param index
     */
    public void setXIndex( int index )
    {
        xIndex = index;
    }

    /**
     * Sets the Vertex's y index on the graph
     * @param index
     */
    public void setYIndex( int index )
    {
        yIndex = index;
    }

    /**
     * Sets the Vertex who's location is used for distance comparasion
     * @param baseVertex
     */
    public void setVertexCompareBase( Vertex baseVertex)
    {
        comparator.setBaseVertex( baseVertex);
    }

    /**
     * Gets the Vertex who's location used for distance comparation
     * @return
     */
    public Vertex getVertexCompareBase()
    {
       return comparator.getBaseVertex();
    }

    /**
     * Gets the Vertex's x index on the Graph
     * @return
     */
    public int getXIndex()
    {
        return xIndex;
    }

    /**
     * Gets the Vertex's y index on the Graph
     * @return
     */
    public int getYIndex()
    {
        return yIndex;
    }

    /**
     * Checks if the Vertex is connected to another vertex
     * @return
     */
    public boolean hasConnectedVertices()
    {
        return !connectedVertices.isEmpty();
    }



    /**
     * Finds the biggest x index of two Vertex objects
     * @param vertex
     * @return int the bigger x index of the two Vertexes
     */
    public int findBiggerX( Vertex vertex )
    {
        int otherX = vertex.xIndex;
        if( this.xIndex >= otherX)
        {
            return this.xIndex;
        }

        else
        {
            return otherX;
        }
    }

    /**
     * Finds the biggest y index of two Vertex's
     * @param vertex
     * @return int the bigger y index of the two Vertexes
     */
    public int findBiggerY( Vertex vertex )
    {
        int otherY = vertex.yIndex;
        if( this.yIndex >= otherY)
        {
            return this.yIndex;
        }

        else
        {
            return otherY;
        }
    }

    /**
     * Finds the smallest x index of two Vertex objects
     * @param vertex
     * @return int the smaller x index of the two Vertexes
     */
    public int findSmallerX( Vertex vertex )
    {
        int otherX = vertex.xIndex;
        if( this.xIndex <= otherX)
        {
            return this.xIndex;
        }

        else
        {
            return otherX;
        }
    }

    /**
     * Finds the smallest y index of two Vertex objects
     * @param vertex
     * @return int the smaller y index of the two Vertexes
     */
    public int findSmallerY( Vertex vertex )
    {
        int otherY = vertex.yIndex;
        if( this.yIndex <= otherY)
        {
            return this.yIndex;
        }

        else
        {
            return otherY;
        }
    }

    /**
     * Finds the sum between two Vertex's y indexes
     * @param vertex
     * @return int that's the sum between the two Vertex's y locations
     */
    public int addYIndex( Vertex vertex )
    {
        return this.yIndex + vertex.yIndex ;
    }

    /**
     * Finds the sum between two Vertex's x indexes
     * @param vertex
     * @return int that's the sum between the two Vertex's x locations
     */
    public int addXIndex( Vertex vertex )
    {
        return this.xIndex + vertex.xIndex ;
    }

    /**
     * Adds a Vertex to this vertex
     * @param vertex
     * <note>
     *     Uses Vector addition
     * </note>
     * @return Vertex who's indices are the sum of the two Vertex object's indexes
     */
    public Vertex add( Vertex vertex)
    {
        int xSum = this.xIndex + vertex.xIndex;
        int ySum = this.yIndex + vertex.yIndex;

        return new Vertex( xSum , ySum );
    }

    /**
     * Subtracts a Vertex from this Vertex
     * @param otherVertex
     * <note>
     *     Uses Vector subtraction
     * </note>
     * @return Vertex who's indexes are the difference between the two Vertex object's indexes
     */
    public Vertex subtract( Vertex otherVertex)
    {
        int xDiff = this.xIndex - otherVertex.xIndex;
        int yDiff = this.yIndex - otherVertex.yIndex;

        return new Vertex( xDiff , yDiff );
    }

    /**
     * Finds the difference between two Vertex's y indexes
     * @param vertex
     * @return int that's the difference between the two Vertex's y locations
     */
    public int subtractYIndex( Vertex vertex )
    {
        return this.yIndex - vertex.yIndex ;
    }

    /**
     * Finds the difference between two Vertex's x indexes
     * @param vertex
     * @return int that's the difference between the two Vertex's x locations
     */
    public int subtractXIndex( Vertex vertex )
    {
        return this.xIndex - vertex.xIndex ;
    }

    /**
     * Finds the distance between two vertices
     * @param otherVertex
     * @return double that's the distance between the two vertices
     */
    public double distance( Vertex otherVertex )
    {
        int xPointsSquared = ( this.xIndex * otherVertex.xIndex ) * ( this.xIndex * otherVertex.xIndex );
        int yPointsSquared = ( this.yIndex * otherVertex.yIndex ) * ( this.yIndex * otherVertex.yIndex );

        return Math.sqrt( xPointsSquared + yPointsSquared ) ;

    }

    /**
     * Finds the distance from the origin ( 0 , 0 ) Graph point
     * @return
     */
    public double distance()
    {
        // The distance formula
        return Math.sqrt( ( this.xIndex * this.xIndex ) + ( this.yIndex * this.yIndex));
    }

    /**
     * Gets the number of connected vertices
     * @return
     */
    public int connectedVertexCount()
    {
        return connectedVertices.size();
    }
    
    /**
     * Removes a Collection of connected vertices
     * @param vertices
     * @return boolean true if removal was successful, else returns false
     */
    public boolean removeConnectedVertices( Collection<Vertex> vertices )
    {

        return connectedVertices.removeAll( vertices );
    }
    
    /**
     * Removes a connected vertex from the choosen index
     * @param index
     * @return Vertex that was removed
     */
    public Vertex removeConnectedVertex( int index )
    {
        return connectedVertices.remove( index );
    }
    
    /**
     * Removes a connected vertex
     * @param vertex
     * @return boolean true if removal was successful, else returns false
     */
    public boolean removeConnectedVertex( Vertex vertex )
    {
        return connectedVertices.remove( vertex );
    }
    /**
     * Adds a connected vertex
     * @param vertex
     * @return boolean true if the add was successful, else returns false
     */
    public boolean addConnectedVertex( Vertex vertex )
    {
        return connectedVertices.add( vertex );
    }

    /**
     * Adds a Collection of connected vertices
     * @param vertices
     * @return boolean true if add was successful, else returns false
     */
    public boolean addConnectedVertices( Collection<Vertex> vertices)
    {
        return connectedVertices.addAll( vertices);
    }

    /**
     * Completely removes all connected vertices
     */
    public void clearConnectedVertices()
    {
        connectedVertices.clear();
    }
    
    /**
     * Returns a Iterator for the Vertex's connected vertices
     * @return
     */
    public Iterator<Vertex> iterator()
    {
        return connectedVertices.iterator();
    }

    /**
     * Gets the Vertex's connected vertices
     * @return
     */
    public ArrayList<Vertex> getConnectedVertices()
    {
        return new ArrayList<Vertex>( connectedVertices );
    }

    /**
     * Gets all the the directions other vertices are connecting to this one
     * @return ArrayList of Integers that hold the direction constants for each direction
     */
    public ArrayList<Integer> getConnectedVerticesDirections()
    {
        ArrayList<Integer> directions = new ArrayList<>();
        int connectedDirection ;

        // Checks each connected vertex and stores it's direction
        for( Vertex connectedVertex : connectedVertices )
        {
            connectedDirection = connectedDirection(connectedVertex);
            directions.add( connectedDirection ) ;
        }

        return directions ;
    }


    /**
     * Wrapper method that finds the direction a Vertex is connecting from
     * @param connectedVertex
     * @return int representation using the direction constants in Vertex class
     */
    public int connectedDirection( Vertex connectedVertex)
    {
        return connectionDirection(this , connectedVertex);
    }

    /**
     * Finds the direction of the connected vertex
     * @param base
     * @param connectedVertex
     * @return int representation of direction using direction constants in Vertex class
     */
    public static int connectionDirection( Vertex base , Vertex connectedVertex )
    {
        int xDiff , yDiff;

        // Finds the displacement of x and y between the two vertices
        xDiff = connectedVertex.subtractXIndex( base ) ;
        yDiff = connectedVertex.subtractYIndex( base ) ;

        // If they are on the same point
        if( xDiff == 0 && yDiff == 0)
        {
            return ON_POINT ;
        }

        // IF the connected vertex is either directly above or below the base vertex
        else if ( xDiff == 0 )
        {
            // If the vertex is above the base
            if( yDiff > 0)
            {
                return NORTH;
            }

            // If the vertex is below the base
            else
            {
                return SOUTH;
            }
        }

        // If the connected vertex is directly left or right of the base vertex
        else if ( yDiff == 0)
        {
            // If the vertex is to right of the base vertex
            if( xDiff > 0)
            {
                return EAST;
            }

            // If the vertex is to the left of the base vertex
            else
            {
                return WEST;
            }
        }

        // If the connected vertex is diagonally to the right of base vertex
        else if ( xDiff > 0 )
        {
            // If the vertex is to diagonally up and to the right of the base vertex
            if( yDiff > 0 )
            {
                return NORTH_EAST;
            }
            // If the vertex is to diagonally down and to the right of the base vertex
            else
            {
                return SOUTH_EAST;
            }
        }

        // If the connected vertex is diagonally to the left of base vertex
        else if ( xDiff < 0 )
        {
            // If the vertex is to diagonally up and to the left of the base vertex
            if ( yDiff > 0)
            {
                return NORTH_WEST;
            }
            // If the vertex is to diagonally down and to the left of the base vertex
            else
            {
                return SOUTH_WEST;
            }
        }

        // If an error occurred and the method couldn't determine the direction
        else
        {
            // TODO : Create error log message and error value
            System.err.println("ERROR : Vertex direction couldn't find a valid direction.");
            return ERROR_DIRECTION;
        }


    }

    /**
     * Creates a String representation of the vertex location
     * @return
     */
    public String indexInfo()
    {
        return String.format("( %d , %d )", xIndex , yIndex);

    }

    /**
     * Compares this Vertex with another Vertex
     * @param vertex
     * @return int 1 if this compared value is greater than the other Vertex,
     *             0 if the compared value is equal,
     *             -1 if the compared value is less than
     */
    @Override
    public int compareTo( Vertex vertex )
    {

     return comparator.compare( this , vertex);

    }

    /**
     * Creates a String representation of the Vertex
     * @return
     */
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        String indexInfo = indexInfo();
        String connectedVerticesInfo = "Connected Vertices : ";
        
        // If there aren't connect vertices
        if( !hasConnectedVertices() )
        {
            connectedVerticesInfo += " None" ;
        }
        
        // Adds the connected vertex's point location info to the output
        else
        {
            for( Vertex vertex : connectedVertices)
            {
                connectedVerticesInfo += vertex.indexInfo() + " ";
            } 
        }
        
        return String.format( "%s%n%s%n" , indexInfo , connectedVerticesInfo );
    }
}
