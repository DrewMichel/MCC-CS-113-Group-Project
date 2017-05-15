package first.approach.one;

import java.util.*;

/**
 * Created by Julian on 5/13/2017.
 */
public class GraphAlgorithms {

    /**
     * FInds the index of the minimum distance that hasn't been visited
     * @param distances
     * @param visited
     * @return
     */
    public static int findMinimum(int[] distances , boolean[] visited)
    {
        int x = Integer.MAX_VALUE;
        int y = -1;

        for( int i = 0 ; i < distances.length ; i++)
        {
            if( !visited[i] && distances[i] < x )
            {
                y = i ;
                x = distances[i];
            }
        }

        return y;
    }
    public static Queue<Vertex> dijkstraAlgorithm( Graph graph , Vertex start, Vertex target, int s)
    {
        Queue<Vertex> path = new LinkedList<>();
        int vertexCount = graph.getVerticesCount();
        int[] distances = new int[vertexCount];
        int[] pred = new int[vertexCount];
        boolean[] visited = new boolean[vertexCount];
        int next ;
        for( int i = 0 ; i < distances.length ; i++)
        {
            distances[i] = Integer.MAX_VALUE;
        }

        distances[s] = 0;

        for (int i = 0 ; i < distances.length ; i++)
        {
              next = findMinimum(distances, visited);
              visited[next] = true;

        }


        return path;

    }
    public static int[] relax( int u  , int v, int[] distances , int weight )
    {
        int newDistance = distances[u] + weight;
        boolean foundSmaller;

        if( distances[v] > newDistance )
        {
            distances[v] = newDistance;
        }

        return new int[2];
    }


    public static void bellmanFord( Graph graph, List<Edge> edges , List<Integer> distances,
                                    List<Vertex> vertices ,int setNumber)
    {
        int n = edges.size();
        int u , v;
        Vertex endVertex;
        NavigableSet<Vertex> edgesKey;
        Iterator<Vertex> iterator;
        Edge edge;
        for ( int i = 0 ; i < n ; i++)
        {
            distances.set( i , Integer.MAX_VALUE );
        }

        distances.set( setNumber , 0 );

        for( int k = 0 ; k < n -1 ; k++)
        {
            for( int j = 0; j < edges.size() ; j++ )
            {
                edge = edges.get(j);
            }
        }
    }
}
