import java.awt.*;

public class Balloon4 {	      //Klassenname
    private int diameter;	       //Instanzvariablen
    private int x,y;
    private Color color;
    private int counter;

    //Konstruktor zur Initialisierung
    public Balloon4(Color color, int mx, int my, int r, int counter){
        this.x = mx-r;
        this.y = my-r;
        this.diameter = 2*r;
        this.color = color;
        this.counter = counter;
    }

    public void setColor(Color color){
        this.color = color;
    }

    public void changeSize (int change) {  //Instanzmethode
        diameter = diameter + change;
    }

    public void draw (Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, diameter, diameter);

        g.setColor(Color.WHITE);
        g.drawString(String.valueOf(counter), x+diameter/2, y+diameter/2);
    }
}