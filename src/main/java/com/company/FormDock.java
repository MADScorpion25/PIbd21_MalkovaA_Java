package com.company;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import sun.misc.Queue;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Random;

public class FormDock extends JPanel {
    private JButton createCruiser, removeCruiser, createDock, removeDock, getRemovedCruiser;
    private JFrame cruiserWindow;
    private JPanel rulePanel;
    private Container elGroup;
    private JFormattedTextField removeIdInput;
    private JTextField parkingName;
    private JList<Dock<ITransport, IWeapon>> listBoxDock;
    private Dock<ITransport, IWeapon> dock;
    private DockCollection dockCollection;
    private Queue<ITransport> removedStages;
    private JMenuBar menuBar;
    private JMenu file, fileDock;
    private JMenuItem save, load, saveDock, loadDock;
    private static final Logger logger = Logger.getLogger(FormDock.class);
    Random rnd = new Random();
    public FormDock() throws ParseException{
        cruiserWindow = new JFrame();
        cruiserWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cruiserWindow.setTitle("Cruiser Moving");
        cruiserWindow.setSize(1500, 800);

       logger.log(Level.FATAL, "Fat Hello");
        menuBar = new JMenuBar();
        file = new JMenu("Collection");
        fileDock = new JMenu("Dock");

        save = new JMenuItem("Save");
        load = new JMenuItem("Load");
        saveDock = new JMenuItem("Save Dock");
        loadDock = new JMenuItem("Load Dock");
        file.add(save);
        file.add(load);
        fileDock.add(loadDock);
        fileDock.add(saveDock);
        menuBar.add(file);
        menuBar.add(fileDock);
        save.setActionCommand("Save");
        load.setActionCommand("Load");
        loadDock.setActionCommand("LoadDock");
        saveDock.setActionCommand("SaveDock");
        cruiserWindow.setJMenuBar(menuBar);

        dockCollection = new DockCollection(1300, 700);

        removedStages = new Queue<>();

        elGroup = cruiserWindow.getContentPane();
        rulePanel = new JPanel();

        parkingName = new JTextField();
        parkingName.setBounds(1300, 190, 150, 20);
        rulePanel.add(parkingName);

        listBoxDock = new JList<>();
        listBoxDock.setModel(dockCollection.modelList);
        listBoxDock.setBounds(1300, 220, 150, 300);
        listBoxDock.setVisibleRowCount(-1);
        rulePanel.add(listBoxDock);

        listBoxDock.getSelectionModel().addListSelectionListener(e -> {
            dock = listBoxDock.getSelectedValue();
            if(dock == null) cruiserWindow.getGraphics().clearRect(0,50, 1300, 750);
            else {
                dock.setBounds(0, 50, 1300, 750);
                dock.setBackground(new Color(0,0,0,0));
                elGroup.add(dock);
                dock.setLayout(null);
                Draw();
            }
        });

        createDock = new JButton("Create Dock");
        createDock.setActionCommand("CreateDock");
        createDock.setBounds(1300, 530, 150, 50);
        rulePanel.add(createDock);

        removeDock = new JButton("Remove Dock");
        removeDock.setActionCommand("RemoveDock");
        removeDock.setBounds(1300, 600, 150, 50);
        rulePanel.add(removeDock);

        getRemovedCruiser = new JButton("Get Removed Cruiser");
        getRemovedCruiser.setActionCommand("GetRemovedCruiser");
        getRemovedCruiser.setBounds(1300, 660, 150, 50);
        rulePanel.add(getRemovedCruiser);

        createCruiser = new JButton("CreateCruiser");
        createCruiser.setActionCommand("CreateCruiser");
        createCruiser.setBounds(1310, 10, 150, 30);
        rulePanel.add(createCruiser);

        removeCruiser = new JButton("Remove Cruiser");
        removeCruiser.setActionCommand("RemoveCruiser");
        removeCruiser.setBounds(1310, 100, 150, 30);
        rulePanel.add(removeCruiser);

        JLabel indexLabel = new JLabel("Index: ");
        indexLabel.setBounds(1320, 140, 50, 30);
        rulePanel.add(indexLabel);
        MaskFormatter mask = new MaskFormatter("##");
        mask.setPlaceholderCharacter('_');
        removeIdInput = new JFormattedTextField(mask);
        removeIdInput.setBounds(1380, 140, 30, 30);
        rulePanel.add(removeIdInput);

        rulePanel.setLayout(null);
        elGroup.add(rulePanel);

        ActionListener actionListener = new FormDock.ButtonActions();
        createCruiser.addActionListener(actionListener);
        removeCruiser.addActionListener(actionListener);
        createDock.addActionListener(actionListener);
        removeDock.addActionListener(actionListener);
        getRemovedCruiser.addActionListener(actionListener);
        save.addActionListener(actionListener);
        load.addActionListener(actionListener);
        saveDock.addActionListener(actionListener);
        loadDock.addActionListener(actionListener);
        cruiserWindow.setVisible(true);
        super.repaint();
    }
    public void createConfigWindow(){
        FormCruiserConfig config = new FormCruiserConfig(this);
    }
    public class ButtonActions extends JPanel implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFileChooser dialog = new JFileChooser();
            FileFilter filter = new FileNameExtensionFilter("TXT file", "txt");
            dialog.setFileFilter(filter);
            int ret;
            switch (e.getActionCommand()) {
                case "CreateCruiser":
                    createConfigWindow();
                    break;
                case "RemoveCruiser":
                    Cruiser cruiser = null;
                    try {
                        cruiser = (Cruiser) dock.Minus(dock, Integer.parseInt(removeIdInput.getText()));
                    } catch (DockNotFoundException dockNotFoundException) {
                        dockNotFoundException.printStackTrace();
                    }
                    if(cruiser != null){
                        removedStages.enqueue(cruiser);
                        logger.info("Cruiser removed: "+cruiser.toString());
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "This dock place is empty");
                    }
                    Draw();
                    break;
                case "CreateDock":
                    dock = dockCollection.AddDock(parkingName.getText());
                    if(dock != null){
                        logger.log(Level.INFO, "Dock is created: "+dock.getName());
                        dock.setBounds(0, 50, 1300, 750);
                        dock.setBackground(new Color(0,0,0,0));
                        elGroup.add(dock);
                        dock.setLayout(null);
                        Draw();
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "The dock with this name is already created");
                    }
                    parkingName.setText("");
                    break;
                case "RemoveDock":
                    if(dockCollection.modelList.indexOf(dock) > -1){
                        dockCollection.DelDock(dockCollection.modelList.get(dockCollection.modelList.indexOf(dock)).getName());
                        cruiserWindow.getGraphics().clearRect(0,50, 1300, 750);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "The collection of docks is empty");
                    }
                    break;
                case "GetRemovedCruiser":
                    cruiser = null;
                    if(!removedStages.isEmpty()){
                        try {
                            cruiser = (Cruiser) removedStages.dequeue();
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                        }
                    }
                    if(cruiser != null){
                        FormCruiser removedCruiser = new FormCruiser();
                        removedCruiser.setCruiser(cruiser);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "The collection of removed cruisers is empty");
                    }
                    Draw();
                    break;
                case "Save":
                    ret = dialog.showDialog(null, "Открыть файл");
                    if(ret == JFileChooser.APPROVE_OPTION) {
                        File file = dialog.getSelectedFile();
                        try {
                            if(dockCollection.saveData(file.getAbsolutePath())){
                                JOptionPane.showMessageDialog(null, "Collection saved successfully");
                            }
                        } catch (FileNotFoundException fileNotFoundException) {
                            fileNotFoundException.printStackTrace();
                        }
                    }
                    break;
                case "Load":
                    ret = dialog.showDialog(null, "Открыть файл");
                    if(ret == JFileChooser.APPROVE_OPTION) {
                        File file = dialog.getSelectedFile();
                        try {
                            if(dockCollection.loadData(file.getAbsolutePath())){
                                JOptionPane.showMessageDialog(null, "Collection loaded successfully");
                            }
                        } catch (FileNotFoundException fileNotFoundException) {
                            fileNotFoundException.printStackTrace();
                        }
                    }
                    break;
                case "SaveDock":
                    ret = dialog.showDialog(null, "Открыть файл");
                    if(ret == JFileChooser.APPROVE_OPTION) {
                        File file = dialog.getSelectedFile();
                        try {
                            if(dockCollection.saveDataFromDock(file.getAbsolutePath(), dock)){
                                JOptionPane.showMessageDialog(null, "Dock saved successfully");
                            }
                        } catch (FileNotFoundException fileNotFoundException) {
                            fileNotFoundException.printStackTrace();
                        }
                    }
                    break;
                case "LoadDock":
                    ret = dialog.showDialog(null, "Открыть файл");
                    if(ret == JFileChooser.APPROVE_OPTION) {
                        File file = dialog.getSelectedFile();
                        try {
                            if(dockCollection.loadDataFromDock(file.getAbsolutePath())){
                                JOptionPane.showMessageDialog(null, "Dock loaded successfully");
                            }
                        } catch (FileNotFoundException fileNotFoundException) {
                            fileNotFoundException.printStackTrace();
                        }
                    }
                    dock.Draw(dock.getGraphics());
                    break;
            }
        }
    }
    public void addCruiser(Vehicle cruiser) throws DockOverflowException {
        if(cruiser != null){
            boolean index = dock.Plus(dock, cruiser);
            if(index){
                dock.add(cruiser);
            }
            else{
                JOptionPane.showMessageDialog(null, "Dock is full!");
            }
            dock.Draw(dock.getGraphics());
        }
    }
    public void Draw(){
        dock.Draw(dock.getGraphics());
        repaint();
    }
}

