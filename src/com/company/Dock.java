package com.company;

import javax.swing.*;
import java.awt.*;

public class Dock<T extends ICruiser> extends JPanel {
    /// <summary>
    /// Массив объектов, которые храним
    /// </summary>
    private final T[] _places;
    /// <summary>
    /// Ширина окна отрисовки
    /// </summary>
    private final int pictureWidth;
    /// <summary>
    /// Высота окна отрисовки
    /// </summary>
    private final int pictureHeight;
    /// <summary>
    /// Размер парковочного места (ширина)
    /// </summary>
    private final int _placeSizeWidth = 210;
    /// <summary>
    /// Размер парковочного места (высота)
    /// </summary>
    private final int _placeSizeHeight = 80;
    /// <summary>
    /// Конструктор
    /// </summary>
    private final int _parkPlacesWidth = 6;
    /// <param name="picWidth">Рамзер парковки - ширина</param>
    /// <param name="picHeight">Рамзер парковки - высота</param>
    public Dock(int picWidth, int picHeight)
    {
        int width = picWidth / _placeSizeWidth;
        int height = picHeight / _placeSizeHeight;
        _places = (T[]) new ICruiser[width * height];
        pictureWidth = picWidth;
        pictureHeight = picHeight;
        repaint();
    }
    /// <summary>
    /// Перегрузка оператора сложения
    /// Логика действия: на парковку добавляется крейсер
    /// </summary>
    /// <param name="p">Парковка</param>
    /// <param name="cruiser">Добавляемый крейсер</param>
    /// <returns></returns>
    public int Plus(Dock<T> p, T cruiser)
    {
        for(int i = 0; i < p._places.length; i++)
        {
            if(p._places[i] == null)
            {
                p._places[i] = cruiser;
                repaint();
                return i;
            }
        }
        return -1;
    }
    /// <summary>
    /// Перегрузка оператора вычитания
    /// Логика действия: с парковки забираем крейсер
    /// </summary>
    /// <param name="p">Парковка</param>
    /// <param name="index">Индекс места, с которого пытаемся извлечь объект</param>
    /// <returns></returns>
    public T Minus(Dock<T> p, int index)
    {
        T removedCruiser;

        if (index > -1 && index < p._places.length && p._places[index] != null)
        {
            removedCruiser = p._places[index];
            p._places[index] = null;
            repaint();
            return removedCruiser;
        }
        return null;
    }
    /// <summary>
    /// Метод отрисовки разметки парковочных мест
    /// </summary>
    /// <param name="g"></param>
    @Override
    public void paintComponent(Graphics gr)
    {
        super.paintComponent(gr);
        Graphics2D g = (Graphics2D) gr;
        g.setStroke(new BasicStroke(3));
        for (int i = 0; i < pictureWidth / _placeSizeWidth; i++)
        {
            for (int j = 0; j < pictureHeight / _placeSizeHeight + 1; ++j)
            {//линия рамзетки места
                g.drawLine(i * _placeSizeWidth, j * _placeSizeHeight, i *
                        _placeSizeWidth + _placeSizeWidth / 2, j * _placeSizeHeight);
            }
            g.drawLine(i * _placeSizeWidth, 0, i * _placeSizeWidth,
                    (pictureHeight / _placeSizeHeight) * _placeSizeHeight);
        }
        for (int i = 0; i < _places.length; i++)
        {
            if(_places[i] != null){
                _places[i].SetPosition((i % _parkPlacesWidth) * (_placeSizeWidth + 10), (i / _parkPlacesWidth) * _placeSizeHeight + 10, pictureWidth, pictureHeight);
            }
        }
    }
}
