
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
public class EnnemyShuttle1 extends SpaceShuttle {

    public EnnemyShuttle1(float x, float y, World world) {
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
        getBody().setUserData("ennemy1");
        getBody().createFixture(fd);
        getBody().setAngularDamping(3);
//                body2.setLinearDamping(0.3f);
        getBody().setLinearVelocity(new Vec2(0, -3f));

    }

    @Override
    public void display(Graphics2D graphics) {
        super.display(graphics);
//        System.out.println("I'm dead " + isDead());
        if (getBody().getContactList() != null) {
            die();
        }
        if (isDead() == false) {
            float posX = MasterPilot.toXCoordinates(getBody().getPosition().x);
            float posY = MasterPilot.toYCoordinates(getBody().getPosition().y);
            float x1Points[] = {
                posX - 15, posX - 5, posX - 13, posX - 13, posX - 10, posX - 10, posX - 5, posX + 5, posX + 10, posX + 10, posX + 13, posX + 13, posX + 5, posX + 15,
                posX + 15, posX + 5, posX + 13, posX + 13, posX + 10, posX + 10, posX + 5, posX - 5, posX - 10, posX - 10, posX - 13, posX - 13, posX - 5, posX - 15
            };
            float y1Points[] = {
                posY + 10, posY + 15, posY + 10, posY + 1, posY + 1, posY + 5, posY + 10, posY + 10, posY + 5, posY + 1, posY + 1, posY + 10, posY + 15, posY + 10,
                posY - 10, posY - 15, posY - 10, posY - 1, posY - 1, posY - 5, posY - 10, posY - 10, posY - 5, posY - 1, posY - 1, posY - 10, posY - 15, posY - 10
            };
            GeneralPath polygon = new GeneralPath(GeneralPath.WIND_EVEN_ODD, x1Points.length);
            polygon.moveTo(x1Points[0], y1Points[0]);

            for (int index = 1; index < x1Points.length; index++) {
                polygon.lineTo(x1Points[index], y1Points[index]);
            }

            polygon.closePath();
            graphics.setPaint(Color.RED);
//            graphics.fill(polygon);

//            graphics.fill(new Rectangle2D.Float(getBody().getPosition().x + 335, getBody().getPosition().y + 260, 100, 20));
            AffineTransform transform = new AffineTransform();
            transform.rotate(getBody().getAngle(),
                    getBody().getPosition().x, getBody().getPosition().y
            );
            Shape transformed = transform.createTransformedShape(polygon);
//         Shape transformed = transform.createTransformedShape(new Rectangle2D.Float(getBody().getPosition().x, getBody().getPosition().y, 100, 20));
            graphics.fill(transformed);
        }
    }

    @Override
    public void behave(MainShuttle mainShuttle) {
//        double angle = this.getAngle();
//        if(mainShuttle.getPosition().sub(this.getPosition()).x){
//        this.applyTorque(x);
//        }
        if (mainShuttle.getPosition().sub(this.getPosition()).length() > 200) {
            this.applyForce(mainShuttle.getPosition().sub(this.getPosition()), this.getPosition());
        }
        this.applyForce(mainShuttle.getPosition().sub(this.getPosition()).skew(), this.getPosition());
    }

}
