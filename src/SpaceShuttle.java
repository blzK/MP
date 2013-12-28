
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import org.jbox2d.common.Vec2;

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

    private ArrayList<Rocket> rockets = new ArrayList<>();

    public SpaceShuttle(float x, float y) {
        this.x = x;
        this.y = y;
    }

    abstract public void behave(MainShuttle mainShuttle);

    public Vec2 getPosition() {
        return getBody().getPosition();
    }

    double getAngle() {
        return getBody().getAngle();
    }

    void applyAngularImpulse(float f) {
        getBody().applyAngularImpulse(f);
    }

    void applyForce(Vec2 vec2, Vec2 position) {
        getBody().applyForce(vec2, position);
    }

    @Override
    public void applyLinearImpulse(Vec2 impulse, Vec2 point, Graphics2D graphics) {
        graphics.setColor(new Color(1f, 0f, 0f, .5f));
        graphics.fill(new Ellipse2D.Float(getBody().getPosition().x, getBody().getPosition().y + 272, 60, 60));
        super.applyLinearImpulse(impulse, point, graphics); //To change body of generated methods, choose Tools | Templates.

    }

    public void applyTorque(float torque) {
        getBody().applyTorque(torque);
    }

    @Override
    public void display(Graphics2D graphics) {
        if (!rockets.isEmpty()) {
            for (Rocket rocket : rockets) {
                rocket.display(graphics);
            }
        }
    }

    public void fire(Graphics2D graphics, RocketType rocketType, float x, float y) {
        double angle = getBody().getAngle();
        float posX = (float) (getBody().getPosition().x + MasterPilot.WIDTH / 2 + Math.cos(getBody().getAngle()) * 30 * 1.f) + x;
//        if (Math.cos(getBody().getAngle()) == 0) {
//            posX = (float) (getBody().getPosition().x + MasterPilot.WIDTH / 2 + Math.cos(getBody().getAngle()) * 30 * 1.2f) + 10 + x;
//        }
        float posY = (float) (getBody().getPosition().y + MasterPilot.HEIGHT / 2 + Math.sin(getBody().getAngle()) * 30 * 1.f) + y;
//        if (Math.sin(getBody().getAngle())==0) {
//           posY = (float) (getBody().getPosition().y + MasterPilot.HEIGHT / 2 + Math.sin(getBody().getAngle()) * 30 * 1.2f+10) + y;
//        }
        Vec2 vector = new Vec2((float) (Math.cos(angle) * 100_000_000_000f), (float) Math.sin(angle) * (100_000_000_000f));

        switch (rocketType) {
            case ExpBomb:
                rockets.add(
                        new ExpBomb(
                                getBody().getWorld(), posX, posY, vector, CollisionCategory.PLAYER));
                break;
            case ImpBomb:
                rockets.add(
                        new ImpBomb(
                                getBody().getWorld(), posX, posY, vector, CollisionCategory.PLAYER));
                break;
            default:
                rockets.add(
                        new Rocket(
                                getBody().getWorld(), posX, posY, vector, CollisionCategory.PLAYER));
                break;

        }
    }

    

}
