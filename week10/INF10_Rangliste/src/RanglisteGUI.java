import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;


class RanglisteGUI extends JFrame implements ActionListener {
	JTextField name, zeit;
	JLabel name_label, zeit_label;
	JTextArea studList;
	JButton load, save, create, read, update, delete;
	static RanglisteDAO dao = new RanglisteDAO();
	
	private JPanel initButtons() {
		// buttons
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		buttonPanel.add(create = new JButton("Create"));
		create.addActionListener(this);
		buttonPanel.add(read = new JButton("Read"));
		read.addActionListener(this);
		buttonPanel.add(update = new JButton("Update"));
		update.addActionListener(this);
		buttonPanel.add(delete = new JButton("Delete"));
		delete.addActionListener(this);
		buttonPanel.add(load = new JButton("Load"));
		load.addActionListener(this);
		buttonPanel.add(save = new JButton("Save"));
		save.addActionListener(this);

		return buttonPanel;
	}
	

	
	private JPanel initInputPanel() {   
	    // textfields
		JPanel maskpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		name = new JTextField();
		name.setColumns(10);
		zeit = new JTextField();
		zeit.setColumns(10);
		name_label = new JLabel("Name:");
		zeit_label = new JLabel("Zeit:");

		maskpanel.add(name_label);
		maskpanel.add(name);
		maskpanel.add(zeit_label);
		maskpanel.add(zeit);

		return  maskpanel;

	}
	
	public void initComponents() {
		// add canvas
		JPanel panel = (JPanel) this.getContentPane();	
		panel.setBackground(Color.LIGHT_GRAY);
		// set layout of content
		panel.setLayout(new BorderLayout());
					
		// initMenu();
		JPanel buttonPanel = initButtons();
			
		// entry mask
		JPanel maskPanel = initInputPanel();

		// build top part of frame
		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.add(BorderLayout.NORTH, buttonPanel);
		topPanel.add(BorderLayout.CENTER, maskPanel);
		topPanel.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
		panel.add(BorderLayout.NORTH, topPanel);
		
		// build content part of frame
		JScrollPane sp = new JScrollPane();
		studList = new JTextArea();
		sp.setViewportView(studList);
		JPanel contentPanel = new JPanel(new BorderLayout()); 
		contentPanel.add(BorderLayout.CENTER, sp);	
			
		contentPanel.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
		panel.add(BorderLayout.CENTER, contentPanel);
	}
	
	Teilnehmer leseTeilnehmer() {
		Teilnehmer tn = new Teilnehmer();
		try {
			tn.setName(name.getText());
			String time = zeit.getText();
			tn.setH(Integer.parseInt(time.split(":")[0]));
			tn.setMin(Integer.parseInt(time.split(":")[1]));
			tn.setSec(Integer.parseInt(time.split(":")[2]));   
		} catch (Exception e) {
		    // intentionally; time might be missing    
		    tn.setH(0);
		    tn.setMin(0);
		    tn.setSec(0);
		}
		return tn;
	}
	
	boolean validate(Teilnehmer tn) {
	    if (tn.getName().equals("")) return false;
	    if (tn.getH() == 0 && tn.getMin() == 0 && tn.getSec() == 0) return false;
	    return true;
	}
    
	void teilnehmerAusgeben(Teilnehmer[] tn) {
		int i;
		studList.setText("");
		for (i = 0; i < tn.length; i++) {
			String output = tn[i].toString();
			studList.setText(studList.getText() + output);
		}  
	}
	
	private void setTeilnehmer(Teilnehmer s) {
		name.setText("" + s.getName());
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == load) {
				JFileChooser fc;
				fc = new JFileChooser();
				fc.showOpenDialog(this);
				String fn = fc.getSelectedFile().getAbsolutePath();
				dao.connect(fn);
				int max = dao.load();
				teilnehmerAusgeben(dao.read());
			} else if (e.getSource() == save) {
				JFileChooser fc;
				fc = new JFileChooser();
				fc.showSaveDialog(this);
				dao.connect(fc.getSelectedFile().getAbsolutePath());
				dao.save();
			} else if (e.getSource() == read) {
				teilnehmerAusgeben(dao.read());
			} else if (e.getSource() == create) {
				Teilnehmer tn = leseTeilnehmer();
				if (validate(tn)) dao.create(tn);
				teilnehmerAusgeben(dao.read());
			} else if (e.getSource() == update) {
				dao.update(leseTeilnehmer());
				teilnehmerAusgeben(dao.read());
			} else if (e.getSource() == delete) {
				dao.delete(leseTeilnehmer());
				teilnehmerAusgeben(dao.read());
			}
		}
		catch(DAOException ex){
			JOptionPane.showMessageDialog(this,ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static void main(String[] args) {
		// set look
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {}
		// Create application frame.
		RanglisteGUI ranglisteGUI = new RanglisteGUI();
		ranglisteGUI.setSize(450, 400);
		ranglisteGUI.setLocation(200, 200);
		ranglisteGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ranglisteGUI.initComponents();
		ranglisteGUI.setTitle("Rangliste");
		// show frame.
		ranglisteGUI.setVisible(true);
		dao.load();

	}

}
