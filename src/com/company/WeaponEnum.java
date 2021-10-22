package com.company;

public enum WeaponEnum {
    Two(2), Four(4), Six(6);
    private int number;
    WeaponEnum(int number){
        this.number = number;
    }
    public int getNumber() {
        return number;
    }
}
