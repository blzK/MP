
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
        bodydefPlanet.type = BodyType.DYNAMIC;
        body = world.createBody(bodydefPlanet);
        FixtureDef fdPlanet = new FixtureDef();
        CircleShape cs = new CircleShape();
        cs.m_radius = 65f;
        fdPlanet.shape = cs;
        fdPlanet.density = 1f;
        fdPlanet.restitution = 1f;
        fdPlanet.friction = 1.0f;
        body.createFixture(fdPlanet);
        body.setUserData("Planet");
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

//        System.out.println("planetX "+body.getPosition().x+"planetY"+body.getPosition().y);
//  int x =(int) (body.getPosition().x*100.0f*1.0f)/800;
//  int y=  (int) (600 - (1.0f*600) * body.getPosition().y / 100.0f);
//        float  x = (800*body.getPosition().x / 100.0f);
//        float  y =  (600 - (1.0f*600) * body.getPosition().y / 100.0f);
//        shape=new Ellipse2D.Float(body.getPosition().x+335,body.getPosition().y+260, 100f,100f);
        shape = new Ellipse2D.Float(MasterPilot.toXCoordinates(body.getPosition().x), MasterPilot.toYCoordinates(body.getPosition().y), 70f, 70f);
//        Shape bodyShape=new Ellipse2D.Float(body.getPosition().x,body.getPosition().y, 100f,100f);

//        Ellipse2D s;
//    s = new Ellipse2D.Float(toPosX(body.getPosition().x), toPosY(body.getPosition().y),100f,100f);
//        s = new Ellipse2D.Float(toPixelPosX(body.getPosition().x), toPixelPosY(body.getPosition().y),100f,100f);
//        s = new Ellipse2D.Float(toPixelWidth(body.getPosition().x), toPixelHeight(body.getPosition().y),100f,100f);
////////////        System.out.println(        s.getCenterX());
////////////        System.out.println(        s.getCenterY());
////////////        
//        System.out.println(s.getMinX());
//        System.out.println(s.getMinY());
        graphics.fill(shape);
//        graphics.setColor(new Color(1f, 1f, 0f,0.5f));
//        graphics.fill(bodyShape);

    }

    @Override
    public boolean isDead() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
//////////
//////////    //Convert a JBox2D x coordinate to a JavaFX pixel x coordinate
//////////public static float toPixelPosX(float posX) {
//////////    float x = WIDTH*posX / 100.0f;
//////////    return x;
//////////}
////////// 
////////////Convert a JavaFX pixel x coordinate to a JBox2D x coordinate
//////////public static float toPosX(float posX) {
//////////    float x =   (posX*100.0f*1.0f)/WIDTH;
//////////    return x;
//////////}
////////// 
////////////Convert a JBox2D y coordinate to a JavaFX pixel y coordinate
//////////public static float toPixelPosY(float posY) {
//////////    float y = HEIGHT - (1.0f*HEIGHT) * posY / 100.0f;
//////////    return y;
//////////}
////////// 
////////////Convert a JavaFX pixel y coordinate to a JBox2D y coordinate
//////////public static float toPosY(float posY) {
//////////    float y = 100.0f - ((posY * 100*1.0f) /HEIGHT) ;
//////////    return y;
//////////}
////////// 
////////////Convert a JBox2D width to pixel width
//////////public static float toPixelWidth(float width) {
//////////    return WIDTH*width / 100.0f;
//////////}
////////// 
////////////Convert a JBox2D height to pixel height
//////////public static float toPixelHeight(float height) {
//////////    return HEIGHT*height/100.0f;
//////////}
}
