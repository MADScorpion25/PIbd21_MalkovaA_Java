package com.company;
import java.awt.*;

public class WarCruiser extends Cruiser{
    /// <summary>
    /// Дополнительный цвет
    /// </summary>
    public Color DopColor;
    /// <summary>
    /// Признак локатора
    /// </summary>
    public boolean Locator;
    /// <summary>
    /// Признак наличия вертолетной площадки
    /// </summary>
    public boolean HelicopterStation;
    /// <summary>
    /// Признак наличия артиллерийского орудия
    /// </summary>
    public boolean isInit;

    //поле ДопКласса
    IWeapon weapons;
    public boolean Artillery;

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

    private void setMainColor(Color mainColor) {
        MainColor = mainColor;
    }

    public Color getDopColor() {
        return DopColor;
    }

    private void setDopColor(Color dopColor) {
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

    public WarCruiser(int maxSpeed, float weight, Color mainColor, Color dopColor,
                      boolean locator, boolean helicopterStation, boolean artillery, int cruiserWidth, int cruiserHeight) {
        super(maxSpeed, weight, mainColor, cruiserWidth, cruiserHeight);
        if(artillery){
            int ID = Math.abs(rnd.nextInt()) % 3;
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
            weapons.setWeaponNumber((rnd.nextInt() % 3 + 1) * 2);
        }
        DopColor = dopColor;
        Locator = locator;
        HelicopterStation = helicopterStation;
        Artillery = artillery;
        isInit = true;
        repaint();
    }
    @Override
    public void paintComponent(Graphics gr) {
        super.paintComponent(gr);
        if (!isInit) return;
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
}

