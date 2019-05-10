import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 * @author Rasmus
 */

public class Main extends Canvas {
    JFrame frame;//Frame that is shown on screen.
    int multiple = 10;//Multiple for use of a bigger screen than 240px*170px(makes everything bigger.)
    int width = 240*multiple;//Width of an original Game Boy (240px)
    int height = 170*multiple;//Height of an original Game Boy (170px)
    private int blockSize = 13;//Size of a block
    static int x = 0;//X position for player
    static int y = 0;//Y position for player
    int size = blockSize*multiple;//Size of player
    int move = size;//Moving distance
    int ups = 30;//How many UPS(Updates per second) the game runs
    Image dbImage;//Image for double buffered graphics
    static Graphics dbg;//Graphics for double buffered graphics
    static boolean Started = false;//Boolean to check if the game is running
    private boolean movingUp = false;//Boolean to check if player is moving Up
    private boolean movingLeft = false;//Boolean to check if the player is moving Left
    private boolean movingDown = false;//Boolean to check if the player is moving Down
    private boolean movingRight = false;//Boolean to check if the player is moving Right
    private String direction = "Down";//Direction string for drawing the character in different directions depending on where you last moved.
    private long moveStop = 1000000000/3;//Determines how long you need to wait between moving
    private long lastMove = System.nanoTime();//Checks when you last moved
    private boolean pickedUp = true;//Checks if you have picked up an object

    Object o;//Object that the player will pick up.

    /**
     * Creator for the game.
     * */
    public Main(){

        Scanner scan = new Scanner(System.in);

        System.out.println("Username?");
        //String username = scan.next();

        //checkUser(username);

        frame = new JFrame("Game");
        frame.setSize(new Dimension(width, height));
        frame.add(this);
        frame.setResizable(false);
        addKeyListener(new KL());
        Started = true;
        o = new Object();

        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);

        long lastUpdate = System.nanoTime();

        long dt = 1000000000/ups;
        while(Started){
            if(System.nanoTime() - lastUpdate > dt){
                lastUpdate = System.nanoTime();
                draw();
            }
            checkMovement();
            if(pickedUp){
                placeObject();
            }
        }
    }

    /**
     * Checks if user exists in Database.
     * @param username The username used to search database
     */
    private void checkUser(String username) {

    }

    /**
     * Checks if the player is moving.
     */

    private void checkMovement(){
        if(movingUp && System.nanoTime()-lastMove >= moveStop && !collide()){
            y -= move;
            lastMove = System.nanoTime();
        }
        if(movingLeft && System.nanoTime()-lastMove >= moveStop && !collide()){
            x -= move;
            lastMove = System.nanoTime();
        }
        if(movingDown && System.nanoTime()-lastMove >= moveStop && !collide()){
            y += move;
            lastMove = System.nanoTime();
        }
        if(movingRight && System.nanoTime()-lastMove >= moveStop && !collide()){
            x += move;
            lastMove = System.nanoTime();
        }
    }

    /**
     * Place new object for player to pick up.
     */
    private void placeObject() {
        boolean xNotPlaced = true;
        boolean yNotPlaced = true;
        int objX;
        int objY;
        do{
            objX = (int) (width * Math.random());
            if(objX%(blockSize * multiple) == 0){
               xNotPlaced = false;
            }
        }while(xNotPlaced);
        do{
            objY = (int) (height * Math.random());
            if(objY%(blockSize * multiple) == 0){
                yNotPlaced = false;
            }
        }while(yNotPlaced);

        o.place(objX, objY);

        pickedUp = false;
    }

    /**
     * Draws the game graphics.
     * */
    private void draw(){
        dbImage = createImage(getWidth(), getHeight());
        dbg = dbImage.getGraphics();

        dbg.setColor(Color.MAGENTA);
        dbg.fillRect(x, y, size, size);

        drawObject(dbg);

        getGraphics().drawImage(dbImage, 0, 0, this);
    }

    /**
     * Draws the playable character depending on where you are looking.
     * @param g Needed to draw in current Graphics
     */
    private void drawChar(Graphics g){
        if(direction.equals("Up")){

        }else if(direction.equals("Left")){

        }else if(direction.equals("Down")){

        }else if(direction.equals("Right")){

        }
    }

    /**
     * Draws the object that the player is supposed to pick up.
     * @param g Needed to draw in current Graphics
     */
    private void drawObject(Graphics g){
        g.setColor(Color.RED);
        g.fillRect(o.getX(), o.getY(), size, size);
    }


    /**
     * Checks if player is about to collide with wall or another object. Returns false if not about to collide, true if about to collide.
     * @return boolean
     */
    private boolean collide(){
        if(direction.equals("Up")){
            if(y-move >= 0){
                return false;
            }
        }else if(direction.equals("Left")){
            if(x-move >= 0){
                return false;
            }
        }else if(direction.equals("Down")){
            if(y+size+2*move <= height){
                return false;
            }
        }else if(direction.equals("Right")){
            if(x+size+move <= width){
                return false;
            }
        }
        return true;
    }

    /**
     * Setter for direction.
     * @param s String for direction
     */

    public void setDirection(String s){
        if(s.equals("Down") || s.equals("Left") || s.equals("Up") || s.equals("Right")){
            this.direction = s;
        }else{
            System.out.println("Write an accepted direction.");
        }
    }

    /**
     * Save and exit game.
     */
    private void exitGame(){


        System.exit(2);
    }

    private void stopMove(){
        movingUp = false;
        movingLeft = false;
        movingDown = false;
        movingRight = false;
    }



    /**
     * Keylistener for platform movement with keyboard.
     * */
    public class KL implements KeyListener {

        @Override
        public void keyTyped(KeyEvent keyEvent) {

        }

        @Override
        public void keyPressed(KeyEvent keyEvent) {
            if(keyEvent.getKeyCode() == KeyEvent.VK_UP){
                stopMove();
                movingUp = true;
                setDirection("Up");
            }
            if(keyEvent.getKeyCode() == KeyEvent.VK_LEFT){
                stopMove();
                movingLeft = true;
                setDirection("Left");
            }
            if(keyEvent.getKeyCode() == KeyEvent.VK_DOWN){
                stopMove();
                movingDown = true;
                setDirection("Down");
            }
            if(keyEvent.getKeyCode() == KeyEvent.VK_RIGHT){
                stopMove();
                movingRight = true;
                setDirection("Right");
            }
            if(keyEvent.getKeyCode() == KeyEvent.VK_R){
                placeObject();
            }
            if(keyEvent.getKeyCode() == KeyEvent.VK_E){
                exitGame();
            }
        }


        @Override
        public void keyReleased(KeyEvent keyEvent) {
            if(keyEvent.getKeyCode() == KeyEvent.VK_UP){
                stopMove();
            }
            if(keyEvent.getKeyCode() == KeyEvent.VK_LEFT){
                stopMove();
            }
            if(keyEvent.getKeyCode() == KeyEvent.VK_DOWN){
                stopMove();
            }
            if(keyEvent.getKeyCode() == KeyEvent.VK_RIGHT){
                stopMove();
            }
        }
    }

    /**
     * Getter for multiple.
     * @return multiple value
     */
    public int getMultiple(){
        return this.multiple;
    }
    /**
     * Starter for game.
     * */
    public static void main(String[] args) {
        new Main();
    }
}
