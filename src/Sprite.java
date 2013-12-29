import java.awt.Graphics2D;

/**
* Sprite is a interface of all the objects wich can be drawn
* 
*/
public interface Sprite {

    /**
    *  Display the Sprite on Graphics2D
    * 
    * @param graphics Graphics2D where the sprite will be draw.
    */
    public void display(Graphics2D graphics);

    default public void display(Graphics2D graphics, MainShuttle mainShuttle) {
        display(graphics);
    }
;
}
