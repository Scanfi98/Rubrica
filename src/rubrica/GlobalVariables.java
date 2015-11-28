/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rubrica;

import grafica.Interfaccia;
import grafica.InterfacciaCrea;
import grafica.InterfacciaModifica;
import terminal.ConsoleOutputStream;
import grafica.InterfacciaTerminal;

/**
 *
 * @author Giovanni
 */
public class GlobalVariables {
    
    public static boolean stream_enable = false;
    public static Interfaccia principale;
    public static InterfacciaCrea crea;
    public static CaricaUtenti carica;
    public static InterfacciaModifica modifica;
    public static InterfacciaTerminal terminale;
    public static ConsoleOutputStream cos;
    
    public static final String PATH = "Contatti/";
    
}
