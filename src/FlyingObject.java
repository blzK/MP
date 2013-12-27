
import java.awt.Graphics2D;
import java.awt.Shape;
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
public abstract class FlyingObject implements Sprite {

    private Body body;
    private boolean isDead = false;
    float x;
    float y;

    public void applyLinearImpulse(Vec2 impulse, Vec2 point, Graphics2D graphics) {

        body.applyLinearImpulse(impulse, point);
    }

    public boolean isDead(){
        return isDead;
    }
    public boolean die() {
        if (isDead==true) {
//            System.out.println("Can't kill me I'm Already Dead");
            isDead=true;
            return false;
        }

        System.out.println("You killed me");
        body.getWorld().destroyBody(body);
        this.isDead = true;
        return true;
    }

    protected void setBody(Body body) {
        if (body == null) {
            throw new NullPointerException();
        }
        this.body = body;
    }

    protected Body getBody() {
        return this.body;
    }

}
