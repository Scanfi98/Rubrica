/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rubrica;

import grafica.Interfaccia;
import static rubrica.GlobalVariables.principale;


/**
 *
 * @author Giovanni
 */
public class Init {
    
    public Init(){
        GlobalVariables.carica = new CaricaUtenti();
        GlobalVariables.principale = new Interfaccia();
        
    }
    
    public void loadList(Persona p){
        principale.addValueToList(p);
    }
}
