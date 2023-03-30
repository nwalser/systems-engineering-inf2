import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



public class HelloBalloon1 extends JFrame implements ActionListener {
    private JButton action;
    private Color color = new Color(0,100,166);
    private Balloon3 balloon;

    public void paint(Graphics g) {
        super.paint(g);
        balloon.draw(g);
        balloon.setColor(color);
    }

    public void actionPerformed(ActionEvent e) {
        balloon.setColor(Color.GREEN);
        balloon.draw(getGraphics());
    }

    public void initComponents() {
        balloon = new Balloon3(color, 200, 200, 100);
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