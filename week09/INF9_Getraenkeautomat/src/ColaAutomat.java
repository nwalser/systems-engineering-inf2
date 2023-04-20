import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.sound.sampled.*;

public class ColaAutomat extends JFrame implements ActionListener {
	private JButton fuenf, zwei, eins, ausgabe;
	private CoinBox coinBox;
	private BottleDispenser bottleDispenser;
	private final boolean MULTISOUND = true;
	
	private Clip clip = null;
	private String clipFile = "";
	public void playSound(String filename) {    
		try {  
			if (!clipFile.equals(filename)) {
			    clipFile = filename;
				AudioInputStream audioIn = AudioSystem.getAudioInputStream(
						new File(filename));				
				clip = AudioSystem.getClip();   
				clip.open(audioIn);	
			}   
			if (!clip.isActive()) {  
				clip.setFramePosition(0);
				clip.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}		
		
	private void sleep(int millis) {
		try {Thread.sleep(millis);} catch (Exception e) {}
	}
	
	private void paintImage(Graphics g, String name, int x, int y) {
		try {
			Image img = ImageIO.read(new File(name)); 
			g.drawImage(img, x, y, img.getWidth(this), img.getHeight(this),this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.WHITE);
		paintImage(g,"logo.jpg",0,100);
		g.drawString("Saldo:" + coinBox.getSaldo() + ".-", 50, 100);
		if (bottleDispenser.isLocked()) {
			g.drawString("locked", 50, 120);
		} else {
			g.drawString("unlocked", 50, 120);
		}
	}
	
	private void showBottle(){
	    Graphics g = getGraphics();
	    /*g.setColor(Color.BLACK);	//Falls kein Bottle-Bild zur Verfügung steht
	    g.fillRect(120,140,20,30);
		g.fillRect(115,170,30,70);*/
		paintImage(g,"bottle.jpg",115,140);
		sleep(2000);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == fuenf) {
			coinBox.change(5);
			if(!bottleDispenser.isLocked())
				ausgabe.setEnabled(true);
		}
		if (e.getSource() == zwei) {
			coinBox.change(2);
			if(!bottleDispenser.isLocked())
				ausgabe.setEnabled(true);
		}
		if (e.getSource() == eins) {
			coinBox.change(1);
			if(!bottleDispenser.isLocked())
				ausgabe.setEnabled(true);
		}
		if (e.getSource() == ausgabe) {
			if(!bottleDispenser.isLocked()) {
				bottleDispenser.dispense();
				playSound("getbottle.wav");
				showBottle();
			}
			if(bottleDispenser.isLocked())
				ausgabe.setEnabled(false);
		}
		paint(getGraphics());
	}
		
	private void initComponents() {
	    setBackground(Color.WHITE);		
		JPanel panel = (JPanel) this.getContentPane();
		panel.setLayout(new FlowLayout());	
		panel.setBackground(new Color(223, 0, 31));
		fuenf = new JButton("5.-");
		fuenf.addActionListener(this);
		panel.add(fuenf);
		zwei = new JButton("2.-");
		zwei.addActionListener(this);
		panel.add(zwei);		
		eins = new JButton("1.-");
		eins.addActionListener(this);
		panel.add(eins);	
		ausgabe = new JButton("Ausgabe");
		ausgabe.addActionListener(this);
		panel.add(ausgabe);
		bottleDispenser = new BottleDispenser();
		coinBox = new CoinBox(bottleDispenser);
		bottleDispenser.setCoinBox(coinBox);
	}	
    
	public static void main(String[] args) {
		// set look
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {}
		ColaAutomat window = new ColaAutomat();
		window.setTitle("Cola");
		window.setSize(300, 400);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.initComponents();
		// show frame
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}
}
