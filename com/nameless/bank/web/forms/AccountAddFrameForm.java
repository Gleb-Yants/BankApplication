package com.nameless.bank.web.forms;

/**
 * Created by Глеб on 05.04.2016.
 */
public class AccountAddFrameForm {
    private int clientId;
    private String clientName;
    private int money;

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
