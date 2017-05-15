package first.approach.one;

/**
 * Created by Julian on 5/14/2017.
 * Used for the Graph data structure's edges
 */
public class Edge {

    private Vertex start , end;
    private int weight ;

    /**
     * Default Constructor for Edge
     */
    public Edge( )
    {
        this.start = new Vertex();
        this.end = new Vertex();
        this.weight = 0 ;
    }

    /**
     * Edge constructor that sets the edge's vertices
     * <Post-Conditions>
     *     The edge weight is automatically set to the distance between the two vertices
     * </Post-Conditions>
     * @param start the start vertex
     * @param end the end vertex
     */
    public Edge( Vertex start , Vertex end)
    {
        this.start = start;
        this.end = end;
        calculateWeight();
    }

    /**
     * Edge constructor that sets the edge's vertices and weight
     * @param start
     * @param end
     * @param weight
     */
    public Edge( Vertex start , Vertex end , int weight)
    {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    /**
     * Sets the start vertex
     * @param start
     */
    public void setStart( Vertex start)
    {
        this.start = start;
    }

    /**
     * Sets the end vertex
     * @param end
     */
    public void setEnd( Vertex end)
    {
        this.end = end;
    }

    /**
     * Sets the weight of the edge
     * @param weight
     */
    public void setWeight(int weight)
    {
        this.weight = weight ;
    }

    /**
     * Gets the start vertex
     * @return
     */
    public Vertex getStart()
    {
        return start;
    }

    /**
     * Gets the end vertex
     * @return
     */
    public Vertex getEnd()
    {
        return end;
    }

    /**
     * Gets the weight of the edge
     * @return
     */
    public int getWeight()
    {
        return weight;
    }

    /**
     * Calculates the weight of the edge using its distance
     */
    public int calculateWeight()
    {
        weight = (int) Math.ceil( start.distance(end));
        return weight ;
    }

    /**
     * String representation of the Edge
     * @return
     */
    @Override
    public String toString()
    {
        return "Start Vertex : " + start + "\tEnd Vertex : " + end + "\tWeight : " + weight;
    }
}
