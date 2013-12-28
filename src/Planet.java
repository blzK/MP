
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import org.jbox2d.collision.shapes.CircleShape;
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

    static int WIDTH = 800;
    static int HEIGHT = 600;

    public Planet(World world, int x, int y) {
        this.x = x;
        this.y = y;
        BodyDef bodydefPlanet = new BodyDef();
        bodydefPlanet.position.set(x, y);
        bodydefPlanet.type = BodyType.STATIC;
        setBody(world.createBody(bodydefPlanet));
        FixtureDef fdPlanet = new FixtureDef();
        CircleShape cs = new CircleShape();
        fdPlanet.filter.categoryBits = CollisionCategory.WORLD.getBits();
        fdPlanet.filter.maskBits = CollisionCategory.PLAYER.getBits() | CollisionCategory.WORLD.getBits();
        cs.m_radius = 65f;
        fdPlanet.shape = cs;
        fdPlanet.density = 1f;
        fdPlanet.restitution = 1f;
        fdPlanet.friction = 1.0f;
        getBody().createFixture(fdPlanet);
        getBody().setUserData("Planet");
    }

    @Override
    public void display(Graphics2D graphics) {
        Point2D center = new Point2D.Float(x, y);
        RadialGradientPaint paint = new RadialGradientPaint(center, 1000,
                new float[]{
                    0.4f,
                    1f},
                new Color[]{
                    Color.black,
                    new Color(37, 197, 246)
                });
        graphics.setPaint(paint);

//        System.out.println("planetX "+getBody().getPosition().x+"planetY"+getBody().getPosition().y);
//  int x =(int) (getBody().getPosition().x*100.0f*1.0f)/800;
//  int y=  (int) (600 - (1.0f*600) * getBody().getPosition().y / 100.0f);
//        float  x = (800*getBody().getPosition().x / 100.0f);
//        float  y =  (600 - (1.0f*600) * getBody().getPosition().y / 100.0f);
//        shape=new Ellipse2D.Float(getBody().getPosition().x+335,getBody().getPosition().y+260, 100f,100f);
        Shape shape = new Ellipse2D.Float(MasterPilot.toXCoordinates(getBody().getPosition().x), MasterPilot.toYCoordinates(getBody().getPosition().y), 70f, 70f);
//        Shape bodyShape=new Ellipse2D.Float(getBody().getPosition().x,getBody().getPosition().y, 100f,100f);

//        Ellipse2D s;
//    s = new Ellipse2D.Float(toPosX(getBody().getPosition().x), toPosY(getBody().getPosition().y),100f,100f);
//        s = new Ellipse2D.Float(toPixelPosX(getBody().getPosition().x), toPixelPosY(getBody().getPosition().y),100f,100f);
//        s = new Ellipse2D.Float(toPixelWidth(getBody().getPosition().x), toPixelHeight(getBody().getPosition().y),100f,100f);
////////////        System.out.println(        s.getCenterX());
////////////        System.out.println(        s.getCenterY());
////////////        
//        System.out.println(s.getMinX());
//        System.out.println(s.getMinY());
        graphics.fill(shape);
//        graphics.setColor(new Color(1f, 1f, 0f,0.5f));
//        graphics.fill(bodyShape);

    }

}
