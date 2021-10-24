package com.company;
import javax.swing.*;
import java.awt.*;

public abstract class Vehicle extends JPanel implements ITransport {
    /// <summary>
    /// Левая координата отрисовки крейсера
    /// </summary>
    protected int startPosX;
    /// <summary>
    /// Правая кооридната отрисовки крейсера
    /// </summary>
    protected int startPosY;
    /// <summary>
    /// Ширина окна отрисовки
    /// </summary>
    protected int pictureWidth;
    /// <summary>
    /// Высота окна отрисовки
    /// </summary>
    protected int pictureHeight;
    /// <summary>
    /// Максимальная скорость
    /// </summary>
    private int MaxSpeed;
    /// <summary>
    /// Вес крейсера
    /// </summary>
    private float Weight;
    /// <summary>
    /// Основной цвет кузова
    /// </summary>
    private Color MainColor;

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

    public abstract int getCruiserWidth();

    public abstract int getCruiserHeight();

    private void setMainColor(Color mainColor) {
        MainColor = mainColor;
    }
    public void SetPosition(int x, int y, int width, int height)
    {
        startPosX = x;
        startPosY = y;
        pictureWidth = width;
        pictureHeight = height;
        repaint();
    }
    public abstract void MoveTransport(Direction direction);
}

