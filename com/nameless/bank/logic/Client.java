package com.nameless.bank.logic;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Глеб on 05.03.2016.
 */
public class Client {
    private int id;
    private String name;
    private String address;

    public Client(){}

    public Client(ResultSet rs) throws SQLException {
        setId(rs.getInt(1));
        setName(rs.getString(2));
        setAddress(rs.getString(3));
}
    public Client(int id, String name, String address) throws SQLException {
        setId(id);
        setName(name);
        setAddress(address);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return name+", проживает по адресу: "+address;
    }
}
