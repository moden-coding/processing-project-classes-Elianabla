import processing.core.PApplet;

public class Bubble { //private variables
    private int x;
    private int y;
    private int size;
    private PApplet canvas;
    private int speed;
    private int color;

    public Bubble(int xPos, int yPos, PApplet c) {
        x = xPos;
        y = yPos;
        size = 40;
        canvas = c;
        speed = 3;
        color = canvas.color(170, 210, 230);

    }

    public void display() {
        canvas.fill(color);
        canvas.circle(x, y, size);

    }

    public void update() { //gets bubble to fall down
        y += speed;
    }

    public int getX() { // return x value
        return x;
    }

    public int getY() { //return y value
        return y;
    }

    public boolean outOfBounds() { // shows if bubble is too low down the screen + if so it will disappear
        if (y > 500) {
            return true;
        } else
            return false;
    }

}