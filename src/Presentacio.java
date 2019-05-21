package src;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Presentacio {

    private JButton button1;
    private JPanel panel1;

    public Presentacio() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int b = JOptionPane.showConfirmDialog(null,"Hace el pol el ranking?");
                if(b == 1)JOptionPane.showMessageDialog(null,"Yo creo que si");
                else JOptionPane.showMessageDialog(null,"Tienie pinta");
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Presentacio");
        frame.setContentPane(new Presentacio().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


    }
}
