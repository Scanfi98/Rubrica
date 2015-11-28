/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rubrica;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Giovanni
 */
public class CreaUtente {

    public CreaUtente() {
        
    }  
    
    public final String PATH = GlobalVariables.PATH;

    public boolean newContactFile(String nome, String cognome, String prefisso, String numero, String indirizzo, String email) {
        boolean creato = false;
        
        if(removeSpace(prefisso).equals("+")){
            prefisso = "";
        }
        try {
            File contatto = new File(PATH);
            contatto.mkdir();
            contatto = new File(PATH + removeSpace(nome + cognome).toUpperCase() + ".txt"); 
            if (contatto.exists()) {
                return true;
            } else {
                contatto.createNewFile();
                FileWriter fw = new FileWriter(contatto);
                try (BufferedWriter scrittore = new BufferedWriter(fw)) {
                    
                    scrittore.write(nome+"\n");
                    scrittore.write(cognome + "\n");
                    scrittore.write(prefisso + "\n");
                    scrittore.write(numero +"\n");
                    scrittore.write(indirizzo+"\n");
                    scrittore.write(email+"\n");
                    scrittore.flush();
                    scrittore.close();
                }

                addNameToArrayList(nome, cognome, prefisso, numero, indirizzo, email);
                fw.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return creato;
    } //FUNZIONA
    
    public static String removeSpace(String s) {
        String out = "";
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ') {
                out = out + s.charAt(i);
            }
        }
        return out;
    } //FUNZIONA

    private void addNameToArrayList(String nome, String cognome, String prefisso, String numero, String indirizzo, String email) {
        GlobalVariables.carica.add(new Persona(nome, cognome, prefisso, numero, indirizzo, email));
    } //FUNZIONA

    public String getPATH() {
        return PATH;
    } //FUNZIONA
    
    
}
