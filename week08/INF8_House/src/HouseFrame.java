/**
 * @author K. Rege
 * @version 1.00 2016/2/23
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class HouseFrame extends JFrame implements ActionListener {
    private Figure[] figures = new Figure[100];
    private House haus_2 = new House(160, 150);
    private int max = 0;

    public void add(Figure fig) {
        figures[max] = fig;
        max++;
    }

    public void paint(Graphics g) {
        super.paint(g);
        for (int i = 0; i < max; i++) {
            figures[i].draw(g);
        }
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception e) {
        }
    }

    private void moveAll() {
        for (int i = 0; i < 150; i++) {
            for (int j = 0; j < max; j++) {
                if(figures[j] instanceof Movable) {
                    ((Movable) figures[j]).move(figures);
                }
            }
            sleep(10);
            paint(getGraphics());
        }
    }

    public void actionPerformed(ActionEvent e) {
        //haus_2.draw(getGraphics());       //only INF8.3
        moveAll();                        //only INF8.2
    }

    public void initComponents() {
        setBackground(Color.WHITE);
        JPanel panel = (JPanel) this.getContentPane();
        panel.setBackground(Color.WHITE);

        // set layout of content
        panel.setLayout(new FlowLayout());

        // add one button
        JButton action = new JButton("action");
        panel.add(action);
        action.addActionListener(this);

        // add geometric figures for house
        add(new Circle(Color.YELLOW, 300,150,30));
        add(new Rectangle(Color.GREEN, 0,200,400,200));
        add(new Triangle(Color.RED, 50, 150, 100, 100, 150, 150));
        add(new Rectangle(Color.LIGHT_GRAY, 60,150,80,80));
        add(new Rectangle(Color.BLACK, 65, 160,30,30));
        add(new Rectangle(Color.BLACK, 105, 160,30,30));

    }

    public static void main(String[] args) {
        // set look
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }
        // Create application frame.
        HouseFrame houseFrame = new HouseFrame();
        houseFrame.setSize(400, 400);
        houseFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        houseFrame.initComponents();
        // show frame.
        houseFrame.setLocationRelativeTo(null);
        houseFrame.setVisible(true);
    }
}