
import java.awt.Graphics2D;
import org.jbox2d.common.Vec2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author azathoth
 */
public interface Shuttle {

    public void fire(Graphics2D graphics);

    public void fire(Graphics2D graphics, RocketType rocketType, float x, float y);

    public void fireBomb(Graphics2D graphics);

    Vec2 getPosition();
    
    public void behave();

}
