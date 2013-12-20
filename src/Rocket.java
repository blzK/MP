
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author azathoth
 */
public class Rocket extends FlyingObject {

    public Rocket(float x, float y) {
        this.x=x;
        this.y=y;        
        shape= new Ellipse2D.Float(x,y,100,100);
    }

    @Override
    public void display(Graphics2D graphics) {
        graphics.setPaint(Color.GREEN);
        graphics.setColor(Color.GREEN);
        graphics.fill(shape);
    }

    @Override
    public boolean isDead() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

