
import org.jbox2d.common.Timer;
import org.jbox2d.common.Vec2;
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
public class Bomb extends Rocket{

    private Timer timer;
    private float trigger=10000;
    
    public Bomb(World world, float x, float y, Vec2 vec) {
        super(world, x, y, vec);
    }
    
    
}
