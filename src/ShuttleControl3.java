
import fr.umlv.zen3.KeyboardEvent;
import java.awt.Graphics2D;
import org.jbox2d.common.Vec2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author azathoth
 */
public class ShuttleControl3 {

    public static void move(SpaceShuttle spaceShuttle, KeyboardEvent event, Graphics2D graphics) {
        if (event != null) {
            double angle = spaceShuttle.getAngle(); 
            switch (event.getKey()) {
                case UP:
                    spaceShuttle.applyLinearImpulse(new Vec2((float) Math.cos(angle) * 500, (float) Math.sin(angle) * 500), spaceShuttle.getPosition(),graphics);
                    break;
                case DOWN:
                    spaceShuttle.applyForce(new Vec2((float) Math.cos(angle + Math.PI) * 1000, (float) Math.sin(angle + Math.PI) * 1000), spaceShuttle.getPosition());
                    break;
                case LEFT:
                    spaceShuttle.applyTorque(-400f);
                    break;
                case RIGHT:
                     spaceShuttle.applyTorque(400f);
                    break;
                case SPACE:
                    spaceShuttle.fire(graphics, RocketType.ROCKET,0,0);
                    break;
                case B:
                    spaceShuttle.fire(graphics, RocketType.ExpBomb,0,0);
                    break;
                case N:
                    spaceShuttle.fire(graphics, RocketType.ImpBomb,0,0);
                    break;
            }
        }
    }

}