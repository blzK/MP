
import java.awt.Shape;
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
 * @author bpertev
 */
public abstract class SpaceShuttle extends FlyingObject {

    public SpaceShuttle(int x, int y, Shape shape, Body body) {
        this.shape = shape;
        this.x = x;
        this.y = y;
        this.body=body;
    }

    
    @Override
    public boolean isDead() {
        return false;
    }

    public void fire() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Vec2 getPosition() {
        return body.getPosition();
    }

    double getAngle() {
        return body.getAngle();
    }

    void applyAngularImpulse(float f) {
         body.applyAngularImpulse(f);
    }

    void applyForce(Vec2 vec2, Vec2 position) {
       body.applyForce(vec2, position);
    }

}
