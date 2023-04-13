import java.awt.*;

public class Billard extends Ball{
    protected int vx, vy;

    public Billard (Color color, int mx, int my, int r, int vx, int vy){
        super(color, mx, my, r);
        this.vx = vx;
        this.vy = vy;
    }

    private void testBounce(){
        if(mx < r || mx > 400-r)
            vx = -vx;
        if(my < r + 25 || my > 400-r)
            vy = -vy;
    }

    public boolean collide(Ball b1, Ball b2){
        double distance_between_balls=0;
        distance_between_balls = Math.sqrt(Math.pow((b1.mx-b2.mx),2) + Math.pow((b1.my-b2.my),2));
        if (distance_between_balls<=(b1.r+b2.r))
            return true;
        else
            return false;
    }

    public void testCollission(Ball[] others){
        boolean test=false;
        int arraysize = others.length;
        for(int i=0;i<arraysize;i++) {
            for (int a = 1; i+a < arraysize; a++) {
                if (others[i+a] != null) {
                    test = collide(others[i], others[i+a]);
                    //test = true;
                    if (test && others[i] instanceof Balloon5)
                        others[i].r = 0;
                    if (test && others[a + i] instanceof Balloon5)
                        others[i+a].r = 0;
                }
            }
        }
    }

    @Override
    public void move(Ball[] others) {
        mx += vx;
        my += vy;
        testBounce();
        testCollission(others);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval (mx-r, my-r, 2*r, 2*r);
    }
}
