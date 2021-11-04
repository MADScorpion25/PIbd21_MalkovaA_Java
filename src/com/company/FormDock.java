package com.company;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Random;

public class FormDock extends JPanel {
    private JButton createSimpCruiser, createWarCruiser, removeCruiser, createDock, removeDock, getRemovedCruiser;
    private JFrame cruiserWindow;
    private JPanel rulePanel;
    private Vehicle cruiser;
    private Container elGroup;
    private JFormattedTextField removeIdInput;
    private JTextField parkingName;
    private JList<Dock<ITransport, IWeapon>> listBoxDock;
    private Dock<ITransport, IWeapon> dock;
    private DockCollection dockCollection;
    Random rnd = new Random();
    public FormDock() throws ParseException {
        cruiserWindow = new JFrame();
        cruiserWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cruiserWindow.setTitle("Cruiser Moving");
        cruiserWindow.setSize(1500, 800);

        dockCollection = new DockCollection(1300, 700);

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
            if(dock == null) cruiserWindow.getGraphics().clearRect(0,0, 1300, 700);
            else Draw();
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

        createSimpCruiser = new JButton("Create Simple Cruiser");
        createSimpCruiser.setActionCommand("CreateSimpCruiser");
        createSimpCruiser.setBounds(1310, 10, 150, 30);
        rulePanel.add(createSimpCruiser);

        createWarCruiser = new JButton("Create War Cruiser");
        createWarCruiser.setActionCommand("CreateWarCruiser");
        createWarCruiser.setBounds(1310, 45, 150, 30);
        rulePanel.add(createWarCruiser);

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
        createSimpCruiser.addActionListener(actionListener);
        createWarCruiser.addActionListener(actionListener);
        removeCruiser.addActionListener(actionListener);
        createDock.addActionListener(actionListener);
        removeDock.addActionListener(actionListener);
        getRemovedCruiser.addActionListener(actionListener);
        cruiserWindow.setVisible(true);
        super.repaint();
    }
    public class ButtonActions extends JPanel implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Color mainColor, addColor;
            int index;
            switch (e.getActionCommand()) {
                case "CreateSimpCruiser":
                    mainColor = JColorChooser.showDialog(null, "Color Chooser", Color.GRAY);
                    cruiser = new Cruiser(Math.abs(rnd.nextInt() % 20), Math.abs(rnd.nextInt() % 20), mainColor, 180, 60);
                    cruiser.setLayout(null);
                    index = dock.Plus(dock, cruiser);
                    if(index > -1){
                        dock.add(cruiser);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Dock is full!");
                    }
                    Draw();
                    break;
                case "CreateWarCruiser":
                    mainColor = JColorChooser.showDialog(null, "Color Chooser", Color.GRAY);
                    addColor = JColorChooser.showDialog(null, "Color Chooser", Color.GRAY);
                    cruiser = new WarCruiser(Math.abs(rnd.nextInt() % 20), Math.abs(rnd.nextInt() % 20), mainColor, addColor, true, true, true, 180, 60);
                    cruiser.setLayout(null);
                    index = dock.Plus(dock, cruiser);
                    if(index > -1){
                        dock.add(cruiser);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Dock is full!");
                    }
                    Draw();
                    break;
                case "RemoveCruiser":
                    Cruiser cruiser = (Cruiser) dock.Minus(dock, Integer.parseInt(removeIdInput.getText()));
                    if(cruiser != null){
                        FormCruiser removedCruiser = new FormCruiser();
                        removedCruiser.setCruiser((Vehicle) cruiser);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "This dock place is empty");
                    }
                    Draw();
                    break;
                case "CreateDock":
                    dock = dockCollection.AddParking(parkingName.getText());
                    dock.setBounds(0, 0, 1300, 700);
                    dock.setBackground(new Color(0,0,0,0));
                    elGroup.add(dock);
                    dock.setLayout(null);
                    Draw();
                    break;
                case "RemoveDock":
                    dockCollection.DelParking(dockCollection.modelList.get(dockCollection.modelList.indexOf(dock)).getName());
                    cruiserWindow.getGraphics().clearRect(0,0, 1300, 700);
                    break;
                case "GetRemovedCruiser":
                    try {
                        cruiser = (Cruiser) dock.getRemovedCruiser();
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                        cruiser = null;
                    }
                    if(cruiser != null){
                        FormCruiser removedCruiser = new FormCruiser();
                        removedCruiser.setCruiser((Vehicle) cruiser);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "The collection is empty");
                    }
                    Draw();
                    break;

            }
        }
    }
    public void Draw(){
        dock.Draw(dock.getGraphics());
    }
}

