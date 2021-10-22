package com.company;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Random;

public class CruiserMove extends JComponent {
    private JButton create, buttonDown, buttonUp, buttonRight, buttonLeft;
    private JFrame cruiserWindow;
    private Cruiser pictureCruiser;
    Random rnd = new Random();

    public CruiserMove() {
        cruiserWindow = new JFrame();
        cruiserWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cruiserWindow.setTitle("Cruiser Moving");
        cruiserWindow.setSize(1500, 800);

        Container elGroup = cruiserWindow.getContentPane();
        pictureCruiser = new Cruiser();

        create = new JButton("Create");
        create.setActionCommand("Create");
        create.setBounds(20, 20, 100, 50);
        pictureCruiser.add(create);

        ImageIcon arrowDown = new ImageIcon("src/source/buttondown.jpg");
        Image imgTmp = arrowDown.getImage();
        Image newimgScale = imgTmp.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
        arrowDown = new ImageIcon(newimgScale);
        buttonDown = new JButton(arrowDown);
        buttonDown.setActionCommand("Down");
        buttonDown.setBounds(1200, 600, 70, 70);
        pictureCruiser.add(buttonDown);

        ImageIcon arrowUp = new ImageIcon("src/source/buttonup.jpg");
        imgTmp = arrowUp.getImage();
        newimgScale = imgTmp.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
        arrowUp = new ImageIcon(newimgScale);
        buttonUp = new JButton(arrowUp);
        buttonUp.setActionCommand("Up");
        buttonUp.setBounds(1200, 520, 70, 70);
        pictureCruiser.add(buttonUp);

        ImageIcon arrowRight = new ImageIcon("src/source/buttonright.jpg");
        imgTmp = arrowRight.getImage();
        newimgScale = imgTmp.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
        arrowRight = new ImageIcon(newimgScale);
        buttonRight = new JButton(arrowRight);
        buttonRight.setActionCommand("Right");
        buttonRight.setBounds(1280, 600, 70, 70);
        pictureCruiser.add(buttonRight);

        ImageIcon arrowLeft = new ImageIcon("src/source/buttonlef.jpg");
        imgTmp = arrowLeft.getImage();
        newimgScale = imgTmp.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
        arrowLeft = new ImageIcon(newimgScale);
        buttonLeft = new JButton(arrowLeft);
        buttonLeft.setActionCommand("Left");
        buttonLeft.setBounds(1120, 600, 70, 70);
        pictureCruiser.add(buttonLeft);

        pictureCruiser.setLayout(null);
        elGroup.add(pictureCruiser);

        ActionListener actionListener = new ButtonActions();
        create.addActionListener(actionListener);
        buttonDown.addActionListener(actionListener);
        buttonUp.addActionListener(actionListener);
        buttonRight.addActionListener(actionListener);
        buttonLeft.addActionListener(actionListener);
        cruiserWindow.setVisible(true);
    }

    public class ButtonActions extends JComponent implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "Create":
                    pictureCruiser.Init(Math.abs(rnd.nextInt() % 100), Math.abs(rnd.nextInt() % 100), Color.gray, Color.lightGray, true, true, true);
                    pictureCruiser.SetPosition(Math.abs(rnd.nextInt() % (cruiserWindow.getWidth() - pictureCruiser.getCruiserWidth())), Math.abs(rnd.nextInt() % (cruiserWindow.getHeight() - pictureCruiser.getCruiserHeight())), 1500, 800);
                    break;
                case "Down":
                    pictureCruiser.MoveTransport(Direction.Down);
                    break;
                case "Up":
                    pictureCruiser.MoveTransport(Direction.Up);
                    break;
                case "Right":
                    pictureCruiser.MoveTransport(Direction.Right);
                    break;
                case "Left":
                    pictureCruiser.MoveTransport(Direction.Left);
                    break;
            }
        }

    }
}

