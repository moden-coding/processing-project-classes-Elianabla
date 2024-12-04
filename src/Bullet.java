import processing.core.*;

public class Bullet {
    private int bulX;
    private int bulY;
    private int bulSpeed =5;
    private boolean bulRemove;
    private PApplet canvas;

    // inspo from aris code

    public Bullet(int X, int Y, int speed, PApplet c){
        this.canvas = c;
        this.bulX = X;
        this.bulY = Y;
        this.bulSpeed = speed;
        this.bulRemove = false;

    }

    public void shoot(){
        
    }


    
}
