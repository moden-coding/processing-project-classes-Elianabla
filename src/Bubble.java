import processing.core.PApplet;

public class Bubble {
    private int x;
    private int y;
    private int size;
    private PApplet canvas;
    private int speed;
    private int color;
    private int health;

    public Bubble(int xPos, int yPos, PApplet c){
        x=xPos;
        y = yPos;
        size = 40;
        canvas = c;
        speed = 2;
        color = canvas.color(170,210,230);
        health =3;

    }

    public void display(){
        canvas.fill(color);
        canvas.circle(x,y,size);
       
    }

     
     


    public void update() {
        y+=speed;
    }

    public int getY() {
        return y;
    }

}