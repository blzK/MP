import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
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
 * @author azathoth
 */
public class Rocket extends FlyingObject {

    public Rocket(World world, float x, float y, Vec2 direction, CollisionCategory col) {
        this.x = x;
        this.y = y;
        BodyDef bodydefRocket = new BodyDef();
//        bodydefRocket.position.set(x - 372 - 55, y - 270 - 30);
        bodydefRocket.position.set(x - 372 - 55, y - 270 - 30);
        bodydefRocket.type = BodyType.DYNAMIC;
        setBody(world.createBody(bodydefRocket));
        FixtureDef fdRocket = new FixtureDef();
        CircleShape cs = new CircleShape();
        cs.m_radius = 0.65f;
        fdRocket.filter.categoryBits = col.getBits();
//        if (col == CollisionCategory.PLAYER) {
//            fdRocket.filter.maskBits = CollisionCategory.WORLD.getBits()|CollisionCategory.ENNEMY.getBits();
//        } else if(col==CollisionCategory.ENNEMY){
//            fdRocket.filter.maskBits = CollisionCategory.WORLD.getBits()|CollisionCategory.PLAYER.getBits();
//        }
        fdRocket.shape = cs;
        fdRocket.density = 0.0001f;
        fdRocket.restitution = 1f;
        fdRocket.friction = .0f;
        getBody().setBullet(true);
        getBody().createFixture(fdRocket);
        getBody().setUserData("Rocket");
        getBody().setLinearVelocity(direction);
    }

    @Override
    public void display(Graphics2D graphics) {
        if (getBody().getContactList() != null) {
            die();
        }
        if (isDead() == false) {
            Shape shape = new Ellipse2D.Float(MasterPilot.toXCoordinates(getBody().getPosition().x) + 55, MasterPilot.toYCoordinates(getBody().getPosition().y) + 30, 5, 2);
            graphics.setPaint(Color.lightGray);
            graphics.fill(shape);
        }
    }

}
