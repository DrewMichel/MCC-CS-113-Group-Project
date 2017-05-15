package first.approach.one;

import javafx.scene.text.*;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.Font;
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
    // ArrayList "entities" to store Entity objects
    private ArrayList<Entity> entities;
    
    // CenterPanel constructor
    public CenterPanel(ArrayList<Entity> entities)
    {
        // Calls JPanel super constructor
        super();
        this.entities = entities;
    }
    
    // paintComponent class used to paint the entities onto the panel
    public void paintComponent(Graphics g)
    {
        // calls JPanel super paintComponent
        super.paintComponent(g);

        g.setColor(Color.BLACK);

        //g.fillRect(0,0,GraphicsController.DEFAULT_WIDTH, GraphicsController.DEFAULT_HEIGHT);

        g.fillRect(0,0, this.getWidth(), this.getHeight());

        g.setColor(Color.DARK_GRAY);
        g.fillRect(460,320,280,80);

        // calls paintEntity(g) for each loop to check if entity doesn't = null, if not, paint entity to panel
        for(Entity entity : entities)
        {
            if(entity != null)
            {
                entity.paintEntity(g);
            }
        }

        g.setColor(Color.WHITE);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 18));
        g.drawString("Project Pacman", 70,665);
        g.drawString("Created by Andrew, Julien, Zach, Nathan, Jack", 770,665);
        g.drawString("Score: " + SystemManager.score, 380,665);
        g.drawString("Cherries: " + SystemManager.cherries, 520,665);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 32));
        g.drawString(SystemManager.message,500,360);
    }

}
