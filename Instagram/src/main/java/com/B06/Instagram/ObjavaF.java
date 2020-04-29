package com.B06.Instagram;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

@SpringBootApplication
public class ObjavaF {
    private JTextField textField1;
    private JTextField textField2;
    private JTextArea textArea1;
    private JButton browseButton;
    private JButton nazadButton;
    public JPanel JField;
    private JButton objaviButton;
    private static JFrame frameObjava;
    private ArrayList<byte[]> attachmenti = new ArrayList<>();
    private ArrayList<String> nazivFajlova = new ArrayList<>();
    private ArrayList<String> putFajlova = new ArrayList<>();
    public static String newline = System.getProperty("line.separator");


    public ObjavaF() {
        nazadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Meni.setFrameMeni(Meni.vratiFrameMeni());
                Meni.getFrameMeni().setVisible(true);
                frameObjava.dispose();
            }
        });
        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JFileChooser chooser = new JFileChooser();
                    chooser.setMultiSelectionEnabled(true);

                    chooser.showOpenDialog(null);
                    File[] f = chooser.getSelectedFiles();

                    for (File file : f) {
                        putFajlova.add(file.getAbsolutePath());
                        nazivFajlova.add(file.getName());
                        textArea1.append(file.getName() + newline);

                        FileInputStream fis = new FileInputStream(file);
                        ByteArrayOutputStream bos = new ByteArrayOutputStream();
                        byte[] buf = new byte[1024];
                        for (int readNum; (readNum = fis.read(buf)) != -1; ) {
                            bos.write(buf, 0, readNum);
                        }
                        attachmenti.add(bos.toByteArray());
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        objaviButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Objava o = new Objava("26.02.2001", "Kemo", "Kemic", "Moke", 3);
                o.dodajObjavu();
            }
        });
    }

    public static JFrame getFrameObjava() {
        return frameObjava;
    }

    public static void setFrameObjava(JFrame frameObjava) {
        ObjavaF.frameObjava = frameObjava;
    }

    public static JFrame vratiFrameObjava(){
        JFrame frame = new JFrame("Objava");
        frame.setContentPane(new ObjavaF().JField);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        return frame;
    }

    public static void main(String[] args) {
        frameObjava = vratiFrameObjava();
        frameObjava.setVisible(true);
    }
}
