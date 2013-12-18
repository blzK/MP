
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.geom.Ellipse2D;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author bpertev
 */
public class Planet extends FlyingObject {

    public Planet(World world) {

        BodyDef bodydefPlanet = new BodyDef();

        bodydefPlanet.position.set(750, 750);
        bodydefPlanet.type = BodyType.DYNAMIC;
        body = world.createBody(bodydefPlanet);
        FixtureDef fdPlanet = new FixtureDef();
      
        CircleShape cs = new CircleShape();
        cs.m_radius = 350f;

        fdPlanet.shape = cs;
        fdPlanet.density = 1f;
        fdPlanet.restitution = 1f;
        fdPlanet.friction = 1.0f;
        body.createFixture(fdPlanet);
        body.setUserData("Planet");
    }

    @Override
    public void display(Graphics2D graphics) {
        RadialGradientPaint paint = new RadialGradientPaint(body.getPosition().x, body.getPosition().y, 500, new float[]{0f, 1f}, new Color[]{new Color(37, 197, 246), new Color(17, 21, 134)});
        graphics.setPaint(paint);
        graphics.fill(new Ellipse2D.Float(body.getPosition().x, body.getPosition().y, 500, 500));

    }

    @Override
    public boolean isDead() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
