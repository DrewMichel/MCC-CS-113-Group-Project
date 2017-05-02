package first.approach.one;

import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;

/**
 *
 * Created by Andrew Michel,
 *
 * on 4/24/2017
 *
 * MiraCosta College - CS 113 Monday 5:30-9:20
 *
 * This class extends JPanel to be added onto a JFrame
 * Accepts an ArrayList of Entity and calls each entitie's
 * paintEntity method everytime this panel is drawn
 */
public class CenterPanel extends JPanel
{
    private ArrayList<Entity> entities;
    
    public CenterPanel(ArrayList<Entity> entities)
    {
        // Calls JPanel super constructor
        super();
        this.entities = entities;
    }
    
    public void paintComponent(Graphics g)
    {
        // calls JPanel super paintComponent
        super.paintComponent(g);

        g.setColor(Color.BLACK);

        //g.fillRect(0,0,GraphicsController.DEFAULT_WIDTH, GraphicsController.DEFAULT_HEIGHT);

        g.fillRect(0,0, this.getWidth(), this.getHeight());

        // calls paintEntity(g)
        for(Entity entity : entities)
        {
            if(entity != null)
            {
                entity.paintEntity(g);
            }
        }
    }
}