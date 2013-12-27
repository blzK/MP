
import java.awt.Graphics2D;
import java.util.ArrayList;
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
public abstract class SpaceShuttle extends FlyingObject implements Shuttle {

    private ArrayList<Rocket> rockets = new ArrayList<>();

    public SpaceShuttle(float x, float y) {
        this.x = x;
        this.y = y;

    }

    @Override
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
//                        graphics.setColor(new Color(1f, 0f, 0f, .5f));
//                        graphics.fill(new Ellipse2D.Float(getBody().getPosition().x , getBody().getPosition().y + 272, 60, 60));
        super.applyLinearImpulse(impulse, point, graphics); //To change body of generated methods, choose Tools | Templates.

    }
    

    public void applyTorque(float torque) {
        getBody().applyTorque(torque);
    }

    @Override
    public void display(Graphics2D graphics) {
//////        float x1Points[] = {getBody().getPosition().x + 800 / 2 - 15, getBody().getPosition().x + 800 / 2 + 30, getBody().getPosition().x + 800 / 2 - 15};
//////        float y1Points[] = {getBody().getPosition().y + 600 / 2 + 15, getBody().getPosition().y + 600 / 2, getBody().getPosition().y + 600 / 2 - 15};
////////                        float x1Points[] = {getBody().getPosition().x + 800 / 2 - 25, getBody().getPosition().x + 800 / 2 + 25, getBody().getPosition().x + 800 / 2 - 25};
////////                        float y1Points[] = {getBody().getPosition().y + 600 / 2 + 10, getBody().getPosition().y + 600 / 2, getBody().getPosition().y + 600 / 2 - 10};
//////        GeneralPath polygon = new GeneralPath(GeneralPath.WIND_EVEN_ODD, x1Points.length);
//////        polygon.moveTo(x1Points[0], y1Points[0]);
//////
//////        for (int index = 1; index < x1Points.length; index++) {
//////            polygon.lineTo(x1Points[index], y1Points[index]);
//////        }
//////
//////        polygon.closePath();
//////        graphics.setColor(Color.darkGray);
////////                        graphics.fill(polygon);
//////        // ANGLE TRANSFORM
//////        AffineTransform transform = new AffineTransform();
//////        transform.rotate(getBody().getAngle(),
//////                getBody().getPosition().x + MasterPilot.WIDTH / 2, getBody().getPosition().y + MasterPilot.HEIGHT / 2
//////        );
//////        Shape transformed = transform.createTransformedShape(polygon);
//////        graphics.fill(transformed);

//        //SHIELD - CHECK COLLISIONS
//        if (getBody().m_contactList != null) {
//            graphics.setColor(new Color(1f, 0f, 0f, .5f));
//            graphics.fill(new Ellipse2D.Float(MasterPilot.toXCoordinates(getBody().getPosition().x), MasterPilot.toYCoordinates(getBody().getPosition().y), 60, 60));
////                            graphics.fill(new Ellipse2D.Float(getBody().getPosition().x+400, getBody().getPosition().y+300, 60, 60));
//        }
        if (!rockets.isEmpty()) {
            for (Rocket rocket : rockets) {
                rocket.display(graphics);
            }
        }
    }

    @Override
    public void fire(Graphics2D graphics, RocketType rocketType, float x, float y) {
        double angle = getBody().getAngle();
        float posX = (float) (getBody().getPosition().x + MasterPilot.WIDTH / 2 + Math.cos(getBody().getAngle()) * 30*1.1f)+x;
        float posY = (float) (getBody().getPosition().y + MasterPilot.HEIGHT / 2 + Math.sin(getBody().getAngle()) * 30*1.1f)+y;
        Vec2 vector = new Vec2((float) (Math.cos(angle) * 100_000_000_000f), (float) Math.sin(angle) * (100_000_000_000f));

        
        switch (rocketType) {
            case ExpBomb:
                rockets.add(
                        new ExpBomb(
                                getBody().getWorld(), posX, posY, vector));
                break;
            case ImpBomb:
                rockets.add(
                        new ImpBomb(
                                getBody().getWorld(), posX, posY, vector));
                break;
            default:
                rockets.add(
                        new Rocket(
                                getBody().getWorld(), posX, posY, vector));
                break;

        }
    }

}
