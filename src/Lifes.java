import processing.core.PApplet;
import processing.core.PImage;

public class Lifes { //private variables for lifes class
    private int x;
    private int y;
    private int size;
    private PApplet canvas;
    private int speed;
    private PImage lifes;


    public Lifes(int xPos, int yPos, PApplet c){
        x=xPos;
        y = yPos;
        size = 40;
        canvas = c;
        speed = 3;
        lifes = c.loadImage("life.png");

    }


    public void display(){ // displays the lifes image
        canvas.image(lifes,x,y,size,size);
       
    }


    public void update() {
        y+=speed; // makes lifes fall down the screen
    }

    
    public int getX() { //returns the x value of the live
        return x;
    }

    public int getY() { //returns the y value of the live
        return y;
    }


}




    

   
