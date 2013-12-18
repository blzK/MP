
import java.awt.Color;
import java.util.Random;
import fr.umlv.zen3.Application;
import fr.umlv.zen3.KeyboardEvent;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Iterator;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

public class MasterPilot {

    public static void main(String[] args) {
        int WIDTH = 800;
        int HEIGHT = 600;
        int SIZE = 30;
        int STRIDE = 100;
        int SIZESTAR = 4;
        HashMap<Integer, Integer> starPositions = new HashMap<>();
        Random random = new Random(0);
        for (int i = 0; i < 100; i++) {
            starPositions.put(random.nextInt(WIDTH * 3), random.nextInt(HEIGHT * 3));
        }
//WORLD
        World world = new World(new Vec2(0, 0));
        float timeStep = 1.0f / 60.f;
        int velocityIterations = 6;
        int positionIterations = 2;
//        MAIN SHUTLE

        Planet planet1 = new Planet(world);
        ShuttleFactory shuttleFactory = new ShuttleFactory();
        SpaceShuttle spaceShuttle = shuttleFactory.createShuttle(0, 0, ShuttleType.SPACESHUTTLE, world);
        SpaceShuttle e1 = shuttleFactory.createShuttle((int) 500, (int) 500, ShuttleType.ENNEMY1, world);
//        WINDOW
        Application.run("Colors", WIDTH, HEIGHT, context -> {

            for (;;) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                context.render(graphics -> {
//                    BACKGROUND
                    graphics.setColor(Color.black);
                    graphics.fill(new Rectangle2D.Float(0, 0, WIDTH, HEIGHT));

                    int j = 0;
                    float x = spaceShuttle.getPosition().x;
                    float y = spaceShuttle.getPosition().y;
//                    CENTERVIEW
                    graphics.translate(-x, -y);

//                    STARS
                    Iterator itx = starPositions.keySet().iterator();
                    Iterator ity = starPositions.values().iterator();
                    for (int i = 0; i < 95; i++) {
                        int xStar = (int) itx.next();
                        int yStar = (int) ity.next();

                        Star star = new Star(xStar, yStar);
                        star.display(graphics);
                    }
//ENNEMY SHUTTLE

                    e1.display(graphics);
//                    e1.applyForce(null, null);

//                    //STARS GENERATION
//                    if (spaceShuttle.getPosition().x % WIDTH * 3 == 0) {
//                        for (int i = 0; i < 100; i++) {
//                            starPositions.put(random.nextInt((int) (spaceShuttle.getPosition().x + WIDTH * 3)), random.nextInt((int) spaceShuttle.getPosition().x + HEIGHT * 3));
//                        }
//                    }

//MAIN SPACESHUTTLE
                    spaceShuttle.display(graphics);

//DEBUG                    
//                    System.out.println(body.getContactList());
//                    System.out.println("angle " + Math.toDegrees(body.getAngle())%360);
////////////                    graphics.fill(transformed);
//                    graphics.fill(new Ellipse2D.Float(x + WIDTH / 2 - SIZE / 2, y + HEIGHT / 2 - SIZE / 2, SIZE * 2, SIZE));
                    planet1.display(graphics);
//DISPOSE AND STEP TIME

                    graphics.dispose();

                    KeyboardEvent event = context.pollKeyboard();

                    if (event != null) {
                        System.out.println(event.toString());
                        System.out.println(" x = " + spaceShuttle.getPosition().x + " y = " + spaceShuttle.getPosition().y);
                        switch (event.getKey()) {
                            case UP:
                                if (Math.toDegrees(spaceShuttle.getAngle()) < 180) {
                                    spaceShuttle.applyAngularImpulse((float) 0.2);
                                }
                                spaceShuttle.applyForce(new Vec2(0, -50), spaceShuttle.getPosition());
                                spaceShuttle.applyLinearImpulse(new Vec2(0, -50), spaceShuttle.getPosition());
                                break;

                            case DOWN:
                                if (Math.toDegrees(spaceShuttle.getAngle()) < 0) {
                                    spaceShuttle.applyAngularImpulse((float) 0.2);
                                }
                                spaceShuttle.applyForce(new Vec2(0, 50), spaceShuttle.getPosition());
                                spaceShuttle.applyLinearImpulse(new Vec2(0, 50), spaceShuttle.getPosition());
                                break;
                            case LEFT:
                                if (Math.toDegrees(spaceShuttle.getAngle()) < 85 && Math.toDegrees(spaceShuttle.getAngle()) % 360 > 95) {
                                    spaceShuttle.applyAngularImpulse((float) 0.2);
                                }

                                spaceShuttle.applyForce(new Vec2(-50, 0), spaceShuttle.getPosition());
                                spaceShuttle.applyLinearImpulse(new Vec2(-50, 0), spaceShuttle.getPosition());
                                break;
                            case RIGHT:
                                if (Math.toDegrees(spaceShuttle.getAngle()) < -95 && Math.toDegrees(spaceShuttle.getAngle()) % 360 > -85) {
                                    spaceShuttle.applyAngularImpulse((float) -0.2);
                                }
                                spaceShuttle.applyForce(new Vec2(50, 0), spaceShuttle.getPosition());
                                spaceShuttle.applyLinearImpulse(new Vec2(50, 0), spaceShuttle.getPosition());
                                break;
                            case R:

//                                body.
                                break;
                        }
                    }
                    event = null;
                    world.step(timeStep, velocityIterations, positionIterations);
                });
            }
        });
    }
}
