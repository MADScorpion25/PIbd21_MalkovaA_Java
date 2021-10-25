package com.company;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Random;

public class FormCruiser extends JPanel {
    private JButton createSimpCruiser, createWarCruiser, buttonDown, buttonUp, buttonRight, buttonLeft;
    private JFrame cruiserWindow;
    private JPanel buttons;
    private Vehicle cruiser;
    private Container elGroup;
    Random rnd = new Random();

    public FormCruiser() {
        cruiserWindow = new JFrame();
        cruiserWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cruiserWindow.setTitle("Cruiser Moving");
        cruiserWindow.setSize(1500, 800);

        elGroup = cruiserWindow.getContentPane();
        buttons = new JPanel();

        createSimpCruiser = new JButton("Create Simple Cruiser");
        createSimpCruiser.setActionCommand("CreateSimpCruiser");
        createSimpCruiser.setBounds(900, 650, 100, 50);
        buttons.add(createSimpCruiser);

        createWarCruiser = new JButton("Create War Cruiser");
        createWarCruiser.setActionCommand("CreateWarCruiser");
        createWarCruiser.setBounds(800, 650, 100, 50);
        buttons.add(createWarCruiser);

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

        ActionListener actionListener = new ButtonActions();
        createSimpCruiser.addActionListener(actionListener);
        createWarCruiser.addActionListener(actionListener);
        buttonDown.addActionListener(actionListener);
        buttonUp.addActionListener(actionListener);
        buttonRight.addActionListener(actionListener);
        buttonLeft.addActionListener(actionListener);
        cruiserWindow.setVisible(true);
    }

    public class ButtonActions extends JPanel implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "CreateSimpCruiser":
                    cruiser = new Cruiser(Math.abs(rnd.nextInt() % 100), Math.abs(rnd.nextInt() % 100), Color.gray, 180, 60);
                    cruiser.setBounds(0, 0, 1500, 500);
                    cruiser.SetPosition(Math.abs(rnd.nextInt() % (cruiser.getWidth() - cruiser.getCruiserWidth())), Math.abs(rnd.nextInt() % (cruiser.getHeight() - cruiser.getCruiserHeight())), cruiser.getWidth(), cruiser.getHeight());
                    elGroup.add(cruiser);
                    break;
                case "CreateWarCruiser":
                    cruiser = new WarCruiser(Math.abs(rnd.nextInt() % 100), Math.abs(rnd.nextInt() % 100), Color.gray, Color.lightGray, true, true, true, 180, 60);
                    cruiser.setBounds(0, 0, 1500, 500);
                    cruiser.SetPosition(Math.abs(rnd.nextInt() % (cruiser.getWidth() - cruiser.getCruiserWidth())), Math.abs(rnd.nextInt() % (cruiser.getHeight() - cruiser.getCruiserHeight())), cruiser.getWidth(), cruiser.getHeight());
                    elGroup.add(cruiser);
                    break;
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