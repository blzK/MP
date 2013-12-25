
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
 * @author azathoth
 */
public class MainShuttle extends SpaceShuttle {

    public MainShuttle(float x, float y, World world) {
        super(x, y);
        BodyDef bodydef = new BodyDef();
        bodydef.angle = 0;
        bodydef.bullet = true;

        FixtureDef fd = new FixtureDef();
        CircleShape cs = new CircleShape();
        cs.m_radius = 1f;

        fd.shape = cs;
        fd.density = 1f;
        fd.restitution = 1f;

        fd.friction = 1f;

        bodydef.position.set(x, y);

        bodydef.type = BodyType.DYNAMIC;

        this.body = world.createBody(bodydef);
        body.setUserData("spaceShuttle");
        body.createFixture(fd);
        body.setAngularDamping(1);
        body.setLinearDamping((float) 0.1);
    }

}
