package com.company;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DropTarget;
import java.awt.event.*;
import java.io.IOException;
import java.util.function.Consumer;

public class FormCruiserConfig extends JFrame {
    final JDialog formConfig;
    private Consumer<ITransport> addEvent;
    private JButton addCruiser;
    private JLabel cruiser, warCruiser;
    private Vehicle pictureCruiser;
    private JPanel confPanel, picture, grayColor, darkGrayColor, blueColor;
    private TransferType tr = new TransferType();
    private TransferColor tc = new TransferColor();
    public FormCruiserConfig(JFrame fdock){
        formConfig = new JDialog(fdock, "Choose cruiser configuration", true);
        formConfig.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        formConfig.setSize(800, 500);
        formConfig.setLayout(null);

        confPanel = new JPanel();
        confPanel.setLayout(null);
        confPanel.setBounds(0,0, formConfig.getWidth(), formConfig.getHeight());

        picture = new JPanel();
        picture.setLayout(null);
        picture.setBounds(150, 10, 200,150);
        picture.setBorder(new LineBorder(new Color(0,0,0)));
        picture.setDropTarget(new DropTarget());
        confPanel.add(picture);

        cruiser = new JLabel("Simple Cruiser");
        cruiser.setBounds(10,10, 100, 50);
        cruiser.setBorder(new BevelBorder(0));
        cruiser.setDropTarget(null);
        confPanel.add(cruiser);

        warCruiser = new JLabel("War Cruiser");
        warCruiser.setBounds(10,70, 100, 50);
        warCruiser.setBorder(new BevelBorder(0));
        warCruiser.setDropTarget(null);
        confPanel.add(warCruiser);

        grayColor = new JPanel();
        grayColor.setLayout(null);
        grayColor.setBounds(400, 50, 40,40);
        grayColor.setBackground(Color.GRAY);
        confPanel.add(grayColor);

        addCruiser = new JButton("Add Cruiser");
        addCruiser.setBounds(400, 400, 100, 40);
        addCruiser.setActionCommand("Add");
        confPanel.add(addCruiser);

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

            }
            public void mouseReleased(MouseEvent e) {
                if(e.getX() < 150 || e.getX() > 350 || e.getY() < 10 || e.getY() > 160){
                    return;
                }
                if(pictureCruiser == null)return;
                pictureCruiser.setBounds(155, 15, 190,190);
                confPanel.add(pictureCruiser);
                pictureCruiser.getGraphics().clearRect(0,0, pictureCruiser.getWidth(), pictureCruiser.getHeight());
                pictureCruiser.SetPosition(165, 50, pictureCruiser.getWidth(), pictureCruiser.getHeight());
                pictureCruiser.DrawTransport(confPanel.getGraphics());
            }
        };
        MouseListener ml2 = new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                JPanel c = (JPanel)e.getSource();
                Color color = c.getBackground();
                Cruiser cr = (Cruiser) pictureCruiser;
                cr.setMainColor(color);
                pictureCruiser.DrawTransport(confPanel.getGraphics());
                tc.exportAsDrag(pictureCruiser, e, TransferHandler.COPY);
            }
        };
        grayColor.setTransferHandler(tc);
        warCruiser.setTransferHandler(tr);
        cruiser.setTransferHandler(tr);
        cruiser.addMouseListener(ml);
        warCruiser.addMouseListener(ml);
        picture.addMouseListener(ml);
        grayColor.addMouseListener(ml2);

        ButtonActions actionListener = new ButtonActions();
        addCruiser.addActionListener(actionListener);
        formConfig.add(confPanel);
        formConfig.setVisible(true);
    }
    public static class TransferType extends TransferHandler{
        public TransferType(){
            super();
        }
        public int getSourceActions(JComponent c){
            return COPY;
        }
        @Override
        protected Transferable createTransferable(JComponent c) {
            JLabel label = (JLabel) c;
            StringSelection str = new StringSelection(label.getText());
            return str;
        }

        @Override
        public boolean canImport(TransferSupport support) {
            return support.isDataFlavorSupported(DataFlavor.stringFlavor);
        }
        @Override
        public boolean importData(TransferSupport support) {
            Transferable data = support.getTransferable();
            try {
                String type = (String) data.getTransferData(DataFlavor.stringFlavor);
            } catch (UnsupportedFlavorException | IOException e) {
                e.printStackTrace();
            }
            JPanel DropTarget = (JPanel) support.getComponent();
            return true;

        }
    }
    public static class TransferColor extends TransferHandler{
        public int getSourceActions(JComponent c){
            return COPY;
        }
        @Override
        protected Transferable createTransferable(JComponent c) {
            JPanel colorPanel = (JPanel) c;
            StringSelection str = new StringSelection(colorPanel.getBackground().toString());
            return str;
        }
        @Override
        public boolean canImport(TransferSupport support) {
            return super.canImport(support);
        }

        @Override
        public boolean importData(TransferSupport support) {
            Transferable data = support.getTransferable();
            try {
                String type = (String) data.getTransferData(DataFlavor.stringFlavor);
            } catch (UnsupportedFlavorException | IOException e) {
                e.printStackTrace();
            }
            JPanel DropTarget = (JPanel) support.getComponent();
            return true;

        }
    }
    public class ButtonActions extends JPanel implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "Add":

                    formConfig.setVisible(false);
                    break;
            }
        }
    }
}
