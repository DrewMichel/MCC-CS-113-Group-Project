package first.approach.one;

import java.util.Comparator;

/**
 * Created by Julian on 5/13/2017.
 */
public class VertexDistanceComparator extends VertexComparator {


    /**
     * Default Constructor For Vertex Distance Comparator
     */
    public VertexDistanceComparator()
    {
        super();
    }

    /**
     * Constructor for Vertex distance comparator
     * @param baseVertex Vertex that is the origin the Vertex distances are calculated from
     */
    public VertexDistanceComparator( Vertex baseVertex )
    {
        super( baseVertex );
    }



    /**
     * Compares the distance of two Vertex objects from the base Vertex
     * @param vertex
     * @param otherVertex
     * @return int for the result of the comparasion,
     *         1 if the first distance is greater,
     *         0 if both distances are equal,
     *         -1 if the first distance is smaller
     */
    @Override
    public int compare( Vertex vertex , Vertex otherVertex)
    {
        if( baseVertex == null )
        {
            baseVertex = new Vertex();
        }
        Double originalDistance = baseVertex.distance( vertex);
        Double comparedDistance = baseVertex.distance( otherVertex);

        return originalDistance.compareTo(comparedDistance) ;
    }
}
