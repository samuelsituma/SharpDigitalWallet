package com.samuelsituma.sharpdigitalwalet;

public class User {
    public String mobileNo;
    public String upiPin;
    public String Address;
    public String City;
    public String bankName;
    public String Name;
    public double bankBalance;
    public double walletBalance;

    User(){
    }

    User(String mobileNo,String upiPin,String Name,String Address,String City,String bankName){
        this.mobileNo= mobileNo;
        this.upiPin=upiPin;
        this.Name =Name;
        this.Address=Address;
        this.City=City;
        bankBalance=1000.00;
        walletBalance=0.00;
        this.bankName=bankName;
    }
}
