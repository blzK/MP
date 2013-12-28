
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author azathoth
 */
public class MainShuttle extends SpaceShuttle {

    public MainShuttle(float x, float y, World world) {
        super(x, y);
        BodyDef bodydef = new BodyDef();
        bodydef.angle = 0;
        bodydef.bullet = true;

        FixtureDef fd = new FixtureDef();
        CircleShape cs = new CircleShape();
        cs.m_radius = 4;

        fd.filter.categoryBits = CollisionCategory.PLAYER.getCategory();
        fd.filter.maskBits = CollisionCategory.WORLD.getCategory();
        fd.shape = cs;
        fd.density = 0.1f;
        fd.restitution = 1f;

        fd.friction = 1f;

        bodydef.position.set(x, y);

        bodydef.type = BodyType.DYNAMIC;

        setBody(world.createBody(bodydef));
        getBody().setUserData("spaceShuttle");
        getBody().createFixture(fd);
        getBody().setAngularDamping(1);
        getBody().setLinearDamping((float) 0.1);
    }

    @Override
    public void display(Graphics2D graphics) {
        super.display(graphics); //To change body of generated methods, choose Tools | Templates.
        //SHIELD - CHECK COLLISIONS
        if (getBody().m_contactList != null) {
            graphics.setColor(new Color(1f, 0f, 0f, .5f));
            graphics.fill(new Ellipse2D.Float(MasterPilot.toXCoordinates(getBody().getPosition().x), MasterPilot.toYCoordinates(getBody().getPosition().y), 60, 60));
//                            graphics.fill(new Ellipse2D.Float(getBody().getPosition().x+400, getBody().getPosition().y+300, 60, 60));
        }
//MAIN SHUTTLE DISPLAY        
        float x1Points[] = {getBody().getPosition().x + 800 / 2 - 15, getBody().getPosition().x + 800 / 2 + 30, getBody().getPosition().x + 800 / 2 - 15};
        float y1Points[] = {getBody().getPosition().y + 600 / 2 + 15, getBody().getPosition().y + 600 / 2, getBody().getPosition().y + 600 / 2 - 15};
//                        float x1Points[] = {getBody().getPosition().x + 800 / 2 - 25, getBody().getPosition().x + 800 / 2 + 25, getBody().getPosition().x + 800 / 2 - 25};
//                        float y1Points[] = {getBody().getPosition().y + 600 / 2 + 10, getBody().getPosition().y + 600 / 2, getBody().getPosition().y + 600 / 2 - 10};
        GeneralPath polygon = new GeneralPath(GeneralPath.WIND_EVEN_ODD, x1Points.length);
        polygon.moveTo(x1Points[0], y1Points[0]);

        for (int index = 1; index < x1Points.length; index++) {
            polygon.lineTo(x1Points[index], y1Points[index]);
        }

        polygon.closePath();
        graphics.setColor(Color.darkGray);
//                        graphics.fill(polygon);
        // ANGLE TRANSFORM
        AffineTransform transform = new AffineTransform();
        transform.rotate(getBody().getAngle(),
                getBody().getPosition().x + MasterPilot.WIDTH / 2, getBody().getPosition().y + MasterPilot.HEIGHT / 2
        );
        Shape transformed = transform.createTransformedShape(polygon);
        graphics.fill(transformed);
    }

    @Override
    public void behave() {

    }

}
