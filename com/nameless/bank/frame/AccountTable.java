package com.nameless.bank.frame;

import com.nameless.bank.logic.Account;

import javax.swing.table.AbstractTableModel;
import java.util.Vector;

/**
 * Created by Глеб on 28.03.2016.
 */
public class AccountTable extends AbstractTableModel {
    private Vector accounts;

    public AccountTable(Vector accounts) {
        this.accounts = accounts;
    }

    public int getRowCount() {
        if (accounts != null) {
            return accounts.size();
        }
        return 0;
    }

    public int getColumnCount() {
        return 3;
    }

    public String getColumnName(int column) {
        String[] colNames = {"ID счета","Держатель счета", "Количество денег (руб.)"};
        return colNames[column];
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        if (accounts != null) {
            Account acc = (Account) accounts.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return acc.getId();
                case 1:
                    return acc.getHolder().getName();
                case 2:
                    return acc.getMoney();
            }
        }
        return null;
    }

    public Account getAccount(int rowIndex) {
        if (accounts != null) {
            if (rowIndex < accounts.size() && rowIndex >= 0) {
                return (Account) accounts.get(rowIndex);
            }
        }
        return null;
    }
}
