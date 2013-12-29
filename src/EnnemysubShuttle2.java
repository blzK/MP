
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
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
public class EnnemysubShuttle2 extends SpaceShuttle {

    public EnnemysubShuttle2(float x, float y, World world) {
        super(x, y);

        BodyDef bodydef2 = new BodyDef();
        bodydef2.angle = 0;
        bodydef2.bullet = true;
        FixtureDef fd = new FixtureDef();
        PolygonShape s = new org.jbox2d.collision.shapes.PolygonShape();
        s.setAsBox(100, 20);
//        CircleShape s = new CircleShape();
//        s.m_radius = 65f;
        fd.filter.categoryBits = CollisionCategory.ENNEMY.getBits();
        fd.filter.maskBits = CollisionCategory.PLAYER.getBits() | CollisionCategory.WORLD.getBits();
        fd.shape = s;
        fd.density = 0.0001f;
        fd.restitution = 1f;
        fd.friction = 1f;
        bodydef2.position.set(x, y);
        bodydef2.type = BodyType.DYNAMIC;
        setBody(world.createBody(bodydef2));
        getBody().setUserData("ennemySubShuttle2");
        getBody().createFixture(fd);
        getBody().setAngularDamping(3);
    }

    @Override
    public void display(Graphics2D graphics) {
        super.display(graphics);
        if (getBody().getContactList() != null) {
            die();
        }
        if (isDead() == false) {
            float posX = getBody().getPosition().x;
            float posY = getBody().getPosition().y;
            Shape s = new Rectangle2D.Float(posX, posY, 20, 20);
            graphics.setColor(Color.RED);
//            graphics.fill(s);
            AffineTransform transform = new AffineTransform();
            transform.rotate(getBody().getAngle(),
                    MasterPilot.toXCoordinates(posX),MasterPilot.toYCoordinates( posY)
                    );
            Shape transformed = transform.createTransformedShape(s);
            graphics.fill(transformed);
        }
    }

    @Override
    public boolean die() {
        System.out.println("aargh");
        return super.die(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void behave(MainShuttle mainShuttle, Graphics2D graphics) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

}
