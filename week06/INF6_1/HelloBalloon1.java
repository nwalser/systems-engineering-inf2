import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HelloBalloon1 extends JFrame implements ActionListener {
    private JButton action;
    private Color color = new Color(0,100,166);


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(color);
        g.fillOval(100, 100, 200, 200);
    }

    public void actionPerformed(ActionEvent e) {
        color = Color.GREEN;
        paint(getGraphics());
    }

    public void initComponents() {
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