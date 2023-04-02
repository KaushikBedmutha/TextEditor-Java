import javax.swing.*;
import java.io.*;
import java.util.Scanner;
import java.awt.event.*;

class editor extends JFrame implements ActionListener {

    JTextArea t;

    JFrame f;

    editor() {

        f = new JFrame("editor");

        {
        }

        t = new JTextArea();

        JMenuBar mb = new JMenuBar();

        JMenu m1 = new JMenu("File");

        JMenuItem mi1 = new JMenuItem("New");
        JMenuItem mi3 = new JMenuItem("Save");

        mi1.addActionListener(this);

        mi3.addActionListener(this);

        m1.add(mi1);

        m1.add(mi3);

        JMenu m2 = new JMenu("Edit");

        JMenuItem mi4 = new JMenuItem("cut");
        JMenuItem mi5 = new JMenuItem("copy");
        JMenuItem mi6 = new JMenuItem("paste");
        JMenuItem mi7 = new JMenuItem("ChangeCase");

        mi4.addActionListener(this);
        mi5.addActionListener(this);
        mi6.addActionListener(this);
        mi7.addActionListener(this);
        m2.add(mi4);
        m2.add(mi5);
        m2.add(mi6);
        m2.add(mi7);
        JMenuItem mc = new JMenuItem("close");
        mc.addActionListener(this);
        mb.add(m1);
        mb.add(m2);
        mb.add(mc);
        f.setJMenuBar(mb);
        f.add(t);
        f.setSize(500, 500);
        f.setVisible(true);
    }

    // If a button is pressed
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if (s.equals("cut")) {
            t.cut();
        } else if (s.equals("copy")) {
            t.copy();
        } else if (s.equals("paste")) {
            t.paste();
        } else if (s.equals("Change Case")) {
            Scanner sc = new Scanner(System.in);
            String str1 = sc.nextLine();
            StringBuffer newStr = new StringBuffer(str1);

            for (int i = 0; i < str1.length(); i++) {

                if (Character.isLowerCase(str1.charAt(i))) {

                    newStr.setCharAt(i, Character.toUpperCase(str1.charAt(i)));
                }

                else if (Character.isUpperCase(str1.charAt(i))) {
                    newStr.setCharAt(i, Character.toLowerCase(str1.charAt(i)));
                }
            }
            System.out.println("String after case conversion : " + newStr);
        }

        else if (s.equals("Save")) {

            JFileChooser j = new JFileChooser("f:");

            int r = j.showSaveDialog(null);
            if (r == JFileChooser.APPROVE_OPTION) {

                File fi = new File(j.getSelectedFile().getAbsolutePath());
                try {

                    FileWriter wr = new FileWriter(fi, false);

                    BufferedWriter w = new BufferedWriter(wr);

                    w.write(t.getText());
                    w.close();
                } catch (Exception evt) {
                    JOptionPane.showMessageDialog(f, evt.getMessage());
                }
            }

            else
                JOptionPane.showMessageDialog(f, "the user cancelled the operation");
        }

        else if (s.equals("New")) {
            t.setText("");
        } else if (s.equals("close")) {
            f.setVisible(false);
        }
    }

    public static void main(String args[]) {
        editor e = new editor();
    }
}