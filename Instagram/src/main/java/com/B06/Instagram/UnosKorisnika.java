package com.B06.Instagram;

import com.B06.Instagram.Repositories.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

@SpringBootApplication
public class UnosKorisnika {
    private JTextField textFieldIme;
    private JTextField textFieldPrezime;
    private JTextField textFieldEmail;
    private JButton buttonSlika;
    private JList listaAttachmenta;
    public JPanel JField;
    private JButton buttonBrowse;
    private JButton buttonUpload;
    private JTextArea textAreaAttachments;
    private JTextField textFieldUsername;
    private JPasswordField passwordField;
    private JLabel labelPoruka;
    private JButton nazadButton;
    public static String newline = System.getProperty("line.separator");


    private static JFrame frameUnosKorisnika;

    private byte[] slika;
    private ArrayList<byte[]> attachmenti = new ArrayList<>();
    private ArrayList<String> nazivFajlova = new ArrayList<>();
    private ArrayList<String> putFajlova = new ArrayList<>();

    @Autowired
    private KorisnikRepository usersRepository;

    public JPanel getJField() {
        return JField;
    }

    public UnosKorisnika(){
        buttonSlika.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filename = null;
                JFileChooser chooser = new JFileChooser();
                chooser.showOpenDialog(null);
                File f = chooser.getSelectedFile();
                filename = f.getAbsolutePath();
                ImageIcon imageIcon = new ImageIcon(new ImageIcon(filename).getImage().getScaledInstance(buttonSlika.getWidth(), buttonSlika.getHeight(), Image.SCALE_SMOOTH));
                buttonSlika.setText("");
                buttonSlika.setIcon(imageIcon);

                try{
                    File image = new File(filename);
                    FileInputStream fis = new FileInputStream(image);
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    byte[] buf = new byte[1024];
                    for(int readNum;(readNum = fis.read(buf)) != -1;){
                        bos.write(buf, 0, readNum);
                    }
                    slika = bos.toByteArray();
                }
                catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });
        buttonBrowse.addActionListener(new ActionListener() {
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
                        textAreaAttachments.append(file.getName() + newline);

                        FileInputStream fis = new FileInputStream(file);
                        ByteArrayOutputStream bos = new ByteArrayOutputStream();
                        byte[] buf = new byte[1024];
                        for(int readNum;(readNum = fis.read(buf)) != -1;){
                            bos.write(buf, 0, readNum);
                        }
                        attachmenti.add(bos.toByteArray());
                    }
                }
                catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });
        buttonUpload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                try{

                    Korisnik k = new Korisnik(textFieldIme.getText(), textFieldPrezime.getText(), textFieldEmail.getText(), textFieldUsername.getText(), passwordField.getText());
                    usersRepository.save(k);

                    Slika s = new Slika(new javax.sql.rowset.serial.SerialBlob(slika), k.getKorisnikID());
                    s.dodajSliku(s);

                    for(byte[] video: attachmenti){
                        Video v = new Video(new javax.sql.rowset.serial.SerialBlob(video), k.getKorisnikID());
                        v.dodajVideo(v);
                    }

                    labelPoruka.setText("Korisnik uspjesno unesen!");
                }
                catch (Exception ex){
                    labelPoruka.setText("Unosenje korisnika nije uspjelo");
                    ex.printStackTrace();
                }
            }
        });
        nazadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Meni.setFrameMeni(Meni.vratiFrameMeni());
                Meni.getFrameMeni().setVisible(true);
                frameUnosKorisnika.dispose();
            }
        });
    }

    public static JFrame getFrameUnosKorisnika() {
        return frameUnosKorisnika;
    }

    public static void setFrameUnosKorisnika(JFrame frameUnosKorisnika) {
        UnosKorisnika.frameUnosKorisnika = frameUnosKorisnika;
    }

    public static JFrame vratiFrameUnosKorisnik(){
        JFrame frame = new JFrame("UnosKorisnika");
        frame.setContentPane(new UnosKorisnika().JField);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        return frame;
    }
    public static void main(String[] args) {
        frameUnosKorisnika = vratiFrameUnosKorisnik();
        frameUnosKorisnika.setVisible(true);
    }
}

