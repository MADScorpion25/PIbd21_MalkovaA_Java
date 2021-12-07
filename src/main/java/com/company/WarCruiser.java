package com.company;
import java.awt.*;
import java.util.Iterator;
import java.util.function.Consumer;

public class WarCruiser extends Cruiser implements Iterator<String> {
    /// <summary>
    /// Дополнительный цвет
    /// </summary>
    private Color DopColor;
    /// <summary>
    /// Признак локатора
    /// </summary>
    private  boolean Locator;
    /// <summary>
    /// Признак наличия вертолетной площадки
    /// </summary>
    private  boolean HelicopterStation;
    /// <summary>
    /// Признак наличия артиллерийского орудия
    /// </summary>
    //поле ДопКласса
    IWeapon weapons;
    private int weaponID = -1, weaponCount = 0;
    private boolean Artillery;
    private int current = -1;
    private final int specsCounter = 9;

    public int getMaxSpeed() {
        return MaxSpeed;
    }

    private void setMaxSpeed(int maxSpeed) {
        MaxSpeed = maxSpeed;
    }

    public float getWeight() {
        return Weight;
    }

    private void setWeight(float weight) {
        Weight = weight;
    }

    public Color getMainColor() {
        return MainColor;
    }

    public void setMainColor(Color mainColor) {
        MainColor = mainColor;
    }

    public Color getDopColor() {
        return DopColor;
    }

    public void setDopColor(Color dopColor) {
        DopColor = dopColor;
    }

    public boolean isLocator() {
        return Locator;
    }

    private void setLocator(boolean locator) {
        Locator = locator;
    }

    public boolean isHelicopterStation() {
        return HelicopterStation;
    }

    private void setHelicopterStation(boolean helicopterStation) {
        HelicopterStation = helicopterStation;
    }

    public boolean isArtillery() {
        return Artillery;
    }

    private void setArtillery(boolean artillery) {
        Artillery = artillery;
    }

    public int getWeaponID() {
        return weaponID;
    }

    public int getWeaponCount() {
        return weaponCount;
    }

