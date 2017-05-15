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
 * Uses a BorderLayout to separate portions of the window into panels
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
    public static final int DEFAULT_WIDTH = 1225;
    public static final int DEFAULT_HEIGHT = 750;
    
    private SystemManager manager;
    
    private JPanel northPanel, eastPanel, southPanel, westPanel;

    private CenterPanel centerPanel;
    
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

        //this.setFocusable(false);
        //centerPanel.setFocusable(true);

        //centerPanel.addKeyListener(manager.getPlayerController());
        addKeyListener(manager.getPlayerController());

        /*
        ThreadDrawer td = new ThreadDrawer(this, centerPanel);

        new Thread()
        {
            td.run();
        }
        */

        ThreadPainter td = new ThreadPainter(this);

        td.start();

        setVisible(true);
    }

    public SystemManager getManager()
    {
        return manager;
    }

    public void paint(Graphics g)
    {
        super.paint(g);

        //centerPanel.paintComponent(g);

        //g.fillRect(0,0,DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    private class ThreadPainter extends Thread
    {
        private JFrame frame;

        public ThreadPainter(JFrame frame)
        {
            this.frame = frame;
        }

        @Override
        public void run()
        {
            while(!manager.isPaused())
            {
                try
                {
                    if(frame != null)
                    {
                        frame.revalidate();
                        frame.repaint();

                        this.sleep(10);
                    }
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    /*
    private class ThreadDrawer implements Runnable
    {

        CenterPanel cp;
        JFrame frame;

        public ThreadDrawer(JFrame frame, CenterPanel cp)
        {
            this.cp = cp;
            this.frame = frame;
        }

        @Override
        public void run()
        {
            try
            {
                while(!manager.isPaused())
                {
                    if(cp != null && frame != null && frame.getGraphics() != null)
                    {
                        cp.paintComponent(frame.getGraphics());

                    }

                    Thread.sleep(5);
                }

            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }
    }
    */
}