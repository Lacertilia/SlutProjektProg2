/**
 * @author Rasmus
 */

class Object {
    private int x;
    private int y;

    /**
     * Creator for object.
     */
    Object(){

    }

    void place(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * Getter for x variable.
     * @return x value
     */
    int getX(){
        return this.x;
    }

    /**
     * Getter for y variable.
     * @return y value
     */
    int getY(){
        return this.y;
    }

}
