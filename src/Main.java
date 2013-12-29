import java.awt.Color;
import java.util.Random;
import fr.umlv.zen3.Application;
import fr.umlv.zen3.KeyboardEvent;
import java.awt.geom.Ellipse2D;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

public class Main {

    public static void main(String[] args) {
        int WIDTH = 800;
        int HEIGHT = 600;
        int SIZE = 30;
        int STRIDE = 100;
        World world = new World(new Vec2(0, 0));
        float timeStep = 1.0f / 60.f;
        int velocityIterations = 6;
        int positionIterations = 2;

        BodyDef bodydefPlanet = new BodyDef();
        bodydefPlanet.position.set(100, 100);
        bodydefPlanet.type = BodyType.DYNAMIC;
        Body body = world.createBody(bodydefPlanet);
        FixtureDef fdPlanet = new FixtureDef();
        CircleShape cs = new CircleShape();
        cs.m_radius = 100f;
        fdPlanet.shape = cs;
        fdPlanet.density = 1f;
        fdPlanet.restitution = 1f;
        fdPlanet.friction = 1.0f;
        body.createFixture(fdPlanet);      

        Application.run("Colors", WIDTH, HEIGHT, context -> {
            Random random = new Random(0);
            for (;;) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                context.render(graphics -> {
                    for (int i = 0; i < STRIDE; i++) {
                        float x = random.nextInt(WIDTH);
                        float y = random.nextInt(HEIGHT);
                        graphics.setPaint(Color.RED);
                        graphics.fill(new Ellipse2D.Float(100, 100, 100, 100));
                        graphics.setPaint(Color.BLUE);
                        graphics.fill(new Ellipse2D.Float(body.getPosition().x, body.getPosition().y, cs.m_radius, cs.m_radius));
                        world.step(timeStep, velocityIterations, positionIterations);

                        KeyboardEvent event = context.pollKeyboard();
                        if (event != null) {
                            switch (event.getKey()) {
                                case UP:
                                    break;
                                case DOWN:
                                    break;
                                case LEFT:
                                    break;
                                case RIGHT:
                                    break;
                                case SPACE:
                                    break;
                                case B:
                                    break;
                                case N:
                                    break;
                            }
                        }

                    }
                });
            }
        });
    }
}
