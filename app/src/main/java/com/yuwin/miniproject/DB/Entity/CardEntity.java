package com.yuwin.miniproject.DB.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_card")
public class CardEntity {

    public CardEntity(String cardOwner, String cardNumber, String cardExpiringMonth, String cardExpiringYear, String CVV) {
        this.cardOwner = cardOwner;
        this.cardNumber = cardNumber;
        this.cardExpiringMonth = cardExpiringMonth;
        this.cardExpiringYear = cardExpiringYear;
        this.CVV = CVV;
    }

    @PrimaryKey()
    public int uid = 1;

    public String cardOwner;
    public String cardNumber;
    public String cardExpiringMonth;
    public String cardExpiringYear;
    public String CVV;

    public String getCardNumber() {
        return "****    ****    ****    " + cardNumber.substring(cardNumber.length() - 4);
    }

    public String getCardOwner() {
        return cardOwner;
    }

    public String getCardExpiringMonth() {
        return cardExpiringMonth;
    }

    public String getCardExpiringYear() {
        return cardExpiringYear;
    }

    public String getCardExpirationDate() {
        String yearSubString = cardExpiringYear.substring(cardExpiringYear.length() - 2);
        return cardExpiringMonth + " \\ " +  yearSubString;
    }
}
