
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Timer;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

/**
 *  EnnemyShuttle1 is an class extends SpaceShuttle because its a king of SpaceShuttle.
 *  Wich represent one type of ennemy.
 */
public class EnnemyShuttle1 extends SpaceShuttle {
    /**
    *  EnnemyShuttle1 constructor, create a ennemy and its specifications
    *  like density, restition, radius and create its body and its shape
    * 
    * @param x x coordinate of the EnnemyShuttle1.
    * @param y y coordinate of the EnnemyShuttle1.
    * @param world the world where the EnnemyShuttle1 will be.
    */
    public EnnemyShuttle1(float x, float y, World world) {
        super(x, y);

        BodyDef bodydef2 = new BodyDef();
        bodydef2.angle = 0;
        bodydef2.bullet = true;
        FixtureDef fd = new FixtureDef();
        CircleShape s = new CircleShape();
        s.m_radius=10;
// s.setAsBox(100, 20, new Vec2(x,y), 0f);
//                
//                
//        CircleShape s = new CircleShape();
//        s.m_radius = 65f;
        fd.filter.categoryBits = CollisionCategory.ENNEMY.getBits();
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
        resetTimer();
    }
    /**
    *  Display the EnnemyShuttle1 to the Graphics2D
    * 
    * @param graphics Graphics2D where the EnnemyShuttle1 will be draw.
    */  
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
            AffineTransform transform = new AffineTransform();
            transform.rotate(getBody().getAngle(),
                    MasterPilot.toXCoordinates(getBody().getPosition().x), MasterPilot.toYCoordinates(getBody().getPosition().y)
            );

            Shape transformed = transform.createTransformedShape((Shape) polygon);
            graphics.fill(transformed);
        }
    }
    
    
    /**
    *  Behave is a method wich contains the comportment of the EnnemyShuttle1
    * 
    * @param mainShuttlePos postion of the player shuttle
    * @param graphics Graphics2D where the EnnemyShuttle1 is.
    */ 
    @Override
    public void behave(MainShuttle mainShuttle, Graphics2D graphics) {
//         fire(graphics, RocketType.ROCKET, 50,50, CollisionCategory.ENNEMY);
        Vec2 vecDiff = mainShuttle.getPosition().sub(this.getPosition());

        if (isDead() == false) {
            if (getTimer().getMilliseconds() > 200) {
                float posX = MasterPilot.toYCoordinates(getBody().getPosition().x) + 100;
                float posY = MasterPilot.toXCoordinates(getBody().getPosition().y) - 100;
                fire(graphics, RocketType.ROCKET, posX, posY, vecDiff, CollisionCategory.ENNEMY);

                getTimer().reset();
            } else {
//        double angle = (this.getAngle() +Math.PI)%(Math.PI*2);
                double angle = this.getAngle() % (2 * Math.PI) + Math.PI;

                double angleVec = Math.atan2(vecDiff.x, vecDiff.y);//+Math.PI*2;
//                System.out.println("angle " + Math.toDegrees(angle) + " angle  " + Math.toDegrees(angleVec));
                if (angle > angleVec) {
                    this.applyAngularImpulse(0.02f);
                } else {
                    this.applyAngularImpulse(-0.02f);
                }
                if (vecDiff.length() > 200) {
                    this.applyForce(vecDiff, this.getPosition());
                }
                this.applyForce(vecDiff.skew().negate(), this.getPosition());
            }
        }

    }
}
