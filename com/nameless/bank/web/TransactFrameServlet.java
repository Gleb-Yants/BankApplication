package com.nameless.bank.web;

import com.nameless.bank.logic.Account;
import com.nameless.bank.logic.ManagementSystem;
import com.nameless.bank.web.forms.MainFrameForm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Collection;

/**
 * Created by Глеб on 06.04.2016.
 */
public class TransactFrameServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("windows-1251");
        if (req.getParameter("OK") != null) {
            try {
                if(Integer.parseInt(req.getParameter("sum"))>=ManagementSystem.getInstance().getAccountById(Integer.parseInt(req.getParameter("fromId"))).getMoney())
                transact(req);
            } catch (SQLException sql_e) {
                sql_e.printStackTrace();
                throw new IOException(sql_e.getMessage());
            } catch (ParseException p_e) {
                throw new IOException(p_e.getMessage());
            }
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


    private void transact(HttpServletRequest req) throws SQLException, ParseException {
        Account from = ManagementSystem.getInstance().getAccountById(Integer.parseInt(req.getParameter("fromId")));
        Account to = ManagementSystem.getInstance().getAccountById(Integer.parseInt(req.getParameter("accTo")));
        int sum = Integer.parseInt(req.getParameter("sum"));
        ManagementSystem.getInstance().transact(from, to, sum);
    }

}
