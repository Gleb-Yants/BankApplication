package com.nameless.bank.frame;

import com.nameless.bank.logic.Client;
import com.nameless.bank.logic.ManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Глеб on 01.04.2016.
 */
public class ClientDialog extends JDialog implements ActionListener {
    private static final int D_HEIGHT = 150;
    private final static int D_WIDTH = 450;
    private final static int L_X = 10;
    private final static int L_W = 100;
    private final static int C_W = 150;
    private JTextField name = new JTextField();
    private JTextField address = new JTextField();
    public ClientDialog(){
        setTitle("Добавление клиента");
        getContentPane().setLayout(new FlowLayout());
        getContentPane().setLayout(null);

        JLabel l = new JLabel("Имя:", JLabel.RIGHT);
        l.setBounds(L_X, 30, L_W, 20);
        getContentPane().add(l);
        name.setBounds(L_X + L_W + 10, 30, C_W, 20);
        getContentPane().add(name);

        l = new JLabel("Адрес:", JLabel.RIGHT);
        l.setBounds(L_X, 50, L_W, 20);
        getContentPane().add(l);
        address.setBounds(L_X + L_W + 10, 50, C_W, 20);
        getContentPane().add(address);

        JButton btnOk = new JButton("OK");
        btnOk.setName("OK");
        btnOk.addActionListener(this);
        btnOk.setBounds(L_X + L_W + C_W + 10 + 50, 10, 100, 25);
        getContentPane().add(btnOk);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.setName("Cancel");
        btnCancel.addActionListener(this);
        btnCancel.setBounds(L_X + L_W + C_W + 10 + 50, 40, 100, 25);
        getContentPane().add(btnCancel);

        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(((int) d.getWidth() - ClientDialog.D_WIDTH) / 2, ((int) d.getHeight() - ClientDialog.D_HEIGHT) / 2,
                ClientDialog.D_WIDTH, ClientDialog.D_HEIGHT);
    }

    public Client getClient(){
        Client cl = new Client();
        cl.setName(name.getText());
        cl.setAddress(address.getText());
        return cl;
    }

    public void actionPerformed(ActionEvent e) {
        JButton src = (JButton) e.getSource();
        if (src.getName().equals("OK")) {
            try {
                ManagementSystem.getInstance().addClient(getClient());
            } catch (Exception sql_e) {
                JOptionPane.showMessageDialog(this, sql_e.getMessage());
            }setVisible(false);
        }
        if (src.getName().equals("Cancel")) {
            setVisible(false);
        }
    }
}
