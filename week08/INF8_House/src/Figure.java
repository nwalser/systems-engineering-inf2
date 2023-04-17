import java.awt.*;


public abstract class Figure {
    protected int x, y, w, h;
    protected Color color;

    /*public Figure(){

    };*/

    public Figure(Color color, int x, int y, int w, int h) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public abstract void draw(Graphics g);
}
