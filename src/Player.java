import processing.core.PApplet;

public class Player {
    private int x;
    private int y;
    private int speed;
    private PApplet canvas;

    public Player(PApplet canvas) {
        this.x = 385;
        this.speed = 5;
        this.canvas = canvas;
        y = 550;
    }

    public void moveLeft() {
       if (x>10){
        x -= speed;
       }

    }

    public void moveRight() {
        if (x<750){
            x += speed;
        }
       
    }

    public void update() {
        canvas.fill(255, 102, 180);
        canvas.square(x, y, 40);
    }

    public int getX(){
        return x;
    }

   
}
