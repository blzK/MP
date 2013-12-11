package masterpilot;

import java.awt.Color;
import java.awt.RadialGradientPaint;
import java.awt.geom.Ellipse2D;
import java.util.Random;
import fr.umlv.zen3.Application;
import fr.umlv.zen3.KeyboardEvent;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
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
        BodyDef bodydef = new BodyDef();
        bodydef.angle = 0;
        FixtureDef fd = new FixtureDef();
        CircleShape cs = new CircleShape();
        cs.m_radius = 1f;

        fd.shape = cs;
        fd.density = 1f;
        fd.restitution = 1f;

        fd.friction = 1f;

        bodydef.position.set(WIDTH / 2, HEIGHT / 2);

        bodydef.type = BodyType.DYNAMIC;
        Body body = world.createBody(bodydef);
        body.createFixture(fd);
//      PLANET

        BodyDef bodydefPlanet = new BodyDef();

        bodydefPlanet.position.set(750, 750);
        bodydefPlanet.type = BodyType.DYNAMIC;
        Body bodyPlanet = world.createBody(bodydefPlanet);
        FixtureDef fdPlanet = new FixtureDef();

        cs.m_radius = 350f;

        fdPlanet.shape = cs;
        fdPlanet.density = 1f;
        fdPlanet.restitution = 1f;

        fdPlanet.friction = 1.0f;
        bodyPlanet.createFixture(fd);

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
                    float x = body.getPosition().x;
                    float y = body.getPosition().y;
//                    CENTERVIEW
                    graphics.translate(-x, -y);

//                    STARS
                    Iterator itx = starPositions.keySet().iterator();
                    Iterator ity = starPositions.values().iterator();
                    for (int i = 0; i < 95; i++) {
                        int xStar = (int) itx.next();
                        int yStar = (int) ity.next();
                        Color color = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255), random.nextInt(255));
                        
                        graphics.setPaint(Color.yellow);
                        graphics.fill(new Ellipse2D.Float(xStar, yStar, SIZESTAR, SIZESTAR));

                    }

//                    body.applyLinearImpulse(new Vec2(50f, 5f), body.getWorldPoint(body.getWorldCenter()));
//                    body.applyTorque(6);
//                    body.applyForceToCenter(new Vec2(50.0f, 50.0f));

//MAIN SPACESHUTTLE
                    int x1Points[] = {(int) x + WIDTH / 2 - 25, (int) x + WIDTH / 2, (int) x + WIDTH / 2 + 25};
                    int y1Points[] = {(int) y + HEIGHT / 2 - 25, (int) y + HEIGHT / 2 + 25, (int) y + HEIGHT / 2 - 25};
                    GeneralPath polygon
                            = new GeneralPath(GeneralPath.WIND_EVEN_ODD,
                                    x1Points.length);
                    polygon.moveTo(x1Points[0], y1Points[0]);

                    for (int index = 1; index < x1Points.length; index++) {
                        polygon.lineTo(x1Points[index], y1Points[index]);
                    };
                    graphics.setColor(Color.darkGray);
                    polygon.closePath();
//graphics.draw(polygon);
//ROTATE
                    AffineTransform transform = new AffineTransform();
                    transform.rotate(body.getAngularVelocity(), x + WIDTH / 2, y + HEIGHT / 2);
                    Shape transformed = transform.createTransformedShape(polygon);
                    System.out.println("angle " + Math.toDegrees(body.getAngle()));
                    graphics.fill(transformed);
//                    graphics.fill(new Ellipse2D.Float(x + WIDTH / 2 - SIZE / 2, y + HEIGHT / 2 - SIZE / 2, SIZE * 2, SIZE));
                    

//PLANET
                    RadialGradientPaint paint = new RadialGradientPaint(bodyPlanet.getPosition().x, bodyPlanet.getPosition().y, 500, new float[]{0f, 1f}, new Color[]{new Color(37,197,246), new Color(17, 21, 134)});
                    graphics.setPaint(paint);
                    graphics.fill(new Ellipse2D.Float(bodyPlanet.getPosition().x, bodyPlanet.getPosition().y, 500, 500));
//DISPOSE AND STEP TIME
                    graphics.dispose();
                        KeyboardEvent event = context.pollKeyboard();

                    if (event != null) {
                        System.out.println(event.toString());
                        System.out.println(" x = " + body.getPosition().x + " y = " + body.getPosition().y);
                        switch (event.getKey()) {
                            case UP:
                                body.applyLinearImpulse(new Vec2(0, -50), body.getPosition());
                                break;

                            case DOWN:

                                body.applyLinearImpulse(new Vec2(0, 50), body.getPosition());
                                break;
                            case LEFT:

                                body.applyLinearImpulse(new Vec2(-50, 0), body.getPosition());
                                break;
                            case RIGHT:

                                body.applyLinearImpulse(new Vec2(50, 0), body.getPosition());
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
