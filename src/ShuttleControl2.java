
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
public class ShuttleControl2 {

    public static void move(SpaceShuttle spaceShuttle, KeyboardEvent event,Graphics2D graphics) {
        if (event != null) {
//            System.out.println(event.toString());
//            System.out.println(" x = " + spaceShuttle.getPosition().x + " y = " + spaceShuttle.getPosition().y);
            double angle = spaceShuttle.getAngle(); //Math.abs(Math.toDegrees(spaceShuttle.getAngle())) % 360;
            //0 vers le bas
            //180 vers le haut
            //90 vers la gauche 
            //270 vers la droite
            switch (event.getKey()) {
                case UP:
                    spaceShuttle.applyForce(new Vec2((float) Math.cos(angle) * 10, (float) Math.sin(angle) * 10), spaceShuttle.getPosition());
                    spaceShuttle.applyLinearImpulse(new Vec2((float) Math.cos(angle) * 50, (float) Math.sin(angle) * 50), spaceShuttle.getPosition());
//                    System.out.println("move X " + Math.cos(angle - Math.PI / 2) * 10);
//                    System.out.println("move Y " + Math.sin(angle - Math.PI / 2) * 10);
                    break;
                case DOWN:
                    if (angle > 0 && angle < 180) {
                        spaceShuttle.applyAngularImpulse(-0.2f);
                    } else {
                        spaceShuttle.applyAngularImpulse(0.2f);
                    }
                    spaceShuttle.applyForce(new Vec2(0, 50), spaceShuttle.getPosition());
                    spaceShuttle.applyLinearImpulse(new Vec2(0, 50), spaceShuttle.getPosition());
                    break;
                case LEFT:
                    spaceShuttle.applyAngularImpulse(-0.2f);
                    break;
                case RIGHT:
                    spaceShuttle.applyAngularImpulse(0.2f);
                    break;
                case SPACE:
                    spaceShuttle.fire(graphics);
                    break;
            }
        }
    }

}
