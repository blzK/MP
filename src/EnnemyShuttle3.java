
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
        float posX = x;//MasterPilot.toXCoordinates(x);
        float posY = y;//MasterPilot.toYCoordinates(y);

        for (int i = 0; i < ennemyGroup.length; i++) {

            ennemyGroup[0] = new EnnemysubShuttle2(posX - 10, posY - 10, world);
            ennemyGroup[1] = new EnnemysubShuttle1(posX, posY + 30, world);
            ennemyGroup[2] = new EnnemysubShuttle1(posX, posY - 30, world);
            ennemyGroup[3] = new EnnemysubShuttle1(posX + 30, posY, world);
            ennemyGroup[4] = new EnnemysubShuttle1(posX + 30, posY + 30, world);
            ennemyGroup[5] = new EnnemysubShuttle1(posX + 30, posY - 30, world);
            ennemyGroup[6] = new EnnemysubShuttle1(posX + 60, posY, world);
            ennemyGroup[7] = new EnnemysubShuttle1(posX + 80, posY, world);
        }
    }

    @Override
    public void display(Graphics2D graphics) {
        if (ennemyGroup[0].isDead() == true) {
            die();
        }
        for (int i = 0; i < ennemyGroup.length; i++) {
            ennemyGroup[i].display(graphics);
        }
    }

    @Override
    public boolean die() {
        if (isDead() == false) {
            for (SpaceShuttle ennemyGroup1 : ennemyGroup) {
                ennemyGroup1.die();
            }
        }
        return super.die();
    }

    @Override
    public void applyLinearImpulse(Vec2 impulse, Vec2 point, Graphics2D graphics) {
        for (int i = 0; i < ennemyGroup.length; i++) {
            ennemyGroup[i].applyLinearImpulse(impulse, point, graphics); //To change body of generated methods, choose Tools | Templates.
        }

    }

    @Override
    void applyAngularImpulse(float f) {
        for (int i = 0; i < ennemyGroup.length; i++) {
            ennemyGroup[i].applyAngularImpulse(f);
        }

    }

    @Override
    void applyForce(Vec2 vec2, Vec2 position) {
        for (int i = 0; i < ennemyGroup.length; i++) {
            ennemyGroup[i].applyForce(vec2, position);
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
        if (isDead() == false) {
            Vec2 vecDiff = mainShuttle.getPosition().sub(ennemyGroup[0].getPosition());

            fire(graphics, RocketType.ROCKET, x, y, mainShuttle.getPosition(), CollisionCategory.WORLD);
            for (int i = 0; i < ennemyGroup.length; i++) {
                ennemyGroup[i].behave(mainShuttle, graphics);
                if (i >= 1) {
                    Vec2 vecDiffToMothership = ennemyGroup[0].getPosition().sub(ennemyGroup[i].getPosition());
                    float r = (float) Math.random() * 0.001f;
                    if (vecDiffToMothership.length() > 400) {
                        ennemyGroup[i].applyForce(vecDiffToMothership.mul(r).negate(), ennemyGroup[i].getPosition());
                        ennemyGroup[i].applyForce(vecDiffToMothership.mul(r).skew(), ennemyGroup[i].getPosition());
                    } else if (vecDiffToMothership.length() < 300) {
                        ennemyGroup[i].applyForce(vecDiffToMothership.mul(r), ennemyGroup[i].getPosition());
                        ennemyGroup[i].applyForce(vecDiffToMothership.mul(r).skew().negate(), ennemyGroup[i].getPosition());
                    }
                    ennemyGroup[i].applyForce(vecDiffToMothership.add(vecDiff).mul(r), ennemyGroup[i].getPosition());

                }
            }

        }
//        if (isDead() == false) {
//            if (getTimer().getMilliseconds() > 200) {
//                float posX = MasterPilot.toYCoordinates(getBody().getPosition().x) + 100;
//                float posY = MasterPilot.toXCoordinates(getBody().getPosition().y) - 100;
//                fire(graphics, RocketType.ROCKET, posX, posY, vecDiff, CollisionCategory.ENNEMY);
//
//                getTimer().reset();
//            } else {
////        double angle = (this.getAngle() +Math.PI)%(Math.PI*2);
//                double angle = this.getAngle() % (2 * Math.PI) + Math.PI;
//
//                double angleVec = Math.atan2(vecDiff.x, vecDiff.y);//+Math.PI*2;
////                System.out.println("angle " + Math.toDegrees(angle) + " angle  " + Math.toDegrees(angleVec));
//                if (angle > angleVec) {
//                    this.applyAngularImpulse(0.02f);
//                } else {
//                    this.applyAngularImpulse(-0.02f);
//                }
//                if (vecDiff.length() > 200) {
//                    this.applyForce(vecDiff, this.getPosition());
//                }
//                this.applyForce(vecDiff.skew().negate(), this.getPosition());
//            }
//        }

    }

}
