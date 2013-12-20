
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
    
        public static void move(SpaceShuttle spaceShuttle, KeyboardEvent event){
                            if (event != null) {
                        System.out.println(event.toString());
                        System.out.println(" x = " + spaceShuttle.getPosition().x + " y = " + spaceShuttle.getPosition().y);
                        switch (event.getKey()) {
                            case UP:
//                                if (Math.toDegrees(spaceShuttle.getAngle()) < 180) {
                                    spaceShuttle.applyAngularImpulse((float) 0.2);
//                                }
                                spaceShuttle.applyForce(new Vec2(0, -50), spaceShuttle.getPosition());
                                spaceShuttle.applyLinearImpulse(new Vec2(0, -50), spaceShuttle.getPosition());
                                break;

                            case DOWN:
//                                if (Math.toDegrees(spaceShuttle.getAngle()) < 0) {
                                    spaceShuttle.applyAngularImpulse((float) 0.2);
//                                }
                                spaceShuttle.applyForce(new Vec2(0, 50), spaceShuttle.getPosition());
                                spaceShuttle.applyLinearImpulse(new Vec2(0, 50), spaceShuttle.getPosition());
                                break;
                            case LEFT:
//                                if (Math.toDegrees(spaceShuttle.getAngle()) < 85 && Math.toDegrees(spaceShuttle.getAngle()) % 360 > 95) {
                                    spaceShuttle.applyAngularImpulse((float) 0.2);
//                                }

                                spaceShuttle.applyForce(new Vec2(-50, 0), spaceShuttle.getPosition());
                                spaceShuttle.applyLinearImpulse(new Vec2(-50, 0), spaceShuttle.getPosition());
                                break;
                            case RIGHT:
//                                if (Math.toDegrees(spaceShuttle.getAngle()) < -95 && Math.toDegrees(spaceShuttle.getAngle()) % 360 > -85) {
                                    spaceShuttle.applyAngularImpulse((float) -0.2);
//                                }
                                spaceShuttle.applyForce(new Vec2(50, 0), spaceShuttle.getPosition());
                                spaceShuttle.applyLinearImpulse(new Vec2(50, 0), spaceShuttle.getPosition());
                                break;
                            case R:

//                                body.
                                break;
                        }
                    }
    }
    
}
