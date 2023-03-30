import java.awt.*;

public class Balloon2 {	      //Klassenname
    private int diameter;	       //Instanzvariablen
    private int x,y;
    private Color color;

    //Konstruktor zur Initialisierung
    public Balloon2(Color color, int mx, int my, int r){
        this.x = mx-r;
        this.y = my-r;
        this.diameter = 2*r;
        this.color = color;
    }

    public void setColor(Color color){
        this.color = color;
    }

    public void changeSize (int change) {  //Instanzmethode
        diameter = diameter + change;
    }

    public void draw (Graphics g) {     //Instanzmethode
        g.setColor(color);
        g.fillOval(x, y, diameter, diameter);
    }
}