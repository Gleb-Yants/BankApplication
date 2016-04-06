package com.nameless.bank.frame;

import com.nameless.bank.logic.Account;
import com.nameless.bank.logic.Client;
import com.nameless.bank.logic.ManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Глеб on 01.04.2016.
 */
public class AccountDialog extends JDialog implements ActionListener {
    private static final int D_HEIGHT = 150;
    private final static int D_WIDTH = 450;
    private final static int L_X = 10;
    private final static int L_W = 100;
    private final static int C_W = 150;
    private Client holder;
    private JLabel client = new JLabel();
    private JTextField money = new JTextField();
    public AccountDialog(Client cl){
        holder=cl;
        setTitle("Добавление аккаунта");
        getContentPane().setLayout(new FlowLayout());
        getContentPane().setLayout(null);

        JLabel l = new JLabel("Клиент:", JLabel.RIGHT);
        l.setBounds(L_X, 30, L_W, 20);
        getContentPane().add(l);
        client.setBounds(L_X + L_W + 10, 30, C_W, 20);
        client.setText(cl.getName());
        getContentPane().add(client);

        l = new JLabel("Вклад", JLabel.RIGHT);
        l.setBounds(L_X, 50, L_W, 20);
        getContentPane().add(l);
        money.setBounds(L_X + L_W + 10, 50, C_W, 20);
        getContentPane().add(money);

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
        setBounds(((int) d.getWidth() - AccountDialog.D_WIDTH) / 2, ((int) d.getHeight() - AccountDialog.D_HEIGHT) / 2,
                AccountDialog.D_WIDTH, AccountDialog.D_HEIGHT);
    }

    public Account getAccount(){
        Account acc = new Account();
        acc.setHolder(holder);
        acc.setMoney(Integer.parseInt(money.getText()));
        return acc;
    }

    public void actionPerformed(ActionEvent e) {
        JButton src = (JButton) e.getSource();
        if (src.getName().equals("OK")) {
            try {
                ManagementSystem.getInstance().addAccount(getAccount());
            } catch (Exception sql_e) {
                JOptionPane.showMessageDialog(this, sql_e.getMessage());
            }setVisible(false);
        }
        if (src.getName().equals("Cancel")) {
            setVisible(false);
        }
    }
}
