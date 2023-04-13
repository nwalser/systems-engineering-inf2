import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class HelloBalloon5 extends JFrame implements ActionListener {
	JButton action;
	Color ZHAWBLUE = new Color(0, 100, 166);
	
	private Ball[] balls = new Ball[100];
	private int max = 0;
	
	public void add(Ball b) {
		balls[max] = b;
		max++;	
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		for (int i = 0; i < max; i++) {
			balls[i].draw(g);
		}
	}
	
	private void moveAll() {
		for (int i = 0; i < 500; i++) {
			for (int j = 0; j < max; j++) {
				if (balls[j] != null) {
					balls[j].move(balls);
				}
			}
			sleep(10);
			paint(getGraphics());
		}
	}
	
	private void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (Exception e) {}
	}
	
	public void actionPerformed(ActionEvent e) {  
		paint(getGraphics());
		moveAll();  
	}
		
	public void initComponents() {
	    setBackground(Color.WHITE);		
		JPanel panel = (JPanel) this.getContentPane();
		panel.setBackground(Color.WHITE);
		panel.setLayout(new FlowLayout());	
		action = new JButton("action");
		panel.add(action);
		action.addActionListener(this);
		//add(new Ball(ZHAWBLUE, 200, 200, 100)); //Demo
		//balls[0] = new Balloon5(Color.RED, 200, 200, 100); //INF7.1
		add(new Balloon5(Color.RED, 300, 375, 25));
		add(new Billard(ZHAWBLUE, 25, 350, 10, 2, -1));
	}	
    
	public static void main(String[] args) {
		// set look
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {}
		
		HelloBalloon5 window = new HelloBalloon5();
		window.setTitle("Hello");
		window.setSize(400, 400);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.initComponents();
		// show frame.
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}
}
