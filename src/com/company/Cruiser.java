package com.company;
import java.awt.*;
import java.util.Random;

public class Cruiser extends Vehicle {
    Random rnd = new Random();
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

    public int getCruiserWidth() {
        return cruiserWidth;
    }

    public int getCruiserHeight() {
        return cruiserHeight;
    }
    /// <summary>
    /// Инициализация свойств
    /// </summary>
    public Cruiser(int maxSpeed, float weight, Color mainColor, int cruiserWidth, int cruiserHeight) {
        MaxSpeed = maxSpeed;
        Weight = weight;
        MainColor = mainColor;
        this.pictureWidth= cruiserWidth;
        this.pictureHeight = cruiserHeight;
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
    }

    @Override
    public void DrawTransport(Graphics gr) {
        Graphics2D g = (Graphics2D) gr;
        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(1));
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
    }
}