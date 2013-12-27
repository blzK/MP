
import java.awt.Color;
import fr.umlv.zen3.Application;
import java.awt.geom.Rectangle2D;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;

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

    public static void main(String[] args) {
//WORLD
        World world = new World(new Vec2(0, 0));
        float timeStep = 1.0f / 60.f;
        int velocityIterations = 6;
        int positionIterations = 2;
//        MAIN SHUTLE

        Planet planet1 = new Planet(world, 500, 0);
//        Planet planet2 = new Planet(world, 1000, 0);
        ShuttleFactory shuttleFactory = new ShuttleFactory();
        SpaceShuttle spaceShuttle = shuttleFactory.createShuttle(0f, 0f, ShuttleType.SPACESHUTTLE, world);
        SpaceShuttle e1 = shuttleFactory.createShuttle((int) 500, (int) 0, ShuttleType.ENNEMY1, world);
        //STARS
        Landscape landscape = new Landscape(spaceShuttle, world, 20);
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
                    graphics.fill(new Rectangle2D.Float(0, 0, WIDTH, HEIGHT));
//                    STARS
                    if (!landscape.isInside(spaceShuttle)) {
                        landscape.generateLandscape(spaceShuttle, world);
                    }
                    landscape.display(graphics);

//ENNEMY SHUTTLE
                    e1.display(graphics);
//                    e1.fire(graphics);
                    

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
//                    planet2.display(graphics);
//DISPOSE AND STEP TIME
                    graphics.dispose();

//KEYBOARD CONTROL
                    ShuttleControl3.move(spaceShuttle, context.pollKeyboard(), graphics);

//DISPOSE BODIES
                    Body bodyTemp = world.getBodyList().getNext();
                    Body bodyTemp2 = world.getBodyList().getNext();
                    for (int i = 0; i < world.getBodyCount() - 1; i++) {

                        if (bodyTemp != null && Math.abs(bodyTemp.getPosition().x - spaceShuttle.getPosition().x) > WIDTH * 7 && Math.abs(bodyTemp.getPosition().y - spaceShuttle.getPosition().y) > HEIGHT * 7) {
                            world.destroyBody(bodyTemp2);
                        }
                        bodyTemp = bodyTemp.getNext();
                        bodyTemp2 = bodyTemp;
                    }
                    world.step(timeStep, velocityIterations, positionIterations);
                });
            }
        });
    }
}
