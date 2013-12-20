
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import org.jbox2d.collision.shapes.CircleShape;
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
 * @author bpertev
 */
public class ShuttleFactory {

    public SpaceShuttle createShuttle(float x, float y, ShuttleType type, World world) {

        switch (type) {
            case SPACESHUTTLE:
                int x1Points[] = {(int) x + 800 / 2 - 15, (int) x + 800 / 2, (int) x + 800 / 2 + 15};
                int y1Points[] = {(int) y + 600 / 2 - 15, (int) y + 600 / 2 + 25, (int) y + 600 / 2 - 15};
                GeneralPath polygon = new GeneralPath(GeneralPath.WIND_EVEN_ODD, x1Points.length);
                polygon.moveTo(x1Points[0], y1Points[0]);
                for (int index = 1; index < x1Points.length; index++) {
                    polygon.lineTo(x1Points[index], y1Points[index]);
                }
                polygon.closePath();
                BodyDef bodydef = new BodyDef();
                bodydef.angle = 0;
                bodydef.bullet = true;

                FixtureDef fd = new FixtureDef();
                CircleShape cs = new CircleShape();
                cs.m_radius = 1f;

                fd.shape = cs;
                fd.density = 1f;
                fd.restitution = 0.5f;

                fd.friction = 0.5f;

                bodydef.position.set(x, y);

                bodydef.type = BodyType.DYNAMIC;

                Body body = world.createBody(bodydef);
                body.createFixture(fd);
                body.setAngularDamping(1);
//                body.setLinearDamping((float) 0.3);
                return new SpaceShuttle(x, y, polygon, body) {

                    @Override
                    public void display(Graphics2D graphics) {
//                        float x1Points[] = {body.getPosition().x + 800 / 2 - 15, body.getPosition().x + 800 / 2, body.getPosition().x + 800 / 2 + 15};
//                        float y1Points[] = {body.getPosition().y + 600 / 2 - 15, body.getPosition().y + 600 / 2 + 25, body.getPosition().y + 600 / 2 - 15};
                        float x1Points[] = {body.getPosition().x + 800 / 2 - 25, body.getPosition().x + 800 / 2 + 25, body.getPosition().x + 800 / 2 - 25};
                        float y1Points[] = {body.getPosition().y + 600 / 2 + 10, body.getPosition().y + 600 / 2, body.getPosition().y + 600 / 2 - 10};
                        GeneralPath polygon = new GeneralPath(GeneralPath.WIND_EVEN_ODD, x1Points.length);
                        polygon.moveTo(x1Points[0], y1Points[0]);

                        for (int index = 1; index < x1Points.length; index++) {
                            polygon.lineTo(x1Points[index], y1Points[index]);
                        }

                        polygon.closePath();
                        graphics.setColor(Color.darkGray);
//                        graphics.fill(polygon);
                        AffineTransform transform = new AffineTransform();
                        transform.rotate(body.getAngle(), body.getPosition().x + 800 / 2, body.getPosition().y + 600 / 2 + 5);
                        Shape transformed = transform.createTransformedShape(polygon);
                        graphics.fill(transformed);
                        if (body.m_contactList != null) {
                            graphics.setColor(new Color(1f, 0f, 0f, .5f));
                            graphics.fill(new Ellipse2D.Float(body.getPosition().x + 372, body.getPosition().y + 272, 60, 60));
                        }

                    }

            @Override
            public void fire(Graphics2D graphics) {
                Rocket rocket= new Rocket(body.getPosition().x, body.getPosition().y);
                rocket.display(graphics);
            }

                };

            case ENNEMY1:

                BodyDef bodydef2 = new BodyDef();
                bodydef2.angle = 0;
                bodydef2.bullet = true;
                FixtureDef fd2 = new FixtureDef();
//        Shape rec = new Rectangle2D.Float((float)x,(float)y,100,20);
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
                Body body2 = world.createBody(bodydef2);
                body2.createFixture(fd2);
                body2.setAngularDamping(3);

                return new SpaceShuttle(x, y, new Rectangle2D.Float(x, y, 100, 20), body2) {

                    @Override
                    public void display(Graphics2D graphics) {
                        graphics.setPaint(Color.RED);
                        graphics.setColor(Color.RED);

                        graphics.fill(new Rectangle2D.Float(body.getPosition().x + 335, body.getPosition().y + 260, 100, 20));
                    }
                };

        }
        throw new IllegalArgumentException("ShuttleType Unknown");

    }

}
