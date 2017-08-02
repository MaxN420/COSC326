import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.util.ArrayList;

public class Screen extends JPanel {
  
  public ArrayList<double[]> squares = new ArrayList<double[]>(); 
  public static double dubScale;
  public static int initialScale;
  public static int scale;
  public static int size;
  public static int r;
  public static int g;
  public static int b;
  
  public Screen(ArrayList<double[]> squares, int size) {
    //work out the size that the first square can be.
    double totalPercent = 0;
    for(int i = 0; i < squares.size(); i++){
      totalPercent += squares.get(i)[0];
    }
    double hypot = Math.sqrt(((size*size) + (size*size)));
    System.out.println("hypot: " + hypot);
    System.out.println("percent: " + totalPercent);
    dubScale = hypot/totalPercent;
    //changing the 1% hypot length into the side lengths of the 1% square.
    dubScale = Math.sqrt((dubScale*dubScale)/2);
    //scale is the length and height of a square with 1% scale.
    initialScale = Integer.valueOf((int) Math.round(dubScale));
    scale = initialScale;
    System.out.println("scale: " + initialScale);
    
//   sorting the quilt patterns. 
//   for(int i = 0; i < squares.size(); i++){
//        double currentValue = squares.get(i)[0];
//        int pos = i;
//        while(pos > 0 && squares.get(pos-1)[0] < currentValue){
//            squares.get(pos)[0] = squares.get(pos-1)[0];
//            pos--;
//        }
//        squares.get(pos)[0] = currentValue;
//    }
    this.squares = squares;
    this.size = size;
    repaint();
  }
  
  public void paint(Graphics draw) { 
    int x = 0;
    int y = 0;
    //for each square in the arrayList, draw it on the page.
    r = Integer.valueOf((int) Math.round(squares.get(0)[1]));
    g = Integer.valueOf((int) Math.round(squares.get(0)[2]));
    b = Integer.valueOf((int) Math.round(squares.get(0)[3]));
    draw.setColor(new Color(r, g, b));
    //draw the first square.
    System.out.println("drawing");
    x = ((size/2)-(scale/2));
    y = ((size/2)-(scale/2));
    draw.fillRect(x, y, scale, scale);
    
    //finding the new dimesions for the next square.
    scale = Integer.valueOf((int) Math.round(initialScale*squares.get(0)[0]));
    
    //starting the recursion for drawing the squares.
    drawSquares(x, y, scale, draw, 1);
    
    
    
    
  }
  
  public void drawSquares(int prevX, int prevY, int prevScale, Graphics draw, int set){
    int x;
    int y;
    System.out.println(Integer.valueOf((int) Math.round(squares.get(set)[0]*initialScale)));
    if(squares.size() > 1){
      for(int i = 0; i < 4; i++){  
        System.out.println("drawing loop");
        switch(i) {
          //topLSquare
          case 0:
            x = (prevX)-Integer.valueOf((int) Math.round(squares.get(set)[0]*initialScale))/2;
            y = (prevY+prevScale)-Integer.valueOf((int) Math.round(squares.get(set)[0]*initialScale))/2;
            r = Integer.valueOf((int) Math.round(squares.get(set)[1]));
            g = Integer.valueOf((int) Math.round(squares.get(set)[2]));
            b = Integer.valueOf((int) Math.round(squares.get(set)[3]));
            draw.setColor(new Color(r, g, b));
            draw.fillRect(x, y, Integer.valueOf((int) Math.round(squares.get(set)[0]*initialScale)), 
                          Integer.valueOf((int) Math.round(squares.get(set)[0]*initialScale)));
            if(squares.size()-1 > set){
              drawSquares(x, y, Integer.valueOf((int) Math.round(squares.get(set)[0]*initialScale)), draw, set+1);
            }
            break;
            //topRSquare
          case 1:
            x = (prevX+prevScale)-Integer.valueOf((int) Math.round(squares.get(set)[0]*initialScale))/2;
            y = (prevY+prevScale)-Integer.valueOf((int) Math.round(squares.get(set)[0]*initialScale))/2;
            r = Integer.valueOf((int) Math.round(squares.get(set)[1]));
            g = Integer.valueOf((int) Math.round(squares.get(set)[2]));
            b = Integer.valueOf((int) Math.round(squares.get(set)[3]));
            draw.setColor(new Color(r, g, b));
            draw.fillRect(x, y, Integer.valueOf((int) Math.round(squares.get(set)[0]*initialScale)), 
                          Integer.valueOf((int) Math.round(squares.get(set)[0]*initialScale)));
            if(squares.size()-1 > set){
              set++;
              drawSquares(x, y, Integer.valueOf((int) Math.round(squares.get(set)[0]*initialScale)), draw, set+1);
            }
            break;
            //botRSquare
          case 2:
            x = (prevX+prevScale)-Integer.valueOf((int) Math.round(squares.get(set)[0]*initialScale))/2;
            y = (prevY)-Integer.valueOf((int) Math.round(squares.get(set)[0]*initialScale))/2;
            r = Integer.valueOf((int) Math.round(squares.get(set)[1]));
            g = Integer.valueOf((int) Math.round(squares.get(set)[2]));
            b = Integer.valueOf((int) Math.round(squares.get(set)[3]));
            draw.setColor(new Color(r, g, b));
            draw.fillRect(x, y, Integer.valueOf((int) Math.round(squares.get(set)[0]*initialScale)), 
                          Integer.valueOf((int) Math.round(squares.get(set)[0]*initialScale)));
            if(squares.size()-1 > set){
              drawSquares(x, y, Integer.valueOf((int) Math.round(squares.get(set)[0]*initialScale)), draw, set+1);
            }
            break;
            //botLSquare
          case 3:
            x = (prevX)-Integer.valueOf((int) Math.round(squares.get(set)[0]*initialScale))/2;
            y = (prevY)-Integer.valueOf((int) Math.round(squares.get(set)[0]*initialScale))/2;
            r = Integer.valueOf((int) Math.round(squares.get(set)[1]));
            g = Integer.valueOf((int) Math.round(squares.get(set)[2]));
            b = Integer.valueOf((int) Math.round(squares.get(set)[3]));
            draw.setColor(new Color(r, g, b));
            draw.fillRect(x, y, Integer.valueOf((int) Math.round(squares.get(set)[0]*initialScale)), 
                          Integer.valueOf((int) Math.round(squares.get(set)[0]*initialScale)));
            if(squares.size()-1 > set){
              drawSquares(x, y, Integer.valueOf((int) Math.round(squares.get(set)[0]*initialScale)), draw, set+1);
            }
            break;
          default:
            break;
        }
      }
    }
    System.out.println("here");
  }
  
  
  
  
}
