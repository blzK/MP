
import com.sun.webkit.dom.KeyboardEventImpl;
import fr.umlv.zen3.KeyboardEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Anthony
 */
public class Menu {
    
    private int compteur;
    
    public Menu(){
        this.compteur =0;
    }
    
    public HashMap<String, Integer> launch(Graphics2D graphics, int width, int height, KeyboardEvent event){
        HashMap<String,Integer> menuChoice = new HashMap<>();
        menuChoice.put("menuOK", 0);
            
        
        graphics.setColor(Color.darkGray);
        graphics.fill(new Rectangle2D.Float(0, 0, width, height));
        graphics.setColor(Color.white);
        graphics.setFont(new Font("Verdana", Font.BOLD, 40));
        graphics.drawString("MasterPilot", width/2-125, height/6);
        graphics.setFont(new Font("Verdana", Font.BOLD, 20));
        graphics.drawString("N - New Game", width/2-100, height/2);
        graphics.drawString("Q - Exit Game", width/2-100, height/2+50);
        if(event != null){
            switch(event.getKey()){
                case N: this.compteur=1;
                break;
                case Q:
                break;
                default:
                        graphics.setColor(Color.red);
                        graphics.setFont(new Font("Verdana", Font.ITALIC, 20));
                        graphics.drawString("Invalid choice", width/2-100, height/2+200);
                break;
            }
            graphics.setColor(Color.white);
            graphics.setFont(new Font("Verdana", Font.BOLD, 20));
        }
        
        return menuChoice;
    }
    
    public int getCompteur(){
        return this.compteur;
    }
}
