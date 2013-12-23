
import java.awt.Graphics2D;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

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

    public SpaceShuttle(float x, float y, Body body) {
        
        this.x = x;
        this.y = y;
        this.body=body;
    }

    
    @Override
    public boolean isDead() {
        return dead;
    }

    public void fire(Graphics2D graphics) {
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

    void applyLinearImpulse(Vec2 vec2, Vec2 position) {
        
    }

    public void fireBomb(Graphics2D graphics) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
