
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

    final static int WIDTH = 800;
    final static int HEIGHT = 600;

    public static float toXCoordinates(float x) {
        return x;
    }

    public static float toYCoordinates(float y) {
        return y;
    }

    public static void main(String[] args) {

        int SIZE = 30;
        int SIZESTAR = 4;
        Stars stars= new Stars(0, 0);
//WORLD
        World world = new World(new Vec2(0, 0));
        float timeStep = 1.0f / 60.f;
        int velocityIterations = 6;
        int positionIterations = 2;
//        MAIN SHUTLE

        Planet planet1 = new Planet(world, 100, 100);
        Planet planet2 = new Planet(world, -100, -100);
        ShuttleFactory shuttleFactory = new ShuttleFactory();
        SpaceShuttle spaceShuttle = shuttleFactory.createShuttle(0f, 0f, ShuttleType.SPACESHUTTLE, world);
        SpaceShuttle e1 = shuttleFactory.createShuttle((int) 500, (int) 500, ShuttleType.ENNEMY1, world);
//        WINDOW
        Application.run("Colors", WIDTH, HEIGHT, context -> {

            for (;;) {
                try {
                    Thread.sleep(4);
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
                    if(!stars.isInside(spaceShuttle)){
                        stars.generateStars(x, y);
                    
                    }
                    stars.display(graphics);
                    
//ENNEMY SHUTTLE

                    e1.display(graphics);

//MAIN SPACESHUTTLE
                    spaceShuttle.display(graphics);

//DEBUG                    
//                    System.out.println("SpaceShuttleX " + (int) spaceShuttle.getPosition().x + " SpaceShuttleY " + (int) spaceShuttle.getPosition().y);
//                    System.out.println(body.getContactList());
//                    System.out.println("angle " + Math.abs(Math.toDegrees(spaceShuttle.getAngle())) % 360);
////////////                    graphics.fill(transformed);
//                    graphics.fill(new Ellipse2D.Float(x + WIDTH / 2 - SIZE / 2, y + HEIGHT / 2 - SIZE / 2, SIZE * 2, SIZE));
//PLANETS
                    planet1.display(graphics);
                    planet2.display(graphics);
//DISPOSE AND STEP TIME

                    graphics.dispose();
//KEYBOARD CONTROL
                    ShuttleControl2.move(spaceShuttle, context.pollKeyboard(), graphics);

                    world.step(timeStep, velocityIterations, positionIterations);
                });
            }
        });
    }
}
