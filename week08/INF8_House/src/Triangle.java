import java.awt.*;

public class Triangle extends Figure{
    protected int [] x_points = new int[3], y_points = new int[3];

    public Triangle(Color colour, int x_pos_1, int y_pos_1, int x_pos_2, int y_pos_2, int x_pos_3, int y_pos_3){
        super(colour,x_pos_1,y_pos_1,x_pos_3-x_pos_1,y_pos_3-y_pos_1);//eigentlich unnötig aber die Figure-class auf dem Server hat keinen leeren Constructor
        this.x_points[0] = x_pos_1;
        this.x_points[1] = x_pos_2;
        this.x_points[2] = x_pos_3;
        this.y_points[0] = y_pos_1;
        this.y_points[1] = y_pos_2;
        this.y_points[2] = y_pos_3;
        this.color = colour;
    }



    @Override
    public void draw (Graphics g){
        g.setColor(color);
        g.fillPolygon(x_points, y_points, 3);
    }
}
