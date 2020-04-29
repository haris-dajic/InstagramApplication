package com.B06.Instagram;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SpringBootApplication
public class Meni {
    private JButton button4;
    private JButton promjenaProfilneSlikeButton;
    private JButton objavaButton;
    private JButton button1;
    public JPanel JField;
    private static JFrame frameMeni;

    public Meni() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UnosKorisnika.setFrameUnosKorisnika(UnosKorisnika.vratiFrameUnosKorisnik());
                UnosKorisnika.getFrameUnosKorisnika().setVisible(true);
                frameMeni.dispose();
            }
        });
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DownloadVideo dw = new DownloadVideo();
                dw.start("Haris", 5);
                frameMeni.dispose();
            }
        });
        objavaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ObjavaF.setFrameObjava(ObjavaF.vratiFrameObjava());
                ObjavaF.getFrameObjava().setVisible(true);
                frameMeni.dispose();
            }
        });
    }

    public static JFrame getFrameMeni(){
        return frameMeni;
    }
    public static void setFrameMeni(JFrame frameTemp){
        frameMeni = frameTemp;
    }
    public static JFrame vratiFrameMeni(){
        JFrame frame = new JFrame("Meni");
        frame.setContentPane(new Meni().JField);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        return frame;
    }
    public static void main(String[] args) {
        frameMeni = vratiFrameMeni();
        frameMeni.setVisible(true);
    }
}

