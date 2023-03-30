import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HelloBalloon1 extends JFrame implements ActionListener {
    private JButton action;
    private Color color = new Color(0,100,166);
    private Balloon4[] balloons = new Balloon4[100];
    private int max = 0;
    public void add(Balloon4 b) {
        balloons [max] = b;
        max++;
    }

    public void addRandom(int radius){
        int my = (int)(Math.random() * 400);
        int mx = (int)(Math.random() * 400);

        Balloon4 balloon = new Balloon4(color, mx, my, radius);
        add(balloon);
    }


    public void paint(Graphics g) {
        super.paint(g);
        for (int i = 0; i < max; i++) {
            balloons [i].draw(g);
        }
    }

    public void actionPerformed(ActionEvent e) {
        addRandom(25);
        paint(getGraphics());
    }

    public void initComponents() {
        for(int i = 1; i < 15; i++){
            addRandom(25);
        }

        setBackground(Color.WHITE);
        JPanel panel = (JPanel) this.getContentPane();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new FlowLayout());
        action = new JButton("action");
        panel.add(action);
        action.addActionListener(this);
    }

    public static void main(String[] args) {
        // set look
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {}
        HelloBalloon1 window = new HelloBalloon1();
        window.setTitle("Hello");
        window.setSize(400, 400);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.initComponents();
        // show frame.
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}