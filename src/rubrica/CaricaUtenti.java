/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rubrica;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Giovanni
 */
public final class CaricaUtenti {

    private final String PATH = GlobalVariables.PATH;;

    private ArrayList<Persona> lista;

    public CaricaUtenti() {
        lista = new ArrayList();
        this.loadListOnStartUp();
    }

    public ArrayList<Persona> getLista() {
        return lista;
    }//FUNZIONA

    public Persona[] getArrayFromList() {

        Persona p[] = new Persona[lista.size()];

        if (lista.isEmpty()) {

            return null;
        } else {
            int i = 0;
            Iterator c = lista.iterator();
            while (c.hasNext()) {
                p[i] = (Persona) c.next();
                i++;
            }
        }
        return p;

    } //FUNZIONA

    public void add(Persona o) {
        lista.add(o);
    }//FUNZIONA

    public void loadListOnStartUp() {
        lista.clear();
        File list = new File(PATH);
        Scanner sc;
        String nome;
        String cognome;
        String prefisso;
        String numero;
        String indirizzo;
        String email;
        for (File f : list.listFiles()) {
            try {
                sc = new Scanner(f);
                nome = sc.nextLine();
                cognome = sc.nextLine();
                prefisso = sc.nextLine();
                numero = sc.nextLine();
                indirizzo = sc.nextLine();
                email = sc.nextLine();
                lista.add(new Persona(nome, cognome, prefisso, numero, indirizzo, email));
                sc.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(CaricaUtenti.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//FUNZIONA
    
    public void reloadList(){
        loadListOnStartUp();        
        GlobalVariables.principale.loadInitialListValues();
        
    }
}
