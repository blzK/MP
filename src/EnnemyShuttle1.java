
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
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
public class EnnemyShuttle1 extends SpaceShuttle{

    public EnnemyShuttle1(float x, float y, World world) {
        super(x, y);
        
                BodyDef bodydef2 = new BodyDef();
                bodydef2.angle = 0;
                bodydef2.bullet = true;
                FixtureDef fd2 = new FixtureDef();
//                org.jbox2d.collision.shapes.PolygonShape s = new org.jbox2d.collision.shapes.PolygonShape();
//                s.setAsBox(50, 10);
//                s.m_radius=100f;
//                s.m_type=
//                
                CircleShape s = new CircleShape();
                s.m_radius = 70f;
                fd2.shape = s;
                fd2.density = 1f;
                fd2.restitution = 1f;
                fd2.friction = 1f;
                bodydef2.position.set(x - 200, y);
                bodydef2.type = BodyType.DYNAMIC;
                body = world.createBody(bodydef2);
                body.setUserData("ennemy1");
                body.createFixture(fd2);
                body.setAngularDamping(3);
//                body2.setLinearDamping(0.3f);
                body.setLinearVelocity(new Vec2(0, -3f));

    }
     @Override
                    public void display(Graphics2D graphics) {
                        if (body.getContactList() != null) {
                            die();
                        }
                        if (!isDead()) {
                            graphics.setPaint(Color.RED);
                            graphics.setColor(Color.RED);

                            graphics.fill(new Rectangle2D.Float(body.getPosition().x + 335, body.getPosition().y + 260, 100, 20));
                        } else {
                            body.getWorld().destroyBody(body);
                        }
                    }
    
}
