
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

    public Stars(SpaceShuttle spaceShuttle) {
        this.xMin = (float) (spaceShuttle.getPosition().x - MasterPilot.WIDTH * 1.5);
        this.yMin = (float) (spaceShuttle.getPosition().y - MasterPilot.HEIGHT * 1.5);
        this.xMax = (float) (spaceShuttle.getPosition().x + MasterPilot.WIDTH * 1.5);
        this.yMax = (float) (spaceShuttle.getPosition().y + MasterPilot.HEIGHT * 1.5);
        generateStarsFirstTime(spaceShuttle);
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

    public void generateStarsFirstTime(SpaceShuttle spaceShuttle) {
        System.out.println("WE GENERATE STARS");
        float x = spaceShuttle.getPosition().x;
        float y = spaceShuttle.getPosition().y;
        this.xMin = (float) (x - MasterPilot.WIDTH * 1.5);
        this.yMin = (float) (y - MasterPilot.HEIGHT * 1.5);
        this.xMax = (float) (x + MasterPilot.WIDTH * 1.5);
        this.yMax = (float) (y + MasterPilot.HEIGHT * 1.5);
        starPositions = new HashMap<>();
        Random random = new Random(0);
        for (int i = 0; i < 200; i++) {

            starPositions.put((int) (random.nextInt((MasterPilot.WIDTH * 5)) + x - 2.5 * MasterPilot.WIDTH), (int) (random.nextInt((MasterPilot.HEIGHT * 5)) + y - 2.5 * MasterPilot.HEIGHT));
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

    public void generateStars(SpaceShuttle spaceShuttle) {
        System.out.println("WE GENERATE STARS");
        float x = spaceShuttle.getPosition().x;
        float y = spaceShuttle.getPosition().y;
        this.xMin = (float) (x - MasterPilot.WIDTH * 1.5);
        this.yMin = (float) (y - MasterPilot.HEIGHT * 1.5);
        this.xMax = (float) (x + MasterPilot.WIDTH * 1.5);
        this.yMax = (float) (y + MasterPilot.HEIGHT * 1.5);
        starPositions = new HashMap<>();
        Random random = new Random(0);

        // EN HAUT
        for (int i = 0; i < 100; i++) {
            starPositions.put((int) (random.nextInt((int) (MasterPilot.WIDTH * 5)) + x), (int) (random.nextInt((int) (MasterPilot.HEIGHT * 5)) + y - 6 * MasterPilot.HEIGHT));
        }
        // BAS
        for (int i = 0; i < 100; i++) {
            starPositions.put((int) (random.nextInt((int) (MasterPilot.WIDTH * 5)) + x), (int) (random.nextInt((int) (MasterPilot.HEIGHT * 5)) + y + 2 * MasterPilot.HEIGHT));
        }

        //A DROITE
        for (int i = 0; i < 100; i++) {
            starPositions.put((int) (random.nextInt((int) (MasterPilot.WIDTH * 5)) + x + 2 * MasterPilot.WIDTH), (int) (random.nextInt((int) (MasterPilot.HEIGHT * 5)) + y));
        }

        //A GAUCHE
        for (int i = 0; i < 100; i++) {
            starPositions.put((int) (random.nextInt((int) (MasterPilot.WIDTH * 5)) + x - 6 * MasterPilot.WIDTH), (int) (random.nextInt((int) (MasterPilot.HEIGHT * 5)) + y));
        }

        starPositions.keySet().iterator();
        Iterator itx = starPositions.keySet().iterator();
        Iterator ity = starPositions.values().iterator();
        while (itx.hasNext()) {
            int xStar = (int) itx.next();
            int yStar = (int) ity.next();
            Star star = new Star(xStar, yStar);
            list.add(star);

        }
    }

}
