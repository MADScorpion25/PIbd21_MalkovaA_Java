package com.company;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Random;

public class FormDock extends JPanel{
    private JButton createSimpCruiser, createWarCruiser, removeCruiser;
    private JFrame cruiserWindow;
    private JPanel rulePanel;
    private Vehicle cruiser;
    private Container elGroup;
    private JFormattedTextField removeIdInput;
    private JTextField text;
    private Dock<ITransport, IWeapon> dock;
    Random rnd = new Random();
    public FormDock() throws ParseException {
        cruiserWindow = new JFrame();
        cruiserWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cruiserWindow.setTitle("Cruiser Moving");
        cruiserWindow.setSize(1500, 800);

        elGroup = cruiserWindow.getContentPane();
        rulePanel = new JPanel();

        createSimpCruiser = new JButton("Create Simple Cruiser");
        createSimpCruiser.setActionCommand("CreateSimpCruiser");
        createSimpCruiser.setBounds(1310, 10, 150, 30);
        rulePanel.add(createSimpCruiser);

        createWarCruiser = new JButton("Create War Cruiser");
        createWarCruiser.setActionCommand("CreateWarCruiser");
        createWarCruiser.setBounds(1310, 100, 150, 30);
        rulePanel.add(createWarCruiser);

        removeCruiser = new JButton("Remove Cruiser");
        removeCruiser.setActionCommand("RemoveCruiser");
        removeCruiser.setBounds(1310, 270, 150, 30);
        rulePanel.add(removeCruiser);

        MaskFormatter mask = new MaskFormatter("##");
        mask.setPlaceholderCharacter('_');
        removeIdInput = new JFormattedTextField(mask);
        removeIdInput.setBounds(1380, 220, 30, 30);
        rulePanel.add(removeIdInput);

        dock = new Dock<>(1300, 700);
        dock.setBounds(0, 0, 1300, 700);
        dock.setBackground(new Color(0,0,0,0));
        elGroup.add(dock);
        dock.setLayout(null);

        rulePanel.setLayout(null);
        elGroup.add(rulePanel);

        ActionListener actionListener = new FormDock.ButtonActions();
        createSimpCruiser.addActionListener(actionListener);
        createWarCruiser.addActionListener(actionListener);
        removeCruiser.addActionListener(actionListener);
        cruiserWindow.setVisible(true);
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
                        cruiser.SetPosition((index % dock.get_parkPlacesWidth() * dock.get_placeSizeWidth()) + 5, (index / dock.get_parkPlacesWidth()) * dock.get_placeSizeHeight() + 10, cruiser.getWidth(), cruiser.getHeight());
                        dock.add(cruiser);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Dock is full!");
                    }
                    break;
                case "CreateWarCruiser":
                    mainColor = JColorChooser.showDialog(null, "Color Chooser", Color.GRAY);
                    addColor = JColorChooser.showDialog(null, "Color Chooser", Color.GRAY);
                    cruiser = new WarCruiser(Math.abs(rnd.nextInt() % 20), Math.abs(rnd.nextInt() % 20), mainColor, addColor, true, true, true, 180, 60);
                    cruiser.setLayout(null);
                    index = dock.Plus(dock, cruiser);
                    if(index > -1){
                        cruiser.SetPosition((index % dock.get_parkPlacesWidth() * dock.get_placeSizeWidth()) + 5, (index / dock.get_placeSizeWidth()) * dock.get_placeSizeHeight() + 10, cruiser.getWidth(), cruiser.getHeight());
                        dock.add(cruiser);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Dock is full!");
                    }
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
                    break;
            }
            Draw();
        }
        public void Draw(){
            dock.Draw(dock.getGraphics());
        }
    }
}

