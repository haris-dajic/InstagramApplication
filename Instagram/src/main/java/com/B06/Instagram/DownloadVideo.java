package com.B06.Instagram;
import com.B06.Instagram.Repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.B06.Instagram.Video;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DownloadVideo extends JFrame implements ActionListener {
    private static JFrame frame;
    private ArrayList<JButton> download_buttons;
    private JPanel panel;
    private  JLabel user_name;
    private JButton nazadButton;
    private int user_id;
    private List<Blob> snimci;

    @Autowired
    private VideoRepository videoRepository;




    public DownloadVideo(){

        frame = new JFrame();
        //download_buttons = new ArrayList<JButton>();
        panel = new JPanel();
        user_name = new JLabel();
        user_id = 5;
        snimci = Video.getAllById(user_id);
        //preko korisnikovog id-a dobaviti sve video snimke
        download_buttons = new ArrayList<JButton>();
        for(int i = 0; i < snimci.size(); i++)
            download_buttons.add(new JButton(Integer.toString(i)));
        nazadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Meni.setFrameMeni(Meni.vratiFrameMeni());
                Meni.getFrameMeni().setVisible(true);
                frame.dispose();
            }
        });
    }


    public void start(String userName, int user_id){

        frame.setTitle("Download videos");
        frame.setVisible(true);
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel.setBackground(Color.white);

        user_name.setText(userName);
        user_name.setFont(new Font("Serif",Font.PLAIN,26));

        /**
         * ovdje dodati u buttone id videa, a izbrisati iz konstruktora
         * dobit cu id-eve videa na osnovu user_id-a
        */
        panel.add(user_name);

        for (int i = 0; i < download_buttons.size(); i++) {
            download_buttons.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                   /* BufferedInputStream is = new BufferedInputStream(snimci.get(i).getBinaryStream());
                    FileOutputStream fos = new FileOutputStream(file);
                    // you can set the size of the buffer
                    byte[] buffer = new byte[2048];
                    int r = 0;
                    while((r = is.read(buffer))!=-1) {
                        fos.write(buffer, 0, r);
                    }
                    fos.flush();
                    fos.close();
                    is.close(); */
                }
                }
            );
            panel.add(download_buttons.get(i));
        }
        panel.add(nazadButton);
        
        frame.add(panel);
    }
    @Override
    public void actionPerformed(ActionEvent e){


        /**
         * koristiti funkciju za skidanje fajlova iz klase videoService
         */
        System.out.println(e.getActionCommand());
    }

    /**
     * Svrha main-a jeste iskljucivo zbog testiranja
     * Treba napraviti pocetnu formu na osnovu koje ce se moci prelaziti sa jedne na drugu
     */
    public static JFrame getFrame() {
        return frame;
    }

    public static void setFrame(JFrame frame) {
        DownloadVideo.frame = frame;
    }
    public static void main(String[] args) {

        DownloadVideo dv = new DownloadVideo();
        dv.start("Haris", 5);
    }

}