package com.company;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.dnd.DropTarget;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class FormCruiserConfig extends JFrame {
    private JLabel cruiser, warCruiser;
    private Vehicle pictureCruiser;
    private JPanel confPanel, picture, grayColor, darkGrayColor, blueColor;
    public FormCruiserConfig(Vehicle cruiserPrototype,JFrame fdock){
        final JDialog formConfig = new JDialog(fdock, "Choose cruiser configuration", true);
        formConfig.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        formConfig.setSize(800, 500);
        formConfig.setLayout(null);

        confPanel = new JPanel();
        confPanel.setLayout(null);
        confPanel.setBounds(0,0, formConfig.getWidth(), formConfig.getHeight());

        picture = new JPanel();
        picture.setLayout(null);
        picture.setBounds(150, 10, 200,200);
        picture.setBorder(new LineBorder(new Color(0,0,0)));
        picture.setTransferHandler(new TransferHandler("drop"));
        picture.setDropTarget(new DropTarget());
        confPanel.add(picture);

        cruiser = new JLabel("Simple Cruiser");
        cruiser.setBounds(10,10, 100, 50);
        cruiser.setBorder(new BevelBorder(0));
        cruiser.setTransferHandler(new TransferHandler("text"));
        cruiser.setDropTarget(null);
        confPanel.add(cruiser);

        warCruiser = new JLabel("War Cruiser");
        warCruiser.setBounds(10,70, 100, 50);
        warCruiser.setBorder(new BevelBorder(0));
        warCruiser.setTransferHandler(new TransferHandler("text"));
        warCruiser.setDropTarget(null);
        confPanel.add(warCruiser);

        cruiserPrototype = pictureCruiser;

        MouseListener ml = new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                JLabel c = (JLabel)e.getSource();
                String text = c.getText();
                if(text.equals("Simple Cruiser")){
                    pictureCruiser = new Cruiser(100, 100, new Color(100, 100, 100), 180, 60);
                }
                else if(text.equals("War Cruiser")){
                    pictureCruiser = new WarCruiser(100, 100, new Color(100, 100, 100), Color.lightGray, true, true, true, 180, 60);
                }
                TransferHandler th = c.getTransferHandler();
                th.exportAsDrag(c, e, TransferHandler.COPY);
            }
        };
        MouseListener ml2 = new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if(e.getSource() == pictureCruiser){

                }
                if(pictureCruiser == null)return;
                pictureCruiser.setBounds(155, 15, 190,190);
                confPanel.add(pictureCruiser);
                pictureCruiser.getGraphics().clearRect(0,0, pictureCruiser.getWidth(), pictureCruiser.getHeight());
                pictureCruiser.SetPosition(165, 50, pictureCruiser.getWidth(), pictureCruiser.getHeight());
                pictureCruiser.DrawTransport(confPanel.getGraphics());
            }
        };
        cruiser.addMouseListener(ml);
        warCruiser.addMouseListener(ml);
        picture.addMouseListener(ml2);

        formConfig.add(confPanel);
        formConfig.setVisible(true);
    }
}
