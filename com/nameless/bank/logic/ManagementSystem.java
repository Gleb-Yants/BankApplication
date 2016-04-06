package com.nameless.bank.logic;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ManagementSystem {
    private static Connection con;
    private static ManagementSystem instance;
    private static DataSource dataSource;

    private ManagementSystem() {
    }

    public static synchronized ManagementSystem getInstance() {
        if (instance == null) {
            try {
                instance = new ManagementSystem();
               Context ctx = new InitialContext();
               instance.dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/CitizensDS");
               con = dataSource.getConnection();
                /*
                для запуска графического интерфейса
                Class.forName("com.mysql.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3306/citizens";
                con = DriverManager.getConnection(url, "****", "****");
                */
            } catch (NamingException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
                //catch(ClassNotFoundException e){}
        }
        return instance;
    }

    public Client getClientById(int clientId) throws SQLException {
        Client client = null;
        PreparedStatement stmt = con.prepareStatement("SELECT client_id, name, address FROM clients WHERE client_id = ?");
        stmt.setInt(1, clientId);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            client = new Client(rs);
        }
        rs.close();
        stmt.close();
        return client;
    }

    public Account getAccountById(int accountId) throws SQLException {
        Account account = null;
        PreparedStatement stmt = con.prepareStatement("SELECT account_id, holder_id, money FROM accounts WHERE account_id = ?");
        stmt.setInt(1, accountId);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            account = new Account(rs);
        }
        rs.close();
        stmt.close();
        return account;
    }

    public ArrayList<Client> getAllClients() throws SQLException {
      ArrayList<Client> clients = new ArrayList<Client>();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(
                    "SELECT client_id, name, address FROM clients ");
            while (rs.next()) {
                Client st = new Client(rs);
                clients.add(st);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }

        return clients;
    }
    public ArrayList<Account> getAllAccounts() throws SQLException {
        ArrayList<Account> accounts = new ArrayList<Account>();
        Statement stmt = null;
        ResultSet rs = null;
        PreparedStatement  stmt2 = null;
        ResultSet rs2 = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(
                    "SELECT account_id, holder_id, money FROM accounts ");
            stmt2 = con.prepareStatement(
                    "SELECT client_id, name, address FROM clients WHERE client_id=?");
            while (rs.next()) {
                stmt2.setInt(1, rs.getInt(2));
                rs2 = stmt2.executeQuery();
                if(rs2.next()){
                Account st = new Account(rs.getInt(1), new Client(rs2),rs.getInt(3));
                accounts.add(st);}
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (rs2 != null) {
                rs2.close();
            }
            if (stmt2 != null) {
                stmt2.close();
            }
        }

        return accounts;
    }

    public ArrayList<Account> getClientAccounts(Client cl) throws SQLException{
        ArrayList<Account> clientAccounts = new ArrayList<Account>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        PreparedStatement  stmt2 = null;
        ResultSet rs2 = null;
        try {
            stmt = con.prepareStatement("SELECT account_id, holder_id, money FROM accounts WHERE holder_id=?");
            stmt.setInt(1, cl.getId());
            rs = stmt.executeQuery();
            stmt2 = con.prepareStatement(
                    "SELECT client_id, name, address FROM clients WHERE client_id=?");
            while (rs.next()) {
                stmt2.setInt(1, rs.getInt(2));
                rs2 = stmt2.executeQuery();
                if(rs2.next()){
                Account st = new Account(rs.getInt(1), new Client(rs2),rs.getInt(3));
                clientAccounts.add(st);}
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (rs2 != null) {
                rs2.close();
            }
            if (stmt2 != null) {
                stmt2.close();
            }
        }
        return clientAccounts;
    }

    public void addClient(Client client) throws SQLException{
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(
                    "INSERT INTO clients " +
                            "(name, address)" +
                            "VALUES (?, ?)");
            stmt.setString(1, client.getName());
            stmt.setString(2, client.getAddress());
            stmt.execute();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void addAccount(Account account) throws SQLException{
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(
                    "INSERT INTO accounts " +
                            "(holder_id, money)" +
                            "VALUES (?, ?)");
            stmt.setInt(1, account.getHolder().getId());
            stmt.setInt(2, account.getMoney());
            stmt.execute();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void removeAccount(Account account) throws SQLException{
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(
                    "DELETE FROM accounts WHERE account_id=?");
            stmt.setInt(1, account.getId());
            stmt.execute();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void transact(Account from, Account to, int amount) throws SQLException{
        if(from.getMoney()>=amount){
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(
                    "INSERT INTO transactions " +
                            "(transfrom_account_id, transto_account_id, amount)" +
                            "VALUES (?, ?, ?)");
            stmt.setInt(1, from.getId());
            stmt.setInt(2, to.getId());
            stmt.setInt(3, amount);
            stmt.execute();
            stmt = con.prepareStatement(
                    "UPDATE accounts SET " +
                            "money=? WHERE account_id=?");
            stmt.setInt(1, from.getMoney()-amount);
            stmt.setInt(2, from.getId());
            stmt.execute();
            stmt = con.prepareStatement(
                    "UPDATE accounts SET " +
                            "money=? WHERE account_id=?");
            stmt.setInt(1, to.getMoney()+amount);
            stmt.setInt(2, to.getId());
            stmt.execute();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }}else{
            System.out.println("На вашем счету недостаточно денег для проведения данной операции");
        }
    }

    public ArrayList<Transaction> getTransactions(Account acc) throws SQLException{
        ArrayList<Transaction> transactions = new ArrayList<Transaction>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        PreparedStatement  stmt2 = null;
        ResultSet rs2 = null;
        PreparedStatement  stmt3 = null;
        ResultSet rs3 = null;
        PreparedStatement  stmt4 = null;
        ResultSet rs4 = null;
        PreparedStatement  stmt5 = null;
        ResultSet rs5 = null;
        try {
            stmt = con.prepareStatement("SELECT transaction_id, transfrom_account_id, transto_account_id, amount FROM transactions WHERE transfrom_account_id=? OR transto_account_id=?");
            stmt.setInt(1, acc.getId());
            stmt.setInt(2, acc.getId());
            rs = stmt.executeQuery();
            while (rs.next()) {
                stmt2 = con.prepareStatement(
                        "SELECT account_id, holder_id, money FROM accounts WHERE account_id=?");
                stmt2.setInt(1, rs.getInt(2));
                rs2 = stmt2.executeQuery();
                if(rs2.next()){
                stmt3 = con.prepareStatement(
                        "SELECT client_id, name, address FROM clients WHERE client_id=?");
                stmt3.setInt(1, rs2.getInt(2));
                rs3 = stmt3.executeQuery();}

                stmt4 = con.prepareStatement(
                        "SELECT account_id, holder_id, money FROM accounts WHERE account_id=?");
                stmt4.setInt(1, rs.getInt(3));
                rs4 = stmt4.executeQuery();
                if(rs4.next()){
                stmt5 = con.prepareStatement(
                        "SELECT client_id, name, address FROM clients WHERE client_id=?");
                stmt5.setInt(1, rs4.getInt(2));
                rs5 = stmt5.executeQuery();}

                if(rs3.next()&rs5.next()){
                Transaction st = new Transaction(rs.getInt(1), new Account(rs2.getInt(1), new Client(rs3), rs2.getInt(3)), new Account(rs4.getInt(1), new Client(rs5), rs4.getInt(3)), rs.getInt(4));
                transactions.add(st);}
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (rs2 != null) {
                rs2.close();
            }
            if (stmt2 != null) {
                stmt2.close();
            }
            if (rs3 != null) {
                rs3.close();
            }
            if (stmt3 != null) {
                stmt3.close();
            }
            if (rs4 != null) {
                rs4.close();
            }
            if (stmt4 != null) {
                stmt4.close();
            }
            if (rs5 != null) {
                rs5.close();
            }
            if (stmt5 != null) {
                stmt5.close();
            }
        }
        return transactions;
    }
}
