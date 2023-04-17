import java.awt.*;

public class Rectangle extends Figure{
    protected int laenge, hoehe;

    public Rectangle(Color colour, int x_pos, int y_pos, int laenge, int hoehe){
        super(colour,x_pos,y_pos,laenge,hoehe);
        this.x = x_pos;
        this.y = y_pos;
        this.color = colour;
        this.laenge = laenge;
        this.hoehe = hoehe;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x,y,laenge,hoehe);
    }

}
