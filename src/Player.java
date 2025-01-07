import processing.core.PApplet;

public class Player { //private player variables
    private int x;
    private int y;
    private int speed;
    private PApplet canvas;
    private boolean left, right = false;

    public Player(PApplet canvas) {
        this.x = 385;
        this.speed = 7;
        this.canvas = canvas;
        y = 550;
    }

    public void moveLeft() { // left is true
        left = true;

    }

    public void moveRight() { //right is true
        right = true;

    }

    public void update() { // if left is true then move player to the left
        if (left) {
            if (x > 10) {
                x -= speed;
            }
        }

        if (right){   //if right is true then move player to the right
            if (x < 750) {
                x += speed;
            }
        }
        canvas.fill(255, 102, 180);
        canvas.square(x, y, 40);

    }

    public int getX() { //returns the players x value
        return x;
    }

    public int getY(){ // returns the players y value
        return y;
    }

    public void stop() { // will make it so the left and right stop moving (boolean gets set to false)
        left = false;
        right = false;
    }

    

}
