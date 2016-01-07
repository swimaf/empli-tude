package com.example.martinet.Emplitude.Emploi;

/**
 * Created by martinet on 11/11/15.
 */

import com.example.martinet.Emplitude.Outil.Fichier;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Hashtable;
import java.util.Objects;
import java.util.TimeZone;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ADE_information {
    final private String store = System.getenv("EXTERNAL_STORAGE");
    final private File file = new File(this.store+"/ADE.cours");
    private Date date;
    private Boolean vide;
    private Vector<Cours> cours;
    private Vector<Object> allCours;
    private SimpleDateFormat dateFormat;


    public ADE_information(Date date){
        this.date = date;
        this.init();
    }
    public ADE_information(){
        this.init();
    }

    public void init(){
        dateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'");
        dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/London"));

        allCours = Fichier.readAll(file);
        if(allCours != null){
            this.vide = false;
        }else{
            this.vide = true;
        }
    }
    //Recupération des cours
    public Vector<Cours> getCours() throws ParseException {
        this.get();
        if(this.vide){
            return null;
        }
        return cours;
    }

    //Récupération dernier cour
    public Cours getNext() throws ParseException{
        Cours c;
        cours = new Vector<>();
        Date d = new Date();
        for(int i =0; i<allCours.size(); i++){
            c = (Cours)allCours.get(i);
            if(d.before(c.getDateD())) {
                this.cours.add(c);
            }
        }
        Collections.sort(this.cours, new Comparator<Object>() {
            public int compare(Object m1, Object m2) {
                Date d = (((Cours) m1).getDateD());
                Date d2 = ((Cours) m2).getDateD();
                return d.compareTo(d2);
            }
        });
        return this.cours.get(0);
    }

    //Récupération toutes les cours par date
    public void get(){
        this.cours = new Vector<>();
        Cours c;
        Jour j = new Jour(this.date);
        Jour j2;
        for(int i =0; i<allCours.size(); i++){
            c = (Cours)allCours.get(i);
            j2 = new Jour(c.getDateD());
            if(j.getDateJour().equals(j2.getDateJour())) {
                this.cours.add(c);
            }
        }
    }

    public Cours getFirstBYDate(Date date) throws ParseException{
        this.date = date;
        this.get();
        Collections.sort(this.cours, new Comparator<Object>() {
            public int compare(Object m1, Object m2) {
                Date d = (((Cours) m1).getDateD());
                Date d2 = ((Cours) m2).getDateD();
                return d.compareTo(d2);
            }
        });
        return this.cours.get(0);
    }

}
