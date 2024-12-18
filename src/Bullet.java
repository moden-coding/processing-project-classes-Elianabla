import processing.core.*;

public class Bullet {
    private int bulX;
    private int bulY;
    private int bulSpeed =5;
    private PApplet canvas;

    // inspo from aris code

    public Bullet(int X, int Y, int speed, PApplet c){
        this.canvas = c;
        this.bulX = X;
        this.bulY = Y;
        this.bulSpeed = speed;


    }

    public void shoot(){
        bulY-=bulSpeed;
        
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

    //public boolean touch(int x, int y){
        //float dist = dist(getX(), getY(), x, y);
                //if (dist <= 20) {
        //return true;
          //      }
    }


    
//}
