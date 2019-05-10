import java.awt.*;

/**
 * @author Rasmus
 */

public class Object {
    private int x;
    private int y;

    /**
     * Creator for object.
     */
    public Object(){

    }

    public void place(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * Getter for x variable.
     * @return x value
     */
    public int getX(){
        return this.x;
    }

    /**
     * Getter for y variable.
     * @return y value
     */
    public int getY(){
        return this.y;
    }

}
