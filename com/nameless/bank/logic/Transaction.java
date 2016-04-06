package com.nameless.bank.logic;

/**
 * Created by Глеб on 05.03.2016.
 */
public class Transaction {
    private int id;
    Account from;
    Account to;
    int amount;

    public Transaction(){}

    public Transaction(int id, Account from, Account to, int amount) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public Account getFrom() {
        return from;
    }

    public Account getTo() {
        return to;
    }

    public int getAmount() {
        return amount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFrom(Account from) {
        this.from = from;
    }

    public void setTo(Account to) {
        this.to = to;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Осуществлена транзация в размере "+amount+" руб. со счета клиента "+from.getHolder().getName()+" на счет клиента "+to.getHolder().getName();
    }
}
