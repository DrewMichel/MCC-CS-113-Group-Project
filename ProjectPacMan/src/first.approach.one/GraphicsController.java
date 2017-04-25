package first.approach.one;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Graphics;

/**
 * Created by Andrew Michel,
 *
 * on 4/24/2017
 *
 * MiraCosta College - CS 113 Monday 5:30-9:20
 *
 *
 * This class extends JFrame to draw a window via Java
 *
 * Contains a SystemManager object which controls game logic
 *
 * Uses a BorderLayout to seperate portions of the window into panels
 *           NORTH
 *      WEST CENTER EAST
 *           SOUTH
 *
 * Where Center is a CenterPanel intended to draw Entity objects
 *
 * TODO: All Panels could be CenterPanel objects, player input(?), ENSURE SINGLE IMPORTS, etc...
 *
 */
public class GraphicsController extends JFrame
{
    public static final int DEFAULT_WIDTH = 600;
    public static final int DEFAULT_HEIGHT = 600;
    
    private SystemManager manager;
    
    private JPanel northPanel, eastPanel, centerPanel, southPanel, westPanel;
    
    public GraphicsController()
    {
        super("Pac Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        
        // layout north, east, center, south, west
        setLayout(new BorderLayout());
        
        manager = new SystemManager();
        
        northPanel = new JPanel();
        eastPanel = new JPanel();
        centerPanel = new CenterPanel(manager.getEntities());
        southPanel = new JPanel();
        westPanel = new JPanel();
        
        add(northPanel, BorderLayout.NORTH);
        add(eastPanel, BorderLayout.EAST);
        add(centerPanel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);
        add(westPanel, BorderLayout.WEST);
        
        setVisible(true);
    }

    public void paint(Graphics g)
    {
        super.paint(g);

        //g.fillRect(0,0,DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
}