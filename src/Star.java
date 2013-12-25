
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.Random;

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

    private int x;
    private int y;

    public Star(int x, int y) {
        this.x = x;
        this.y = y;

    }

    @Override
    public void display(Graphics2D graphics) {
        int SIZESTAR = 2;
        Random random = new Random(0);
//    Color color = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255), random.nextInt(255));
        Color color = Color.yellow;
        graphics.setPaint(color);
        graphics.fill(new Ellipse2D.Float(x, y, SIZESTAR, SIZESTAR));
    }

}
