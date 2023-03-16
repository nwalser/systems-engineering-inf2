import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RanglisteGUI extends javax.swing.JFrame{
    static final int MAXT = 100;
    static String filename = "Rangliste.csv";
    static int maxT = 0;
    static int res = 0;
    static Teilnehmer[] teilnehmer = new Teilnehmer[MAXT];
    private JButton ladenButton;
    private JButton speichernButton;
    private JButton sortierenButton;
    private JButton erfassenButton;
    private JButton ausgebenButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextArea textArea1;
    private JPanel panel_gui;




    public RanglisteGUI()  {

        ladenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                res = Rangliste.teilnehmerLaden(filename, teilnehmer);
                zeigeResultat("Teilnehmer geladen", res);
                if (res >= 0) {
                    maxT = res;
                }
            }
        });
        erfassenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Teilnehmer tn = leseTeilnehmer();
                if (tn != null) {
                    teilnehmer[maxT] = leseTeilnehmer();
                    maxT++;
                    zeigeResultat("Teilnehmer erfasst", 1);
                } else {
                    zeigeResultat("", -582);
                }
            }
        });
        ausgebenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                teilnehmerAusgeben(maxT,teilnehmer);
            }
        });
        sortierenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Rangliste.teilnehmerSortieren(maxT, teilnehmer);
            }
        });
        speichernButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                res = Rangliste.teilnehmerSpeichern(filename, maxT, teilnehmer);
                zeigeResultat("Teilnehmer gespeichert", res);
                if (res >= 0) {
                    maxT = res;
                }
            }
        });
    }

    void teilnehmerAusgeben(int maxT, Teilnehmer[] tn) {
        int i;
        this.textArea1.setText("");
        for (i = 0; i < maxT; i++) {
            String output = String.format("%-22s\t%02d:%02d:%02d\n", tn[i].name,
                    tn[i].h, tn[i].min, tn[i].sec);
            this.textArea1.setText(this.textArea1.getText() + output);
        }
    }


    Teilnehmer leseTeilnehmer() {
        Teilnehmer tn = new Teilnehmer();
        try {
            tn.name = this.textField1.getText();
            String time = this.textField2.getText();
            tn.h = Integer.parseInt(time.split(":")[0]);
            tn.min = Integer.parseInt(time.split(":")[1]);
            tn.sec = Integer.parseInt(time.split(":")[2]);
        }
        catch (Exception e) {
            tn = null;
        }
        return tn;
    }


    void zeigeResultat(String msg, int res) {
        if (res >= 0) {
            JOptionPane.showMessageDialog(this, res + " " + msg, "Info", JOptionPane.PLAIN_MESSAGE);
        } else {
            //JOptionPane.showMessageDialog(this, "Errorcode: " + (-res) + "\n" + msg, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); //Windows Look and feel
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {

        }
        JFrame frame = new JFrame("RanglisteGUI");
        frame.setContentPane(new RanglisteGUI().panel_gui);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


    }


}
