import processing.core.PApplet;

public class Sketch extends PApplet {
	
	float[] fltSnowY = new float[25]; 
  float[] fltSnowX = new float[25]; 

  /**
   * Called once at the beginning of execution, put your size all in this method
   */
  public void settings() {
	// put your size call here
    size(400, 400);
  }

 

  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {
    for (int i = 0; i < fltSnowY.length; i++) {
      fltSnowY[i] = random(height);
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
      ellipse(fltSnowX[i], fltSnowY[i], 25, 25);
  
      fltSnowY[i]++;
  
      if (fltSnowY[i] > height) {
        fltSnowY[i] = 0;
        fltSnowX[i] = random(width); 
      }
    }
  if (upPressed) {
    circleY--;
  }
  
  if (downPressed) {
    circleY++;
  }
  
  if (leftPressed) {
    circleX--;
  }
  
  if (rightPressed) {
    circleX++;
  }
    drawPlayer(circleX, circleY);
  }


  boolean upPressed = false;
  boolean downPressed = false;
  boolean leftPressed = false;
  boolean rightPressed = false;

  float circleX = 200;
  float circleY = 200;


  public void keyPressed() {
    if (key == 'w') {
      upPressed = true;
    }
    else if (key == 's') {
      downPressed = true;
    }
    else if (key == 'a') {
      leftPressed = true;
    }
    else if (key == 'd') {
      rightPressed = true;
    }
  }

  public void keyReleased() {
    if (key == 'w') {
      upPressed = false;
    }
    else if (key == 's') {
      downPressed = false;
    }
    else if (key == 'a') {
      leftPressed = false;
    }
    else if (key == 'd') {
      rightPressed = false;
    }
  }

  public void drawPlayer(float fltPlayerX2, float fltPlayerY2) {
    fill(28, 35, 173);
    ellipse(fltPlayerX2, fltPlayerY2, 25, 25);
  }
}