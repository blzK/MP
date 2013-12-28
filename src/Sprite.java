
import java.awt.Graphics2D;

/**
 *
 * @author azathoth
 */
public interface Sprite {

    public void display(Graphics2D graphics);

    default public void display(Graphics2D graphics, MainShuttle mainShuttle) {
        display(graphics);
    }
;
}
