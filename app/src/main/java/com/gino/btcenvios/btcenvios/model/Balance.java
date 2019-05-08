package com.gino.btcenvios.btcenvios.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * @author gino.ghiotto
 */
@Entity(tableName = "balance")
public class Balance {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "current")
    private Double current;

    public Balance(Double current) {
        this.current = current;
    }

    public Double getCurrent() {
        return current;
    }

    public void setCurrent(Double current) {
        this.current = current;
    }
}
