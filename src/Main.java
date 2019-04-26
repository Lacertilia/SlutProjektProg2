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
    JFrame frame;
    private int multiple = 5;
    int width = 240*multiple;
    int height = 170*multiple;
    static int x = 10;
    static int y = 10;
    int size = 13*multiple;
    int move = size;
    int fps = 30;
    Image dbImage;
    static Graphics dbg;
    static boolean Started = false;
    private boolean movingUp = false;
    private boolean movingLeft = false;
    private boolean movingDown = false;
    private boolean movingRight = false;
    private String direction = "Down";


    /**
     * Creating everything for the game.
     * */
    public Main(){
        Scanner scan = new Scanner(System.in);


        frame = new JFrame("Game");
        frame.setSize(new Dimension(width, height));
        frame.add(this);
        frame.setResizable(false);
        addKeyListener(new KL());
        Started = true;

        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);

        long lastUpdate = System.nanoTime();
        long lastMove = System.nanoTime();
        long moveStop = 1000000000/5;
        long dt = 1000000000/fps;
        while(Started){
            if(System.nanoTime() - lastUpdate > dt){
                lastUpdate = System.nanoTime();
                draw();
            }
            if(movingUp && System.nanoTime()-lastMove >= moveStop && y-move >= 0){
                y -= move;
                lastMove = System.nanoTime();
            }
            if(movingLeft && System.nanoTime()-lastMove >= moveStop && x-move >= 0){
                x -= move;
                lastMove = System.nanoTime();
            }
            if(movingDown && System.nanoTime()-lastMove >= moveStop && y+size+move <= height){
                y += move;
                lastMove = System.nanoTime();
            }
            if(movingRight && System.nanoTime()-lastMove >= moveStop && x+size+move <= width){
                x += move;
                lastMove = System.nanoTime();
            }
        }
    }
    /**
     * Draws the game graphics.
     * */
    private void draw(){
        dbImage = createImage(getWidth(), getHeight());
        dbg = dbImage.getGraphics();

        dbg.setColor(Color.MAGENTA);
        dbg.fillRect(x, y, size, size);

        getGraphics().drawImage(dbImage, 0, 0, this);
    }

    /**
     * Draws the character you play as.
     * @param g Needed in order to draw in current graphics
     */
    private void drawChar(Graphics g){
        if(direction.equals("Up")){

        }else if(direction.equals("Left")){

        }else if(direction.equals("Down")){

        }else if(direction.equals("Right")){

        }
    }

    /**
     * Setter for direction.
     * @param s
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
                movingUp = true;
                setDirection("Up");
            }
            if(keyEvent.getKeyCode() == KeyEvent.VK_LEFT){
                movingLeft = true;
                setDirection("Left");
            }
            if(keyEvent.getKeyCode() == KeyEvent.VK_DOWN){
                movingDown = true;
                setDirection("Down");
            }
            if(keyEvent.getKeyCode() == KeyEvent.VK_RIGHT){
                movingRight = true;
                setDirection("Right");
            }
            if(keyEvent.getKeyCode() == KeyEvent.VK_E){
                exitGame();
            }
        }


        @Override
        public void keyReleased(KeyEvent keyEvent) {
            if(keyEvent.getKeyCode() == KeyEvent.VK_UP){
                movingUp = false;
            }
            if(keyEvent.getKeyCode() == KeyEvent.VK_LEFT){
                movingLeft = false;
            }
            if(keyEvent.getKeyCode() == KeyEvent.VK_DOWN){
                movingDown = false;
            }
            if(keyEvent.getKeyCode() == KeyEvent.VK_RIGHT){
                movingRight = false;
            }
        }
    }

    /**
     * Starter for game.
     * */
    public static void main(String[] args) {
        new Main();
    }
}