package com.nameless.bank.web;

import com.nameless.bank.logic.Account;
import com.nameless.bank.logic.Client;
import com.nameless.bank.logic.ManagementSystem;
import com.nameless.bank.web.forms.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Глеб on 05.04.2016.
 */
public class AccountFrameServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("windows-1251");
        int answer = 0;
        try {
            answer = checkAction(req);
        } catch (SQLException sql_e) {
            throw new IOException(sql_e.getMessage());
        }
        if (answer == 1) {
            try{
            AccountAddFrameForm adForm = new AccountAddFrameForm();
            adForm.setClientId(Integer.parseInt(req.getParameter("clientId")));
            Client cl = ManagementSystem.getInstance().getClientById(Integer.parseInt(req.getParameter("clientId")));
                adForm.setClientName(cl.getName());
                adForm.setMoney(0);
            req.setAttribute("form", adForm);
            getServletContext().getRequestDispatcher("/AccountAddFrame.jsp").forward(req, resp);}
            catch(SQLException ex){ex.printStackTrace();}
            return;
        }
        if (answer == 2) {
            if (req.getParameter("accountId") != null) {
                try{
                    TransactionsFrameForm tForm = new TransactionsFrameForm();
                    Account acc = ManagementSystem.getInstance().getAccountById(Integer.parseInt(req.getParameter("accountId")));
                    tForm.setTransactions(ManagementSystem.getInstance().getTransactions(acc));
                    req.setAttribute("form", tForm);
                    getServletContext().getRequestDispatcher("/TransactionsFrame.jsp").forward(req, resp);
                }catch(SQLException ex){ex.printStackTrace();}
                return;}
        }
        if (answer == 3) {
            if (req.getParameter("accountId") != null) {
                try{
                    int accId = Integer.parseInt(req.getParameter("accountId"));
                    TransactFrameForm tForm = new TransactFrameForm();
                    Account acc = ManagementSystem.getInstance().getAccountById(Integer.parseInt(req.getParameter("accountId")));
                    tForm.setFromName(acc.getHolder().getName());
                    Collection<Account> accounts = new ArrayList<Account>();
                    for(Account a : ManagementSystem.getInstance().getAllAccounts()){
                        if(a.getId()!=accId){
                            accounts.add(a);
                        }
                    }
                    tForm.setTo(accounts);
                    tForm.setFromId(Integer.parseInt(req.getParameter("accountId")));
                    tForm.setSum(0);
                    req.setAttribute("form", tForm);
                    getServletContext().getRequestDispatcher("/TransactFrame.jsp").forward(req, resp);
                }catch(SQLException ex){ex.printStackTrace();}
                return;}
        }

        MainFrameForm form = new MainFrameForm();
        try {
            Collection clients = ManagementSystem.getInstance().getAllClients();
            form.setClients(clients);
        } catch (SQLException sql_e) {
            throw new IOException(sql_e.getMessage());
        }

        req.setAttribute("form", form);
        getServletContext().getRequestDispatcher("/MainFrame.jsp").forward(req, resp);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }

    private int checkAction(HttpServletRequest req) throws SQLException {
        if (req.getParameter("AddAcc") != null) {
            return 1;
        }
        if (req.getParameter("Remove") != null) {
            if (req.getParameter("accountId") != null) {
                Account acc = new Account();
                acc.setId(Integer.parseInt(req.getParameter("accountId")));
                ManagementSystem.getInstance().removeAccount(acc);
            }
            return 0;
        }
        if (req.getParameter("Transactions") != null) {
            return 2;
        }
        if (req.getParameter("Transact") != null) {
            return 3;
        }
        if (req.getParameter("Cancel") != null) {
            return 0;
        }
        return 0;
    }

}
