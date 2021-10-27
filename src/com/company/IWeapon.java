package com.company;
import java.awt.*;

public interface IWeapon {
    //установка значение количества орудий
    void setWeaponNumber(int count);
    //метод отрисовки орудия
    void drawWeapons(Graphics gr, Color dopColor, int startPosX, int startPosY);
}

