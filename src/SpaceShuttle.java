
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
    public void fire(Graphics2D graphics) {
        if (isDead() == false) {
            double angle = getBody().getAngle();
//                        double angle= ;
//                        double angle = Math.toRadians(Math.abs(Math.toDegrees(getAngle()))% 360);
//                        Vec2 vecAdd= new Vec2( (float) (Math.cos(angle) * 100_000_000f), (float) Math.sin(angle) * (100_000_000f)).add(getBody().getLinearVelocity());
//
            rockets.add(
                    new Rocket(
                            getBody().getWorld(),
                            (float) (getBody().getPosition().x + MasterPilot.WIDTH / 2 + Math.cos(getBody().getAngle()) * 30) + 100,
                            (float) (getBody().getPosition().y + MasterPilot.HEIGHT / 2 + Math.sin(getBody().getAngle()) * 30),
                            //                                        (float) (MasterPilot.toXCoordinates(getBody().getPosition().x)+ MasterPilot.WIDTH / 2 + Math.cos(getBody().getAngle())*30),
                            //                                        (float)(MasterPilot.toYCoordinates(getBody().getPosition().y)+ MasterPilot.HEIGHT / 2 +Math.sin(getBody().getAngle())*30),
                            //                                        //va dans le sens horaire 
                            //                                                                                vecAdd
                            new Vec2((float) (Math.cos(angle) * 100_000_000_000f), (float) Math.sin(angle) * (100_000_000_000f))
                    //                                        new Vec2((float) Math.cos( angle) * 1000, (float) Math.sin(angle) * 1000)
                    //                                        new Vec2((float)Math.cos(Math.PI/4)*1000, (float)Math.sin(Math.PI/4)*1000) // va vers bas droite
                    //                                        new Vec2((float)Math.cos(Math.PI/2)*1000, (float)Math.sin(Math.PI/2)*1000) //va vers le bas
                    //                                        new Vec2((float)Math.cos(0)*1000, (float)Math.sin(0)*1000)//va vers la droite
                    //                                        new Vec2((float) Math.cos(Math.PI) * 1000, (float) Math.sin(Math.PI) * 1000) // va vers la gauche
                    //                                        new Vec2(0,-10_000)

                    ));

////                        EXPLOSION TEST
//        System.out.println(getBody().getWorld().getBodyCount());
//        Body bodyTemp = getBody().getWorld().getBodyList().getNext();
//        for (int i = 0; i < getBody().getWorld().getBodyCount() - 1; i++) {
//
//            if (bodyTemp != null) {
////                            bodyTemp.applyForceToCenter(new Vec2(0f, 50000f));
//                bodyTemp.applyLinearImpulse(new Vec2(bodyTemp.getPosition().add(getBody().getPosition().negate())), bodyTemp.getPosition());
////                            System.out.println(bodyTemp.getUserData());
//            }
//            bodyTemp = bodyTemp.getNext();
//
//        }
//                      ANGLE DEBUG
//            System.out.println("angle " + Math.abs(Math.toDegrees(getAngle())) % 360);
////                        System.out.println("angle radian "+ angle);
//            System.out.println("Rocket X " + Math.cos(getAngle()));
//            System.out.println("Rocket Y " + Math.sin(getAngle()));
        }
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

    @Override
    public void fireBomb(Graphics2D graphics) {
        double angle = getBody().getAngle();
//                        double angle= ;
//                        double angle = Math.toRadians(Math.abs(Math.toDegrees(getAngle()))% 360);
//                        Vec2 vecAdd= new Vec2( (float) (Math.cos(angle) * 100_000_000f), (float) Math.sin(angle) * (100_000_000f)).add(getBody().getLinearVelocity());
//
        rockets.add(
                new ExpBomb(
                        getBody().getWorld(),
                        (float) (getBody().getPosition().x + MasterPilot.WIDTH / 2 + Math.cos(getBody().getAngle()) * 30),
                        (float) (getBody().getPosition().y + MasterPilot.HEIGHT / 2 + Math.sin(getBody().getAngle()) * 30),
                        //                                        (float) (MasterPilot.toXCoordinates(getBody().getPosition().x)+ MasterPilot.WIDTH / 2 + Math.cos(getBody().getAngle())*30),
                        //                                        (float)(MasterPilot.toYCoordinates(getBody().getPosition().y)+ MasterPilot.HEIGHT / 2 +Math.sin(getBody().getAngle())*30),
                        //                                        //va dans le sens horaire 
                        //                                                                                vecAdd
                        new Vec2((float) (Math.cos(angle) * 100_000_000_000f), (float) Math.sin(angle) * (100_000_000_000f))
                //                                        new Vec2((float) Math.cos( angle) * 1000, (float) Math.sin(angle) * 1000)
                //                                        new Vec2((float)Math.cos(Math.PI/4)*1000, (float)Math.sin(Math.PI/4)*1000) // va vers bas droite
                //                                        new Vec2((float)Math.cos(Math.PI/2)*1000, (float)Math.sin(Math.PI/2)*1000) //va vers le bas
                //                                        new Vec2((float)Math.cos(0)*1000, (float)Math.sin(0)*1000)//va vers la droite
                //                                        new Vec2((float) Math.cos(Math.PI) * 1000, (float) Math.sin(Math.PI) * 1000) // va vers la gauche
                //                                        new Vec2(0,-10_000)

                ));

//                  
//                              EXPLOSION TEST
        System.out.println(getBody().getWorld().getBodyCount());
        Body bodyTemp = getBody().getWorld().getBodyList().getNext();
        for (int i = 0; i < getBody().getWorld().getBodyCount() - 1; i++) {

//                            if (bodyTemp != null) {
//                            bodyTemp.applyForceToCenter(new Vec2(0f, 50000f));
//                                getBody().getWorld().raycast(new RayCastCallback() {
//
//                                    @Override
//                                    public float reportFixture(Fixture fixture, Vec2 point, Vec2 normal, float fraction) {
//                                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                                    }
//                                }, null, null);
            bodyTemp.applyLinearImpulse(new Vec2(bodyTemp.getPosition().add(getBody().getPosition().negate())), bodyTemp.getPosition());
            System.out.println(bodyTemp.getUserData());
//                            }
            bodyTemp = bodyTemp.getNext();

        }
//                      ANGLE DEBUG
//        System.out.println("angle " + Math.abs(Math.toDegrees(getAngle())) % 360);
////                        System.out.println("angle radian "+ angle);
//        System.out.println("Rocket X " + Math.cos(getAngle()));
//        System.out.println("Rocket Y " + Math.sin(getAngle()));

    }

    public void fireBomb2(Graphics2D graphics) {
        double angle = getBody().getAngle();
//                        double angle= ;
//                        double angle = Math.toRadians(Math.abs(Math.toDegrees(getAngle()))% 360);
//                        Vec2 vecAdd= new Vec2( (float) (Math.cos(angle) * 100_000_000f), (float) Math.sin(angle) * (100_000_000f)).add(getBody().getLinearVelocity());
//
        rockets.add(
                new ExpBomb(
                        getBody().getWorld(),
                        (float) (getBody().getPosition().x + MasterPilot.WIDTH / 2 + Math.cos(getBody().getAngle()) * 30),
                        (float) (getBody().getPosition().y + MasterPilot.HEIGHT / 2 + Math.sin(getBody().getAngle()) * 30),
                        //                                        (float) (MasterPilot.toXCoordinates(getBody().getPosition().x)+ MasterPilot.WIDTH / 2 + Math.cos(getBody().getAngle())*30),
                        //                                        (float)(MasterPilot.toYCoordinates(getBody().getPosition().y)+ MasterPilot.HEIGHT / 2 +Math.sin(getBody().getAngle())*30),
                        //                                        //va dans le sens horaire 
                        //                                                                                vecAdd
                        new Vec2((float) (Math.cos(angle) * 100_000_000_000f), (float) Math.sin(angle) * (100_000_000_000f))
                //                                        new Vec2((float) Math.cos( angle) * 1000, (float) Math.sin(angle) * 1000)
                //                                        new Vec2((float)Math.cos(Math.PI/4)*1000, (float)Math.sin(Math.PI/4)*1000) // va vers bas droite
                //                                        new Vec2((float)Math.cos(Math.PI/2)*1000, (float)Math.sin(Math.PI/2)*1000) //va vers le bas
                //                                        new Vec2((float)Math.cos(0)*1000, (float)Math.sin(0)*1000)//va vers la droite
                //                                        new Vec2((float) Math.cos(Math.PI) * 1000, (float) Math.sin(Math.PI) * 1000) // va vers la gauche
                //                                        new Vec2(0,-10_000)

                ));

//                  
//                              EXPLOSION TEST
        System.out.println(getBody().getWorld().getBodyCount());
        Body bodyTemp = getBody().getWorld().getBodyList().getNext();
        for (int i = 0; i < getBody().getWorld().getBodyCount() - 1; i++) {

//                            if (bodyTemp != null) {
//                            bodyTemp.applyForceToCenter(new Vec2(0f, 50000f));
//                                getBody().getWorld().raycast(new RayCastCallback() {
//
//                                    @Override
//                                    public float reportFixture(Fixture fixture, Vec2 point, Vec2 normal, float fraction) {
//                                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                                    }
//                                }, null, null);
            bodyTemp.applyLinearImpulse(new Vec2(bodyTemp.getPosition().add(getBody().getPosition().negate())).negate(), bodyTemp.getPosition());
            System.out.println(bodyTemp.getUserData());
//                            }
            bodyTemp = bodyTemp.getNext();

        }
//                      ANGLE DEBUG
//        System.out.println("angle " + Math.abs(Math.toDegrees(getAngle())) % 360);
////                        System.out.println("angle radian "+ angle);
//        System.out.println("Rocket X " + Math.cos(getAngle()));
//        System.out.println("Rocket Y " + Math.sin(getAngle()));

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
        float posX = (float) (getBody().getPosition().x + MasterPilot.WIDTH / 2 + Math.cos(getBody().getAngle()) * 30);
        float posY = (float) (getBody().getPosition().y + MasterPilot.HEIGHT / 2 + Math.sin(getBody().getAngle()) * 30);
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
