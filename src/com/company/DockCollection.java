package com.company;

import javax.swing.*;
import java.util.*;

public class DockCollection {
    /// <summary>
    /// Словарь (хранилище) с парковками
    /// </summary>
    final HashMap<String, Dock<ITransport, IWeapon>> dockStages;
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
        dockStages = new HashMap<>();
        this.pictureWidth = pictureWidth;
        this.pictureHeight = pictureHeight;
        modelList = new DefaultListModel<>();
    }
    /// <summary>
    /// Добавление парковки
    /// </summary>
    /// <param name="name">Название парковки</param>
    public Dock<ITransport, IWeapon> AddDock(String name)
    {
        if (!dockStages.containsKey(name))
        {
            Dock<ITransport, IWeapon> dock = new Dock<>(pictureWidth, pictureHeight);
            dock.setName(name);
            dockStages.put(name, dock);
            modelList.addElement(dock);
            return dock;
        }
        return null;
    }
    /// <summary>
    /// Удаление парковки
    /// </summary>
    /// <param name="name">Название парковки</param>
    public void DelDock(String name)
    {
        if (dockStages.containsKey(name))
        {
            Dock dock = dockStages.get(name);
            dockStages.remove(name);
            modelList.removeElement(dock);
        }
    }
    /// <summary>
    /// Доступ к парковке
    /// </summary>
    /// <param name="ind"></param>
    /// <returns></returns>
    public Dock<ITransport, IWeapon> index(String ind)
    {
        return dockStages.getOrDefault(ind, null);
    }
    public ITransport index(String key, int index)
    {
        if(dockStages.containsKey(key)){
            return dockStages.get(key).indexator(index);
        }
        return null;
    }
}

