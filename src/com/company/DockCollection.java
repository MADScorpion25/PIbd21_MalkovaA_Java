package com.company;

import javax.swing.*;
import java.util.*;

public class DockCollection {
    /// <summary>
    /// Словарь (хранилище) с парковками
    /// </summary>
    final HashMap<String, Dock<ITransport, IWeapon>> parkingStages;
    /// <summary>
    /// Возвращение списка названий праковок
    /// </summary>
    public DefaultListModel<Dock<ITransport, IWeapon>> modelList;
    /// <summary>
    /// Ширина окна отрисовки
    /// </summary>
    private final int pictureWidth;
    /// <summary>
    /// Высота окна отрисовки
    /// </summary>
    private final int pictureHeight;
    /// <summary>
    /// Конструктор
    /// </summary>
    /// <param name="pictureWidth"></param>
    /// <param name="pictureHeight"></param>
    public DockCollection(int pictureWidth, int pictureHeight)
    {
        parkingStages = new HashMap<>();
        this.pictureWidth = pictureWidth;
        this.pictureHeight = pictureHeight;
        modelList = new DefaultListModel<>();
    }
    /// <summary>
    /// Добавление парковки
    /// </summary>
    /// <param name="name">Название парковки</param>
    public Dock<ITransport, IWeapon> AddParking(String name)
    {
        if (!parkingStages.containsKey(name))
        {
            Dock<ITransport, IWeapon> dock = new Dock<>(pictureWidth, pictureHeight);
            dock.setName(name);
            parkingStages.put(name, dock);
            modelList.addElement(dock);
            return dock;
        }
        return null;
    }
    /// <summary>
    /// Удаление парковки
    /// </summary>
    /// <param name="name">Название парковки</param>
    public void DelParking(String name)
    {
        if (parkingStages.containsKey(name))
        {
            Dock dock = parkingStages.get(name);
            parkingStages.remove(name);
            modelList.removeElement(dock);
        }
    }
    /// <summary>
    /// Доступ к парковке
    /// </summary>
    /// <param name="ind"></param>
    /// <returns></returns>
    public Dock<ITransport, IWeapon> indexator(String ind)
    {
        return parkingStages.getOrDefault(ind, null);
    }
    public ITransport indexator(String key, int index)
    {
        if(parkingStages.containsKey(key)){
            return parkingStages.get(key).indexator(index);
        }
        return null;
    }
}

