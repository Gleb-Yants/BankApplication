package com.nameless.bank.web.forms;

import java.util.Collection;

/**
 * Created by Глеб on 06.04.2016.
 */
public class TransactFrameForm {
    private int fromId;
    private String fromName;
    private Collection to;
    private int sum;

    public int getFromId() {
        return fromId;
    }

    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public Collection getTo() {
        return to;
    }

    public void setTo(Collection to) {
        this.to = to;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
