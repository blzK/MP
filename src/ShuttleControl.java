
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
public class ShuttleControl {

    public static void move(SpaceShuttle spaceShuttle, KeyboardEvent event, Graphics2D graphics) {
        if (event != null) {
//            System.out.println(event.toString());
//            System.out.println(" x = " + spaceShuttle.getPosition().x + " y = " + spaceShuttle.getPosition().y);
            double angle = Math.abs(Math.toDegrees(spaceShuttle.getAngle())) % 360;
                        //0 vers le bas
            //180 vers le haut
            //90 vers la gauche 
            //270 vers la droite
            switch (event.getKey()) {
                case UP:
                    if (angle < 180) {
                        spaceShuttle.applyAngularImpulse(0.2f);
                    } else {
                        spaceShuttle.applyAngularImpulse(-0.2f);
                    }
                    spaceShuttle.applyForce(new Vec2(0, -50), spaceShuttle.getPosition());
                    spaceShuttle.applyLinearImpulse(new Vec2(0, -50), spaceShuttle.getPosition(), graphics);
                    break;

                case DOWN:
                    if (angle >0&&angle<180) {
                        spaceShuttle.applyAngularImpulse(-0.2f);
                    } else  {
                        spaceShuttle.applyAngularImpulse(0.2f);
                    }
                    spaceShuttle.applyForce(new Vec2(0, 50), spaceShuttle.getPosition());
                    spaceShuttle.applyLinearImpulse(new Vec2(0, 50), spaceShuttle.getPosition(),graphics);
                    break;
                case LEFT:
                    if (angle > 90 && angle < 270) {
                        spaceShuttle.applyAngularImpulse(-0.2f);
                    } else {
                        spaceShuttle.applyAngularImpulse(0.2f);
                    }

                    spaceShuttle.applyForce(new Vec2(-50, 0), spaceShuttle.getPosition());
                    spaceShuttle.applyLinearImpulse(new Vec2(-50, 0), spaceShuttle.getPosition(),graphics);
                    break;
                case RIGHT:
                    if (angle > 270||angle<90) {
                        spaceShuttle.applyAngularImpulse(-0.2f);
                    } else {
                        spaceShuttle.applyAngularImpulse(0.2f);
                    }
                    spaceShuttle.applyForce(new Vec2(50, 0), spaceShuttle.getPosition());
                    spaceShuttle.applyLinearImpulse(new Vec2(50, 0), spaceShuttle.getPosition(),graphics);
                    break;
                  case SPACE:
                    spaceShuttle.fire(graphics, RocketType.ROCKET,-100,-100);

                    break;
                case B:
                    spaceShuttle.fire(graphics, RocketType.ExpBomb,-100,-100);
                    break;
                case N:
                    spaceShuttle.fire(graphics, RocketType.ImpBomb,-100,-100);
                    break;
            }
        }
    }

}
