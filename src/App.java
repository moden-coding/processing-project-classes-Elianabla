import processing.core.*;

//import java.nio.file.Paths;
import java.util.ArrayList;
//import java.util.Scanner;

public class App extends PApplet {
    boolean left;
    boolean right;
    boolean space;
    Bubble firstOne;
    Player player;
    Bullet bullet;

    ArrayList<Bubble> bubbles;
    ArrayList<Bullet> bullets;

    int scene = 1;
    int xPosition = 385;

    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void setup() {
        player = new Player(this);
        bullet = new Bullet(player.getX() + 20,550,5,this);
        bubbles = new ArrayList<>();
        bullets = new ArrayList<>();
        firstOne = new Bubble(150, 200, this);

    }

    public void settings() {
        size(800, 600);
    }

    public void draw() {
        background(0, 0, 0);
        if (scene == 1) {

            for (int i = 0; i < bubbles.size(); i++) {
                Bubble b = bubbles.get(i);
                b.display();
                b.update();

                if (b.getY() >500) {
                    bubbles.remove(i);
                }
            }

            for (int i = 0; i < bullets.size(); i++){
                Bullet b = bullets.get(i);

            }

        }


        if (frameCount % 90 == 0) {
            bubbleMaker();
        }
       
        if (left) {
            player.moveLeft();
        }

        if (right){
           player.moveRight();
        }

        if (space){
            bullet.shoot();
            bulletMaker();
          
        }

        player.update();
        bullet.update(player.getX());
       //player.display();

    }

    public void keyPressed() {
        if (scene == 1) {
            if (keyCode == LEFT && xPosition > 10) {
                left = true;
            } else if (keyCode == RIGHT && xPosition < 760) {
                right = true;
            }

            if (keyCode == ' ' ){
                space = true;
            }
        }
    } 

    public void keyReleased(){
        left = false;
        right = false;
        // space = false;
    }

    public void bubbleMaker() {
        int x = (int) random(700) + 40;
        int y = 20;
        Bubble bubble = new Bubble(x, y, this);
        bubbles.add(bubble);
    }

    public void bulletMaker(){
        Bullet bullet = new Bullet(player.getX()+20, 550, 5, this);
        bullets.add(bullet);
    }
}
