package com.gino.btcenvios.btcenvios.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * @author gino.ghiotto
 */
@Entity(tableName = "savedOperations")
public class SavedOperations {

    @PrimaryKey
    @ColumnInfo(name = "operationID")
    private int operationID;

    @ColumnInfo(name = "amount")
    private String amount;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "destAddress")
    private String destAddress;

    @ColumnInfo(name = "state")
    private String state;

    @ColumnInfo(name = "fee")
    private String fee;

    @ColumnInfo(name = "rate")
    private String rate;

    @ColumnInfo(name = "total")
    private String total;

    public SavedOperations() {
    }

    public SavedOperations(int operationID, String amount, String date,
                           String destAddress, String state,
                           String fee, String rate) {
        this.operationID = operationID;
        this.amount = amount;
        this.date = date;
        this.destAddress = destAddress;
        this.state = state;
        this.fee = fee;
        this.rate = rate;
        this.total = total;
    }

    public int getOperationID() {
        return operationID;
    }

    public void setOperationID(int operationID) {
        this.operationID = operationID;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDestAddress() {
        return destAddress;
    }

    public void setDestAddress(String destAddress) {
        this.destAddress = destAddress;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
