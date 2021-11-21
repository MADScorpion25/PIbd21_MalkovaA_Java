package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Dock<T extends ITransport, P extends IWeapon> extends JPanel {
    /// <summary>
    /// Массив объектов, которые храним
    /// </summary>
    private final ArrayList<T> _places;
    /// <summary>
    /// Максимальное количество мест на парковке
    /// </summary>
    private final int _maxCount = 48;
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
    private String name;
    public Dock(int picWidth, int picHeight)
    {
        int width = picWidth / _placeSizeWidth;
        int height = picHeight / _placeSizeHeight;
        _places = new ArrayList<>();
        pictureWidth = picWidth;
        pictureHeight = picHeight;
    }
    /// <summary>
    /// Перегрузка оператора сложения
    /// Логика действия: на парковку добавляется крейсер
    /// </summary>
    /// <param name="p">Парковка</param>
    /// <param name="cruiser">Добавляемый крейсер</param>
    /// <returns></returns>
    public int Plus(Dock<T, P> p, T cruiser)
    {
        if (p._maxCount <= p._places.size()) return -1;

        for(int i = 0; i < p._places.size() + 1; i++)
        {
            p._places.add(cruiser);
            return p._places.indexOf(cruiser);
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
    public T Minus(Dock<T, P> p, int index)
    {
        T removedCruiser;

        if (index > -1 && index < p._places.size() && p._places.get(index) != null)
        {
            removedCruiser = p._places.get(index);
            p._places.remove(index);
            return removedCruiser;
        }
        return null;
    }
    public boolean LessOrEqual(Dock<T, P> p, int number){
        int indexSum = 0;
        for(int i = 0; i < p._places.size(); i++)
        {
           if(_places.get(i) != null){
               indexSum += i;
           }
        }
        return indexSum <= number;
    }

    public boolean MoreOrEqual(Dock<T, P> p, int number){
        int indexSum = 0;
        for(int i = 0; i < p._places.size(); i++)
        {
            if(_places.get(i) != null){
                indexSum += i;
            }
        }
        return indexSum >= number;
    }
    public int get_parkPlacesWidth() {
        return _parkPlacesWidth;
    }

    public int get_placeSizeWidth() {
        return _placeSizeWidth;
    }

    public int get_placeSizeHeight() {
        return _placeSizeHeight;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public ArrayList<T> get_places() {
        return _places;
    }

    /// <summary>
    /// Метод отрисовки разметки парковочных мест
    /// </summary>
    /// <param name="g"></param>
    public void Draw(Graphics g){
        g.clearRect(0,0,1300, 500);
        for (int i = 0; i < _places.size(); i++)
        {
            if(_places.get(i) != null){
                _places.get(i).SetPosition(i % _parkPlacesWidth * _placeSizeWidth + 5, i / _placeSizeWidth * _placeSizeHeight + 10, 1300, 700);
                _places.get(i).DrawTransport(g);
            }
        }
        DrawMarking(g);
    }
    public void DrawMarking(Graphics gr)
    {
        Graphics2D g = (Graphics2D) gr;
        g.setStroke(new BasicStroke(3));
        g.setColor(Color.BLACK);
        for (int i = 0; i < pictureWidth / _placeSizeWidth; i++)
        {
            for (int j = 0; j < pictureHeight / _placeSizeHeight + 1; ++j)
            {//линия рамзетки места
                g.drawLine(i * _placeSizeWidth, j * _placeSizeHeight, i * _placeSizeWidth + _placeSizeWidth / 2, j * _placeSizeHeight);
            }
            g.drawLine(i * _placeSizeWidth, 0, i * _placeSizeWidth, (pictureHeight / _placeSizeHeight) * _placeSizeHeight);
        }
    }
    public T indexator(int index){
        return _places.get(index);
    }
}

