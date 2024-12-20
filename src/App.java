import processing.core.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class App extends PApplet {
    boolean left; // keeps track if left arrow key was pressed
    boolean right; // keeps track if right arrow key was pressed
    boolean space; // keeps track if space bar is pressed
    Bubble firstOne;
    Player player; 
    PImage lives; // heart photo
    PImage play; // photo with play screen
    PImage home; // photo with home button
    PImage instructions; // photo with instructions
    boolean changeFrame;

    ArrayList<Bubble> bubbles;
    ArrayList<Bullet> bullets;
    ArrayList<Lifes> lifes;

    int scene = 0;
    int highScore;
    int xPosition = 385;
    int lastTimeShot = 0; // how many milliseconds ago was the last bullet shot
    int cooldown = 300; // milliseconds between bullets being shot
    int life = 3;
    int score = 0;
    int frames = 120;
    int speedupTimer = 0;

    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void setup() {
        player = new Player(this);
        // declaring new array lsits
        bubbles = new ArrayList<>();
        bullets = new ArrayList<>();
        lifes = new ArrayList<>();
        firstOne = new Bubble(150, 200, this);

        // loading in images
        lives = loadImage("life.png");
        play = loadImage("playreal.png");
        home = loadImage("house.png");
        instructions = loadImage("instructions.png");

    }

    public void settings() {
        size(800, 600);
    }

    public void draw() {
        background(0, 0, 0);

        if (scene == 0) {
            image(play, 0, 0);

        }

        if (scene == 1) {
           sceneOne(); // everything used for scene 1 (the main game)
        }

        if (scene == 3) { // ending scene (game over)
            fill(60, 10, 166);
            text("Game Over", 340, 100);
            textSize(40);
            text("Score: " + score, 340, 200);
            text("High Score: " + highScore, 290, 300);
            text("Press enter to play again", 200, 400);
        }

        if (scene == 2) { // instructions page
            image(instructions, 0, 0);
            image(home, 20, 550, 40, 40);
        }

    }

    public void keyPressed() {
        if (scene == 1) {

            if (keyCode == LEFT || keyCode == 'A') {
                player.moveLeft(); // player moves left

            } else if (keyCode == RIGHT || keyCode == 'D') {
                player.moveRight(); // player will move right
            }

            if (keyCode == ' ') { // bullets will shoot

                if (millis() - lastTimeShot >= cooldown) { // used from chat gpt if time minus last time is more than cooldown time then..
                    lastTimeShot = millis(); // resets last shot time
                    bulletMaker(); // makes a bullet
                }

            }

        }

        if (scene == 3) {
            if (keyCode == ENTER) { // will reset the game
                resetGame();

            }
        }

    }

    public void sceneOne(){
        collision(); // method detecting collision
        bubbleShower(); // shows the bubbles
        bulletShower(); // showws the bullets

        fill(255);
        textSize(20);
        text("Score: " + score, 20, 20);

        speedUpLogic(); // speeds up how many bubbles fall
        printSpeedUp();  // writes text saying "speed up"

        lives();

        if (frames != 0 && frameCount % frames == 0) {
            bubbleMaker(); // will make bubbles if frameCount is divisible by frames
        }

        if (left) {
            player.moveLeft();
        }

        if (right) {
            player.moveRight();
        }

        player.update();
    }

    public void keyReleased() {
        player.stop(); // player will stop moving

    }

    public void bubbleMaker() { // makes bubble
        int x = (int) random(700) + 40;
        int y = 20;
        Bubble bubble = new Bubble(x, y, this);
        bubbles.add(bubble); // adds bubble to array list
    }

    public void lifeMaker() { // will make a life heart drop down
        int x = (int) random(700) + 40;
        int y = 20;
        Lifes life = new Lifes(x, y, this);
        lifes.add(life); // adds life to array list

    }

    public void bulletMaker() { //makes bullet
        Bullet bullet = new Bullet(player.getX() + 20, 550, 5, this);
        bullets.add(bullet); 
    }

    public void mousePressed() {
        if (scene == 0) {
            if (mouseX >= 260 && mouseX <= 541 && mouseY >= 220 && mouseY <= 323) {
                scene = 1; // switch to the game scene
            }

            if (mouseX >= 260 && mouseX <= 541 && mouseY >= 367 && mouseY <= 480) {
                scene = 2; // switch to instructions
            }

        }

        if (scene == 2) {
            if (mouseX >= 20 && mouseX <= 60 && mouseY >= 550 && mouseY <= 590) {
                scene = 0; // switch to home page
            }
        }

    }

    public void collision() {

        for (int i = 0; i < bubbles.size(); i++) { // chat gpt helped with collision
            Bubble b = bubbles.get(i);
            for (int j = 0; j < bullets.size(); j++) {
                Bullet bul = bullets.get(j);
                if (bul.touch(b.getX(), b.getY())) {
                    bubbles.remove(i);
                    bullets.remove(j);
                    score += 10;
                }

            }
        }

    }

    public void bubbleShower() {
        for (int i = 0; i < bubbles.size(); i++) {
            Bubble b = bubbles.get(i);
            b.display();
            b.update();
            if (b.outOfBounds()) {
                bubbles.remove(i);
                life--;
            }

        }
    }

    public void bulletShower() {
        for (int i = 0; i < bullets.size(); i++) {
            Bullet b = bullets.get(i);
            b.update();

            if (b.getY() < 30) {
                bullets.remove(i);
            }

        }

    }

    public void lives() {
        if (life == 3) {
            image(lives, 650, 30, 20, 20);
            image(lives, 690, 30, 20, 20);
            image(lives, 730, 30, 20, 20);

        }

        if (life == 2) {
            image(lives, 650, 30, 20, 20);
            image(lives, 690, 30, 20, 20);
            if (frameCount % 600 == 0) {
                lifeMaker();
            }
            dropLifes();

        }

        if (life == 1) {
            image(lives, 650, 30, 20, 20);

            if (frameCount % 200 == 0) { // drops a life every 15 seconds
                lifeMaker();
            }
            dropLifes();

        }

        if (life == 0) {
            scene = 3;
            reading();
            if (score > highScore) {
                highScore = score;
                writing();
            }
        }
    }

    public void resetGame() {
        frames = 120;
        life = 3;
        score = 0;
        bubbles.clear();
        bullets.clear();
        scene = 1;
    }

    public void reading() {
        try (Scanner scanner = new Scanner(Paths.get("file.txt"))) {

            // we read the file until all lines have been read
            while (scanner.hasNextLine()) {
                // we read one line
                String row = scanner.nextLine();
                // we print the line that we read
                highScore = Integer.valueOf(row);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void writing() {
        try (PrintWriter writer = new PrintWriter("file.txt")) {
            writer.println(highScore); // writes the integer to the file
            writer.close(); // closes the writer and saves the file
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
        }

    }

    public void speedUpLogic() {
        if (score % 100 == 0 && score > 0 && changeFrame) {
            frames = max(20, frames - 10); // from chat gpt
            changeFrame = false;
            speedupTimer = 60;
            System.out.println(frames);
        } else if (score % 100 != 0) {
            changeFrame = true;
        }
    }

    public void printSpeedUp() {
        if (speedupTimer > 0) {
            textSize(30);
            fill(255);
            text("SPEED UP", 300, 300);
            speedupTimer--;
        }
    }

    public void dropLifes() {
        for (int i = lifes.size() - 1; i >= 0; i--) {
            Lifes l = lifes.get(i);
            l.update();
            l.display();

            if (dist(l.getX(), l.getY(), player.getX(), player.getY()) < 20) {
                System.out.println("heart touches");
                life++;
                lifes.remove(l);

            }

        }
    }
}
