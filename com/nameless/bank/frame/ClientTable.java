package com.nameless.bank.frame;

import com.nameless.bank.logic.Client;

import javax.swing.table.AbstractTableModel;
import java.util.Vector;

/**
 * Created by √ÎÂ· on 28.03.2016.
 */
public class ClientTable extends AbstractTableModel {
    private Vector clients;

    public ClientTable(Vector clients) {
        this.clients = clients;
    }

    public int getRowCount() {
        if (clients != null) {
            return clients.size();
        }
        return 0;
    }

    public int getColumnCount() {
        return 3;
    }

    public String getColumnName(int column) {
        String[] colNames = {"ID", "‘»Œ", "¿‰ÂÒ"};
        return colNames[column];
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        if (clients != null) {
            Client cl = (Client) clients.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return cl.getId();
                case 1:
                    return cl.getName();
                case 2:
                    return cl.getAddress();
            }
        }
        return null;
    }

    public Client getClient(int rowIndex) {
        if (clients != null) {
            if (rowIndex < clients.size() && rowIndex >= 0) {
                return (Client) clients.get(rowIndex);
            }
        }
        return null;
    }

}
