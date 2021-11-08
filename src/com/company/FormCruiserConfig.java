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
                TransferHandler th = new TransferType();
                th.exportAsDrag(c, e, TransferHandler.COPY);
            }

        };
        MouseListener ml2 = new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                JPanel c = (JPanel)e.getSource();
                Color color = c.getBackground();
                TransferHandler th = new TransferColor();
                th.exportAsDrag(pictureCruiser, e, TransferHandler.COPY);
            }
        };
        grayColor.setTransferHandler(tc);
        warCruiser.setTransferHandler(tr);
        cruiser.setTransferHandler(tr);
        picture.setTransferHandler(tr);
        warCruiser.addMouseListener(ml);
        cruiser.addMouseListener(ml);

        ButtonActions actionListener = new ButtonActions();
        addCruiser.addActionListener(actionListener);
        formConfig.add(confPanel);
        formConfig.setVisible(true);
    }
    public class TransferType extends TransferHandler{
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
        protected void exportDone(JComponent source, Transferable data, int action) {
            JLabel l = (JLabel) source;
            if(action == TransferHandler.NONE){
                if(l.getText().equals("Simple Cruiser")){
                    pictureCruiser = new Cruiser(100,100, Color.GRAY, 180,60);
                    picture.getGraphics().clearRect(0,0, pictureCruiser.getWidth(),pictureCruiser.getHeight());
                    Cruiser cr = (Cruiser) pictureCruiser;
                    cr.SetPosition(210, 60, pictureCruiser.getWidth(),pictureCruiser.getHeight());
                }
                else if(l.getText().equals("War Cruiser")){
                    pictureCruiser = new WarCruiser(100,100, Color.GRAY, Color.BLACK, true,true,true, 180,60);
                    picture.getGraphics().clearRect(0,0, pictureCruiser.getWidth(),pictureCruiser.getHeight());
                    Cruiser cr = (Cruiser) pictureCruiser;
                    cr.SetPosition(210, 60, pictureCruiser.getWidth(),pictureCruiser.getHeight());
                }
                pictureCruiser.DrawTransport(formConfig.getGraphics());
            }
        }

        @Override
        public boolean importData(TransferSupport support) {
            Transferable data = support.getTransferable();
            String type = "";
            try {
                type = (String) data.getTransferData(DataFlavor.stringFlavor);
            } catch (UnsupportedFlavorException | IOException e) {
                e.printStackTrace();
            }
            JPanel DropTarget = (JPanel) support.getComponent();
            if(DropTarget.contains(200, 50)){
                if(type.equals("Simple Cruiser")){
                    pictureCruiser = new Cruiser(100,100, Color.GRAY, 180,60);
                }
                else if(type.equals("War Cruiser")){
                    pictureCruiser = new WarCruiser(100,100, Color.GRAY, Color.BLACK, true,true,true, 180,60);
                }
                pictureCruiser.DrawTransport(formConfig.getGraphics());
            }
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
            if(support.isDataFlavorSupported(DataFlavor.stringFlavor)){
                return true;
            }
            return false;
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
