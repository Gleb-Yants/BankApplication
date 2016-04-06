package com.nameless.bank.frame;

import com.nameless.bank.logic.Account;
import com.nameless.bank.logic.Client;
import com.nameless.bank.logic.ManagementSystem;
import com.nameless.bank.logic.Transaction;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

/**
 * Created by Глеб on 16.03.2016.
 */
public class SystemInterface extends JFrame implements ActionListener, ListSelectionListener {
    private static final String ADD_CL = "addClient";
    private static final String ADD_ACC = "addAccount";
    private static final String REM_ACC = "removeAccount";
    private static final String TRANS = "transact";
    private static final String REPORT = "report";

    private ManagementSystem ms = ManagementSystem.getInstance();
    private JTable clList;
    private JTable accList;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    SystemInterface si = new SystemInterface();
                    si.setDefaultCloseOperation(EXIT_ON_CLOSE);
                    si.setVisible(true);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public SystemInterface() throws Exception{
        getContentPane().setLayout(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Меню");
        JMenuItem menuItem = new JMenuItem("Отчеты");
        menuItem.setName(REPORT);
        menu.add(menuItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);
        menuItem.addActionListener(this);

        JPanel left = new JPanel();
        left.setLayout(new BorderLayout());
        left.add(new JLabel("Граждане"), BorderLayout.NORTH);
        Vector<Client> cl = new Vector<Client>(ms.getAllClients());
        clList = new JTable(new ClientTable(cl));
        clList.getSelectionModel().addListSelectionListener(this);
        clList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        left.add(new JScrollPane(clList), BorderLayout.WEST);
        left.setBorder(new BevelBorder(BevelBorder.LOWERED));
        JPanel bot = new JPanel();
        bot.setLayout(new FlowLayout());
        JButton btnAdCl = new JButton("Добавить клиента");
        btnAdCl.setName(ADD_CL);
        btnAdCl.addActionListener(this);
        bot.add(btnAdCl);
        left.add(bot, BorderLayout.SOUTH);

        JPanel right = new JPanel();
        right.setLayout(new BorderLayout());
        right.add(new JLabel("Счета"), BorderLayout.NORTH);
        accList = new JTable();
        right.add(new JScrollPane(accList), BorderLayout.CENTER);
        right.setBorder(new BevelBorder(BevelBorder.LOWERED));
        JPanel bot2 = new JPanel();
        bot2.setLayout(new FlowLayout());
        JButton btnAdAc = new JButton("Добавить счет");
        btnAdAc.setName(ADD_ACC);
        btnAdAc.addActionListener(this);
        JButton btnReAc = new JButton("Удалить счет");
        btnReAc.setName(REM_ACC);
        btnReAc.addActionListener(this);
        JButton btnTr = new JButton("Перевод денег");
        btnTr.setName(TRANS);
        btnTr.addActionListener(this);
        bot2.add(btnAdAc);
        bot2.add(btnReAc);
        bot2.add(btnTr);
        right.add(bot2, BorderLayout.SOUTH);

        getContentPane().add(right,BorderLayout.EAST);
        getContentPane().add(left, BorderLayout.WEST);
        setBounds(100, 100, 970, 500);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof Component) {
            Component c = (Component) e.getSource();
            if (c.getName().equals(ADD_ACC)) {
                addAcc();
            }
            if (c.getName().equals(ADD_CL)) {
                addCl();
            }
            if (c.getName().equals(REM_ACC)) {
                remAcc();
            }
            if (c.getName().equals(TRANS)) {
                trans();
            }
            if (c.getName().equals(REPORT)) {
                showTrans();
            }
        }

    }
    public void valueChanged(ListSelectionEvent event) {
                accountRefresh();
    }
    private void accountRefresh(){
        SwingUtilities.invokeLater(new Runnable(){
            public void run() {
        try {
            ClientTable tbl = (ClientTable) clList.getModel();
            Client selCl = tbl.getClient(clList.getSelectedRow());
            if (selCl != null) {
                Vector<Account> acc = new Vector<Account>(ms.getClientAccounts(selCl));
                accList.setModel(new AccountTable(acc));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }}});
    }
    private void addCl(){
        SwingUtilities.invokeLater(new Runnable(){
            public void run() {
                ClientDialog cd = new ClientDialog();
                cd.setModal(true);
                cd.setVisible(true);
                try {
                    Vector<Client> cl = new Vector<Client>(ms.getAllClients());
                    clList.setModel(new ClientTable(cl));
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }});
    }
    private void addAcc(){
        if(clList.getSelectedRow() >= 0){
        SwingUtilities.invokeLater(new Runnable(){
            public void run() {
                    ClientTable tbl = (ClientTable) clList.getModel();
                    Client selCl = tbl.getClient(clList.getSelectedRow());
                AccountDialog ad = new AccountDialog(selCl);
                ad.setModal(true);
                ad.setVisible(true);
                accountRefresh();
            }});}else{
            JOptionPane.showMessageDialog(SystemInterface.this,
                    "Необходимо выделить клиента");
        }
    }

    private void remAcc(){
        if (accList != null) {
            AccountTable acTa = (AccountTable) accList.getModel();
            if (accList.getSelectedRow() >= 0) {
                if (JOptionPane.showConfirmDialog(SystemInterface.this,
                        "Вы хотите удалить аккаунт?", "Удаление аккаунта",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    Account ac = acTa.getAccount(accList.getSelectedRow());
                    try {
                        ms.removeAccount(ac);
                        accountRefresh();
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(SystemInterface.this, e.getMessage());
                    }
                }
            }
            else {
                JOptionPane.showMessageDialog(SystemInterface.this, "Необходимо выделить аккаунт");
            }
        }
    }
    private void trans(){
        if(clList.getSelectedRow() >= 0 & accList.getSelectedRow() >= 0){
            SwingUtilities.invokeLater(new Runnable(){
                public void run() {
                    AccountTable tbl = (AccountTable) accList.getModel();
                    Account selAcc = tbl.getAccount(accList.getSelectedRow());
                    try{
                    Vector<Account> acc = new Vector<Account>(ms.getAllAccounts());
                    TransactionDialog td = new TransactionDialog(selAcc, acc);
                        td.setModal(true);
                        td.setVisible(true);
                    accountRefresh();}catch(SQLException ex){ex.printStackTrace();}
                }});}else{
            JOptionPane.showMessageDialog(SystemInterface.this,
                    "Необходимо выделить аккаунт");
        }
    }
    private void showTrans(){
        if(clList.getSelectedRow() >= 0 & accList.getSelectedRow() >= 0){
        try {
            AccountTable tbl = (AccountTable) accList.getModel();
            Account selAc = tbl.getAccount(accList.getSelectedRow());
            Vector<Transaction> acc = new Vector<Transaction>(ms.getTransactions(selAc));
            JFrame  transactions = new JFrame ();
            transactions.setName("Список транзакций");
            JList translist = new JList(acc);
            transactions.add(translist);
            transactions.setVisible(true);
            transactions.setSize(750,200);
        }catch(SQLException ex){ex.printStackTrace();}
        }else{
            JOptionPane.showMessageDialog(SystemInterface.this,
                    "Необходимо выделить аккаунт");
        }
    }
}
