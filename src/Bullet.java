import processing.core.*;

public class Bullet { //private variables for bullet
    private int bulX;
    private int bulY;
    private int bulSpeed = 6;
    private PApplet canvas;

    // inspo from aris code

    public Bullet(int X, int Y, int speed, PApplet c) {
        this.canvas = c;
        this.bulX = X;
        this.bulY = Y;
        this.bulSpeed = speed;

    }

    public void shoot() {  // bullet gets shot
        bulY -= bulSpeed;

    }

    public void update() { // displays the shooting of the bullet
        shoot();
        canvas.fill(255, 0, 0);
        canvas.circle(bulX, bulY, 20);

    }

    public int getX() { //return bullets x value
        return bulX;
    }

    public int getY() { //return bullets y value
        return bulY;
    }

    public boolean touch(int x, int y) {
        float distance = canvas.dist(bulX, bulY, x, y);
        if (distance <= 20) {
            return true; // if bullet touches bubble then boolean returns true
        }
        return false;
    }
}
