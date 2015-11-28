/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package terminal;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 *
 * @author Giovanni
 */
public class EnterAction extends AbstractAction {

    
    private String comando;
    private final JFrame FRAME;
    private final JTextArea CONSOLE;

    public EnterAction(String comando, JFrame FRAME, JTextArea CONSOLE) {
        this.comando = comando;
        this.FRAME = FRAME;
        this.CONSOLE = CONSOLE;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!comando.toUpperCase().equals("CLS")) {
                        CONSOLE.append("\n");
                        FRAME.validate();
                    }
                    CONSOLE.append("RUBRICA@USER## ");
                    comando = "";
    }

    public void setComando(String comando) {
        this.comando = comando;
    }
    
    
    
}
