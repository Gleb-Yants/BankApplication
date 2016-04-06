package com.nameless.bank.web.forms;

import com.nameless.bank.logic.Client;

import java.util.Collection;

/**
 * Created by Глеб on 05.04.2016.
 */
public class AccountFrameForm {
    private Collection accounts;
    private int clientId;

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public Collection getAccounts() {
        return accounts;
    }

    public void setAccounts(Collection accounts) {
        this.accounts = accounts;
    }
}
