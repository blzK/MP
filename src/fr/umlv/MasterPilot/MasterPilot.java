package fr.umlv.MasterPilot;

import java.awt.Color;
import fr.umlv.zen3.Application;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.xml.parsers.ParserConfigurationException;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;
import org.xml.sax.SAXException;

public class MasterPilot {

    final static int WIDTH = 800;
    final static int HEIGHT = 600;

    public static float toXCoordinates(float x) {
        return x + 372;
//        return x+WIDTH/2;
    }

    public static float toYCoordinates(float y) {
        return y + 270;
//        return y+HEIGHT/2;
    }

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
//WORLD
        World world = new World(new Vec2(0, 0));
        float timeStep = 1.0f / 60.f;
        int velocityIterations = 6;
        int positionIterations = 2;
//        MAIN SHUTLE

        Planet planet1 = new Planet(world, 500, 500);

//        Planet planet2 = new Planet(world, 1000, 0);//BONUS
//        Bonus bonus1 = new Bonus(world, 0, 200, RocketType.ExpBomb);
//        Bonus bonus2 = new Bonus(world, 0, 300, RocketType.ImpBomb);
        ShuttleFactory shuttleFactory = new ShuttleFactory();
        MainShuttle mainShuttle = new MainShuttle(0f, 0f, world);
//        SpaceShuttle e1 = shuttleFactory.createShuttle((int)-500, (int) 0, ShuttleType.ENNEMY1, world);
//        SpaceShuttle e2 = shuttleFactory.createShuttle((int) 500, (int) -500, ShuttleType.ENNEMY2, world);
//        SpaceShuttle e3 = shuttleFactory.createShuttle((int) 0, (int) 500, ShuttleType.ENNEMY3, world);
//                SpaceShuttle e4 = shuttleFactory.createShuttle((int) 0, (int) 500, ShuttleType.ENNEMY4, world);
        EnnemyGeneration ennemyGeneration = new EnnemyGeneration();//world, LevelFactory.loadLevel("level"+menuChoice.get("level")));
        Ennemies ennemies = new Ennemies(0, 0);
        //STARS
        Landscape landscape = new Landscape(mainShuttle, world, 20, 30);
        //GENERATION

//        WINDOW
        Menu menu = new Menu();

        Application.run("MasterPilot", WIDTH, HEIGHT, context -> {

            for (;;) {
                try {
                    Thread.sleep(4);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                context.render(graphics -> {

                    HashMap<String, Integer> menuChoice;
                    menuChoice = menu.getChoice();
                    if (menu.getCompteur() == 0) {
                        menuChoice = menu.launch(graphics, WIDTH, HEIGHT, context.pollKeyboard());
                    } else {
                        //             System.out.println("level"++" mode"+menuChoice.get("mode")); // mode = 0 if easy mode or 1 for hardcore mode

//                        try {

////                            ArrayList<SpaceShuttle> listEnnemy = 
//                            ennemies.setEnnemies(ennemyGeneration.Generate("1", world, LevelFactory.loadLevel("level" + menuChoice.get("level"))));
////                        
//                        } catch (ParserConfigurationException | SAXException | IOException e) {
//                            System.err.println("Error load XML");
////                            ArrayList<SpaceShuttle> listEnnemy = null;
//                        }
                        //          BACKGROUND
                        graphics.setColor(Color.black);
                        graphics.fill(new Rectangle2D.Float(0, 0, WIDTH, HEIGHT));
                        int j = 0;
                        float x = mainShuttle.getPosition().x;
                        float y = mainShuttle.getPosition().y;
                        //                    CENTERVIEW
                        graphics.translate(-x, -y);
                        graphics.fill(new Rectangle2D.Float(0, 0, WIDTH, HEIGHT));
                        //                    STARS
                        if (!landscape.isInside(mainShuttle)) {
                            landscape.generateLandscape(mainShuttle, world, 20, 30);
                        }
                        landscape.display(graphics, mainShuttle);

                        //ENNEMY SHUTTLE 
                        if (ennemyGeneration.isGenerated() == false) {
//                            ArrayList<SpaceShuttle> listEnnemy;
                            System.out.println(ennemyGeneration.isGenerated());
                            try {
                                ennemies.setEnnemies(ennemyGeneration.Generate("1", world, LevelFactory.loadLevel("level" + menuChoice.get("level"))));
                                System.out.println(ennemies);
//                        
                            } catch (ParserConfigurationException | SAXException | IOException e) {
                                System.err.println("Error load XML");
//                                listEnnemy = new ArrayList<>();
                            }
//                            if (!listEnnemy.isEmpty()) {
//                                for (SpaceShuttle ennemy : listEnnemy) {
//                                    ennemy.display(graphics);
//                                    ennemy.behave(mainShuttle.getPosition(), graphics);
//                                }
//                            }
                        }

                        if (!ennemies.isEmpty()) {
                            ennemies.display(graphics, mainShuttle);
                            ennemies.behave(mainShuttle.getPosition(), graphics);
                        }
    //                    System.out.println(CollisionCategory.BONUS.getBits()&&CollisionCategory.);
                        //                    e1.display(graphics);
                        //                     e2.display(graphics);
                        //                     e3.display(graphics);
//                                             e4.display(graphics);
                        //                    
                        //                    e1.behave(mainShuttle, graphics);
                        //                    e2.behave(mainShuttle, graphics);
                        //                     e3.behave(mainShuttle, graphics);
//                                             e4.behave(mainShuttle.getPosition(), graphics);

                        //MAIN SPACESHUTTLE
                        mainShuttle.display(graphics);

                        //DEBUG                    
                        //                    System.out.println("SpaceShuttleX " + (int) spaceShuttle.getPosition().x + " SpaceShuttleY " + (int) spaceShuttle.getPosition().y);
                        //                    System.out.println(body.getContactList());
                        //                    System.out.println("angle " + Math.abs(Math.toDegrees(spaceShuttle.getAngle())) % 360);
                        ////////////                    graphics.fill(transformed);
                        //                    graphics.fill(new Ellipse2D.Float(x + WIDTH / 2 - SIZE / 2, y + HEIGHT / 2 - SIZE / 2, SIZE * 2, SIZE));
                        //PLANETS
                        planet1.display(graphics);
                        //                    bonus1.display(graphics, mainShuttle);
                        //                    bonus2.display(graphics, mainShuttle);
                        //                    planet2.display(graphics);
                        //DISPOSE AND STEP TIME
                        graphics.dispose();

                        //KEYBOARD CONTROL
                        ShuttleControl.move(mainShuttle, context.pollKeyboard(), graphics);

                        //DISPOSE BODIES
                        Body bodyTemp = world.getBodyList().getNext();
                        Body bodyTemp2 = world.getBodyList().getNext();
                        for (int i = 0; i < world.getBodyCount() - 1; i++) {

                            if (bodyTemp != null && Math.abs(bodyTemp.getPosition().x - mainShuttle.getPosition().x) > WIDTH * 7 && Math.abs(bodyTemp.getPosition().y - mainShuttle.getPosition().y) > HEIGHT * 7) {
                                world.destroyBody(bodyTemp2);
                            }
                            bodyTemp = bodyTemp.getNext();
                            bodyTemp2 = bodyTemp;
                        }
                        world.step(timeStep, velocityIterations, positionIterations);
                    }
                });
            }
        });
    }
}
