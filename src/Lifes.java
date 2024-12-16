import processing.core.PApplet;
import processing.core.PImage;

public class Lifes {
    private int x;
    private int y;
    private int size;
    private PApplet canvas;
    private int speed;
    private int health;
    private PImage lifes;


    public Lifes(int xPos, int yPos, PApplet c){
        x=xPos;
        y = yPos;
        size = 40;
        canvas = c;
        speed = 3;
        health =3;
        lifes = c.loadImage("life.png");

    }


    public void display(){
        canvas.image(lifes,x,y,size);
       
    }


    public void update() {
        y+=speed;
    }

    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


}




    

   
