package com.company;

import javax.swing.*;
import java.io.*;
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
    private final char separator = ':';
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

    public Dock<ITransport, IWeapon> indexator(String ind)
    {
        return dockStages.getOrDefault(ind, null);
    }
    public ITransport indexator(String key, int index)
    {
        if(dockStages.containsKey(key)){
            return dockStages.get(key).indexator(index);
        }
        return null;
    }
    public boolean saveData(String filename) throws FileNotFoundException {
        File file = new File(filename);
        PrintWriter writer = new PrintWriter(file);
        writer.println("DockCollection");
        for(Map.Entry<String, Dock<ITransport, IWeapon>> dock : dockStages.entrySet()){
            writer.println("Dock"+separator+dock.getKey());
            List<ITransport> list = dock.getValue().get_places();
            for(ITransport cruiser : list){
                if(cruiser.getClass().equals(Cruiser.class)){
                    writer.println("Cruiser"+separator+cruiser.toString());
                }
                if(cruiser.getClass().equals(WarCruiser.class)){
                    writer.println("WarCruiser"+separator+cruiser.toString());
                }
            }
        }
        writer.close();
        return true;
    }
    public boolean loadData(String filename) throws FileNotFoundException, DockOverflowException, DockAlreadyHaveException {
        Vehicle cruiser;
        File file = new File(filename);
        if(!file.exists()){
            throw new FileNotFoundException();
        }
        Scanner scanner = new Scanner(file);
        String line = scanner.nextLine(), key = "";
        if(!line.contains("DockCollection")){
            return false;
        }
        while(scanner.hasNextLine()){
            line = scanner.nextLine();
            if(line.contains("Dock")){
                key = line.split(String.valueOf(separator))[1];
                AddDock(key);
            }
            else if(line.split(String.valueOf(separator))[0].equals("Cruiser")){
                cruiser = new Cruiser(line.split(String.valueOf(separator))[1]);
                boolean result = false;
                result = dockStages.get(key).Plus(dockStages.get(key), cruiser);
                if(!result){
                    return false;
                }
                dockStages.get(key).add(cruiser);
            }
            else if(line.split(String.valueOf(separator))[0].equals("WarCruiser")){
                cruiser = new WarCruiser(line.split(String.valueOf(separator))[1]);
                boolean result = false;
                result = dockStages.get(key).Plus(dockStages.get(key), cruiser);
                if(!result){
                    return false;
                }
                dockStages.get(key).add(cruiser);
            }
        }
        return true;
    }
    public boolean saveDataFromDock(String filename, Dock<ITransport, IWeapon> dock) throws FileNotFoundException {
        File file = new File(filename);
        PrintWriter writer = new PrintWriter(file);
        writer.println("Dock"+separator+dock.getName());
        ArrayList<ITransport> list = dock.get_places();
        for(ITransport cruiser : list){
            if(cruiser.getClass().equals(Cruiser.class)){
                writer.println("Cruiser"+separator+((Cruiser)cruiser).toString());
            }
            if(cruiser.getClass().equals(WarCruiser.class)){
                writer.println("WarCruiser"+separator+((WarCruiser)cruiser).toString());
            }
        }
        writer.close();
        return true;
    }
    public boolean loadDataFromDock(String filename) throws FileNotFoundException, DockOverflowException, DockAlreadyHaveException {
        Vehicle cruiser;
        File file = new File(filename);
        if(!file.exists()){
            throw new FileNotFoundException();
        }
        Scanner scanner = new Scanner(file);
        String line, key = "";
        while(scanner.hasNextLine()){
            line = scanner.nextLine();
            if(line.contains("Dock")){
                key = line.split(String.valueOf(separator))[1];
                if(dockStages.containsKey(key)){
                    dockStages.get(key).get_places().clear();
                }
                else{
                    AddDock(key);
                }
            }
            else if(line.split(String.valueOf(separator))[0].equals("Cruiser")){
                cruiser = new Cruiser(line.split(String.valueOf(separator))[1]);
                boolean result = false;
                result = dockStages.get(key).Plus(dockStages.get(key), cruiser);
                if(!result){
                    return false;
                }
                dockStages.get(key).add(cruiser);
            }
            else if(line.split(String.valueOf(separator))[0].equals("WarCruiser")){
                cruiser = new WarCruiser(line.split(String.valueOf(separator))[1]);
                boolean result = false;
                result = dockStages.get(key).Plus(dockStages.get(key), cruiser);
                if(!result){
                    return false;
                }
                dockStages.get(key).add(cruiser);
            }
        }
        return true;
    }
}

