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
    private Double amount;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "destAddress")
    private String destAddress;

    @ColumnInfo(name = "state")
    private String state;

    public SavedOperations(int operationID, Double amount, String date, String destAddress, String state) {
        this.operationID = operationID;
        this.amount = amount;
        this.date = date;
        this.destAddress = destAddress;
        this.state = state;
    }

    public int getOperationID() {
        return operationID;
    }

    public void setOperationID(int operationID) {
        this.operationID = operationID;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
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
}
