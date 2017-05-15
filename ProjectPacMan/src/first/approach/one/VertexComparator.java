package first.approach.one;

import java.util.Collection;
import java.util.Comparator;

/**
 * Created by Julian on 5/13/2017.
 */
public abstract class VertexComparator implements Comparator<Vertex> {

    protected Vertex baseVertex ;


    public VertexComparator()
    {
    }

    /**
     * Constructor for Vertex comparator
     * @param baseVertex Vertex that is used in all calculations for compareTo()
     */
    public VertexComparator( Vertex baseVertex )
    {
        this.baseVertex = baseVertex ;
    }

    /**
     * Sets the comparator's base Vertex for distance calculation
     * @param vertex
     */
    protected final void setBaseVertex( Vertex vertex)
    {
        this.baseVertex = vertex;
    }

    /**
     * Gets the comparator's base Vertex used for distance calculation
     * @return
     */
    protected final Vertex getBaseVertex( )
    {
        return baseVertex ;
    }


    /**
     * Abstract method to compare two Vertex objectes
     * @param o1
     * @param o2
     * @return
     */
    public abstract int compare(Vertex o1, Vertex o2);
}
