import processing.core.PApplet;

/**
 * A program that draws a garden using mouse and keyboard input variables and event functions.
 * @author: L. Wong  
 */
public class Sketch extends PApplet {

  float[] fltSnowY = new float[10];
  float[] fltSnowX = new float[25];
  boolean[] ballHideStatus = new boolean[25];

  boolean blnUp = false;
  boolean blnDown = false;
  boolean blnLeft = false;
  boolean blnRight = false;

  float fltPlayerX = 200;
  float fltPlayerY = 200;
  float fltSnowSpeed = 2;
  float fltDistance;

  int intPlayerLives = 3;

  /**
   * Called once at the beginning of execution, put your size call here
   */
  public void settings() {
    // put your size call here
    size(400, 400);
  }

  /**
   * Called once at the beginning of execution. Add initial set up values here i.e background, stroke, fill etc.
   */
  public void setup() {

    for (int i = 0; i < fltSnowY.length; i++) {
      fltSnowY[i] = random(height);
      ballHideStatus[i] = false;  // Adjusted this line
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
      if (!ballHideStatus[i]) {
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
    } else {
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

  public void mousePressed() {
    for (int i = 0; i < fltSnowX.length; i++) {
      try {
        float d = dist(mouseX, mouseY, fltSnowX[i], fltSnowY[i]);
        if (d < 15 && !ballHideStatus[i]) { 
          ballHideStatus[i] = true;
        }
      } catch (ArrayIndexOutOfBoundsException e) {

      }
    }
  }

  public void keyPressed() {
    if (keyCode == UP) {
      fltSnowSpeed -= 0.1;
    } else if (keyCode == DOWN) {
      fltSnowSpeed += 0.1;
    }

    if (key == 'w') {
      blnUp = true;
    } else if (key == 's') {
      blnDown = true;
    } else if (key == 'a') {
      blnLeft = true;
    } else if (key == 'd') {
      blnRight = true;
    }
  }

  public void keyReleased() {
    if (key == 'w') {
      blnUp = false;
    } else if (key == 's') {
      blnDown = false;
    } else if (key == 'a') {
      blnLeft = false;
    } else if (key == 'd') {
      blnRight = false;
    }
  }

  public void drawPlayer(float fltPlayerX2, float fltPlayerY2) {
    fill(28, 35, 173);
    ellipse(fltPlayerX2, fltPlayerY2, 25, 25);
  }

  public void drawLivesIndicator() {
    for (int i = 0; i < intPlayerLives; i++) {
      fill(169, 25, 209);
      rect(width - 30, 10 + i * 30, 25, 25);
    }
  }
}