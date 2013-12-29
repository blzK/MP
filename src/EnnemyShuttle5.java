
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
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
public class EnnemyShuttle5 extends SpaceShuttle {

    public EnnemyShuttle5(float x, float y, World world) {
        super(x, y);

        BodyDef bodydef2 = new BodyDef();
        bodydef2.angle = 0;
        bodydef2.bullet = true;
        FixtureDef fd = new FixtureDef();
        PolygonShape s = new org.jbox2d.collision.shapes.PolygonShape();
        s.setAsBox(100, 20);
// s.setAsBox(100, 20, new Vec2(x,y), 0f);
//                
//                
//        CircleShape s = new CircleShape();
//        s.m_radius = 65f;
        fd.filter.categoryBits = CollisionCategory.WORLD.getBits();
        fd.filter.maskBits = CollisionCategory.PLAYER.getBits() | CollisionCategory.WORLD.getBits();
        fd.shape = s;
        fd.density = 0.0001f;
        fd.restitution = 1f;
        fd.friction = 1f;
//        bodydef2.position.set(x - 200, y);
        bodydef2.position.set(x, y);
        bodydef2.type = BodyType.DYNAMIC;
        setBody(world.createBody(bodydef2));
        getBody().setUserData("ennemy5");
        getBody().createFixture(fd);
        getBody().setAngularDamping(3);
//                body2.setLinearDamping(0.3f);

    }

    @Override
    public void display(Graphics2D graphics) {
        super.display(graphics);
//        System.out.println("I'm dead " + isDead());
        if (getBody().getContactList() != null) {
            die();
        }
        if (isDead() == false) {

            graphics.setPaint(Color.RED);
            graphics.setColor(Color.RED);
//            graphics.fill(new Rectangle2D.Float(getBody().getPosition().x + 335, getBody().getPosition().y + 260, 100, 20));
            AffineTransform transform = new AffineTransform();
            transform.rotate(getBody().getAngle(),
                    getBody().getPosition().x, getBody().getPosition().y
            );
            Shape transformed = transform.createTransformedShape(new Rectangle2D.Float(MasterPilot.toXCoordinates(getBody().getPosition().x), MasterPilot.toYCoordinates(getBody().getPosition().y), 100, 20));
//         Shape transformed = transform.createTransformedShape(new Rectangle2D.Float(getBody().getPosition().x, getBody().getPosition().y, 100, 20));
            graphics.fill(transformed);
        }
    }

   @Override
       public void behave(MainShuttle mainShuttle,Graphics2D graphics) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
