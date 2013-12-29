
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
public class EnnemyShuttle3 extends SpaceShuttle {

    SpaceShuttle[] ennemyGroup = new SpaceShuttle[8];

    public EnnemyShuttle3(float x, float y, World world) {
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
        getBody().setUserData("ennemy3");
        getBody().createFixture(fd);
        getBody().setAngularDamping(3);
//                body2.setLinearDamping(0.3f);
        for (int i = 0; i < ennemyGroup.length; i++) {

            ennemyGroup[0] = new EnnemysubShuttle2(x - 10, y - 10, world);
            ennemyGroup[1] = new EnnemysubShuttle1(x, y + 30, world);
            ennemyGroup[2] = new EnnemysubShuttle1(x, y - 30, world);
            ennemyGroup[3] = new EnnemysubShuttle1(x + 30, y, world);
            ennemyGroup[4] = new EnnemysubShuttle1(x + 30, y + 30, world);
            ennemyGroup[5] = new EnnemysubShuttle1(x + 30, y - 30, world);
            ennemyGroup[6] = new EnnemysubShuttle1(x + 60, y, world);
            ennemyGroup[7] = new EnnemysubShuttle1(x + 80, y, world);
        }
    }

    @Override
    public void display(Graphics2D graphics) {
        for (SpaceShuttle ennemy : ennemyGroup) {
            ennemy.display(graphics);
        }
    }

    @Override
    public void applyLinearImpulse(Vec2 impulse, Vec2 point, Graphics2D graphics) {
        for (SpaceShuttle ennemy : ennemyGroup) {
            ennemy.applyLinearImpulse(impulse, point, graphics); //To change body of generated methods, choose Tools | Templates.
        }

    }

    @Override
    public void fire(Graphics2D graphics, RocketType rocketType, float x, float y, Vec2 direction, CollisionCategory col) {
         for (int i = 0; i < ennemyGroup.length; i++) {
            ennemyGroup[i].fire(graphics, rocketType, x, y, direction, col); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public void behave(MainShuttle mainShuttle, Graphics2D graphics) {

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
