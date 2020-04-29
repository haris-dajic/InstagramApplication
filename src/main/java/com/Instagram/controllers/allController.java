package com.Instagram.controllers;


import com.Instagram.domain.mongodbUser;
import com.Instagram.mongoDBConnection.mongodb;
import com.Instagram.domain.Korisnik;
import com.Instagram.domain.Slika;
import com.Instagram.domain.Video;
import com.Instagram.services.KorisnikService;
import com.Instagram.services.SlikaService;
import com.Instagram.services.VideoService;
import com.Instagram.services.mongodbService;
import com.github.mongobee.Mongobee;
import com.github.mongobee.exception.MongobeeException;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;
import javax.validation.Valid;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


@Controller
public class allController {

    private KorisnikService korisnikService;
    private VideoService videoService;
    private SlikaService slikaService;
    private Korisnik user;
    private mongodbService mongo;

    @Autowired
    public void setmongodbService(mongodbService mongo){this.mongo = mongo;}


    @Autowired
    public void setVideoService(VideoService videoService){this.videoService = videoService;}


    @Autowired
    public void setKorisnikService(KorisnikService k){
        korisnikService = k;
    }

    @Autowired
    public void setSlikaService(SlikaService slikaService){ this.slikaService = slikaService;}


    /**
     * LOGIN
     */
    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public String login(){
        return "public/login";
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPost(@RequestParam("username") String username, @RequestParam("password") String password){

         Korisnik k = korisnikService.login(username, password);

         if(k == null){
            return "redirect:/login";
         }
         else
             user = k;

         return "redirect:/files/video/" + k.getUsername();
    }

    /**
     * REGISTRATION
     */

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(){
        return "public/register";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String register(@Valid Korisnik korisnik, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "public/error";
        }

        korisnikService.dodajKorisnika(korisnik);
        user = korisnik;

        return "redirect:/files/video/" + korisnik.getUsername();
    }

    /**
     * FILES
     */
    @RequestMapping(value = "/files/video/{id}")
    public String filesVideo(@PathVariable String id, Model model){

        Korisnik k = korisnikService.findByUsername(id);
        if(videoService.findByUserId(k.getKorisnikID()).isEmpty())
            model.addAttribute("error", "Korisnik nema objavljenog sadrzaja!");
        else{
            model.addAttribute("video", videoService.findByUserId(k.getKorisnikID()));
            model.addAttribute("username", user.getUsername());
        }
        return "public/files";
    }

    @RequestMapping(value = "/files/picture/{id}")
    public String filesPicture(@PathVariable String id, Model model){

        Korisnik k = korisnikService.findByUsername(id);
        if(slikaService.findByUserId(k.getKorisnikID()) == null)
            model.addAttribute("error", "Korisnik nema objavljenog sadrzaja!");
        else{

            model.addAttribute("slika", slikaService.findByUserId(k.getKorisnikID()));
            model.addAttribute("username", user.getUsername());
        }
        return "public/files";
    }

    @RequestMapping(value = "/files/pdf/{id}")
    public String filesPDF(@PathVariable String id, Model model){

        Korisnik k = korisnikService.findByUsername(id);
        if(videoService.findByUserId(k.getKorisnikID()).isEmpty())
            model.addAttribute("error", "Korisnik nema objavljenog sadrzaja!");
        else{
            model.addAttribute("pdf", videoService.findByUserId(k.getKorisnikID()));
            model.addAttribute("username", user.getUsername());
        }
        return "public/files";
    }

    /**
     * DELETE, UPLOAD AND DOWNLOAD VIDEO
     */

    @RequestMapping(value = "/upload/video", method = RequestMethod.GET)
    public String videoUploadGet(Model model){
        model.addAttribute("username", user.getUsername());
        return "/public/videoUpload";
    }


    @RequestMapping(value = "/upload/video")
    public String videoUploadPost(@RequestParam("video") MultipartFile video) throws IOException, SQLException {

        byte[] snimak = video.getBytes();
        SerialBlob v = new SerialBlob(snimak);
        Video spremi = new Video(v, user.getKorisnikID());
        videoService.dodajVideo(spremi);

        mongodb.Konektuj(spremi.getVideoID(), user.getIme(), user.getPrezime(), user.getUsername());

        return "redirect:/files/video/" + user.getUsername();
    }

    @RequestMapping(value = "/download/video/{id}", method = RequestMethod.GET)
    public ResponseEntity<ByteArrayResource> downloadVideo(@PathVariable String id, HttpServletResponse response) throws SQLException, IOException {

        Video video = videoService.findById(Integer.valueOf(id));

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("video/mp4"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + video.getVideoID().toString() + "\"")
                .body(new ByteArrayResource(video.getVideo().getBytes(1, (int)video.getVideo().length())));

    }

    @RequestMapping(value = "/delete/video/{id}", method = RequestMethod.POST)
    public String deleteVideo(@PathVariable String id){
        Video v = videoService.findById(Integer.valueOf(id));
        if(v.getKorisnikID() == user.getKorisnikID())
            videoService.izbrisiVideo(v);
        return "redirect:/files/video/" + user.getUsername();
    }

    /**
     * DELETE, UPLOAD AND DOWNLOAD PICTURE
     */

    @RequestMapping(value = "/upload/picture", method = RequestMethod.GET)
    public String pictureUploadGet(Model model){
        model.addAttribute("username", user.getUsername());
        return "/public/pictureUpload";
    }

    @RequestMapping(value = "/upload/picture")
    public String pictureUploadPost(@RequestParam("picture") MultipartFile picture) throws IOException, SQLException {

        byte[] img = picture.getBytes();
        SerialBlob v = new SerialBlob(img);
        Slika spremi = new Slika(v, user.getKorisnikID());
        slikaService.dodajSliku(spremi);

        mongodb.Konektuj(spremi.getSlikaID(), user.getIme(), user.getPrezime(), user.getUsername());

        return "redirect:/files/picture/" + user.getUsername();
    }

    @RequestMapping(value = "/download/picture/{id}", method = RequestMethod.GET)
    public ResponseEntity<ByteArrayResource> downloadPicture(@PathVariable String id, HttpServletResponse response) throws SQLException, IOException {

        Slika slika = slikaService.findById(Integer.valueOf(id));

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("image/jpeg"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + slika.getSlikaID().toString() + "\"")
                .body(new ByteArrayResource(slika.getSlika().getBytes(1, (int)slika.getSlika().length())));

    }

    @RequestMapping(value = "/delete/picture/{id}", method = RequestMethod.POST)
    public String deletePicture(@PathVariable String id){

        Slika v = slikaService.findById(Integer.valueOf(id));
        if(v.getKorisnikID() == user.getKorisnikID())
            slikaService.izbrisiSliku(v);
        return "redirect:/files/picture/" + user.getUsername();
    }


    /**
     * DELETE, UPLOAD AND DOWNLOAD PDF FILE
     * NAPOMENA: Ovdje pdf fajl dodajem u tabelu VIDEO. Iz tog razloga prilikom rada sa pdf fajlovima koristimo instancu video servisa.
     */

    @RequestMapping(value = "/upload/pdf", method = RequestMethod.GET)
    public String pdfUploadGet(Model model){
        model.addAttribute("username", user.getUsername());
        return "/public/pdfUpload";
    }


    @RequestMapping(value = "/upload/pdf")
    public String pdfUploadPost(@RequestParam("pdf") MultipartFile pdf) throws IOException, SQLException {

        byte[] img = pdf.getBytes();
        SerialBlob v = new SerialBlob(img);
        Video spremi = new Video(v, user.getKorisnikID());
        videoService.dodajVideo(spremi);

        mongodb.Konektuj(spremi.getVideoID(), user.getIme(), user.getPrezime(), user.getUsername());

        return "redirect:/files/pdf/" + user.getUsername();
    }

    @RequestMapping(value = "/download/pdf/{id}", method = RequestMethod.GET)
    public ResponseEntity<ByteArrayResource> downloadPDF(@PathVariable String id, HttpServletResponse response) throws SQLException, IOException {

        Video slika = videoService.findById(Integer.valueOf(id));

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/pdf"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + slika.getVideoID().toString() + "\"")
                .body(new ByteArrayResource(slika.getVideo().getBytes(1, (int)slika.getVideo().length())));

    }


    @RequestMapping(value = "/delete/pdf/{id}", method = RequestMethod.POST)
    public String deletePDF(@PathVariable String id){
        Video v = videoService.findById(Integer.valueOf(id));
        if(v.getKorisnikID() == user.getKorisnikID())
            videoService.izbrisiVideo(v);
        return "redirect:/files/pdf/" + user.getUsername();
    }


    @RequestMapping(value = "/metadata", method = RequestMethod.GET)
    public String metadata(Model model) throws MongobeeException {

        ArrayList<Document> podaci = mongodb.getDataMongo();
        ArrayList<mongodbUser> korisnik = new ArrayList<mongodbUser>();
        for(Document d : podaci){
            mongodbUser novi = new mongodbUser();
            novi.setVideoId(d.getInteger("Video_ID"));
            novi.setDatum(d.getString("Datum"));
            novi.setIme(d.getString("Ime"));
            novi.setPrezime(d.getString("Prezime"));
            novi.setUsername(d.getString("Username"));
            korisnik.add(novi);
        }

        for (mongodbUser u : korisnik) {
            mongo.spremiPodatke(u);
        }

        model.addAttribute("podaci", korisnik);

        return "/public/metadata";
    }

}
