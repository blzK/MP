
import fr.umlv.zen3.KeyboardEvent;
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

    public static void move(SpaceShuttle spaceShuttle, KeyboardEvent event) {
        if (event != null) {
            System.out.println(event.toString());
            System.out.println(" x = " + spaceShuttle.getPosition().x + " y = " + spaceShuttle.getPosition().y);
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
                    spaceShuttle.applyLinearImpulse(new Vec2(0, -50), spaceShuttle.getPosition());
                    break;

                case DOWN:
                    if (angle >0&&angle<180) {
                        spaceShuttle.applyAngularImpulse(-0.2f);
                    } else  {
                        spaceShuttle.applyAngularImpulse(0.2f);
                    }
                    spaceShuttle.applyForce(new Vec2(0, 50), spaceShuttle.getPosition());
                    spaceShuttle.applyLinearImpulse(new Vec2(0, 50), spaceShuttle.getPosition());
                    break;
                case LEFT:
                    if (angle > 90 && angle < 180) {
                        spaceShuttle.applyAngularImpulse(-0.2f);
                    } else {
                        spaceShuttle.applyAngularImpulse(0.2f);
                    }

                    spaceShuttle.applyForce(new Vec2(-50, 0), spaceShuttle.getPosition());
                    spaceShuttle.applyLinearImpulse(new Vec2(-50, 0), spaceShuttle.getPosition());
                    break;
                case RIGHT:
                    if (angle > 270) {
                        spaceShuttle.applyAngularImpulse(-0.2f);
                    } else {
                        spaceShuttle.applyAngularImpulse(0.2f);
                    }
                    spaceShuttle.applyForce(new Vec2(50, 0), spaceShuttle.getPosition());
                    spaceShuttle.applyLinearImpulse(new Vec2(50, 0), spaceShuttle.getPosition());
                    break;
                case SPACE:
                    spaceShuttle.fire();
                    break;
            }
        }
    }

}
