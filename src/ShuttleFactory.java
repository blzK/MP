
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Rot;
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
 * @author bpertev
 */
public class ShuttleFactory {

    public SpaceShuttle createShuttle(float x, float y, ShuttleType type, World world) {

        switch (type) {
            case SPACESHUTTLE:
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
                return new SpaceShuttle(x, y, body) {
                    ArrayList<Rocket> rockets = new ArrayList<>();

                    @Override
                    public void applyLinearImpulse(Vec2 impulse, Vec2 point, Graphics2D graphics) {
                        graphics.setColor(new Color(1f, 0f, 0f, .5f));
                        graphics.fill(new Ellipse2D.Float(body.getPosition().x + 372, body.getPosition().y + 272, 60, 60));
                        super.applyLinearImpulse(impulse, point, graphics); //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void display(Graphics2D graphics) {
                        float x1Points[] = {body.getPosition().x + 800 / 2 - 15, body.getPosition().x + 800 / 2 + 30, body.getPosition().x + 800 / 2 - 15};
                        float y1Points[] = {body.getPosition().y + 600 / 2 + 15, body.getPosition().y + 600 / 2, body.getPosition().y + 600 / 2 - 15};
//                        float x1Points[] = {body.getPosition().x + 800 / 2 - 25, body.getPosition().x + 800 / 2 + 25, body.getPosition().x + 800 / 2 - 25};
//                        float y1Points[] = {body.getPosition().y + 600 / 2 + 10, body.getPosition().y + 600 / 2, body.getPosition().y + 600 / 2 - 10};
                        GeneralPath polygon = new GeneralPath(GeneralPath.WIND_EVEN_ODD, x1Points.length);
                        polygon.moveTo(x1Points[0], y1Points[0]);

                        for (int index = 1; index < x1Points.length; index++) {
                            polygon.lineTo(x1Points[index], y1Points[index]);
                        }

                        polygon.closePath();
                        graphics.setColor(Color.darkGray);
//                        graphics.fill(polygon);
                        // ANGLE TRANSFORM
                        Rot rot= new Rot(body.getAngle());
                        
                        AffineTransform transform = new AffineTransform();
                        transform.rotate(body.getAngle(),
                                body.getPosition().x + MasterPilot.WIDTH / 2, body.getPosition().y + MasterPilot.HEIGHT / 2
                        );
                        Shape transformed = transform.createTransformedShape(polygon);
                        graphics.fill(transformed);

                        //SHIELD - CHECK COLLISIONS
                        if (body.m_contactList != null) {
                            graphics.setColor(new Color(1f, 0f, 0f, .5f));
                            graphics.fill(new Ellipse2D.Float(body.getPosition().x + 372, body.getPosition().y + 272, 60, 60));
                        }
                        if (!rockets.isEmpty()) {
                            for (Rocket rocket : rockets) {
                                rocket.display(graphics);
                            }
                        }
                    }

                    @Override
                    public void fire(Graphics2D graphics) {
                        double angle = body.getAngle();
//                        double angle= ;
//                        double angle = Math.toRadians(Math.abs(Math.toDegrees(getAngle()))% 360);
//                        Vec2 vecAdd= new Vec2( (float) (Math.cos(angle) * 100_000_000f), (float) Math.sin(angle) * (100_000_000f)).add(body.getLinearVelocity());
                      
                        rockets.add(
                                new Rocket(
                                        world,
                                        (float) (body.getPosition().x + MasterPilot.WIDTH / 2 + Math.cos(body.getAngle())*30),
                                        (float)(body.getPosition().y + MasterPilot.HEIGHT / 2 +Math.sin(body.getAngle())*30),
                                        //va dans le sens horaire 
                                        //                                                                                vecAdd
                                        new Vec2((float) (Math.cos(angle) * 100_000_000_000f), (float) Math.sin(angle) * (100_000_000_000f))
                                //                                        new Vec2((float) Math.cos( angle) * 1000, (float) Math.sin(angle) * 1000)
                                //                                        new Vec2((float)Math.cos(Math.PI/4)*1000, (float)Math.sin(Math.PI/4)*1000) // va vers bas droite
                                //                                        new Vec2((float)Math.cos(Math.PI/2)*1000, (float)Math.sin(Math.PI/2)*1000) //va vers le bas
                                //                                        new Vec2((float)Math.cos(0)*1000, (float)Math.sin(0)*1000)//va vers la droite
                                //                                        new Vec2((float) Math.cos(Math.PI) * 1000, (float) Math.sin(Math.PI) * 1000) // va vers la gauche
                                //                                        new Vec2(0,-10_000)

                                ));
                        System.out.println("angle " + Math.abs(Math.toDegrees(getAngle())) % 360);
//                        System.out.println("angle radian "+ angle);
                        System.out.println("Rocket X " + Math.cos(getAngle()));
                        System.out.println("Rocket Y " + Math.sin(getAngle()));

                    }

                };

            case ENNEMY1:

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
                Body body2 = world.createBody(bodydef2);
                body2.createFixture(fd2);
                body2.setAngularDamping(3);

                return new SpaceShuttle(x, y, body2) {

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
