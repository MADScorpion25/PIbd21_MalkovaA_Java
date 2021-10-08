package com.company;

import java.awt.*;

public interface IWeapon {
    //установка значение количества орудий
    void setWeaponNumber(int count);
    //возвращение значения количества орудий
    int getWeaponNumber();
    //метод отрисовки орудия
    void drawWeapons(Graphics gr, Color dopColor, int startPosX, int startPosY);
}