    public WarCruiser(int maxSpeed, float weight, Color mainColor, Color dopColor,
                      boolean locator, boolean helicopterStation, boolean artillery, int cruiserWidth, int cruiserHeight) {
        super(maxSpeed, weight, mainColor, cruiserWidth, cruiserHeight);
        if(artillery){
            int ID = Math.abs(rnd.nextInt()) % 3;
            switch (ID){
                case 0:
                    weapons = new Artillery();
                    weaponID = 0;
                    break;
                case 1:
                    weapons = new ZenitArtillery();
                    weaponID = 1;
                    break;
                case 2:
                    weapons = new TorpedWeapon();
                    weaponID = 2;
                    break;
            }
            int count = Math.abs((rnd.nextInt() % 3 + 1) * 2);
            weapons.setWeaponNumber(count);
            weaponCount = count;
        }
        DopColor = dopColor;
        Locator = locator;
        HelicopterStation = helicopterStation;
        Artillery = artillery;
    }
    public WarCruiser(int maxSpeed, float weight, Color mainColor, Color dopColor,
                      boolean locator, boolean helicopterStation, boolean artillery, int cruiserWidth, int cruiserHeight, int ID, int count) {
        super(maxSpeed, weight, mainColor, cruiserWidth, cruiserHeight);
        setWeaponType(ID, count);
        DopColor = dopColor;
        Locator = locator;
        HelicopterStation = helicopterStation;
        Artillery = artillery;
    }
    public WarCruiser(String info) {
        super(info);
        String[] strs = info.split(String.valueOf(separator));
        if (strs.length == 9)
        {
            MaxSpeed = Integer.parseInt(strs[0]);
            Weight = Float.parseFloat(strs[1]);
            MainColor = Color.decode(strs[2]);
            DopColor = Color.decode(strs[3]);
            Locator = Boolean.parseBoolean(strs[4]);
            HelicopterStation = Boolean.parseBoolean(strs[5]);
            Artillery = Boolean.parseBoolean(strs[6]);
            weaponID = Integer.parseInt(strs[7]);
            weaponCount = Integer.parseInt(strs[8]);
            setWeaponType(weaponID, weaponCount);
        }
    }
    public void setWeaponType(int ID, int count){
        switch (ID){
            case 0:
                weapons = new Artillery();
                break;
            case 1:
                weapons = new ZenitArtillery();
                break;
            case 2:
                weapons = new TorpedWeapon();
                break;
        }
        weaponID = ID;
        weaponCount = count;
        weapons.setWeaponNumber(count);
    }
    @Override
    public void DrawTransport(Graphics gr) {
        super.DrawTransport(gr);
        Graphics2D g = (Graphics2D) gr;
        g.setColor(Color.BLACK);
        //рисуем артиллерийские орудия
        if (Artillery) {
            weapons.drawWeapons(g, DopColor, startPosX, startPosY);
        }
        //отрисовка локатора
        if (Locator) {
            g.setColor(Color.BLACK);
            g.drawRect(startPosX + 100, startPosY + 20, 15, 20);
            g.drawLine(startPosX + 105, startPosY + 20, startPosX + 105, startPosY + 40);
            g.drawLine(startPosX + 110, startPosY + 20, startPosX + 110, startPosY + 40);
            g.drawLine(startPosX + 105, startPosY + 20, startPosX + 105, startPosY + 40);
            g.drawLine(startPosX + 100, startPosY + 25, startPosX + 115, startPosY + 25);
            g.drawLine(startPosX + 100, startPosY + 30, startPosX + 115, startPosY + 30);
            g.drawLine(startPosX + 100, startPosY + 35, startPosX + 115, startPosY + 35);

        }
        // вертолетная станция
        if (HelicopterStation) {
            g.setStroke(new BasicStroke(5));
            g.fillRect(startPosX, startPosY, 50, 60);
            g.setColor(Color.white);
            g.drawOval(startPosX + 10, startPosY + 15, 30, 30);
        }
    }

    @Override
    public String toString() {
        String res = super.toString();
        return res+separator+super.toHexString(DopColor)+separator+Locator+separator+HelicopterStation+separator+Artillery+separator+String.valueOf(weaponID)+separator+String.valueOf(weaponCount);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof WarCruiser)) {
            return false;
        }
        else {
            return equals((WarCruiser)obj);
        }
    }
    public boolean equals(WarCruiser other) {
        if (MaxSpeed != other.MaxSpeed) {
            return false;
        }
        else if (Weight != other.Weight) {
            return false;
        }
        else if (MainColor != other.MainColor) {
            return false;
        }
        else if (HelicopterStation != other.HelicopterStation) {
            return false;
        }
        else if (Artillery != other.Artillery) {
            return false;
        }
        else if (Locator != other.Locator) {
            return false;
        }
        else if (DopColor != other.DopColor) {
            return false;
        }
        else if (weaponID != other.weaponID){
            return false;
        }
        else if (weaponCount != other.weaponCount){
            return false;
        }
       return true;
    }

    @Override
    public boolean hasNext() {
        if(current <= specsCounter - 1){
            current++;
            return true;
        }
        return false;
    }

    @Override
    public String next() {
        if(current == 0){
            return "Weight: "+Weight;
        }
        else if(current == 1){
            return "MaxSpeed: "+MaxSpeed;
        }
        else if(current == 2){
            return "MainColor: "+MainColor;
        }
        else if(current == 3){
            return "DopColor: "+DopColor;
        }
        else if(current == 4){
            return "isHelicopterStation: "+HelicopterStation;
        }
        else if(current == 5){
            return "isArtillery: "+Artillery;
        }
        else if(current == 6){
            return "isLocator: "+Locator;
        }
        else if(current == 7){
            return "Weapon Id: "+weaponID;
        }
        return "Weapon Count: "+weaponCount;
    }
    @Override
    public void remove() {
        current = -1;
    }
}