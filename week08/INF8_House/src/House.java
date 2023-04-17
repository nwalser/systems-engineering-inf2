import java.awt.*;

public class House extends Figure {
    private Figure[] figures = new Figure[100];
    private int max = 0;

    public void draw(Graphics g) {
        for (int i = 0; i < max; i++) {
            figures[i].draw(g);
        }
    }

    public void add(Figure fig) {
        figures[max] = fig;
        max++;
    }

    public House(int x, int y) {
        super(Color.WHITE, x, y, 80, 80);
        add(new Triangle(Color.RED, x-10, x+40, x+90, y, y-50, y));
        add(new Rectangle(Color.LIGHT_GRAY, x,y,80,80));
        add(new Rectangle(Color.BLACK, x+5, y+10,30,30));
        add(new Rectangle(Color.BLACK, x+45, y+10,30,30));
    }


}