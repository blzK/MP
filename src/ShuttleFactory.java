
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
public class ShuttleFactory {

    public SpaceShuttle createShuttle(float x, float y, ShuttleType type, World world) {

        switch (type) {
            case MAINSHUTTLE:

                return new  MainShuttle(x, y, world);

            case ENNEMY1:

                return new EnnemyShuttle1(x, y, world);

        }
        throw new IllegalArgumentException("ShuttleType Unknown");

    }

}
