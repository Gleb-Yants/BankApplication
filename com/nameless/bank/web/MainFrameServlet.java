package com.nameless.bank.web;

import com.nameless.bank.logic.Client;
import com.nameless.bank.logic.ManagementSystem;
import com.nameless.bank.web.forms.AccountFrameForm;
import com.nameless.bank.web.forms.ClientFrameForm;
import com.nameless.bank.web.forms.MainFrameForm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by Глеб on 05.04.2016.
 */
public class MainFrameServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        int answer = 0;
        try {
            answer = checkAction(req);
        } catch (SQLException sql_e) {
            throw new IOException(sql_e.getMessage());
        }
        if (answer == 1) {
            ClientFrameForm cForm = new ClientFrameForm();
            req.setAttribute("client", cForm);
                getServletContext().getRequestDispatcher("/ClientFrame.jsp").forward(req, resp);
                return;
        }
        if (answer == 2) {
            if (req.getParameter("clientId") != null) {
                try{
                int clId = Integer.parseInt(req.getParameter("clientId"));
            AccountFrameForm aForm = new AccountFrameForm();
                    aForm.setClientId(Integer.parseInt(req.getParameter("clientId")));
                    aForm.setAccounts(ManagementSystem.getInstance().getClientAccounts(new Client(clId,"","")));
            req.setAttribute("form", aForm);
            getServletContext().getRequestDispatcher("/AccountFrame.jsp").forward(req, resp);
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

    private int checkAction(HttpServletRequest req) throws SQLException {
        if (req.getParameter("Add") != null) {
            return 1;
        }
        if (req.getParameter("Accounts") != null) {
            return 2;
        }
        return 0;
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
}
