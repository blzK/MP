
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
public class Stars implements Sprite {

    ArrayList<Star> list = new ArrayList<>();
    HashMap<Integer, Integer> starPositions = new HashMap<>();
    float xMin;
    float yMin;
    float xMax;
    float yMax;

    public Stars(float x, float y) {
        this.xMin = x;
        this.yMin = y-2*MasterPilot.HEIGHT;
        this.xMax = x + MasterPilot.WIDTH * 4;
        this.yMax = y + MasterPilot.HEIGHT * 4;
        generateStars(x, y);
    }

    public void generateStars(float x, float y) {
        System.out.println("WE GENERATE STARS");
        this.xMin = x;
          this.yMin = y-2*MasterPilot.HEIGHT;
        this.xMax = x + MasterPilot.WIDTH * 4;
        this.yMax = y + MasterPilot.HEIGHT * 4;
        starPositions = new HashMap<>();
        Random random = new Random(0);
        for (int i = 0; i < 200; i++) {
            starPositions.put(random.nextInt((int) (x + MasterPilot.WIDTH * 4)), random.nextInt((int) (y + MasterPilot.HEIGHT * 4)));
        }

        starPositions.keySet().iterator();
        Iterator itx = starPositions.keySet().iterator();
        Iterator ity = starPositions.values().iterator();
        for (int i = 0; i < 95; i++) {
            int xStar = (int) itx.next();
            int yStar = (int) ity.next();

            Star star = new Star(xStar, yStar);
            list.add(star);

        }
    }

    public boolean isInside(SpaceShuttle spaceShuttle) {
        if (spaceShuttle.getPosition().x >= xMin && spaceShuttle.getPosition().x <= xMax && spaceShuttle.getPosition().y >= yMin && spaceShuttle.getPosition().y <= yMax) {
            return true;
        }
        System.out.println("NOT INSIDE");
        return false;
    }

    @Override
    public void display(Graphics2D graphics) {
        for (Star star : list) {
            star.display(graphics);
        }
    }

    @Override
    public boolean isDead() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
