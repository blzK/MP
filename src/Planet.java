
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
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
static int WIDTH=800;
static int HEIGHT=600;

    public Planet(World world, int x, int y) {
        BodyDef bodydefPlanet = new BodyDef();
        bodydefPlanet.position.set(x,y);
        bodydefPlanet.type = BodyType.STATIC;
        body = world.createBody(bodydefPlanet);
        FixtureDef fdPlanet = new FixtureDef();
        CircleShape cs = new CircleShape();
        cs.m_radius = 70f;
        fdPlanet.shape = cs;
        fdPlanet.density = 1f;
        fdPlanet.restitution = 1f;
        fdPlanet.friction = 1.0f;
        body.createFixture(fdPlanet);
        body.setUserData("Planet");
        
    }

    @Override
    public void display(Graphics2D graphics) {
        RadialGradientPaint paint = new RadialGradientPaint(body.getPosition().x, body.getPosition().y, 100f, new float[]{0f, 1f}, new Color[]{new Color(37, 197, 246), new Color(17, 21, 134)});
        graphics.setPaint(paint);
       
//        System.out.println("planetX "+body.getPosition().x+"planetY"+body.getPosition().y);
//  int x =(int) (body.getPosition().x*100.0f*1.0f)/800;
//  int y=  (int) (600 - (1.0f*600) * body.getPosition().y / 100.0f);
//        float  x = (800*body.getPosition().x / 100.0f);
//        float  y =  (600 - (1.0f*600) * body.getPosition().y / 100.0f);
        shape=new Ellipse2D.Float(body.getPosition().x+335,body.getPosition().y+260, 100f,100f);
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
