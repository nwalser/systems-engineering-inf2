import java.awt.*;

public class Ball {	      //Klassenname
    protected int r;	       //Instanzvariablen
    protected int mx,my;
    protected Color color;

    //Konstruktor zur Initialisierung
    public Ball (Color color, int mx, int my, int r){ 	
        this.mx = mx;
        this.my = my;
        this.r = r;
        this.color = color;
    }
    
    public void move(Ball[] others) {}

    public void draw (Graphics g) {     //Instanzmethode
        g.setColor(color);
        g.drawOval (mx-r, my-r, 2*r, 2*r);
    }
}