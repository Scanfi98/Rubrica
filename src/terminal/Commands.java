/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package terminal;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.JList;
import javax.swing.JTextArea;
import rubrica.GlobalVariables;
import rubrica.Persona;
import rubrica.Rimuovi;

/**
 *
 * @author Giovanni
 */
public class Commands {

    private String command;

    private ArrayList<String> comandi = new ArrayList();
    private HashMap<String, String> info = new HashMap();

    public Commands(String command) {
        this.command = command.toUpperCase();
        loadCommands();
        execute();
    }

    private void loadCommands() {
        comandi.add("CLOSE");
        comandi.add("REMOVE");
        comandi.add("CLS");
        comandi.add("RELOAD");
        comandi.add("TREE");
        comandi.add("HELP");
        comandi.add("SHOW");

        info.put("CLOSE", "Chiude il programma");
        info.put("REMOVE", "Rimuove l'elemento selezionato");
        info.put("CLS", "Resetta il terminale");
        info.put("RELOAD", "Ricarica la lista");
        info.put("TREE", "Mostra l'albero della cartella Contatti/");
        info.put("HELP", "Mostra le informazioni sui comandi");
        info.put("SHOW", "Mostra le informazioni del contatto selezionato");

    }

    private void execute() {
        JTextArea jta = GlobalVariables.terminale.getJTA_console();
        JList jl = GlobalVariables.principale.getJLS_contatti();
        switch (command) {
            default:
                if (!command.equals("")) {
                    jta.append("\n\n");
                    jta.append("Comando inesistente --- Scrivi HELP per la lista dei comandi");
                    jta.append("\n");
                }
                break;
            case "CLOSE":
                System.exit(1);
                break;
            case "REMOVE":

                try {
                    Rimuovi r = new Rimuovi(GlobalVariables.principale.selectedItem());
                    r.rimuoviNome();
                    GlobalVariables.carica.reloadList();
                } catch (NullPointerException ex) {
                    jta.append("\n\n");
                    jta.append("Devi selezionare un elemento");
                    jta.append("\n");
                }
                break;
            case "CLS":
                jta.setText("");
                break;
            case "RELOAD":
                GlobalVariables.carica.reloadList();
                break;
            case "TREE":
                File f = new File(GlobalVariables.PATH);
                jta.append("\n\n");
                for (File f1 : f.listFiles()) {
                    jta.append("     " + GlobalVariables.PATH + f1.getName() + "\n");
                }
                break;
            case "HELP":
                Iterator it = comandi.iterator();
                jta.append("\n\n");
                while (it.hasNext()) {
                    String obj = (String) it.next();
                    jta.append("     " + obj + " - " + info.get(obj) + "\n");
                }
                break;
            case "SHOW":
                try {
                   Persona p = GlobalVariables.principale.getMap().get((String)jl.getSelectedValue());
                   jta.append("\n\n");
                   jta.append("Nome: " + p.getNome() + " " + p.getCognome() + "\n");
                   jta.append("Numero: " + p.getPrefisso() + " " +  p.getN_phone() + "\n");
                   jta.append("Indirizzo: " + p.getIndirizzo() + "\n");
                   jta.append("Email: " + p.getEmail() + "\n");
                } catch (NullPointerException ex) {
                    jta.append("\n");
                    jta.append("Devi selezionare un elemento");
                    jta.append("\n");
                }
        }
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

}
