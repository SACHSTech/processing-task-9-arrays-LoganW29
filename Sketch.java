import processing.core.PApplet;

/**
 * A program that draws a game with mouse and keyboard input variables and event functions.
 * It features a player controlled with the keyboard and snowflakes that can be clicked with the mouse.
 * Snowflakes disappear when clicked and reduce player lives upon collision.
 * @author: L. Wong  
 */
public class Sketch extends PApplet {

  // Arrays to store positions of snowflakes and their hidden status
  float[] fltSnowY = new float[10];
  float[] fltSnowX = new float[25];
  boolean[] blnBallHideStatus = new boolean[25];

  // Boolean variables to track keyboard input
  boolean blnUp = false;
  boolean blnDown = false;
  boolean blnLeft = false;
  boolean blnRight = false;

  // Player position 
  float fltPlayerX = 200;
  float fltPlayerY = 300;

  // Speed of snowflakes and distance variable for collisions detection 
  float fltSnowSpeed = 2;
  float fltDistance;

  // Player lives counter
  int intPlayerLives = 3;

  /**
   * Called once at the beginning of execution, the size call. 
   */
  public void settings() {
    size(400, 400);
  }

  /**
   * Called once at the beginning of execution the initial set up values i.e background, stroke, fill etc.
   */
  public void setup() {

    // Initialize snowflake positions and blnBallHideStatus array
    for (int i = 0; i < fltSnowY.length; i++) {
      fltSnowY[i] = random(height);
      blnBallHideStatus[i] = false;  
    }
    for (int i = 0; i < fltSnowX.length; i++) {
      fltSnowX[i] = random(width);
    }
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
    background(50);

    fill(255);
    for (int i = 0; i < fltSnowY.length; i++) {
      if (!blnBallHideStatus[i]) {
        ellipse(fltSnowX[i], fltSnowY[i], 30, 30);
  
        fltSnowY[i] += fltSnowSpeed;
  
        if (fltSnowY[i] > height) {
          fltSnowY[i] = 0;
          fltSnowX[i] = random(width);
        }
  
        // Check for collision with player
        fltDistance = dist(fltPlayerX, fltPlayerY, fltSnowX[i], fltSnowY[i]);
        if (fltDistance < 30) {
          intPlayerLives--;
          fltPlayerX = width / 2;
          fltPlayerY = height - 50;
        }
      }
    }

    if (intPlayerLives > 0) {
      drawLivesIndicator();
    }
  
    if (intPlayerLives <= 0) {
      background(83, 117, 153);
      textSize(32);
      fill(0);
      textAlign(CENTER, CENTER);
      text("Game Over", width / 2, height / 2);
    } 
    else {
      if (blnUp) {
        fltPlayerY--;
      }
      if (blnDown) {
        fltPlayerY++;
      }
      if (blnLeft) {
        fltPlayerX--;
      }
      if (blnRight) {
        fltPlayerX++;
      }
      drawPlayer(fltPlayerX, fltPlayerY);
    }
  }

  /**
   * Called when the mouse is pressed. Checks for clicking on snowflakes and hides them. 
   */
  public void mousePressed() {
    for (int i = 0; i < fltSnowX.length; i++) {
      try {
        float d = dist(mouseX, mouseY, fltSnowX[i], fltSnowY[i]);
        if (d < 15 && !blnBallHideStatus[i]) { 
          blnBallHideStatus[i] = true;
        }
      } 
      catch (ArrayIndexOutOfBoundsException e) {

      }
    }
  }

  /**
   * Called when a key is pressed. 
   * Handles keyboard input for controlling the player and adjusting snowflake speed.
   */
  public void keyPressed() {
    if (keyCode == UP) {
      fltSnowSpeed -= 0.1;
    } 
    else if (keyCode == DOWN) {
      fltSnowSpeed += 0.1;
    }

    if (key == 'w') {
      blnUp = true;
    } 
    else if (key == 's') {
      blnDown = true;
    } 
    else if (key == 'a') {
      blnLeft = true;
    } 
    else if (key == 'd') {
      blnRight = true;
    }
  }

  /**
   * Called when a key is released. Handles keyboard input for controlling the player.
   */
  public void keyReleased() {
    if (key == 'w') {
      blnUp = false;
    } 
    else if (key == 's') {
      blnDown = false;
    } 
    else if (key == 'a') {
      blnLeft = false;
    } 
    else if (key == 'd') {
      blnRight = false;
    }
  }

  /**
   * Draws the player on the screen.
   * @param fltPlayerX2 The x-coordinate of the player.
   * @param fltPlayerY2 The y-coordintae of the player. 
   */
  public void drawPlayer(float fltPlayerX2, float fltPlayerY2) {
    fill(28, 35, 173);
    ellipse(fltPlayerX2, fltPlayerY2, 25, 25);
  }

  /**
   * Draws the player lives indicator on the screen. 
   */
  public void drawLivesIndicator() {
    for (int i = 0; i < intPlayerLives; i++) {
      fill(169, 25, 209);
      rect(width - 30, 10 + i * 30, 25, 25);
    }
  }
}