import processing.core.*;

public class Bullet {
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

    public void shoot() {
        bulY -= bulSpeed;

    }

    public void update() {
        shoot();
        canvas.fill(255, 0, 0);
        canvas.circle(bulX, bulY, 20);

    }

    public int getX() {
        return bulX;
    }

    public int getY() {
        return bulY;
    }

    public boolean touch(int x, int y) {
        float distance = canvas.dist(bulX, bulY, x, y);
        if (distance <= 20) {
            return true;
        }
        return false;
    }
}
