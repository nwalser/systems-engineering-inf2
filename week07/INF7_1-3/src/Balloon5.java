import java.awt.*;

public class Balloon5 extends Ball{

    public Balloon5(Color color, int mx, int my, int r){
        super(color, mx, my, r);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval (mx-r, my-r, 2*r, 2*r);
    }

    @Override
    public void move(Ball[] others) {
        my--;
    }
}
