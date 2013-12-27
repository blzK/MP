
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

    public static void move(SpaceShuttle spaceShuttle, KeyboardEvent event, Graphics2D graphics) {
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
//                    spaceShuttle.applyLinearImpulse(new Vec2((float) Math.cos(angle) * 1000, (float) Math.sin(angle) * 1000), spaceShuttle.getPosition(), graphics);
                    spaceShuttle.applyForce(new Vec2((float) Math.cos(angle) * 1000, (float) Math.sin(angle) * 1000), spaceShuttle.getPosition());

//                    System.out.println("move X " + Math.cos(angle - Math.PI / 2) * 10);
//                    System.out.println("move Y " + Math.sin(angle - Math.PI / 2) * 10);
                    break;
                case DOWN:
                    spaceShuttle.applyLinearImpulse(new Vec2((float) Math.cos(angle + Math.PI) * 1000, (float) Math.sin(angle + Math.PI) * 1000), spaceShuttle.getPosition(), graphics);
                    break;
                case LEFT:
//                    spaceShuttle.applyForce(new Vec2((float) Math.cos(angle - Math.PI / 2) * 50, (float) Math.sin(angle - Math.PI / 2) * 50), spaceShuttle.getPosition());
                    spaceShuttle.applyLinearImpulse(new Vec2((float) Math.cos(angle - Math.PI / 2) * 50, (float) Math.sin(angle - Math.PI / 2) * 50), spaceShuttle.getPosition(), graphics);
                    spaceShuttle.applyAngularImpulse(-1.5f);
                    break;
                case RIGHT:
//                    spaceShuttle.applyForce(new Vec2((float) Math.cos(angle + Math.PI / 2) * 50, (float) Math.sin(angle + Math.PI / 2) * 50), spaceShuttle.getPosition());
                    spaceShuttle.applyLinearImpulse(new Vec2((float) Math.cos(angle + Math.PI / 2) * 50, (float) Math.sin(angle + Math.PI / 2) * 50), spaceShuttle.getPosition(), graphics);
                    spaceShuttle.applyAngularImpulse(1.5f);
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
