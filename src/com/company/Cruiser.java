package com.company;
import java.awt.*;
import java.util.Random;
import javax.swing.*;

class Cruiser extends JPanel {
    Random rnd = new Random();
    /// <summary>
    /// Левая координата отрисовки крейсера
    /// </summary>
    private int startPosX;
    /// <summary>
    /// Правая кооридната отрисовки крейсера
    /// </summary>
    private int startPosY;
    /// <summary>
    /// Ширина окна отрисовки
    /// </summary>
    private int pictureWidth;
    /// <summary>
    /// Высота окна отрисовки
    /// </summary>
    private int pictureHeight;
    /// <summary>
    /// Ширина отрисовки крейсера
    /// </summary>
    private final int cruiserWidth = 180;
    /// <summary>
    /// Высота отрисовки крейсера
    /// </summary>
    private final int cruiserHeight = 60;
    /// <summary>
    /// Максимальная скорость
    /// </summary>
    public int MaxSpeed;
    /// <summary>
    /// Вес крейсера
    /// </summary>
    public float Weight;
    /// <summary>
    /// Основной цвет кузова
    /// </summary>
    public Color MainColor;
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
    public boolean isInit = false;

    //поле ДопКласса
    AddWeapon weapons;
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

    public int getCruiserWidth() {
        return cruiserWidth;
    }

    public int getCruiserHeight() {
        return cruiserHeight;
    }
    /// <summary>
    /// Инициализация свойств
    /// </summary>
    public void Init(int maxSpeed, float weight, Color mainColor, Color dopColor,
                     boolean locator, boolean helicopterStation, boolean artillery) {
        if(artillery){
            weapons = new AddWeapon();
            weapons.setCount((rnd.nextInt() % 3 + 1) * 2);
        }
        MaxSpeed = maxSpeed;
        Weight = weight;
        MainColor = mainColor;
        DopColor = dopColor;
        Locator = locator;
        HelicopterStation = helicopterStation;
        Artillery = artillery;
        isInit = true;
    }
    /// <summary>
    /// Установка позиции крейсера
    /// </summary>
    /// <param name="x">Координата X</param>
    /// <param name="y">Координата Y</param>
    /// <param name="width">Ширина картинки</param>
    /// <param name="height">Высота картинки</param>
    public void SetPosition(int x, int y, int width, int height) {
        startPosX = x;
        startPosY = y;
        pictureWidth = width;
        pictureHeight = height;
        repaint();
    }
    /// <summary>
    /// Изменение направления пермещения
    /// </summary>
    /// <param name="direction">Направление</param>
    public void MoveTransport(Direction direction) {
        float step = MaxSpeed * 100 / Weight;
        switch (direction) {
            // вправо
            case Right:
                if (startPosX + step < pictureWidth - cruiserWidth) {
                    startPosX += step;
                }
                break;
            //влево
            case Left:
                if (startPosX > 0) {
                    startPosX -= step;
                }
                break;
            //вверх
            case Up:
                if (startPosY > 0) {
                    startPosY -= step;
                }
                break;
            //вниз
            case Down:
                if (startPosY + step < pictureHeight - cruiserHeight) {
                    startPosY += step;
                }
                break;
        }
        repaint();
    }

    /// <summary>
    /// Отрисовка крейсера
    /// </summary>
    /// <param name="g"></param>
    @Override
    public void paint(Graphics gr) {
        if (!isInit) return;
        Graphics2D g = (Graphics2D) gr;
        super.paint(g);
        g.setColor(Color.BLACK);
        // теперь отрисуем основной кузов крейсера
        //границы крейсера и кузов
        Polygon carcass = new Polygon();
        carcass.addPoint(startPosX, startPosY);
        carcass.addPoint(startPosX + 130, startPosY);
        carcass.addPoint(startPosX + 180, startPosY + 30);
        carcass.addPoint(startPosX + 130, startPosY + 60);
        carcass.addPoint(startPosX, startPosY + 60);
        g.setColor(MainColor);
        g.fillPolygon(carcass);
        g.setColor(Color.BLACK);
        g.drawPolygon(carcass);
        //рубка капитана
        g.setColor(MainColor);
        g.fillRect(startPosX + 90, startPosY + 10, 20, 40);
        g.setColor(Color.BLACK);
        g.drawRect(startPosX + 90, startPosY + 10, 20, 40);
        //отсек для кают
        g.setColor(MainColor);
        g.fillRect(startPosX + 60, startPosY + 25, 30, 10);
        g.setColor(Color.BLACK);
        g.drawRect(startPosX + 60, startPosY + 25, 30, 10);
        g.drawOval(startPosX + 125, startPosY + 20, 20, 20);
        //отрисовка турбин
        g.setColor(Color.BLACK);
        g.fillRect(startPosX - 5, startPosY + 10, 5, 17);
        g.fillRect(startPosX - 5, startPosY + 33, 5, 17);
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
