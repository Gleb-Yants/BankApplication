package com.nameless.bank.frame;

import com.nameless.bank.logic.Account;
import com.nameless.bank.logic.ManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * Created by Глеб on 01.04.2016.
 */
public class TransactionDialog extends JDialog implements ActionListener {
    private static final int D_HEIGHT = 150;
    private final static int D_WIDTH = 450;
    private final static int L_X = 10;
    private final static int L_W = 100;
    private final static int C_W = 150;
    private Account accSender;
    private JLabel client = new JLabel();
    private JTextField money = new JTextField();
    private JComboBox<Account> accList;
    public TransactionDialog(Account acc, Vector<Account> accounts){
        accSender=acc;
        int id = acc.getId();
        Vector<Account> accounts1 = new Vector<Account>();
        for(Account a : accounts){
            if(a.getId()!=id){
                accounts1.add(a);
            }
        }
        setTitle("Добавление аккаунта");
        getContentPane().setLayout(new FlowLayout());
        getContentPane().setLayout(null);

        JLabel l = new JLabel("Отправитель:", JLabel.RIGHT);
        l.setBounds(L_X, 10, L_W, 20);
        getContentPane().add(l);
        client.setBounds(L_X + L_W + 10, 10, C_W, 20);
        client.setText(acc.getHolder().getName());
        getContentPane().add(client);

        l = new JLabel("Получатель:", JLabel.RIGHT);
        l.setBounds(L_X, 30, L_W, 20);
        getContentPane().add(l);
        accList = new JComboBox(new Vector<Account>(accounts1));
        accList.setBounds(L_X + L_W + 10, 30, C_W, 20);
        getContentPane().add(accList);

        l = new JLabel("Сумма:", JLabel.RIGHT);
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
        setBounds(((int) d.getWidth() - TransactionDialog.D_WIDTH) / 2, ((int) d.getHeight() - TransactionDialog.D_HEIGHT) / 2,
                TransactionDialog.D_WIDTH, TransactionDialog.D_HEIGHT);
    }

    public void actionPerformed(ActionEvent e) {
        JButton src = (JButton) e.getSource();
        if (src.getName().equals("OK")) {
            try {
                if(Integer.parseInt(money.getText())<=accSender.getMoney()){
                    ManagementSystem.getInstance().transact(accSender, (Account) accList.getSelectedItem(), Integer.parseInt(money.getText()));
                }else{
                    JOptionPane.showMessageDialog(TransactionDialog.this,
                            "Недостаточно средств на счете");
                }
            } catch (Exception sql_e) {
                JOptionPane.showMessageDialog(this, sql_e.getMessage());
            }setVisible(false);
        }
        if (src.getName().equals("Cancel")) {
            setVisible(false);
        }
    }
}
