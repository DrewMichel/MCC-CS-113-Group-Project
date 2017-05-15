package first.approach.one;

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
    /**
     * Creates a GraphicsController object and initializes its SystemManager
     * @param args unused String array
     */
    public static void main(String[] args)
    {
        GraphicsController gc = new GraphicsController();

        gc.getManager().initialize();
    }
}