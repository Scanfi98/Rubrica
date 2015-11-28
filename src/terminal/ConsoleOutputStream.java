/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package terminal;

import java.awt.Color;
import javax.swing.JTextArea;

/**
 *
 * @author Giovanni
 */
public class ConsoleOutputStream {

    private final JTextArea JTA_console;

    public ConsoleOutputStream(JTextArea textArea) {
        this.JTA_console = textArea;
    }

    public void write(String scritta) {
        // redirects data to the text area
        JTA_console.setForeground(Color.RED);
        JTA_console.append(scritta);
        JTA_console.setForeground(Color.WHITE);
        JTA_console.repaint();
        JTA_console.append("\n");
        JTA_console.append("RUBRICA@USER## ");
        JTA_console.setCaretPosition(JTA_console.getDocument().getLength());
    }

}
