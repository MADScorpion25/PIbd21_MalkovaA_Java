package com.company;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeListener;

public class FormCruiserConfig extends JFrame {
    private JFrame formConfig;
    private Dock dock;
    private JLabel pictureMask, cruiser, warCruiser, mainColor, addColor, speedLabel, weightLabel, artilleryType, torpedType, zenitType;
    private MouseReaction mouseType, mouseColor;
    private ITransport pictureCruiser;
    private JPanel confPanel, grayColor, darkRedColor, blueColor, whiteColor, purpleColor, cyanColor, greenColor, yellowColor;
    private DrawPanel drawPanel;
    private JSpinner chooseSpeed, chooseWeight, weaponCount;
    private JCheckBox setLocator, setHelicopterStation, setWeapons;
    private JButton createCruiser, cancel;
    public FormCruiserConfig(Dock dock){
        this.dock = dock;
        formConfig = new JFrame("Choose Configuration");
        Init();
    }
    public void Init(){
        mouseType = new MouseReaction();
        MouseReaction mouseType = new MouseReaction();
        mouseColor = new MouseReaction();
        formConfig.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        formConfig.setSize(800, 500);
        formConfig.setLayout(null);

        confPanel = new JPanel();
        confPanel.setLayout(null);
        confPanel.setBounds(0,0, formConfig.getWidth(), formConfig.getHeight());

        pictureMask = new JLabel();
        pictureMask.setBounds(150, 10, 200,150);
        pictureMask.setBorder(new LineBorder(new Color(0,0,0)));
        pictureMask.setTransferHandler(new TransferHandler("text"));
        confPanel.add(pictureMask);

        drawPanel = new DrawPanel();
        drawPanel.setBounds(150, 10, 200,150);
        drawPanel.setBorder(new LineBorder(new Color(0,0,0)));
        confPanel.add(drawPanel);

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
        setWeapons.setBounds(180, 280, 120, 30);
        confPanel.add(setWeapons);

        setLocator = new JCheckBox("Set Locator");
        setLocator.setSelected(true);
        setLocator.setBounds(180, 310, 120, 30);
        confPanel.add(setLocator);

        setHelicopterStation = new JCheckBox("Set Helicopter Station");
        setHelicopterStation.setSelected(true);
        setHelicopterStation.setBounds(180, 340, 150, 30);
        confPanel.add(setHelicopterStation);

        artilleryType = new JLabel("Artillery Type");
        artilleryType.setBounds(400,220, 100, 50);
        artilleryType.setBorder(new BevelBorder(0));
        artilleryType.setTransferHandler(new TransferHandler("text"));
        artilleryType.addMouseListener(mouseType);
        artilleryType.setDropTarget(null);
        confPanel.add(artilleryType);

        torpedType = new JLabel("Torped Type");
        torpedType.setBounds(400,280, 100, 50);
        torpedType.setBorder(new BevelBorder(0));
        torpedType.setTransferHandler(new TransferHandler("text"));
        torpedType.addMouseListener(mouseType);
        torpedType.setDropTarget(null);
        confPanel.add(torpedType);

        zenitType = new JLabel("Zenit Type");
        zenitType.setBounds(400,340, 100, 50);
        zenitType.setBorder(new BevelBorder(0));
        zenitType.setTransferHandler(new TransferHandler("text"));
        zenitType.addMouseListener(mouseType);
        zenitType.setDropTarget(null);
        confPanel.add(zenitType);

        weaponCount = new JSpinner();
        weaponCount.setBounds(400, 410, 60, 20);
        weaponCount.setModel(new SpinnerNumberModel(1, 1, 3, 1));
        confPanel.add(weaponCount);

        createCruiser = new JButton("Create Cruiser");
        createCruiser.setBounds(600, 250, 140, 50);
        confPanel.add(createCruiser);

        cancel = new JButton("Cancel");
        cancel.setBounds(600, 310, 140, 50);
        confPanel.add(cancel);

        PropertyChangeListener colorChangeListener = PropertyChangeEvent ->{
            if(pictureCruiser == null)return;
            if(pictureCruiser.getClass().equals(Cruiser.class) || pictureCruiser.getClass().equals(WarCruiser.class)){
                setMainColor();
            }
            if(pictureCruiser.getClass().equals(WarCruiser.class)){
                setAddColor();
            }
        };
        PropertyChangeListener typeChangeListener = PropertyChangeEvent ->{
            if(pictureMask.getText().equals("Simple Cruiser")){
                setCruiser();
            }
            else if(pictureMask.getText().equals("War Cruiser")){
                setWarCruiser();
            }
            if(pictureCruiser != null && pictureCruiser.getClass().equals(WarCruiser.class)){
                if(pictureMask.getText().equals("Artillery Type")){
                    setWeapon(0);
                }
                else if(pictureMask.getText().equals("Zenit Type")){
                    setWeapon(1);
                }
                else if(pictureMask.getText().equals("Torped Type")){
                    setWeapon(2);
                }
            }
            pictureMask.setText("");
        };
        createCruiser.addActionListener(ActionEvent -> {
            if(pictureCruiser != null){
                int index = dock.Plus(dock, pictureCruiser);
                if(index > -1){
                    dock.add(cruiser);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Dock is full!");
                }
                dock.Draw(dock.getGraphics());
            }
            formConfig.setVisible(false);
            formConfig.dispose();
        });
        cancel.addActionListener(ActionEvent ->  {
            formConfig.setVisible(false);
            formConfig.dispose();
        });
        mainColor.addPropertyChangeListener(colorChangeListener);
        addColor.addPropertyChangeListener(colorChangeListener);
        pictureMask.addPropertyChangeListener(typeChangeListener);
        pictureMask.addPropertyChangeListener(colorChangeListener);
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
    public void setCruiser(){
        pictureCruiser = new Cruiser((int)chooseSpeed.getValue(), (int)chooseWeight.getValue(), Color.GRAY, 180,60);
        pictureCruiser.SetPosition(20, 50, formConfig.getWidth(), formConfig.getHeight());
        drawPanel.setCruiser(pictureCruiser);
        drawPanel.repaint();
    }
    public void setWarCruiser(){
        pictureCruiser = new WarCruiser((int)chooseSpeed.getValue(), (int)chooseWeight.getValue(), Color.GRAY, Color.CYAN, setLocator.isSelected(), setHelicopterStation.isSelected(), setWeapons.isSelected(), 180,60);
        pictureCruiser.SetPosition(20, 50, confPanel.getWidth(),  confPanel.getHeight());
        drawPanel.setCruiser(pictureCruiser);
        drawPanel.repaint();
    }
    public void setWeapon(int ID){
        pictureCruiser = new WarCruiser((int)chooseSpeed.getValue(), (int)chooseWeight.getValue(), Color.GRAY, Color.CYAN, setLocator.isSelected(), setHelicopterStation.isSelected(), setWeapons.isSelected(), 180,60, ID, (int)weaponCount.getValue() * 2);
        pictureCruiser.SetPosition(20, 50, confPanel.getWidth(),  confPanel.getHeight());
        drawPanel.setCruiser(pictureCruiser);
        drawPanel.repaint();
    }
    public void setMainColor(){
        Cruiser cruiser = (Cruiser) pictureCruiser;
        cruiser.setMainColor(mainColor.getBackground());
        drawPanel.setCruiser(cruiser);
        drawPanel.repaint();
    }
    public void setAddColor(){
        WarCruiser cruiser = (WarCruiser) pictureCruiser;
        cruiser.setDopColor(addColor.getBackground());
        drawPanel.setCruiser(cruiser);
        drawPanel.repaint();
    }
    public class DrawPanel extends JPanel{
        private ITransport cruiser;
        public void setCruiser(ITransport cruiser) {
            this.cruiser = cruiser;
        }
        public void paintComponent(Graphics gr) {
            if (cruiser != null) cruiser.DrawTransport(gr);
        }
    }
}
