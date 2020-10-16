import javax.management.StringValueExp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class afvink6 extends JFrame implements ActionListener {

    private final JFileChooser fc = new JFileChooser();
    private JButton b1, b2;
    private JPanel p1;
    private JTextArea t1;


    public static void main(String[] args) {
        afvink6 f = new afvink6();
        f.setSize(500, 200);
        f.createGUI();
        f.setVisible(true);
    }

    void createGUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new FlowLayout());
        t1 = new JTextArea("");
        t1.setPreferredSize(new Dimension(500, 150));
        b1 = new JButton("blader");
        window.add(b1);
        window.add(t1);
        b1.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            int returnVal = fc.showOpenDialog(afvink6.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                try {
                    StringBuilder fileInhoud = readFile();
                    calc(fileInhoud);
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }

            }
        }

    }

    public StringBuilder readFile() throws FileNotFoundException {
        StringBuilder fileInhoud = new StringBuilder();
        File file = fc.getSelectedFile();
        try (Scanner sc = new Scanner(fc.getSelectedFile())) {
            while (sc.hasNextLine()) {
                fileInhoud.append(sc.nextLine().strip());

            }
        }
        return fileInhoud;

    }

    public void calc(StringBuilder fileInhoud) {
        String aa = "AFILMPWVRNDCQEGHKSTY";
        String apolair = "AFILMPWV";
        String polair = "RNDCQEGHKSTY";
        float pol = 0;
        float apol = 0;
        boolean bevataa = true;
        for (int z = 0; z < fileInhoud.length(); z++) {
            char w = fileInhoud.charAt(z);
            String v = String.valueOf(w);
            if (aa.indexOf(v) != -1) {
                if (polair.indexOf(v) != -1) {
                    pol++;
                } else if (apolair.indexOf(v) != -1) {
                    apol++;
                }
            } else {
                t1.setText(v + " is geen aminozuur");
                break;

            }
        }
        float polpr = (pol/fileInhoud.length()*100);
        float apolpr = (apol/fileInhoud.length()*100);
        t1.setText("de sequentie is "+fileInhoud.length()+" characters lang"+"\n"+
                "de sequentie is "+polpr+"% polair"+"\n"+"de sequentie is "+apolpr+"% apolair");
    }
}

// B J O  U X Z