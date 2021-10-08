package com.company;

import java.awt.*;

import static com.company.WeaponEnum.*;

public class TorpedWeapon implements IWeapon{
    private WeaponEnum weaponQuantity;
    @Override
    public void setWeaponNumber(int count) {
        if(count == Two.getNumber())weaponQuantity = Two;
        else if (count == Four.getNumber())weaponQuantity = Four;
        else weaponQuantity = Six;
    }

    @Override
    public int getWeaponNumber() {
        return weaponQuantity.getNumber();
    }

    @Override
    public void drawWeapons(Graphics gr, Color dopColor, int startPosX, int startPosY) {
        Graphics2D g = (Graphics2D) gr;
        g.setColor(dopColor);
        g.fillRect(startPosX + 60, startPosY + 5, 20, 15);
        g.fillRect(startPosX + 60, startPosY + 40, 20, 15);
        g.setColor(Color.darkGray);
        g.setStroke(new BasicStroke(3));
        switch(weaponQuantity){
            case Six:
                g.drawLine(startPosX + 73, startPosY + 5, startPosX + 77, startPosY - 5);
                g.drawLine(startPosX + 73, startPosY + 55, startPosX + 77, startPosY + 65);
            case Four:
                g.drawLine(startPosX + 69, startPosY + 5, startPosX + 73, startPosY - 5);
                g.drawLine(startPosX + 69, startPosY + 55, startPosX + 73, startPosY + 65);
            case Two:
                g.drawLine(startPosX + 65, startPosY + 5, startPosX + 69, startPosY - 5);
                g.drawLine(startPosX + 65, startPosY + 55, startPosX + 69, startPosY + 65);
                break;
        }
        g.setColor(Color.BLACK);
        g.drawLine(startPosX + 65, startPosY - 3, startPosX + 83, startPosY - 3);
        g.drawLine(startPosX + 65, startPosY + 63, startPosX + 83, startPosY + 63);
        g.drawRect(startPosX + 60, startPosY + 5, 20, 15);
        g.drawRect(startPosX + 60, startPosY + 40, 20, 15);
    }
}
