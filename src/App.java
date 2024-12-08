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
    PImage lives;
    PImage play;

    ArrayList<Bubble> bubbles;
    ArrayList<Bullet> bullets;

    int scene = 0;
    int xPosition = 385;
    int lastTimeShot = 0;
    int cooldown = 500;
    int life = 3;
    int score = 0;

    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void setup() {
        player = new Player(this);
        // 
        bubbles = new ArrayList<>();
        bullets = new ArrayList<>();
        firstOne = new Bubble(150, 200, this);

        lives = loadImage("life.png");
        play = loadImage("playreal.png");

    }

    public void settings() {
        size(800, 600);
    }

    public void draw() {
        background(0, 0, 0);

        if (scene == 0){
            image(play, 0, 0);

        }


        if (scene == 1) {
            mainGame();

        if (life == 3){ 
            image(lives, 650, 30, 20, 20);
            image(lives, 690, 30, 20, 20);
            image(lives, 730, 30, 20, 20);

        }

        if (life == 2){
            image(lives, 650, 30, 20, 20);
            image(lives, 690, 30, 20, 20);

        }

        if (life == 1){
            image(lives, 650, 30, 20, 20);

        }

        if (life == 0){
            scene = 3;
        }

        color(0);
        textSize(20);
        text("Score: " + score, 20, 20);


        


        if (frameCount % 100 == 0) {
            bubbleMaker();
        }
       
        if (left) {
            player.moveLeft();
        }

        if (right){
           player.moveRight();
        }

        player.update();
    }

    if (scene == 3){
        
        text("Game Over", 300, 100);
        textSize(40);
        text("High Score: " + score, 300, 200);
    }
       
       //player.display();

    }

    public void keyPressed() {
        if (scene == 1) {
            // if (keyCode == LEFT && xPosition > 10) {
            //     left = true;
            // } else if (keyCode == RIGHT && xPosition < 760) {
            //     right = true;
            // }

            if (keyCode == LEFT){
                player.moveLeft();

            }else if (keyCode == RIGHT) {
                player.moveRight(); }
            //     right = true;
            // }

            if (keyCode == ' ' ){
                
                if (millis() - lastTimeShot >= cooldown) {  //used from chat gpt
                    lastTimeShot = millis(); 
                    bulletMaker();
                }
            
                
            }
        
        }
    
    } 

    public void keyReleased(){
        player.stop();
        
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

    public void mousePressed(){
        if (scene == 0){
            if (mouseX >= 260 && mouseX <= 541 && mouseY >= 220 && mouseY <= 323) {
                scene = 1; // switch to the game scene
            }

            if (mouseX >= 260 && mouseX <= 541 && mouseY >= 367 && mouseY <= 480) {
                scene = 2;
        }
        
    }

    }

    public void mainGame(){

        for (int i = 0; i < bubbles.size(); i++) {
            Bubble b = bubbles.get(i);
            b.display();
            b.update();

            if (b.getY() >500) {
                bubbles.remove(i);
                life--;
            }
        }

        for (int i = 0; i < bullets.size();  i++) {
            Bullet b = bullets.get(i);
            b.update(); 

            if(b.getY()<30){
                bullets.remove(i);
            }

        }

        for (int i = 0; i < bubbles.size(); i++) { //chat gpt helped with collision
            Bubble b = bubbles.get(i);
            for (int j = 0; j < bullets.size();  j++) {
                Bullet bul = bullets.get(j);

                float dist = dist(bul.getX(),bul.getY(), b.getX(),b.getY());
                if (dist<=20){
                    bubbles.remove(i);
                    bullets.remove(j);
                    score+=10;
                }
            }   




    }


    }
}
