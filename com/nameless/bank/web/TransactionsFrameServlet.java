package com.nameless.bank.web;

import com.nameless.bank.logic.Client;
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
 * Created by Глеб on 05.04.2016.
 */
public class TransactionsFrameServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("windows-1251");
        if (req.getParameter("Cancel") != null) {
        MainFrameForm form = new MainFrameForm();
        try {
            Collection clients = ManagementSystem.getInstance().getAllClients();
            form.setClients(clients);
        } catch (Exception sql_e) {
            throw new IOException(sql_e.getMessage());
        }

        req.setAttribute("form", form);
        getServletContext().getRequestDispatcher("/MainFrame.jsp").forward(req, resp);
    }}

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }


}
