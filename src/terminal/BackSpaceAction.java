/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package terminal;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

/**
 *
 * @author Giovanni
 */
public class BackSpaceAction extends AbstractAction {

    private JTextArea JTA_console;
    private final boolean ENABLE;

    public BackSpaceAction(JTextArea JTA_console, boolean enable) {
        this.JTA_console = JTA_console;
        this.ENABLE = enable;
    }
    
    

    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(ENABLE){
            Document doc = JTA_console.getDocument();
                            if (doc.getLength() > 0) {
                                int position = JTA_console.getCaretPosition();
                                if (position > 0) {
                                    try {
                                        doc.remove(position - 1, 1);
                                        JTA_console.setCaretPosition(Math.max(position - 1, 0));
                                    } catch (BadLocationException ex) {
                                        ex.printStackTrace();
                                    }
                                }
                            }
        }
        
    }
    
}
