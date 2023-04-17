import java.awt.*;

public class Circle extends Figure implements Movable{
    protected int radius;

    public Circle(Color colour, int x_pos, int y_pos, int radius){
        super(colour,x_pos,y_pos,2*radius,2*radius); //eigentlich unnötig aber die Figure-class auf dem Server hat keinen leeren Constructor
        this.x = x_pos;
        this.y = y_pos;
        this.radius = radius;
        this.color = colour;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval (x-radius, y-radius, 2*radius, 2*radius);
    }

    @Override
    public void move(Figure[] others){
        y++;
    }

}
