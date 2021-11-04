package com.company;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class FormCruiser extends JPanel {
    private JButton buttonDown, buttonUp, buttonRight, buttonLeft;
    private JFrame cruiserWindow;
    private JPanel buttons;
    private Vehicle cruiser;
    private Container elGroup;
    ActionListener actionListener;

    public FormCruiser() {
        cruiserWindow = new JFrame();
        cruiserWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        cruiserWindow.setTitle("Cruiser Moving");
        cruiserWindow.setSize(1500, 800);

        elGroup = cruiserWindow.getContentPane();
        buttons = new JPanel();

        ImageIcon arrowDown = new ImageIcon("src/source/buttondown.jpg");
        Image imgTmp = arrowDown.getImage();
        Image newimgScale = imgTmp.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
        arrowDown = new ImageIcon(newimgScale);
        buttonDown = new JButton(arrowDown);
        buttonDown.setActionCommand("Down");
        buttonDown.setBounds(1200, 600, 70, 70);
        buttons.add(buttonDown);

        ImageIcon arrowUp = new ImageIcon("src/source/buttonup.jpg");
        imgTmp = arrowUp.getImage();
        newimgScale = imgTmp.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
        arrowUp = new ImageIcon(newimgScale);
        buttonUp = new JButton(arrowUp);
        buttonUp.setActionCommand("Up");
        buttonUp.setBounds(1200, 520, 70, 70);
        buttons.add(buttonUp);

        ImageIcon arrowRight = new ImageIcon("src/source/buttonright.jpg");
        imgTmp = arrowRight.getImage();
        newimgScale = imgTmp.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
        arrowRight = new ImageIcon(newimgScale);
        buttonRight = new JButton(arrowRight);
        buttonRight.setActionCommand("Right");
        buttonRight.setBounds(1280, 600, 70, 70);
        buttons.add(buttonRight);

        ImageIcon arrowLeft = new ImageIcon("src/source/buttonlef.jpg");
        imgTmp = arrowLeft.getImage();
        newimgScale = imgTmp.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
        arrowLeft = new ImageIcon(newimgScale);
        buttonLeft = new JButton(arrowLeft);
        buttonLeft.setActionCommand("Left");
        buttonLeft.setBounds(1120, 600, 70, 70);
        buttons.add(buttonLeft);

        buttons.setLayout(null);
        elGroup.add(buttons);

        actionListener = new ButtonActions();
        buttonDown.addActionListener(actionListener);
        buttonUp.addActionListener(actionListener);
        buttonRight.addActionListener(actionListener);
        buttonLeft.addActionListener(actionListener);
        cruiserWindow.setVisible(true);
    }

    public void setCruiser(Vehicle cruiser){
        this.cruiser = cruiser;
        this.cruiser.setBounds(0, 0, 1500, 500);
        this.cruiser.setLayout(null);
        this.cruiser.SetPosition(100, 100, 1500, 500);
        elGroup.add(this.cruiser);
        Draw();
    }

    public class ButtonActions extends JPanel implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "Down":
                    cruiser.MoveTransport(Direction.Down);
                    break;
                case "Up":
                    cruiser.MoveTransport(Direction.Up);
                    break;
                case "Right":
                    cruiser.MoveTransport(Direction.Right);
                    break;
                case "Left":
                    cruiser.MoveTransport(Direction.Left);
                    break;
            }
            Draw();
        }
    }
    public void Draw(){
        cruiser.getGraphics().clearRect(0,0,1500, 500);
        cruiser.DrawTransport(cruiser.getGraphics());
    }
}