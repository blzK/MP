
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
public abstract class  FlyingObject implements Sprite{
    protected Body body;
    protected Shape shape;
     float x;
    float y;

public void applyLinearImpulse(Vec2 impulse, Vec2 point, Graphics2D graphics){
    
    body.applyLinearImpulse(impulse, point);
}

    
}
