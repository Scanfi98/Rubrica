/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rubrica;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Giovanni
 */
public class Persona {
    
    private String n_phone;
    private String nome;
    private String indirizzo;
    private String email;
    private String cognome;
    private String prefisso;
    private boolean imgPath;
    private String img;

    public Persona(String nome, String cognome, String prefisso, String n_phone, String indirizzo, String email) {
        this.prefisso = prefisso;
        this.n_phone = n_phone;
        this.nome = nome;
        this.cognome = cognome;
        this.indirizzo = indirizzo;
        this.email = email;
        img = CreaUtente.removeSpace(nome + cognome).toUpperCase();
        this.imgPath = containsImg();

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getN_phone() {
        return n_phone;
    }

    public void setN_phone(String n_phone) {
        this.n_phone = n_phone;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getPrefisso() {
        return prefisso;
    }

    public void setPrefisso(String prefisso) {
        this.prefisso = prefisso;
    }

    public boolean isImgPath() {
        return imgPath;
    }

    public void setImgPath(boolean imgPath) {
        this.imgPath = imgPath;
    }

    public String getImg() {
        return img;
    }

    public boolean containsImg(){
        File f = new File("Immagini\\");

        f.mkdirs();

        String [] s = f.list();

        for(String nome: s){
            if(nome.equals(img + ".jpg") || nome.equals(img + ".png")){
                img = f.getAbsolutePath() +"\\"+ nome;
                return  true;
            }
        }
        return false;
    }
}
