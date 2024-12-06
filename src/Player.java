import processing.core.PApplet;

public class Player {
    private int x;
    private int y;
    private int speed;
    private PApplet canvas;
    private boolean left, right = false;

    public Player(PApplet canvas) {
        this.x = 385;
        this.speed = 5;
        this.canvas = canvas;
        y = 550;
    }

    public void moveLeft() {
        left = true;

    }

    public void moveRight() {
        right = true;

    }

    public void update() {
        if (left) {
            if (x > 10) {
                x -= speed;
            }
        }

        if (right){
            if (x < 750) {
                x += speed;
            }
        }
        canvas.fill(255, 102, 180);
        canvas.square(x, y, 40);

    }

    public int getX() {
        return x;
    }

    public void stop() {
        left = false;
        right = false;
    }

}
