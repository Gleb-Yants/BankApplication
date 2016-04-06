package com.nameless.bank.logic;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Глеб on 05.03.2016.
 */

public class Account {
    private int id;
    private Client holder;
    private int money;

    public Account(){}
    public Account(ResultSet rs) throws SQLException {
        setId(rs.getInt(1));
        setHolder(ManagementSystem.getInstance().getClientById(Integer.parseInt(rs.getString(2))));
        setMoney(rs.getInt(3));
    }
    public Account(int id, Client holder, int money) {
        this.id = id;
        this.holder = holder;
        this.money = money;
    }

    public int getId() {
        return id;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Client getHolder() {
        return holder;
    }

    public int getMoney() {
        return money;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setHolder(Client holder) {
        this.holder = holder;
    }

    @Override
    public String toString() {
        return "ID="+id+" "+holder.getName();
    }
}
