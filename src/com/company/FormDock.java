package com.company;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Random;

public class FormDock extends Frame {
    private JButton createSimpCruiser, createWarCruiser, removeCruiser;
    private JFrame cruiserWindow;
    private JPanel rulePanel;
    private Vehicle cruiser;
    private Container elGroup;
    private JFormattedTextField removeIdInput;
    private JTextField text;
    private Dock<ICruiser> dock;
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
        removeIdInput.setBounds(1380, 220, 20, 30);
        rulePanel.add(removeIdInput);
        text = new JTextField();
        text.setBounds(1380, 420, 20, 30);
        rulePanel.add(text);

        dock = new Dock<>(1300, 700);
        dock.setBounds(0, 0, 1300, 700);
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

    public class ButtonActions extends JFrame implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Color mainColor, addColor;
            switch (e.getActionCommand()) {
                case "CreateSimpCruiser":
                    mainColor = JColorChooser.showDialog(null, "Color Chooser", Color.GRAY);
                    cruiser = new CruiserSimp(Math.abs(rnd.nextInt() % 100), Math.abs(rnd.nextInt() % 100), mainColor, 180, 60);
                    elGroup.add(cruiser);
                    cruiser.setBounds(0, 0, 1500, 500);
                    cruiser.setBackground(new Color(0,0,0,0));
                    //cruiser.setBackground(Color.GRAY);
                    cruiser.setLayout(null);
                    dock.Plus(dock, cruiser);
                    break;
                case "CreateWarCruiser":
                    mainColor = JColorChooser.showDialog(null, "Color Chooser", Color.GRAY);
                    addColor = JColorChooser.showDialog(null, "Color Chooser", Color.GRAY);
                    cruiser = new WarCruiser(Math.abs(rnd.nextInt() % 100), Math.abs(rnd.nextInt() % 100), mainColor, addColor, true, true, true, 180, 60);
                    elGroup.add(cruiser);
                    cruiser.setBounds(0, 0, 1500, 500);
                    cruiser.setBackground(new Color(0,0,0,0));
                    //cruiser.setBackground(Color.GRAY);
                    cruiser.setLayout(null);
                    dock.Plus(dock, cruiser);
                    break;
                case "RemoveCruiser":
                    Vehicle cruiser = (Vehicle) dock.Minus(dock, Integer.parseInt(text.getText()));
                    Interface removedCruiser = new Interface(cruiser);
                    break;
            }
        }

    }
}
