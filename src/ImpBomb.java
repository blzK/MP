
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jbox2d.callbacks.RayCastCallback;
import org.jbox2d.common.Timer;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.World;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author azathoth
 */
public class ImpBomb extends Rocket {

    private Timer timer;
    private float trigger = 750;

    public ImpBomb(World world, float x, float y, Vec2 vec) {
        super(world, x, y, vec);
        timer = new Timer();
    }

    @Override
    public void display(Graphics2D graphics) {
        if (isDead() == false) {
            super.display(graphics); //To change body of generated methods, choose Tools | Templates.
            if (timer.getMilliseconds() > trigger) {
                implode(getBody().getWorld());
                Shape shape = new Ellipse2D.Float(MasterPilot.toXCoordinates(getBody().getPosition().x), MasterPilot.toYCoordinates(getBody().getPosition().y), 70f, 70f);
                graphics.setColor(Color.ORANGE);

                graphics.fill(shape);

                die();
            }
        }
    }

    private void implode(World world) {
        //                              EXPLOSION TEST
        System.out.println(getBody().getWorld().getBodyCount());
        Body bodyTemp = getBody().getWorld().getBodyList().getNext();
        for (int i = 0; i < getBody().getWorld().getBodyCount() - 1; i++) {
//            RayCastCallback rc = new RayCastCallback() {
//                Fixture fixture;
//                Vec2 point;
//                Vec2 normal;
//                float fraction;
//
//                @Override
//                public float reportFixture(Fixture fixture, Vec2 point, Vec2 normal, float fraction) {
//                    System.out.println("I colide with"+fixture.getBody().getUserData());
//                    System.out.println("with fraction " +fraction);
//                    return fraction;
//                }
//            };
//            getBody().getWorld().raycast(rc, bodyTemp.getPosition(),bodyTemp.getPosition());
            bodyTemp.applyLinearImpulse(new Vec2(bodyTemp.getPosition().add(getBody().getPosition().negate())).negate(), bodyTemp.getPosition());
            System.out.println(bodyTemp.getUserData());
            bodyTemp = bodyTemp.getNext();

        }
    }
}
