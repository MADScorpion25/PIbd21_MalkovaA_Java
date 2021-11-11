package com.company;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeListener;
import java.util.Calendar;

public class FormCruiserConfig extends JFrame {
    final JDialog formConfig;
    private JButton addCruiser;
    private JLabel pictureMask, cruiser, warCruiser, mainColor, addColor, speedLabel, weightLabel;
    private MouseReaction mouseType, mouseColor;
    private Cruiser pictureCruiser;
    private JPanel confPanel, drawPanel, grayColor, darkRedColor, blueColor, whiteColor, purpleColor, cyanColor, greenColor, yellowColor;
    private JSpinner chooseSpeed, chooseWeight;
    private JCheckBox setLocator, setHelicopterStation, setWeapons;
    public FormCruiserConfig(JFrame fdock){
        formConfig = new JDialog(fdock, "Choose cruiser configuration", true);
        Init();
    }
    public void Init(){
        mouseType = new MouseReaction();
        MouseReaction mouseType = new MouseReaction();
        mouseColor = new MouseReaction();
        formConfig.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        formConfig.setSize(800, 500);
        formConfig.setLayout(null);

        confPanel = new JPanel();
        confPanel.setLayout(null);
        confPanel.setBounds(0,0, formConfig.getWidth(), formConfig.getHeight());

        pictureCruiser = new Cruiser(100, 100, Color.GRAY, 180,60);

        pictureMask = new JLabel();
        pictureMask.setLayout(new GridLayout(0, 1));
        pictureMask.setBounds(150, 10, 200,150);
        pictureMask.setBorder(new LineBorder(new Color(0,0,0)));
        pictureMask.setTransferHandler(new TransferHandler("text"));
        confPanel.add(pictureMask);

        cruiser = new JLabel("Simple Cruiser");
        cruiser.setBounds(10,10, 100, 50);
        cruiser.setBorder(new BevelBorder(0));
        cruiser.setTransferHandler(new TransferHandler("text"));
        cruiser.addMouseListener(mouseType);
        cruiser.setDropTarget(null);
        confPanel.add(cruiser);

        warCruiser = new JLabel("War Cruiser");
        warCruiser.setBounds(10,70, 100, 50);
        warCruiser.setBorder(new BevelBorder(0));
        warCruiser.setTransferHandler(new TransferHandler("text"));
        warCruiser.addMouseListener(mouseType);
        warCruiser.setDropTarget(null);
        confPanel.add(warCruiser);

        mainColor = new JLabel("Main Color");
        mainColor.setBounds(400, 10, 90, 30);
        mainColor.setBorder(new LineBorder(Color.BLACK));
        mainColor.setHorizontalAlignment(JLabel.CENTER);
        mainColor.setTransferHandler(new TransferHandler("background"));
        confPanel.add(mainColor);

        grayColor = new JPanel();
        grayColor.setLayout(null);
        grayColor.setBounds(400, 50, 40,40);
        grayColor.setBackground(new Color(139, 139, 139));
        grayColor.setTransferHandler(new TransferHandler("background"));
        grayColor.addMouseListener(mouseColor);
        grayColor.setDropTarget(null);
        confPanel.add(grayColor);

        darkRedColor = new JPanel();
        darkRedColor.setLayout(null);
        darkRedColor.setBounds(450, 50, 40,40);
        darkRedColor.setBackground(new Color(168, 112, 112));
        darkRedColor.setTransferHandler(new TransferHandler("background"));
        darkRedColor.addMouseListener(mouseColor);
        darkRedColor.setDropTarget(null);
        confPanel.add(darkRedColor);

        blueColor = new JPanel();
        blueColor.setLayout(null);
        blueColor.setBounds(400, 100, 40,40);
        blueColor.setBackground(new Color(160, 229, 238, 215));
        blueColor.setTransferHandler(new TransferHandler("background"));
        blueColor.addMouseListener(mouseColor);
        blueColor.setDropTarget(null);
        confPanel.add(blueColor);

        whiteColor = new JPanel();
        whiteColor.setLayout(null);
        whiteColor.setBounds(450, 100, 40,40);
        whiteColor.setBackground(Color.WHITE);
        whiteColor.setTransferHandler(new TransferHandler("background"));
        whiteColor.addMouseListener(mouseColor);
        whiteColor.setDropTarget(null);
        confPanel.add(whiteColor);

        addColor = new JLabel("Addition Color");
        addColor.setBounds(600, 10, 90, 30);
        addColor.setBorder(new LineBorder(Color.BLACK));
        addColor.setHorizontalAlignment(JLabel.CENTER);
        addColor.setTransferHandler(new TransferHandler("background"));
        confPanel.add(addColor);

        purpleColor = new JPanel();
        purpleColor.setLayout(null);
        purpleColor.setBounds(600, 50, 40,40);
        purpleColor.setBackground(new Color(124, 82, 227));
        purpleColor.setTransferHandler(new TransferHandler("background"));
        purpleColor.addMouseListener(mouseColor);
        purpleColor.setDropTarget(null);
        confPanel.add(purpleColor);

        cyanColor = new JPanel();
        cyanColor.setLayout(null);
        cyanColor.setBounds(650, 50, 40,40);
        cyanColor.setBackground(new Color(151, 224, 200));
        cyanColor.setTransferHandler(new TransferHandler("background"));
        cyanColor.addMouseListener(mouseColor);
        cyanColor.setDropTarget(null);
        confPanel.add(cyanColor);

        greenColor = new JPanel();
        greenColor.setLayout(null);
        greenColor.setBounds(600, 100, 40,40);
        greenColor.setBackground(new Color(168, 238, 160, 215));
        greenColor.setTransferHandler(new TransferHandler("background"));
        greenColor.addMouseListener(mouseColor);
        greenColor.setDropTarget(null);
        confPanel.add(greenColor);

        yellowColor = new JPanel();
        yellowColor.setLayout(null);
        yellowColor.setBounds(650, 100, 40,40);
        yellowColor.setBackground(new Color(229, 207, 152));
        yellowColor.setTransferHandler(new TransferHandler("background"));
        yellowColor.addMouseListener(mouseColor);
        yellowColor.setDropTarget(null);
        confPanel.add(yellowColor);

        speedLabel = new JLabel("Speed:");
        speedLabel.setBounds(30, 270, 60, 20);
        confPanel.add(speedLabel);

        chooseSpeed = new JSpinner();
        chooseSpeed.setBounds(30, 290, 60, 20);
        chooseSpeed.setModel(new SpinnerNumberModel(100, 1, 200, 1));
        confPanel.add(chooseSpeed);

        weightLabel = new JLabel("Weight:");
        weightLabel.setBounds(30, 320, 60, 20);
        confPanel.add(weightLabel);

        chooseWeight = new JSpinner();
        chooseWeight.setBounds(30, 340, 60, 20);
        chooseWeight.setModel(new SpinnerNumberModel(100, 1, 200, 1));
        confPanel.add(chooseWeight);

        setWeapons = new JCheckBox("Set Weapons");
        setWeapons.setSelected(true);
        setWeapons.setBounds(150, 280, 120, 30);
        confPanel.add(setWeapons);

        setLocator = new JCheckBox("Set Locator");
        setLocator.setSelected(true);
        setLocator.setBounds(150, 310, 120, 30);
        confPanel.add(setLocator);

        setHelicopterStation = new JCheckBox("Set Helicopter Station");
        setHelicopterStation.setSelected(true);
        setHelicopterStation.setBounds(150, 340, 150, 30);
        confPanel.add(setHelicopterStation);

        PropertyChangeListener typeChangeListener = PropertyChangeEvent ->{
            if(pictureMask.getText().equals("Simple Cruiser")){
                confPanel.getGraphics().clearRect(150, 10, 200,150);
                pictureCruiser = new Cruiser((int)chooseSpeed.getValue(), (int)chooseWeight.getValue(), Color.GRAY, 180,60);
                pictureCruiser.SetPosition(160, 50, formConfig.getWidth(), formConfig.getHeight());
                mainColor.setBackground(Color.GRAY);
                repaintModel();
            }
            else if(pictureMask.getText().equals("War Cruiser")){
                pictureCruiser = new WarCruiser((int)chooseSpeed.getValue(), (int)chooseWeight.getValue(), Color.GRAY, Color.CYAN, setLocator.isSelected(), setHelicopterStation.isSelected(), setWeapons.isSelected(), 180,60);
                pictureCruiser.SetPosition(160, 50, confPanel.getWidth(),  confPanel.getHeight());
                pictureCruiser.DrawTransport(confPanel.getGraphics());
                mainColor.setBackground(Color.GRAY);
                repaintModel();
            }
            pictureMask.setText("");
            repaintModel();
            confPanel.repaint();
        };
        PropertyChangeListener colorChangeListener = PropertyChangeEvent ->{
            if(pictureCruiser == null)return;
            if(pictureCruiser.getClass().equals(Cruiser.class) || pictureCruiser.getClass().equals(WarCruiser.class)){
                pictureCruiser.setMainColor(mainColor.getBackground());
                repaintModel();
            }
            if(pictureCruiser.getClass().equals(WarCruiser.class)){
                WarCruiser cruiser = (WarCruiser) pictureCruiser;
                cruiser.setDopColor(addColor.getBackground());
                repaintModel();
            }
        };
        mainColor.addPropertyChangeListener(colorChangeListener);
        addColor.addPropertyChangeListener(colorChangeListener);
        pictureMask.addPropertyChangeListener(typeChangeListener);
        formConfig.add(confPanel);
        formConfig.setVisible(true);
    }
    public class MouseReaction extends MouseAdapter{
        @Override
        public void mousePressed(MouseEvent e) {
            if(e.getSource().getClass().equals(JLabel.class)){
                JLabel element = (JLabel) e.getSource();
                TransferHandler handler = element.getTransferHandler();
                handler.exportAsDrag(element, e, TransferHandler.COPY);
            }
            else if(e.getSource().getClass().equals(JPanel.class)){
                JPanel element = (JPanel) e.getSource();
                TransferHandler handler = element.getTransferHandler();
                handler.exportAsDrag(element, e, TransferHandler.COPY);
            }
        }
    }
    public void repaintModel(){
        pictureCruiser.DrawTransport(confPanel.getGraphics());
    }
}
