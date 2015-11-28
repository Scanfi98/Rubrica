/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rubrica;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Giovanni
 */
public class Rimuovi {
    
    private ArrayList<String> nomi;
    
    private String nome;

    private final String PATH = new CreaUtente().getPATH();
    
    public Rimuovi(ArrayList<String> nomi) {
        this.nomi = nomi;
    }

    public Rimuovi(String nome) {
        this.nome = nome;
    }

    public void rimuoviNome(){
        nome = CreaUtente.removeSpace(nome).toUpperCase();
        
        File f = new File(PATH + nome + ".txt");
        
        if (!f.exists()) {
            System.out.println("File non presente");
        } else {
            f.delete();
        }
    }
    
    public void rimuoviLista(){
        Iterator r = nomi.iterator();
        while(r.hasNext()){
        nome = CreaUtente.removeSpace((String)r.next()).toUpperCase();
        
        File f = new File(PATH + nome + ".txt");
        
        if (!f.exists()) {
            System.out.println("File non presente");
        } else {
            f.delete();
        }}
    }
    
}
