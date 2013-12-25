
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import org.jbox2d.callbacks.DestructionListener;
import org.jbox2d.callbacks.RayCastCallback;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.joints.Joint;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author bpertev
 */
public class ShuttleFactory {

    public SpaceShuttle createShuttle(float x, float y, ShuttleType type, World world) {

        switch (type) {
            case SPACESHUTTLE:

                return new  MainShuttle(x, y, world);

            case ENNEMY1:

////                BodyDef bodydef2 = new BodyDef();
////                bodydef2.angle = 0;
////                bodydef2.bullet = true;
////                FixtureDef fd2 = new FixtureDef();
//////                org.jbox2d.collision.shapes.PolygonShape s = new org.jbox2d.collision.shapes.PolygonShape();
//////                s.setAsBox(50, 10);
//////                s.m_radius=100f;
//////                s.m_type=
//////                
////                CircleShape s = new CircleShape();
////                s.m_radius = 70f;
////                fd2.shape = s;
////                fd2.density = 1f;
////                fd2.restitution = 1f;
////                fd2.friction = 1f;
////                bodydef2.position.set(x - 200, y);
////                bodydef2.type = BodyType.DYNAMIC;
////                Body body2 = world.createBody(bodydef2);
////                body2.setUserData("ennemy1");
////                body2.createFixture(fd2);
////                body2.setAngularDamping(3);
//////                body2.setLinearDamping(0.3f);
////                body2.setLinearVelocity(new Vec2(0, -3f));
////
////                return new SpaceShuttle(x, y, world) {
////
////                    @Override
////                    public void display(Graphics2D graphics) {
////                        if (body2.getContactList() != null) {
////                            die();
////                        }
////                        if (!isDead()) {
////                            graphics.setPaint(Color.RED);
////                            graphics.setColor(Color.RED);
////
////                            graphics.fill(new Rectangle2D.Float(body2.getPosition().x + 335, body2.getPosition().y + 260, 100, 20));
////                        } else {
////                            world.destroyBody(body2);
////                        }
////                    }
////
////                };
                return new EnnemyShuttle1(x, y, world);

        }
        throw new IllegalArgumentException("ShuttleType Unknown");

    }

}
