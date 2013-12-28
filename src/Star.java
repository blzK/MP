
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
public class Star implements Sprite {

    private final int x;
    private final int y;

    public Star(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void display(Graphics2D graphics) {
        int SIZESTAR = 2;
        graphics.setPaint(Color.yellow);
        graphics.fill(new Ellipse2D.Float(x, y, SIZESTAR, SIZESTAR));
    }

}
